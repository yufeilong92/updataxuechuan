package com.xuechuan.xcedu.sqlitedb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xuechuan.xcedu.base.DataMessageVo;
import com.xuechuan.xcedu.utils.DbQueryUtil;
import com.xuechuan.xcedu.vo.SqliteVo.DeleteSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.QuestionChapterSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.QuestionSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.QuestionTagRelationSqliteVo;
import com.xuechuan.xcedu.vo.SqliteVo.TagSqliteVo;

import java.io.File;
import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.sqlitedb
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.12.10 上午 10:36
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class SqliteHelp {
    private static volatile SqliteHelp _singleton;
    private Context mContext;
    private final DbQueryUtil mQueryUtil;

    private SqliteHelp(Context context) {
        this.mContext = context;
        mQueryUtil = DbQueryUtil.get_Instance();
    }

    public static SqliteHelp get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (SqliteHelp.class) {
                if (_singleton == null) {
                    _singleton = new SqliteHelp(context);
                }
            }
        }
        return _singleton;
    }

    /**
     * 获取想要打开本地的数据库
     *
     * @param path
     * @param dbNmae
     * @return
     */
    public SQLiteDatabase acquireSqliteDb(String path, String dbNmae) {
        String concat = path.concat(dbNmae);
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(new File(concat), null);
        return database;
    }

    /**
     * 获取想要打开本地的数据库
     *
     * @param path
     * @return
     */
    public SQLiteDatabase acquireSqliteDb(String path) {
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(new File(path), null);
        return database;
    }

    /**
     * 输入要查询的数据库管理器
     *
     * @param sqliteManger
     */
    public Cursor findUserInfomAll(SQLiteDatabase sqliteManger, String dbName) {
        Cursor query = sqliteManger.query(dbName, null, null, null, null, null, null);
        return query;
    }

    public void findAllQuestionAndAdd(SQLiteDatabase database) {
        final QuestionSqliteHelp help = QuestionSqliteHelp.get_Instance(mContext);
        help.initOpenUserInfom();
        UserInfomDbHelp instance = UserInfomDbHelp.get_Instance(mContext);
        instance.initOpenUserInfom();
        Cursor query = database.query(DataMessageVo.t_question, null, null, null, null, null, null);
        mQueryUtil.initCursor(query);
        ArrayList<QuestionSqliteVo> list = new ArrayList<>();
        boolean isAdd = false;
        while (query.moveToNext()) {
            isAdd = true;
            final QuestionSqliteVo sqliteVo = QuestionSqliteHelp.getQuesitonSqlite(mQueryUtil);
            if (sqliteVo != null)
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        help.addQuestionItem(sqliteVo);
                    }
                });
        }
        if (isAdd) {
            instance.upDateAddQuestion(2);
        }
    }

    public void finAllQuestioDeleteAndAdd(SQLiteDatabase database) {
        final DeleteSqliteHelp deleteSqliteHelp = DeleteSqliteHelp.get_Instance(mContext);
        deleteSqliteHelp.initOpenUserInfom();
        Cursor query = database.query(DataMessageVo.t_delete, null, null, null, null, null, null);
        mQueryUtil.initCursor(query);
        ArrayList<DeleteSqliteVo> sqliteVos = new ArrayList<>();
        while (query.moveToNext()) {
            final DeleteSqliteVo vo = DeleteSqliteHelp.getDeleteValue(mQueryUtil);
            if (vo != null)
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        deleteSqliteHelp.addItemDelete(vo);
                    }
                });
        }
    }

    public void finAllQuestionChapterAndAdd(SQLiteDatabase database) {
        final QuestionChapterSqliteHelp help = QuestionChapterSqliteHelp.get_Instance(mContext);
        help.initOpenUserInfom();
        Cursor query = database.query(DataMessageVo.t_questionchapter, null, null, null, null, null, null);
        mQueryUtil.initCursor(query);
        while (query.moveToNext()) {
            final QuestionChapterSqliteVo sqliteVo = QuestionChapterSqliteHelp.getQuestionChapterSqliteVo(mQueryUtil);
            if (sqliteVo != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        help.addQestionChapterItem(sqliteVo);
                    }
                });
            }
        }


    }


    public void findAllQuestionTagRealtionAndAdd(SQLiteDatabase database) {
        final QuestionTagreLationSqliteHelp help = QuestionTagreLationSqliteHelp.get_Instance(mContext);
        help.initOpenUserInfom();
        Cursor query = database.query(DataMessageVo.t_questiontagrelation, null, null, null, null, null, null);
        mQueryUtil.initCursor(query);
        while (query.moveToNext()) {
            final QuestionTagRelationSqliteVo vo = QuestionTagreLationSqliteHelp.getQuestionTagRelationSqliteVo(mQueryUtil);
            if (vo != null)
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        help.addQuestionTagerlationItem(vo);
                    }
                });
        }
    }

    public void findTagAndTag(SQLiteDatabase database) {
        final TagSqliteHelp help = TagSqliteHelp.get_Instance(mContext);
        help.initOpenUserInfom();
        Cursor query = database.query(DataMessageVo.t_tag, null, null, null, null, null, null);
        mQueryUtil.initCursor(query);
        while (query.moveToNext()) {
            final TagSqliteVo vo = TagSqliteHelp.getTagSqliteVo(mQueryUtil);
            if (vo != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        help.addTagItem(vo);
                    }
                });
            }
        }
    }

}
