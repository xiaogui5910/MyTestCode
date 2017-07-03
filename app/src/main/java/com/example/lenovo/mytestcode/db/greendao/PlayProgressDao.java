package com.example.lenovo.mytestcode.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.lenovo.mytestcode.bean.PlayProgress;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "play_progress".
*/
public class PlayProgressDao extends AbstractDao<PlayProgress, Void> {

    public static final String TABLENAME = "play_progress";

    /**
     * Properties of entity PlayProgress.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Program_number = new Property(0, String.class, "program_number", false, "PROGRAM_NUMBER");
        public final static Property Event_id = new Property(1, String.class, "event_id", false, "EVENT_ID");
        public final static Property Time = new Property(2, String.class, "time", false, "TIME");
    }


    public PlayProgressDao(DaoConfig config) {
        super(config);
    }
    
    public PlayProgressDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"play_progress\" (" + //
                "\"PROGRAM_NUMBER\" TEXT," + // 0: program_number
                "\"EVENT_ID\" TEXT," + // 1: event_id
                "\"TIME\" TEXT);"); // 2: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"play_progress\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PlayProgress entity) {
        stmt.clearBindings();
 
        String program_number = entity.getProgram_number();
        if (program_number != null) {
            stmt.bindString(1, program_number);
        }
 
        String event_id = entity.getEvent_id();
        if (event_id != null) {
            stmt.bindString(2, event_id);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(3, time);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PlayProgress entity) {
        stmt.clearBindings();
 
        String program_number = entity.getProgram_number();
        if (program_number != null) {
            stmt.bindString(1, program_number);
        }
 
        String event_id = entity.getEvent_id();
        if (event_id != null) {
            stmt.bindString(2, event_id);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(3, time);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public PlayProgress readEntity(Cursor cursor, int offset) {
        PlayProgress entity = new PlayProgress( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // program_number
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // event_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PlayProgress entity, int offset) {
        entity.setProgram_number(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setEvent_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(PlayProgress entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(PlayProgress entity) {
        return null;
    }

    @Override
    public boolean hasKey(PlayProgress entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
