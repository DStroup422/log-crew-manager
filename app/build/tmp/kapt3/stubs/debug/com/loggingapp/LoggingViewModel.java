package com.loggingapp;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00070\u00062\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\n\u00a8\u0006\u001a"}, d2 = {"Lcom/loggingapp/LoggingViewModel;", "Landroidx/lifecycle/ViewModel;", "database", "Lcom/loggingapp/AppDatabase;", "(Lcom/loggingapp/AppDatabase;)V", "loads", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/loggingapp/LoadEntity;", "getLoads", "()Lkotlinx/coroutines/flow/Flow;", "locations", "Lcom/loggingapp/LocationEntity;", "getLocations", "properties", "Lcom/loggingapp/PropertyEntity;", "getProperties", "workers", "Lcom/loggingapp/WorkerEntity;", "getWorkers", "addSampleData", "", "getPropertyPoints", "Lcom/loggingapp/PropertyPointEntity;", "propertyId", "", "app_debug"})
public final class LoggingViewModel extends androidx.lifecycle.ViewModel {
    private final com.loggingapp.AppDatabase database = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.loggingapp.WorkerEntity>> workers = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.loggingapp.LocationEntity>> locations = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.loggingapp.LoadEntity>> loads = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.loggingapp.PropertyEntity>> properties = null;
    
    public LoggingViewModel(@org.jetbrains.annotations.NotNull
    com.loggingapp.AppDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.loggingapp.WorkerEntity>> getWorkers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.loggingapp.LocationEntity>> getLocations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.loggingapp.LoadEntity>> getLoads() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.loggingapp.PropertyEntity>> getProperties() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.loggingapp.PropertyPointEntity>> getPropertyPoints(long propertyId) {
        return null;
    }
    
    public final void addSampleData() {
    }
}