package com.loggingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon


@Entity
data class WorkerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val payPercentage: Double
)

@Entity
data class LocationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String?,
    val lat: Double,
    val lng: Double
)

@Entity
data class LoadEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val date: Long,
    val value: Double,
    val pickupLocationId: Long,
    val dropoffLocationId: Long
)

@Entity
data class PropertyEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val owner: String?,
    val source: String
)

@Entity(primaryKeys = ["propertyId", "orderIndex"])
data class PropertyPointEntity(
    val propertyId: Long,
    val lat: Double,
    val lng: Double,
    val orderIndex: Int
)

object PayCalculator {
    fun calculate(loadValue: Double, workers: List<WorkerEntity>): Map<Long, Double> {
        return workers.associate { it.id to (loadValue * it.payPercentage / 100.0) }
    }
}

object GeoUtils {
    fun isPointInside(
        pointLat: Double,
        pointLng: Double,
        polygon: List<Pair<Double, Double>>
    ): Boolean {
        if (polygon.size < 3) return false
        var intersectCount = 0
        for (i in polygon.indices) {
            val j = (i + 1) % polygon.size
            val lat1 = polygon[i].first
            val lng1 = polygon[i].second
            val lat2 = polygon[j].first
            val lng2 = polygon[j].second
            val intersects = (lng1 > pointLng) != (lng2 > pointLng)
            if (intersects) {
                val slope = (lat2 - lat1) / (lng2 - lng1)
                val intersectLat = slope * (pointLng - lng1) + lat1
                if (pointLat < intersectLat) intersectCount++
            }
        }
        return intersectCount % 2 == 1
    }
}

@Composable
fun MapScreen(
    properties: List<PropertyEntity>,
    points: List<PropertyPointEntity>,
    locations: List<LocationEntity>
) {
    GoogleMap(modifier = Modifier.fillMaxSize()) {
        properties.forEach { property ->
            val polyPoints = points
                .filter { it.propertyId == property.id }
                .sortedBy { it.orderIndex }
                .map { LatLng(it.lat, it.lng) }
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapScreen(
                properties = emptyList(),
                points = emptyList(),
                locations = emptyList()
            )
        }
    }
}
