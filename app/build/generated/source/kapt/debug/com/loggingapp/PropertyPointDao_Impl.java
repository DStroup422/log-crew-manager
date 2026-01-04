package com.loggingapp;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
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
public final class PropertyPointDao_Impl implements PropertyPointDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PropertyPointEntity> __insertionAdapterOfPropertyPointEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeletePointsForProperty;

  public PropertyPointDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPropertyPointEntity = new EntityInsertionAdapter<PropertyPointEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `PropertyPointEntity` (`propertyId`,`lat`,`lng`,`orderIndex`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PropertyPointEntity value) {
        stmt.bindLong(1, value.getPropertyId());
        stmt.bindDouble(2, value.getLat());
        stmt.bindDouble(3, value.getLng());
        stmt.bindLong(4, value.getOrderIndex());
      }
    };
    this.__preparedStmtOfDeletePointsForProperty = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM PropertyPointEntity WHERE propertyId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertPoint(final PropertyPointEntity point,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPropertyPointEntity.insert(point);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deletePointsForProperty(final long propertyId,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePointsForProperty.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, propertyId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeletePointsForProperty.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<PropertyPointEntity>> getPointsForProperty(final long propertyId) {
    final String _sql = "SELECT * FROM PropertyPointEntity WHERE propertyId = ? ORDER BY orderIndex";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, propertyId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"PropertyPointEntity"}, new Callable<List<PropertyPointEntity>>() {
      @Override
      public List<PropertyPointEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfLat = CursorUtil.getColumnIndexOrThrow(_cursor, "lat");
          final int _cursorIndexOfLng = CursorUtil.getColumnIndexOrThrow(_cursor, "lng");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final List<PropertyPointEntity> _result = new ArrayList<PropertyPointEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PropertyPointEntity _item;
            final long _tmpPropertyId;
            _tmpPropertyId = _cursor.getLong(_cursorIndexOfPropertyId);
            final double _tmpLat;
            _tmpLat = _cursor.getDouble(_cursorIndexOfLat);
            final double _tmpLng;
            _tmpLng = _cursor.getDouble(_cursorIndexOfLng);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            _item = new PropertyPointEntity(_tmpPropertyId,_tmpLat,_tmpLng,_tmpOrderIndex);
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
