package com.shanbei.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.shanbei.greendao.ScallopWord;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ScallopWordCache".
*/
public class ScallopWordDao extends AbstractDao<ScallopWord, Long> {

    public static final String TABLENAME = "ScallopWordCache";

    /**
     * Properties of entity ScallopWord.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property LessonBelong = new Property(1, String.class, "lessonBelong", false, "LESSON_BELONG");
        public final static Property Content = new Property(2, String.class, "content", false, "CONTENT");
        public final static Property Level = new Property(3, String.class, "level", false, "LEVEL");
        public final static Property Info = new Property(4, String.class, "info", false, "INFO");
    };


    public ScallopWordDao(DaoConfig config) {
        super(config);
    }
    
    public ScallopWordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ScallopWordCache\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"LESSON_BELONG\" TEXT NOT NULL ," + // 1: lessonBelong
                "\"CONTENT\" TEXT NOT NULL ," + // 2: content
                "\"LEVEL\" TEXT NOT NULL ," + // 3: level
                "\"INFO\" TEXT NOT NULL );"); // 4: info
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ScallopWordCache\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ScallopWord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getLessonBelong());
        stmt.bindString(3, entity.getContent());
        stmt.bindString(4, entity.getLevel());
        stmt.bindString(5, entity.getInfo());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ScallopWord readEntity(Cursor cursor, int offset) {
        ScallopWord entity = new ScallopWord( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // lessonBelong
            cursor.getString(offset + 2), // content
            cursor.getString(offset + 3), // level
            cursor.getString(offset + 4) // info
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ScallopWord entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLessonBelong(cursor.getString(offset + 1));
        entity.setContent(cursor.getString(offset + 2));
        entity.setLevel(cursor.getString(offset + 3));
        entity.setInfo(cursor.getString(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ScallopWord entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ScallopWord entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
