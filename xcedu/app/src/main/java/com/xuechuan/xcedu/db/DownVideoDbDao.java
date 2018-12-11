package com.xuechuan.xcedu.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.xuechuan.xcedu.db.Converent.DownVideoConverent;
import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DOWN_VIDEO_DB".
*/
public class DownVideoDbDao extends AbstractDao<DownVideoDb, Long> {

    public static final String TABLENAME = "DOWN_VIDEO_DB";

    /**
     * Properties of entity DownVideoDb.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Staffid = new Property(1, String.class, "staffid", false, "STAFFID");
        public final static Property Kid = new Property(2, String.class, "kid", false, "KID");
        public final static Property UrlImg = new Property(3, String.class, "urlImg", false, "URL_IMG");
        public final static Property KName = new Property(4, String.class, "kName", false, "K_NAME");
        public final static Property Vid = new Property(5, String.class, "vid", false, "VID");
        public final static Property Time = new Property(6, String.class, "time", false, "TIME");
        public final static Property Downlist = new Property(7, String.class, "downlist", false, "DOWNLIST");
    }

    private final DownVideoConverent downlistConverter = new DownVideoConverent();

    public DownVideoDbDao(DaoConfig config) {
        super(config);
    }
    
    public DownVideoDbDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DOWN_VIDEO_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"STAFFID\" TEXT," + // 1: staffid
                "\"KID\" TEXT," + // 2: kid
                "\"URL_IMG\" TEXT," + // 3: urlImg
                "\"K_NAME\" TEXT," + // 4: kName
                "\"VID\" TEXT," + // 5: vid
                "\"TIME\" TEXT," + // 6: time
                "\"DOWNLIST\" TEXT);"); // 7: downlist
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DOWN_VIDEO_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DownVideoDb entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String staffid = entity.getStaffid();
        if (staffid != null) {
            stmt.bindString(2, staffid);
        }
 
        String kid = entity.getKid();
        if (kid != null) {
            stmt.bindString(3, kid);
        }
 
        String urlImg = entity.getUrlImg();
        if (urlImg != null) {
            stmt.bindString(4, urlImg);
        }
 
        String kName = entity.getKName();
        if (kName != null) {
            stmt.bindString(5, kName);
        }
 
        String vid = entity.getVid();
        if (vid != null) {
            stmt.bindString(6, vid);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(7, time);
        }
 
        List downlist = entity.getDownlist();
        if (downlist != null) {
            stmt.bindString(8, downlistConverter.convertToDatabaseValue(downlist));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DownVideoDb entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String staffid = entity.getStaffid();
        if (staffid != null) {
            stmt.bindString(2, staffid);
        }
 
        String kid = entity.getKid();
        if (kid != null) {
            stmt.bindString(3, kid);
        }
 
        String urlImg = entity.getUrlImg();
        if (urlImg != null) {
            stmt.bindString(4, urlImg);
        }
 
        String kName = entity.getKName();
        if (kName != null) {
            stmt.bindString(5, kName);
        }
 
        String vid = entity.getVid();
        if (vid != null) {
            stmt.bindString(6, vid);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(7, time);
        }
 
        List downlist = entity.getDownlist();
        if (downlist != null) {
            stmt.bindString(8, downlistConverter.convertToDatabaseValue(downlist));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DownVideoDb readEntity(Cursor cursor, int offset) {
        DownVideoDb entity = new DownVideoDb( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // staffid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // kid
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // urlImg
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // kName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // vid
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // time
            cursor.isNull(offset + 7) ? null : downlistConverter.convertToEntityProperty(cursor.getString(offset + 7)) // downlist
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DownVideoDb entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStaffid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setKid(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUrlImg(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setKName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setVid(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTime(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDownlist(cursor.isNull(offset + 7) ? null : downlistConverter.convertToEntityProperty(cursor.getString(offset + 7)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DownVideoDb entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DownVideoDb entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DownVideoDb entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}