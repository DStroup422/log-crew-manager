package com.loggingapp

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodels.compose.viewModels
import kotlinx.coroutines.flow.first
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import android.os.Bundle
import androidx.activity.viewModels

@Dao
interface WorkerDao {
    @Query("SELECT * FROM WorkerEntity")
    fun getAllWorkers(): Flow<List<WorkerEntity>>

    @Insert
    suspend fun insertWorker(worker: WorkerEntity): Long

    @Update
    suspend fun updateWorker(worker: WorkerEntity)

    @Delete
    suspend fun deleteWorker(worker: WorkerEntity)
}

@Dao
interface LocationDao {
    @Query("SELECT * FROM LocationEntity")
    fun getAllLocations(): Flow<List<LocationEntity>>

    @Insert
    suspend fun insertLocation(location: LocationEntity): Long

    @Update
    suspend fun updateLocation(location: LocationEntity)

    @Delete
    suspend fun deleteLocation(location: LocationEntity)
}

@Dao
interface LoadDao {
    @Query("SELECT * FROM LoadEntity")
    fun getAllLoads(): Flow<List<LoadEntity>>

    @Insert
    suspend fun insertLoad(load: LoadEntity): Long

    @Update
    suspend fun updateLoad(load: LoadEntity)

    @Delete
    suspend fun deleteLoad(load: LoadEntity)
}

@Dao
interface PropertyDao {
    @Query("SELECT * FROM PropertyEntity")
    fun getAllProperties(): Flow<List<PropertyEntity>>

    @Insert
    suspend fun insertProperty(property: PropertyEntity): Long

    @Update
    suspend fun updateProperty(property: PropertyEntity)

    @Delete
    suspend fun deleteProperty(property: PropertyEntity)
}

@Dao
interface PropertyPointDao {
    @Query("SELECT * FROM PropertyPointEntity WHERE propertyId = :propertyId ORDER BY orderIndex")
    fun getPointsForProperty(propertyId: Long): Flow<List<PropertyPointEntity>>

    @Insert
    suspend fun insertPoint(point: PropertyPointEntity)

    @Query("DELETE FROM PropertyPointEntity WHERE propertyId = :propertyId")
    suspend fun deletePointsForProperty(propertyId: Long)
}

@Database(entities = [WorkerEntity::class, LocationEntity::class, LoadEntity::class, PropertyEntity::class, PropertyPointEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workerDao(): WorkerDao
    abstract fun locationDao(): LocationDao
    abstract fun loadDao(): LoadDao
    abstract fun propertyDao(): PropertyDao
    abstract fun propertyPointDao(): PropertyPointDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "logging_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

object PayCalculator {
    fun calculate(loadValue: Double, workers: List<WorkerEntity>): Map<Long, Double> {
        return workers.associate { it.id to (loadValue * it.payPercentage / 100.0) }
    }
}

class LoggingViewModel(private val database: AppDatabase) : ViewModel() {
    val workers: Flow<List<WorkerEntity>> = database.workerDao().getAllWorkers()
    val locations: Flow<List<LocationEntity>> = database.locationDao().getAllLocations()
    val loads: Flow<List<LoadEntity>> = database.loadDao().getAllLoads()
    val properties: Flow<List<PropertyEntity>> = database.propertyDao().getAllProperties()

    fun getPropertyPoints(propertyId: Long): Flow<List<PropertyPointEntity>> =
        database.propertyPointDao().getPointsForProperty(propertyId)

    fun addSampleData() {
        viewModelScope.launch {
            val existingWorkers = database.workerDao().getAllWorkers().first()
            if (existingWorkers.isEmpty()) {
                // Add sample workers
                database.workerDao().insertWorker(WorkerEntity(name = "John Doe", payPercentage = 50.0))
                database.workerDao().insertWorker(WorkerEntity(name = "Jane Smith", payPercentage = 30.0))
                database.workerDao().insertWorker(WorkerEntity(name = "Bob Johnson", payPercentage = 20.0))

                // Add sample locations
                val pickupId = database.locationDao().insertLocation(LocationEntity(name = "Forest Entrance", lat = 45.0, lng = -122.0))
                val dropoffId = database.locationDao().insertLocation(LocationEntity(name = "Mill Site", lat = 45.1, lng = -122.1))

                // Add sample load
                database.loadDao().insertLoad(LoadEntity(date = System.currentTimeMillis(), value = 1000.0, pickupLocationId = pickupId, dropoffLocationId = dropoffId))

                // Add sample property
                val propertyId = database.propertyDao().insertProperty(PropertyEntity(name = "North Forest", owner = "State Forestry", source = "GIS"))

                // Add property points (triangle)
                database.propertyPointDao().insertPoint(PropertyPointEntity(propertyId, 45.0, -122.0, 0))
                database.propertyPointDao().insertPoint(PropertyPointEntity(propertyId, 45.05, -121.95, 1))
                database.propertyPointDao().insertPoint(PropertyPointEntity(propertyId, 45.02, -122.05, 2))
            }
        }
    }
}

@Composable
fun MapScreen(
    properties: List<PropertyEntity>,
    propertyPoints: Map<Long, List<PropertyPointEntity>>,
    locations: List<LocationEntity>
) {
    GoogleMap(modifier = Modifier.fillMaxSize()) {
        properties.forEach { property ->
            val polyPoints = propertyPoints[property.id]?.map { LatLng(it.lat, it.lng) } ?: emptyList()
            if (polyPoints.size >= 3) {
                Polygon(
                    points = polyPoints,
                    strokeColor = if (property.source == "GIS") Color.Green else Color(0xFFFF9800),
                    fillColor = Color.Transparent
                )
            }
        }
        locations.forEach { location ->
            Marker(
                state = MarkerState(LatLng(location.lat, location.lng)),
                title = location.name ?: "Location"
            )
        }
    }
}

class MainActivity : ComponentActivity() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val viewModel: LoggingViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return LoggingViewModel(database) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Add sample data if database is empty
        viewModel.addSampleData()

        setContent {
            val properties by viewModel.properties.collectAsState(initial = emptyList())
            val locations by viewModel.locations.collectAsState(initial = emptyList())
            val propertyPoints = properties.associateWith { property ->
                viewModel.getPropertyPoints(property.id).collectAsState(initial = emptyList()).value
            }

            MapScreen(
                properties = properties,
                propertyPoints = propertyPoints.mapKeys { it.key.id },
                locations = locations
            )
        }
    }
}
