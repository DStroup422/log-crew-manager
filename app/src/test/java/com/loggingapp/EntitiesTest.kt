package com.loggingapp

import org.junit.Test
import org.junit.Assert.*

class EntitiesTest {

    @Test
    fun workerEntity_initialization_createsCorrectObject() {
        val worker = WorkerEntity(name = "John Doe", payPercentage = 50.0)
        
        assertEquals("John Doe", worker.name)
        assertEquals(50.0, worker.payPercentage, 0.0)
        assertEquals(0L, worker.id)
    }

    @Test
    fun workerEntity_withId_createsCorrectObject() {
        val worker = WorkerEntity(id = 5L, name = "Jane Smith", payPercentage = 30.0)
        
        assertEquals(5L, worker.id)
        assertEquals("Jane Smith", worker.name)
        assertEquals(30.0, worker.payPercentage, 0.0)
    }

    @Test
    fun locationEntity_initialization_createsCorrectObject() {
        val location = LocationEntity(name = "Site A", lat = 45.5, lng = -122.6)
        
        assertEquals("Site A", location.name)
        assertEquals(45.5, location.lat, 0.0)
        assertEquals(-122.6, location.lng, 0.0)
        assertEquals(0L, location.id)
    }

    @Test
    fun locationEntity_withNullName_createsCorrectObject() {
        val location = LocationEntity(name = null, lat = 45.5, lng = -122.6)
        
        assertNull(location.name)
        assertEquals(45.5, location.lat, 0.0)
        assertEquals(-122.6, location.lng, 0.0)
    }

    @Test
    fun loadEntity_initialization_createsCorrectObject() {
        val currentTime = System.currentTimeMillis()
        val load = LoadEntity(
            date = currentTime,
            value = 1500.0,
            pickupLocationId = 1L,
            dropoffLocationId = 2L
        )
        
        assertEquals(currentTime, load.date)
        assertEquals(1500.0, load.value, 0.0)
        assertEquals(1L, load.pickupLocationId)
        assertEquals(2L, load.dropoffLocationId)
        assertEquals(0L, load.id)
    }

    @Test
    fun loadEntity_withId_createsCorrectObject() {
        val currentTime = System.currentTimeMillis()
        val load = LoadEntity(
            id = 10L,
            date = currentTime,
            value = 2500.0,
            pickupLocationId = 3L,
            dropoffLocationId = 4L
        )
        
        assertEquals(10L, load.id)
        assertEquals(currentTime, load.date)
        assertEquals(2500.0, load.value, 0.0)
        assertEquals(3L, load.pickupLocationId)
        assertEquals(4L, load.dropoffLocationId)
    }

    @Test
    fun propertyEntity_initialization_createsCorrectObject() {
        val property = PropertyEntity(
            name = "Forest Lot 42",
            owner = "ABC Logging",
            source = "Survey"
        )
        
        assertEquals("Forest Lot 42", property.name)
        assertEquals("ABC Logging", property.owner)
        assertEquals("Survey", property.source)
        assertEquals(0L, property.id)
    }

    @Test
    fun propertyEntity_withNullOwner_createsCorrectObject() {
        val property = PropertyEntity(
            name = "Forest Lot 43",
            owner = null,
            source = "GPS"
        )
        
        assertEquals("Forest Lot 43", property.name)
        assertNull(property.owner)
        assertEquals("GPS", property.source)
    }

    @Test
    fun propertyPointEntity_initialization_createsCorrectObject() {
        val point = PropertyPointEntity(
            propertyId = 1L,
            lat = 45.123,
            lng = -122.456,
            orderIndex = 0
        )
        
        assertEquals(1L, point.propertyId)
        assertEquals(45.123, point.lat, 0.0)
        assertEquals(-122.456, point.lng, 0.0)
        assertEquals(0, point.orderIndex)
    }

    @Test
    fun propertyPointEntity_withDifferentOrder_createsCorrectObject() {
        val point = PropertyPointEntity(
            propertyId = 5L,
            lat = 46.789,
            lng = -123.012,
            orderIndex = 3
        )
        
        assertEquals(5L, point.propertyId)
        assertEquals(46.789, point.lat, 0.0)
        assertEquals(-123.012, point.lng, 0.0)
        assertEquals(3, point.orderIndex)
    }

    @Test
    fun propertyPointEntity_multiplePointsForSameProperty_createsUniqueObjects() {
        val point1 = PropertyPointEntity(propertyId = 1L, lat = 45.0, lng = -122.0, orderIndex = 0)
        val point2 = PropertyPointEntity(propertyId = 1L, lat = 45.1, lng = -122.1, orderIndex = 1)
        val point3 = PropertyPointEntity(propertyId = 1L, lat = 45.2, lng = -122.2, orderIndex = 2)
        
        assertEquals(1L, point1.propertyId)
        assertEquals(1L, point2.propertyId)
        assertEquals(1L, point3.propertyId)
        
        assertEquals(0, point1.orderIndex)
        assertEquals(1, point2.orderIndex)
        assertEquals(2, point3.orderIndex)
        
        assertNotEquals(point1.lat, point2.lat, 0.0)
        assertNotEquals(point2.lat, point3.lat, 0.0)
    }
}
