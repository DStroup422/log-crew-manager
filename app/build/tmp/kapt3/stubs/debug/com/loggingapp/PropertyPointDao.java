package com.loggingapp;

import java.lang.System;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0019\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lcom/loggingapp/PropertyPointDao;", "", "deletePointsForProperty", "", "propertyId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPointsForProperty", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/loggingapp/PropertyPointEntity;", "insertPoint", "point", "(Lcom/loggingapp/PropertyPointEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface PropertyPointDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM PropertyPointEntity WHERE propertyId = :propertyId ORDER BY orderIndex")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.loggingapp.PropertyPointEntity>> getPointsForProperty(long propertyId);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert
    public abstract java.lang.Object insertPoint(@org.jetbrains.annotations.NotNull
    com.loggingapp.PropertyPointEntity point, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "DELETE FROM PropertyPointEntity WHERE propertyId = :propertyId")
    public abstract java.lang.Object deletePointsForProperty(long propertyId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}