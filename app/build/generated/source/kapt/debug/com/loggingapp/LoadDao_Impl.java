package com.loggingapp;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class LoadDao_Impl implements LoadDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LoadEntity> __insertionAdapterOfLoadEntity;

  private final EntityDeletionOrUpdateAdapter<LoadEntity> __deletionAdapterOfLoadEntity;

  private final EntityDeletionOrUpdateAdapter<LoadEntity> __updateAdapterOfLoadEntity;

  public LoadDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLoadEntity = new EntityInsertionAdapter<LoadEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `LoadEntity` (`id`,`date`,`value`,`pickupLocationId`,`dropoffLocationId`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LoadEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getDate());
        stmt.bindDouble(3, value.getValue());
        stmt.bindLong(4, value.getPickupLocationId());
        stmt.bindLong(5, value.getDropoffLocationId());
      }
    };
    this.__deletionAdapterOfLoadEntity = new EntityDeletionOrUpdateAdapter<LoadEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `LoadEntity` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LoadEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfLoadEntity = new EntityDeletionOrUpdateAdapter<LoadEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `LoadEntity` SET `id` = ?,`date` = ?,`value` = ?,`pickupLocationId` = ?,`dropoffLocationId` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LoadEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getDate());
        stmt.bindDouble(3, value.getValue());
        stmt.bindLong(4, value.getPickupLocationId());
        stmt.bindLong(5, value.getDropoffLocationId());
        stmt.bindLong(6, value.getId());
      }
    };
  }

  @Override
  public Object insertLoad(final LoadEntity load, final Continuation<? super Long> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfLoadEntity.insertAndReturnId(load);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteLoad(final LoadEntity load, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfLoadEntity.handle(load);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateLoad(final LoadEntity load, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfLoadEntity.handle(load);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<LoadEntity>> getAllLoads() {
    final String _sql = "SELECT * FROM LoadEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"LoadEntity"}, new Callable<List<LoadEntity>>() {
      @Override
      public List<LoadEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final int _cursorIndexOfPickupLocationId = CursorUtil.getColumnIndexOrThrow(_cursor, "pickupLocationId");
          final int _cursorIndexOfDropoffLocationId = CursorUtil.getColumnIndexOrThrow(_cursor, "dropoffLocationId");
          final List<LoadEntity> _result = new ArrayList<LoadEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final LoadEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final double _tmpValue;
            _tmpValue = _cursor.getDouble(_cursorIndexOfValue);
            final long _tmpPickupLocationId;
            _tmpPickupLocationId = _cursor.getLong(_cursorIndexOfPickupLocationId);
            final long _tmpDropoffLocationId;
            _tmpDropoffLocationId = _cursor.getLong(_cursorIndexOfDropoffLocationId);
            _item = new LoadEntity(_tmpId,_tmpDate,_tmpValue,_tmpPickupLocationId,_tmpDropoffLocationId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
