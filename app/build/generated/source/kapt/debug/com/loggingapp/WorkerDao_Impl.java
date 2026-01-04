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
public final class WorkerDao_Impl implements WorkerDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WorkerEntity> __insertionAdapterOfWorkerEntity;

  private final EntityDeletionOrUpdateAdapter<WorkerEntity> __deletionAdapterOfWorkerEntity;

  private final EntityDeletionOrUpdateAdapter<WorkerEntity> __updateAdapterOfWorkerEntity;

  public WorkerDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWorkerEntity = new EntityInsertionAdapter<WorkerEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `WorkerEntity` (`id`,`name`,`payPercentage`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WorkerEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindDouble(3, value.getPayPercentage());
      }
    };
    this.__deletionAdapterOfWorkerEntity = new EntityDeletionOrUpdateAdapter<WorkerEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `WorkerEntity` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WorkerEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfWorkerEntity = new EntityDeletionOrUpdateAdapter<WorkerEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `WorkerEntity` SET `id` = ?,`name` = ?,`payPercentage` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WorkerEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindDouble(3, value.getPayPercentage());
        stmt.bindLong(4, value.getId());
      }
    };
  }

  @Override
  public Object insertWorker(final WorkerEntity worker,
      final Continuation<? super Long> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfWorkerEntity.insertAndReturnId(worker);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteWorker(final WorkerEntity worker,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfWorkerEntity.handle(worker);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateWorker(final WorkerEntity worker,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfWorkerEntity.handle(worker);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<WorkerEntity>> getAllWorkers() {
    final String _sql = "SELECT * FROM WorkerEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"WorkerEntity"}, new Callable<List<WorkerEntity>>() {
      @Override
      public List<WorkerEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPayPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "payPercentage");
          final List<WorkerEntity> _result = new ArrayList<WorkerEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final WorkerEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final double _tmpPayPercentage;
            _tmpPayPercentage = _cursor.getDouble(_cursorIndexOfPayPercentage);
            _item = new WorkerEntity(_tmpId,_tmpName,_tmpPayPercentage);
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
