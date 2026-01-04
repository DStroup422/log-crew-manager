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
public final class PropertyDao_Impl implements PropertyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PropertyEntity> __insertionAdapterOfPropertyEntity;

  private final EntityDeletionOrUpdateAdapter<PropertyEntity> __deletionAdapterOfPropertyEntity;

  private final EntityDeletionOrUpdateAdapter<PropertyEntity> __updateAdapterOfPropertyEntity;

  public PropertyDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPropertyEntity = new EntityInsertionAdapter<PropertyEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `PropertyEntity` (`id`,`name`,`owner`,`source`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PropertyEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getOwner() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getOwner());
        }
        if (value.getSource() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSource());
        }
      }
    };
    this.__deletionAdapterOfPropertyEntity = new EntityDeletionOrUpdateAdapter<PropertyEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `PropertyEntity` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PropertyEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfPropertyEntity = new EntityDeletionOrUpdateAdapter<PropertyEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `PropertyEntity` SET `id` = ?,`name` = ?,`owner` = ?,`source` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PropertyEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getOwner() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getOwner());
        }
        if (value.getSource() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSource());
        }
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public Object insertProperty(final PropertyEntity property,
      final Continuation<? super Long> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfPropertyEntity.insertAndReturnId(property);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteProperty(final PropertyEntity property,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPropertyEntity.handle(property);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateProperty(final PropertyEntity property,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPropertyEntity.handle(property);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<PropertyEntity>> getAllProperties() {
    final String _sql = "SELECT * FROM PropertyEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"PropertyEntity"}, new Callable<List<PropertyEntity>>() {
      @Override
      public List<PropertyEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "owner");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final List<PropertyEntity> _result = new ArrayList<PropertyEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PropertyEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpOwner;
            if (_cursor.isNull(_cursorIndexOfOwner)) {
              _tmpOwner = null;
            } else {
              _tmpOwner = _cursor.getString(_cursorIndexOfOwner);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            _item = new PropertyEntity(_tmpId,_tmpName,_tmpOwner,_tmpSource);
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
