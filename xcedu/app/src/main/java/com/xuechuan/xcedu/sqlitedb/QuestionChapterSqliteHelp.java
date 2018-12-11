package com.xuechuan.xcedu.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xuechuan.xcedu.base.DataMessageVo;
import com.xuechuan.xcedu.utils.DbQueryUtil;
import com.xuechuan.xcedu.utils.StringUtil;
import com.xuechuan.xcedu.vo.SqliteVo.QuestionChapterSqliteVo;

import java.io.File;
import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.sqlitedb
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.12.11 下午 2:25
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class QuestionChapterSqliteHelp {
    private static volatile QuestionChapterSqliteHelp _singleton;
    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;
    private final DbQueryUtil mDbQueryUtil;

    private QuestionChapterSqliteHelp(Context context) {
        this.mContext = context;
        mDbQueryUtil = DbQueryUtil.get_Instance();
    }

    public static QuestionChapterSqliteHelp get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (QuestionChapterSqliteHelp.class) {
                if (_singleton == null) {
                    _singleton = new QuestionChapterSqliteHelp(context);
                }
            }
        }
        return _singleton;
    }

    public void initOpenUserInfom() {
        DbPathUitl instance = DbPathUitl.get_Instance(mContext);
        String dbPath = instance.getDbPath();
        if (StringUtil.isEmpty(dbPath)) return;
        String concat = dbPath.concat(DataMessageVo.USER_QEUSTION_TABLE_QUESTION_CHAPTER);
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

    public synchronized void addQestionChapterItem(QuestionChapterSqliteVo vo) {
        if (vo == null) return;
        if (empty()) return;
//        String sql = "insert into " + DataMessageVo.USER_QEUSTION_TABLE_QUESTION_CHAPTER +
//                "(courseid,chaptername,mold,questionnum,sort,parentid) values (" +
//                vo.getCourseid() + ",'" +
//                vo.getChaptername() + "'," +
//                vo.getMold() + "," +
//                vo.getQuestionnum() + "," +
//                vo.getSort() + "," +
//                vo.getParentid() + ");";
//        mSqLiteDatabase.execSQL(sql)
        ContentValues values = new ContentValues();
        values.put("question_chapter_id",vo.getId());
        values.put("courseid",vo.getCourseid());
        values.put("chaptername",vo.getChaptername());
        values.put("mold",vo.getMold());
        values.put("questionnum",vo.getQuestionnum());
        values.put("sort",vo.getSort());
        values.put("parentid",vo.getParentid());
        mSqLiteDatabase.beginTransaction();
        mSqLiteDatabase.insert(DataMessageVo.USER_QEUSTION_TABLE_QUESTION_CHAPTER
        ,null,values);
        mSqLiteDatabase.endTransaction();
    }

    public QuestionChapterSqliteVo findQuestionChapterItemAll(int idParamer) {
        if (empty()) return null;
        Cursor query = mSqLiteDatabase.query(DataMessageVo.USER_QEUSTION_TABLE_QUESTION_CHAPTER, null, "id=?",
                new String[]{String.valueOf(idParamer)}, null, null, null);
        mDbQueryUtil.initCursor(query);
        while (query.moveToNext()) {
            QuestionChapterSqliteVo sqliteVo = getQuestionChapterSqliteVo(mDbQueryUtil);
            return sqliteVo;
        }
        return null;
    }

    public void UpDateQestionChaperItem(QuestionChapterSqliteVo vo) {
        if (empty()) return;
        String sql = "update " + DataMessageVo.USER_QEUSTION_TABLE_QUESTION_CHAPTER +
                "  set courseid=" + vo.getCourseid() +
                ",chaptername ='" + vo.getChaptername() +
                "',mold=" + vo.getMold() +
                ",questionnum=" + vo.getQuestionnum() +
                ",sort=" + vo.getSort() +
                ",parentid=" + vo.getParentid() +
                "  where  " + ");";
        mSqLiteDatabase.execSQL(sql);

    }

    public ArrayList<QuestionChapterSqliteVo> findQuestionChapterItemAll() {
        if (empty()) return null;
        Cursor query = mSqLiteDatabase.query(DataMessageVo.USER_QEUSTION_TABLE_QUESTION_CHAPTER, null, null,
                null, null, null, null);
        mDbQueryUtil.initCursor(query);
        ArrayList<QuestionChapterSqliteVo> list = new ArrayList<>();

        while (query.moveToNext()) {
            QuestionChapterSqliteVo sqliteVo = getQuestionChapterSqliteVo(mDbQueryUtil);
            list.add(sqliteVo);
        }
        return list;
    }

    /**
     * 查找类型并不排序
     *
     * @param couserid
     * @param mold
     * @param parentid
     * @return
     */
    public ArrayList<QuestionChapterSqliteVo> findQuestionChapterItemAllWiteCoureseid(String couserid, String mold, String parentid) {
        if (empty()) return null;
        Cursor query = mSqLiteDatabase.query(DataMessageVo.USER_QEUSTION_TABLE_QUESTION_CHAPTER, null,
                "courseid =? and mold=? and parentid=?",
                new String[]{String.valueOf(couserid), String.valueOf(mold), String.valueOf(parentid)}, null, null, "srot desc ,id asc");
        mDbQueryUtil.initCursor(query);
        ArrayList<QuestionChapterSqliteVo> list = new ArrayList<>();
        while (query.moveToNext()) {
            QuestionChapterSqliteVo sqliteVo = getQuestionChapterSqliteVo(mDbQueryUtil);
            list.add(sqliteVo);
        }
        return list;
    }

    public ArrayList<QuestionChapterSqliteVo> QueryCourseidZWmoidParenid(int mold, int parentid) {
        if (empty()) return null;
        Cursor query = mSqLiteDatabase.query(DataMessageVo.USER_QEUSTION_TABLE_QUESTION_CHAPTER, null,
                " mold=? and parentid=?",
                new String[]{String.valueOf(mold), String.valueOf(parentid)}, null, null, "srot desc ,id asc");
        mDbQueryUtil.initCursor(query);
        ArrayList<QuestionChapterSqliteVo> list = new ArrayList<>();
        while (query.moveToNext()) {
            QuestionChapterSqliteVo sqliteVo = getQuestionChapterSqliteVo(mDbQueryUtil);
            list.add(sqliteVo);
        }
        return list;
    }

    public static QuestionChapterSqliteVo getQuestionChapterSqliteVo(DbQueryUtil mDbQueryUtil) {
        QuestionChapterSqliteVo sqliteVo = new QuestionChapterSqliteVo();
        int id = mDbQueryUtil.queryInt("id");
        int courseid = mDbQueryUtil.queryInt("courseid");
        String chaptername = mDbQueryUtil.queryString("chaptername");
        int mold = mDbQueryUtil.queryInt("mold");
        int questionnum = mDbQueryUtil.queryInt("questionnum");
        int sort = mDbQueryUtil.queryInt("sort");
        int parentid = mDbQueryUtil.queryInt("parentid");
        sqliteVo.setId(id);
        sqliteVo.setParentid(parentid);
        sqliteVo.setChaptername(chaptername);
        sqliteVo.setCourseid(courseid);
        sqliteVo.setMold(mold);
        sqliteVo.setQuestionnum(questionnum);
        sqliteVo.setSort(sort);
        return sqliteVo;
    }
}
