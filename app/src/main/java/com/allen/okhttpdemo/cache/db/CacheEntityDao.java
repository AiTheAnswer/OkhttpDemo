package com.allen.okhttpdemo.cache.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.allen.okhttpdemo.cache.db.bean.CacheEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CACHE_ENTITY".
*/
public class CacheEntityDao extends AbstractDao<CacheEntity, Long> {

    public static final String TABLENAME = "CACHE_ENTITY";

    /**
     * Properties of entity CacheEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Host = new Property(1, String.class, "host", false, "HOST");
        public final static Property Action = new Property(2, String.class, "action", false, "ACTION");
        public final static Property Params = new Property(3, String.class, "params", false, "PARAMS");
        public final static Property Result = new Property(4, String.class, "result", false, "RESULT");
        public final static Property CreateTime = new Property(5, String.class, "createTime", false, "CREATE_TIME");
    }


    public CacheEntityDao(DaoConfig config) {
        super(config);
    }
    
    public CacheEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CACHE_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"HOST\" TEXT NOT NULL ," + // 1: host
                "\"ACTION\" TEXT NOT NULL ," + // 2: action
                "\"PARAMS\" TEXT NOT NULL ," + // 3: params
                "\"RESULT\" TEXT NOT NULL ," + // 4: result
                "\"CREATE_TIME\" TEXT NOT NULL );"); // 5: createTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CACHE_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CacheEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getHost());
        stmt.bindString(3, entity.getAction());
        stmt.bindString(4, entity.getParams());
        stmt.bindString(5, entity.getResult());
        stmt.bindString(6, entity.getCreateTime());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CacheEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getHost());
        stmt.bindString(3, entity.getAction());
        stmt.bindString(4, entity.getParams());
        stmt.bindString(5, entity.getResult());
        stmt.bindString(6, entity.getCreateTime());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CacheEntity readEntity(Cursor cursor, int offset) {
        CacheEntity entity = new CacheEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // host
            cursor.getString(offset + 2), // action
            cursor.getString(offset + 3), // params
            cursor.getString(offset + 4), // result
            cursor.getString(offset + 5) // createTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CacheEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setHost(cursor.getString(offset + 1));
        entity.setAction(cursor.getString(offset + 2));
        entity.setParams(cursor.getString(offset + 3));
        entity.setResult(cursor.getString(offset + 4));
        entity.setCreateTime(cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CacheEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CacheEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CacheEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
