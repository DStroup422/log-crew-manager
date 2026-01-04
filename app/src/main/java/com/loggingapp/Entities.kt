package com.loggingapp

import androidx.room.Entity
import androidx.room.PrimaryKey

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