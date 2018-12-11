package com.xuechuan.xcedu.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xuechuan.xcedu.base.DataMessageVo;
import com.xuechuan.xcedu.utils.DbQueryUtil;
import com.xuechuan.xcedu.utils.StringUtil;
import com.xuechuan.xcedu.vo.SqliteVo.QuestionSqliteVo;

import java.io.File;
import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.sqlitedb
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.12.11 上午 11:56
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class QuestionSqliteHelp {
    private static volatile QuestionSqliteHelp _singleton;
    private Context mContext;
    private SQLiteDatabase mSqLiteDatabase;
    private final DbQueryUtil mDbQueryUtil;

    private QuestionSqliteHelp(Context context) {
        this.mContext = context;
        mDbQueryUtil = DbQueryUtil.get_Instance();
    }

    public static QuestionSqliteHelp get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (QuestionSqliteHelp.class) {
                if (_singleton == null) {
                    _singleton = new QuestionSqliteHelp(context);
                }
            }
        }
        return _singleton;
    }

    public void initOpenUserInfom() {
        DbPathUitl instance = DbPathUitl.get_Instance(mContext);
        String dbPath = instance.getDbPath();
        if (StringUtil.isEmpty(dbPath)) return;
        String concat = dbPath.concat(DataMessageVo.USER_QUESTION_TABLE_QUESTION);
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

    /**
     * 添加数据
     *
     * @param vo
     */
    public synchronized void addQuestionItem(QuestionSqliteVo vo) {
        if (vo == null) return;
        if (empty()) return;
        mSqLiteDatabase.beginTransaction();
        ContentValues values = setValues(vo);
        mSqLiteDatabase.insert(DataMessageVo.USER_QUESTION_TABLE_QUESTION,null,values);
        mSqLiteDatabase.endTransaction();

        String nulla = "null";
        if (StringUtil.isEmpty(vo.getQuestionimg())) {
            vo.setQuestionimg(nulla);
        }
        if (StringUtil.isEmpty(vo.getOption_a())) {
            vo.setOption_a(nulla);
        }
        if (StringUtil.isEmpty(vo.getOption_b())) {
            vo.setOption_b(nulla);
        }
        if (StringUtil.isEmpty(vo.getOption_c())) {
            vo.setOption_c(nulla);
        }
        if (StringUtil.isEmpty(vo.getOption_d())) {
            vo.setOption_d(nulla);
        }
        if (StringUtil.isEmpty(vo.getOption_f())) {
            vo.setOption_f(nulla);
        }
        if (StringUtil.isEmpty(vo.getOption_e())) {
            vo.setOption_e(nulla);
        }
        if (StringUtil.isEmpty(vo.getOption_h())) {
            vo.setOption_h(nulla);
        }
        if (StringUtil.isEmpty(vo.getOption_g())) {
            vo.setOption_g(nulla);
        }
        if (StringUtil.isEmpty(vo.getChoice_answer())) {
            vo.setChoice_answer(nulla);
        }

        String sql = "insert into " + DataMessageVo.USER_QUESTION_TABLE_QUESTION +
                "(question,questionimg,isreadcom,parent_id,questiontype,option_a,option_b,option_c,option_d" +
                ",option_e,option_f,option_g,option_h,choice_answer,explain,explainimg,chapter_id,question_mold," +
                "sort,courseid,keywords,difficulty,wrong_rate,score) values (" +
                vo.getQuestion() + ",'" +
                vo.getQuestionimg() + "'," +
                vo.getIsreadcom() + "," +
                vo.getParent_id() + "," +
                vo.getQuestiontype() + "," +
                "'" + vo.getOption_a() + "'," +
                "'" + vo.getOption_b() + "'," +
                "'" + vo.getOption_c() + "'," +
                "'" + vo.getOption_d() + "'," +
                "'" + vo.getOption_e() + "'," +
                "'" + vo.getOption_f() + "'," +
                "'" + vo.getOption_g() + "'," +
                "'" + vo.getOption_h() + "'," +
                "'" + vo.getChoice_answer() + "'," +
                vo.getExplained() + "," +
                vo.getExplainimg() + "," +
                vo.getChapter_id() + "," +
                vo.getQuestion_mold() + "," +
                vo.getSort() + "," +
                vo.getCourseid() + "," +
                vo.getKeywords() + "," +
                vo.getDifficulty() + "," +
                vo.getWrong_rate() + "," +
                vo.getScore() + ");";

//        mSqLiteDatabase.execSQL(sql);


    }



    private ContentValues setValues(QuestionSqliteVo vo){
        ContentValues values = new ContentValues();
        values.put("question",vo.getQuestion());
        values.put("questionimg",vo.getQuestionimg());
        values.put("isreadcom",vo.getIsreadcom());
        values.put("parent_id",vo.getParent_id());
        values.put("questiontype",vo.getQuestiontype());
        values.put("option_a",vo.getOption_a());
        values.put("option_b",vo.getOption_b());
        values.put("option_c",vo.getOption_c());
        values.put("option_d",vo.getOption_d());
        values.put("option_e",vo.getOption_e());
        values.put("option_f",vo.getOption_f());
        values.put("option_g",vo.getOption_g());
        values.put("option_h",vo.getOption_h());
        values.put("choice_answer",vo.getChoice_answer());
        values.put("explain",vo.getExplained());
        values.put("explainimg",vo.getExplainimg());
        values.put("chapter_id",vo.getChapter_id());
        values.put("question_mold",vo.getQuestion_mold());
        values.put("sort",vo.getSort());
        values.put("courseid",vo.getCourseid());
        values.put("keywords",vo.getKeywords());
        values.put("difficulty",vo.getDifficulty());
        values.put("wrong_rate",vo.getWrong_rate());
        values.put("score",vo.getScore());
        return values;
    }

    /**
     * 删除莫个数据
     *
     * @param paraentid
     */
    public void deleteItemData(int paraentid) {
        if (empty()) return;
        mSqLiteDatabase.delete(DataMessageVo.USER_QUESTION_TABLE_QUESTION, "parent_id=?",
                new String[]{String.valueOf(paraentid)});

    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public ArrayList<QuestionSqliteVo> findQuestionAll() {
        if (empty()) return null;
        Cursor query = mSqLiteDatabase.query(DataMessageVo.USER_QUESTION_TABLE_QUESTION, null, null, null, null, null, null);
        mDbQueryUtil.initCursor(query);
        ArrayList<QuestionSqliteVo> list = new ArrayList<>();
        while (query.moveToNext()) {
            QuestionSqliteVo vo = getQuesitonSqlite(mDbQueryUtil);
            list.add(vo);
        }
        return list;
    }

    /**
     * 更新莫条数据
     *
     * @param vo
     */
    public void updateQuestionItem(QuestionSqliteVo vo) {
        if (empty()) return;

        String sql = "update " + DataMessageVo.USER_QUESTION_TABLE_QUESTION +
                " set  question=" + vo.getQuestion() + "," +
                "questionimg='" + vo.getQuestionimg() + "'," +
                "isreadcom=" + vo.getIsreadcom() + "," +
                "parent_id=" + vo.getParent_id() + "," +
                "questiontype=" + vo.getQuestiontype() + "," +
                "option_a='" + vo.getOption_a() + "'," +
                "option_b='" + vo.getOption_b() + "'," +
                "option_c='" + vo.getOption_c() + "'," +
                "option_d='" + vo.getOption_d() + "'," +
                "option_e='" + vo.getOption_e() + "'," +
                "option_f='" + vo.getOption_f() + "'," +
                "option_g='" + vo.getOption_g() + "'," +
                "option_h='" + vo.getOption_h() + "'," +
                "choice_answer='" + vo.getChoice_answer() + "'," +
                "explain=" + vo.getExplained() + "," +
                "explainimg=" + vo.getExplainimg() + "," +
                "chapter_id=" + vo.getChapter_id() + "," +
                "question_mold=" + vo.getQuestion_mold() + "," +
                "sort=" + vo.getSort() + "," +
                "courseid=" + vo.getCourseid() + "," +
                "keywords=" + vo.getKeywords() + "," +
                "difficulty=" + vo.getDifficulty() + "," +
                "wrong_rate=" + vo.getWrong_rate() + "," +
                "score=" + vo.getScore() + " where id= " + vo.getId();
        mSqLiteDatabase.execSQL(sql);

    }

    public static QuestionSqliteVo getQuesitonSqlite(DbQueryUtil mQueryUtil) {
        QuestionSqliteVo sqliteVo = new QuestionSqliteVo();
        int id = mQueryUtil.queryInt("id");
        byte[] questions = mQueryUtil.queryBLOB("question");
        String questionimg = mQueryUtil.queryString("questionimg");
        int isreadcom = mQueryUtil.queryInt("isreadcom");
        int parent_id = mQueryUtil.queryInt("parent_id");
        int questiontype = mQueryUtil.queryInt("questiontype");
        String option_a = mQueryUtil.queryString("option_a");
        String option_b = mQueryUtil.queryString("option_b");
        String option_c = mQueryUtil.queryString("option_c");
        String option_d = mQueryUtil.queryString("option_d");
        String option_e = mQueryUtil.queryString("option_e");
        String option_f = mQueryUtil.queryString("option_f");
        String option_g = mQueryUtil.queryString("option_g");
        String option_h = mQueryUtil.queryString("option_h");
        String choice_answer = mQueryUtil.queryString("choice_answer");
        byte[] explained = mQueryUtil.queryBLOB("explain");
        byte[] explainimg = mQueryUtil.queryBLOB("explainimg");
        int chapter_id = mQueryUtil.queryInt("chapter_id");
        int question_mold = mQueryUtil.queryInt("question_mold");
        int sort = mQueryUtil.queryInt("sort");
        int courseid = mQueryUtil.queryInt("courseid");
        byte[] keywords = mQueryUtil.queryBLOB("keywords");
        int difficulty = mQueryUtil.queryInt("difficulty");
        double wrong_rate = mQueryUtil.querydouble("wrong_rate");
        double score = mQueryUtil.querydouble("score");
        sqliteVo.setChapter_id(chapter_id);
        sqliteVo.setChoice_answer(choice_answer);
        sqliteVo.setDifficulty(difficulty);
        sqliteVo.setExplained(explained);
        sqliteVo.setExplainimg(explainimg);
        sqliteVo.setId(id);
        sqliteVo.setIsreadcom(isreadcom);
        sqliteVo.setKeywords(keywords);
        sqliteVo.setOption_a(option_a);
        sqliteVo.setOption_b(option_b);
        sqliteVo.setOption_c(option_c);
        sqliteVo.setOption_d(option_d);
        sqliteVo.setOption_e(option_e);
        sqliteVo.setOption_f(option_f);
        sqliteVo.setOption_g(option_g);
        sqliteVo.setOption_h(option_h);
        sqliteVo.setParent_id(parent_id);
        sqliteVo.setQuestion(questions);
        sqliteVo.setQuestion_mold(question_mold);
        sqliteVo.setQuestionimg(questionimg);
        sqliteVo.setQuestiontype(questiontype);
        sqliteVo.setScore(score);
        sqliteVo.setWrong_rate(wrong_rate);
        sqliteVo.setSort(sort);
        sqliteVo.setCourseid(courseid);
        return sqliteVo;
    }

}
