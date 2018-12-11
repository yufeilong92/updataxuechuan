package com.xuechuan.xcedu.sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xuechuan.xcedu.base.DataMessageVo;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.sqlitedb
 * @Description: 穿件本地数据库
 * @author: L-BackPacker
 * @date: 2018.12.10 上午 8:51
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class UserInfomOpenHelp extends SQLiteOpenHelper {


    public UserInfomOpenHelp(Context context) {
        super(context, DataMessageVo.USER_INFOM_DATABASE_NAME, null, DataMessageVo.USER_INFOM_DATABASE_VERISON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
   /*     String userinfom = "create table " + DataMessageVo.USER_INFOM_TABLE_USER + "(" +
                "id integer primary key," +//id 自动增值
                "_id long ," + //原数据id
                "copy int ," + //是否拷贝(1为拷贝，0没有拷贝)
                "userinfomvo text," + //用户信息
                "moid text," + //用户标识
                "skillbook text," + //用户是否购买技术实务
                "colligatebook text," + //用户是否购买综合案例
                "casebook text," + //用户是否购买案例分析
                "showDayOrNight text," + //用户选中的观看模式
                "userNextGo text," + //用户是否跳转到下一页
                "token text," + //用户token
                "userphone text," + //用户的手机号
                "username text)"; //用户的密码
        String look = "create table " + DataMessageVo.USER_INFOM_TABLE_LOOK +//做题记录表
                "(id integer primary key," +
                "chapterid integer," +//章节di
                "kname text," +//科程id
                "userid varchar," +//用户id
                "rightnumber text," + //第几题
                "rightallnumber text);";//当前总体数
        String videolook = "create table " + DataMessageVo.USER_INFOM_TABLE_VIDEOLOOK +//观看记录表
                "(id integer primary key," +
                "chapterid integer," +//章节di
                "kname text," +//科程id
                "userid varchar," +//用户id
                "rightnumber text," + //第几题
                "rightallnumber text);";//当前总体数
        String error = "create table " + DataMessageVo.USER_INFOM_TABLE_ERROR +
                "(id integer primary key," +
                "chapterid integer," +//当前题干id
                "kname text," +//科目id
                "rightnumber text," +//(第几次正确)
                "userid varchar," +//用户id
                "rightallnumber text);";//用户设置总对数
        String delete = "create table " + DataMessageVo.USER_QUESTION_TABLE_DELETE +//删除表
                "( id integer primary key," +
                "type text);";
        String question = "create table " + DataMessageVo.USER_QUESTION_TABLE_QUESTION +//问题表
                "(id integer primary key," +
                "question BLOB," +
                "questionimg text," +
                "isreadcom int," +
                "parent_id INT," +
                "questiontype INT," +
                "option_a TEXT," +
                "option_b TEXT," +
                "option_c TEXT," +
                "option_d TEXT," +
                "option_e TEXT," +
                "option_f TEXT," +
                "option_g TEXT," +
                "option_h TEXT," +
                "choice_answer TEXT," +
                "explained BLOB," +
                "explainimg BLOB," +
                "chapter_id INT," +
                "question_mold INT," +
                "sort INT," +
                "courseid INT," +
                "keywords BLOB," +
                "difficulty INT," +
                "wrong_rate DOUBLE," +
                "score DOUBLE);";
        String questionChapter = "create table " + DataMessageVo.USER_QEUSTION_TABLE_QUESTION_CHAPTER +
                "(id integer primary key ," +
                "courseid int," +
                "chaptername text," +
                "mold  int," +
                "questionnum int ," +
                "sort int ," +
                "parentid id);";
        String questiontagrelagtion = "create table " + DataMessageVo.USER_INFOM_TABLE_QUESTION_TAGRELATION +
                "(id integer primary key ," +
                "questionid int ," +
                "tagid int);";
        String tag = "create table " + DataMessageVo.USER_QUESTIONTABLE_TAG +
                "(id integer primary key ," +
                "tagname text," +
                "courseid int ," +
                "questionnum int);";*/

        db.execSQL(DataMessageVo.userinfom);
        db.execSQL(DataMessageVo.look);
        db.execSQL(DataMessageVo.error);
        db.execSQL(DataMessageVo.videolook);
        db.execSQL(DataMessageVo.delete);
        db.execSQL(DataMessageVo.question);
        db.execSQL(DataMessageVo.questionChapter);
        db.execSQL(DataMessageVo.questiontagrelagtion);
        db.execSQL(DataMessageVo.tag);
//        db.execSQL(DataMessageVo.down);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
