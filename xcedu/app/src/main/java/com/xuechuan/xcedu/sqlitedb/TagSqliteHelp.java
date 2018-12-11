package com.xuechuan.xcedu.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xuechuan.xcedu.base.DataMessageVo;
import com.xuechuan.xcedu.utils.DbQueryUtil;
import com.xuechuan.xcedu.utils.StringUtil;
import com.xuechuan.xcedu.vo.SqliteVo.TagSqliteVo;

import java.io.File;
import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.sqlitedb
 * @Description:tag tag帮助类
 * @author: L-BackPacker
 * @date: 2018.12.11 下午 3:04
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class TagSqliteHelp {
    private static volatile TagSqliteHelp _singleton;
    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;
    private final DbQueryUtil mDbQueryUtil;

    private TagSqliteHelp(Context context) {
        this.mContext = context;
        mDbQueryUtil = DbQueryUtil.get_Instance();
    }

    public static TagSqliteHelp get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (TagSqliteHelp.class) {
                if (_singleton == null) {
                    _singleton = new TagSqliteHelp(context);
                }
            }
        }
        return _singleton;
    }


    public void initOpenUserInfom() {
        DbPathUitl instance = DbPathUitl.get_Instance(mContext);
        String dbPath = instance.getDbPath();
        if (StringUtil.isEmpty(dbPath)) return;
        String concat = dbPath.concat(DataMessageVo.USER_QUESTIONTABLE_TAG);
        File file = new File(concat);
        mSqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(file, null);
        if (mSqLiteDatabase == null) {
            createtable();
        }
    }

    private SQLiteDatabase createtable() {
        UserInfomOpenHelp userInfomOpenHelp = new UserInfomOpenHelp(mContext);
        return userInfomOpenHelp.getWritableDatabase();
    }

    private boolean empty() {
        if (mSqLiteDatabase == null)
            return true;
        if (mSqLiteDatabase.isReadOnly())
            return true;
        return false;
    }

    public synchronized void addTagItem(TagSqliteVo sqliteVo) {
        if (empty()) return;
//        String sql = "insert into " + DataMessageVo.USER_QUESTIONTABLE_TAG +
//                "(tagname,courseid,questionnum,tagid)" +
//                " values ('" +
//                sqliteVo.getTagname() + "'" +
//                sqliteVo.getCourseid() + "," +
//                sqliteVo.getQuestionnum() + "," +
//                sqliteVo.getTagid() +
//                ") ;";
//
//        mSqLiteDatabase.execSQL(sql);
        mSqLiteDatabase.beginTransaction();
        ContentValues values = new ContentValues();
        values.put("tagname", sqliteVo.getTagname());
        values.put("id", sqliteVo.getId());
        values.put("courseid", sqliteVo.getCourseid());
        values.put("questionnum", sqliteVo.getQuestionnum());
        mSqLiteDatabase.insert(DataMessageVo.USER_QUESTIONTABLE_TAG, null, values);
        mSqLiteDatabase.endTransaction();

    }

    public ArrayList<TagSqliteVo> findTagAll() {
        if (empty()) return null;
        Cursor query = mSqLiteDatabase.query(DataMessageVo.USER_QUESTIONTABLE_TAG, null, null, null, null
                , null, null);
        mDbQueryUtil.initCursor(query);
        ArrayList<TagSqliteVo> list = new ArrayList<>();
        while (query.moveToNext()) {
            TagSqliteVo vo = getTagSqliteVoBen(mDbQueryUtil);
            list.add(vo);
        }
        return list;

    }

    public ArrayList<TagSqliteVo> finTagAllWithCourserid(int courseid) {
        if (empty()) {
            return null;
        }
        Cursor query = mSqLiteDatabase.query(DataMessageVo.USER_QUESTIONTABLE_TAG, null,
                "courseid=?", new String[]{String.valueOf(courseid)}, null, null, null);
        mDbQueryUtil.initCursor(query);
        ArrayList<TagSqliteVo> list = new ArrayList<>();
        while (query.moveToNext()) {
            TagSqliteVo tagSqliteVo = getTagSqliteVoBen(mDbQueryUtil);
            list.add(tagSqliteVo);
        }
        return list;

    }


    public static TagSqliteVo getTagSqliteVo(DbQueryUtil mDbQueryUtil) {
        TagSqliteVo vo = new TagSqliteVo();
        int id = mDbQueryUtil.queryInt("id");
//        int tagid = mDbQueryUtil.queryInt("tagid");
        String tagname = mDbQueryUtil.queryString("tagname");
        int courseid = mDbQueryUtil.queryInt("courseid");
        int questionnum = mDbQueryUtil.queryInt("questionnum");
        vo.setId(id);
//        vo.setTagid(tagid);
        vo.setCourseid(courseid);
        vo.setQuestionnum(questionnum);
        vo.setTagname(tagname);
        return vo;
    }
    public static TagSqliteVo getTagSqliteVoBen(DbQueryUtil mDbQueryUtil) {
        TagSqliteVo vo = new TagSqliteVo();
        int id = mDbQueryUtil.queryInt("id");
        int tagid = mDbQueryUtil.queryInt("tagid");
        String tagname = mDbQueryUtil.queryString("tagname");
        int courseid = mDbQueryUtil.queryInt("courseid");
        int questionnum = mDbQueryUtil.queryInt("questionnum");
        vo.setId(id);
        vo.setTagid(tagid);
        vo.setCourseid(courseid);
        vo.setQuestionnum(questionnum);
        vo.setTagname(tagname);
        return vo;
    }
}
