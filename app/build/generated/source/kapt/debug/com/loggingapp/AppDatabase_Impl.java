package com.loggingapp;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile WorkerDao _workerDao;

  private volatile LocationDao _locationDao;

  private volatile LoadDao _loadDao;

  private volatile PropertyDao _propertyDao;

  private volatile PropertyPointDao _propertyPointDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `WorkerEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `payPercentage` REAL NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LocationEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `lat` REAL NOT NULL, `lng` REAL NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LoadEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` INTEGER NOT NULL, `value` REAL NOT NULL, `pickupLocationId` INTEGER NOT NULL, `dropoffLocationId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `PropertyEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `owner` TEXT, `source` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `PropertyPointEntity` (`propertyId` INTEGER NOT NULL, `lat` REAL NOT NULL, `lng` REAL NOT NULL, `orderIndex` INTEGER NOT NULL, PRIMARY KEY(`propertyId`, `orderIndex`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '510e64ff93dd33804bf274f39076f2f1')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `WorkerEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `LocationEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `LoadEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `PropertyEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `PropertyPointEntity`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsWorkerEntity = new HashMap<String, TableInfo.Column>(3);
        _columnsWorkerEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkerEntity.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkerEntity.put("payPercentage", new TableInfo.Column("payPercentage", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorkerEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWorkerEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWorkerEntity = new TableInfo("WorkerEntity", _columnsWorkerEntity, _foreignKeysWorkerEntity, _indicesWorkerEntity);
        final TableInfo _existingWorkerEntity = TableInfo.read(_db, "WorkerEntity");
        if (! _infoWorkerEntity.equals(_existingWorkerEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "WorkerEntity(com.loggingapp.WorkerEntity).\n"
                  + " Expected:\n" + _infoWorkerEntity + "\n"
                  + " Found:\n" + _existingWorkerEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsLocationEntity = new HashMap<String, TableInfo.Column>(4);
        _columnsLocationEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationEntity.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationEntity.put("lat", new TableInfo.Column("lat", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLocationEntity.put("lng", new TableInfo.Column("lng", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLocationEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLocationEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLocationEntity = new TableInfo("LocationEntity", _columnsLocationEntity, _foreignKeysLocationEntity, _indicesLocationEntity);
        final TableInfo _existingLocationEntity = TableInfo.read(_db, "LocationEntity");
        if (! _infoLocationEntity.equals(_existingLocationEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "LocationEntity(com.loggingapp.LocationEntity).\n"
                  + " Expected:\n" + _infoLocationEntity + "\n"
                  + " Found:\n" + _existingLocationEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsLoadEntity = new HashMap<String, TableInfo.Column>(5);
        _columnsLoadEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadEntity.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadEntity.put("value", new TableInfo.Column("value", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadEntity.put("pickupLocationId", new TableInfo.Column("pickupLocationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoadEntity.put("dropoffLocationId", new TableInfo.Column("dropoffLocationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLoadEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLoadEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLoadEntity = new TableInfo("LoadEntity", _columnsLoadEntity, _foreignKeysLoadEntity, _indicesLoadEntity);
        final TableInfo _existingLoadEntity = TableInfo.read(_db, "LoadEntity");
        if (! _infoLoadEntity.equals(_existingLoadEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "LoadEntity(com.loggingapp.LoadEntity).\n"
                  + " Expected:\n" + _infoLoadEntity + "\n"
                  + " Found:\n" + _existingLoadEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsPropertyEntity = new HashMap<String, TableInfo.Column>(4);
        _columnsPropertyEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPropertyEntity.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPropertyEntity.put("owner", new TableInfo.Column("owner", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPropertyEntity.put("source", new TableInfo.Column("source", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPropertyEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPropertyEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPropertyEntity = new TableInfo("PropertyEntity", _columnsPropertyEntity, _foreignKeysPropertyEntity, _indicesPropertyEntity);
        final TableInfo _existingPropertyEntity = TableInfo.read(_db, "PropertyEntity");
        if (! _infoPropertyEntity.equals(_existingPropertyEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "PropertyEntity(com.loggingapp.PropertyEntity).\n"
                  + " Expected:\n" + _infoPropertyEntity + "\n"
                  + " Found:\n" + _existingPropertyEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsPropertyPointEntity = new HashMap<String, TableInfo.Column>(4);
        _columnsPropertyPointEntity.put("propertyId", new TableInfo.Column("propertyId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPropertyPointEntity.put("lat", new TableInfo.Column("lat", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPropertyPointEntity.put("lng", new TableInfo.Column("lng", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPropertyPointEntity.put("orderIndex", new TableInfo.Column("orderIndex", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPropertyPointEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPropertyPointEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPropertyPointEntity = new TableInfo("PropertyPointEntity", _columnsPropertyPointEntity, _foreignKeysPropertyPointEntity, _indicesPropertyPointEntity);
        final TableInfo _existingPropertyPointEntity = TableInfo.read(_db, "PropertyPointEntity");
        if (! _infoPropertyPointEntity.equals(_existingPropertyPointEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "PropertyPointEntity(com.loggingapp.PropertyPointEntity).\n"
                  + " Expected:\n" + _infoPropertyPointEntity + "\n"
                  + " Found:\n" + _existingPropertyPointEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "510e64ff93dd33804bf274f39076f2f1", "f4518bf3f00fb11cc3040a079351f1b7");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "WorkerEntity","LocationEntity","LoadEntity","PropertyEntity","PropertyPointEntity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `WorkerEntity`");
      _db.execSQL("DELETE FROM `LocationEntity`");
      _db.execSQL("DELETE FROM `LoadEntity`");
      _db.execSQL("DELETE FROM `PropertyEntity`");
      _db.execSQL("DELETE FROM `PropertyPointEntity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(WorkerDao.class, WorkerDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LocationDao.class, LocationDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LoadDao.class, LoadDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PropertyDao.class, PropertyDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PropertyPointDao.class, PropertyPointDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public WorkerDao workerDao() {
    if (_workerDao != null) {
      return _workerDao;
    } else {
      synchronized(this) {
        if(_workerDao == null) {
          _workerDao = new WorkerDao_Impl(this);
        }
        return _workerDao;
      }
    }
  }

  @Override
  public LocationDao locationDao() {
    if (_locationDao != null) {
      return _locationDao;
    } else {
      synchronized(this) {
        if(_locationDao == null) {
          _locationDao = new LocationDao_Impl(this);
        }
        return _locationDao;
      }
    }
  }

  @Override
  public LoadDao loadDao() {
    if (_loadDao != null) {
      return _loadDao;
    } else {
      synchronized(this) {
        if(_loadDao == null) {
          _loadDao = new LoadDao_Impl(this);
        }
        return _loadDao;
      }
    }
  }

  @Override
  public PropertyDao propertyDao() {
    if (_propertyDao != null) {
      return _propertyDao;
    } else {
      synchronized(this) {
        if(_propertyDao == null) {
          _propertyDao = new PropertyDao_Impl(this);
        }
        return _propertyDao;
      }
    }
  }

  @Override
  public PropertyPointDao propertyPointDao() {
    if (_propertyPointDao != null) {
      return _propertyPointDao;
    } else {
      synchronized(this) {
        if(_propertyPointDao == null) {
          _propertyPointDao = new PropertyPointDao_Impl(this);
        }
        return _propertyPointDao;
      }
    }
  }
}
