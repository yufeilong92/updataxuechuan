package com.xuechuan.xcedu.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.xuechuan.xcedu.base.DataMessageVo;
import com.xuechuan.xcedu.mvp.contract.BankHomeGradeContract;
import com.xuechuan.xcedu.utils.DbQueryUtil;
import com.xuechuan.xcedu.utils.EncryptionUtil;
import com.xuechuan.xcedu.utils.StringUtil;
import com.xuechuan.xcedu.vo.SqliteVo.UserInfomSqliteVo;
import com.xuechuan.xcedu.vo.UserInfomVo;

import java.io.File;
import java.util.ArrayList;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.sqlitedb
 * @Description: 个人信息表操作类
 * @author: L-BackPacker
 * @date: 2018.12.10 上午 11:04
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class UserInfomDbHelp {
    private static volatile UserInfomDbHelp _singleton;
    private Context mContext;
    private static String TAG = "【" + UserInfomDbHelp.class + "】==";
    private final DbQueryUtil mQueryUtil;
    private SQLiteDatabase mSqLiteDatabase;

    private UserInfomDbHelp(Context context) {
        this.mContext = context;
        mQueryUtil = DbQueryUtil.get_Instance();
    }

    public static UserInfomDbHelp get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (UserInfomDbHelp.class) {
                if (_singleton == null) {
                    _singleton = new UserInfomDbHelp(context);
                }
            }
        }
        return _singleton;
    }

    public void initOpenUserInfom() {
        DbPathUitl instance = DbPathUitl.get_Instance(mContext);
        String dbPath = instance.getDbPath();
        if (StringUtil.isEmpty(dbPath)) return;
        String concat = dbPath.concat(DataMessageVo.USER_INFOM_DATABASE_NAME);
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


    public UserInfomSqliteVo findUserInfomVo() {
        if (empty()) return null;
        Cursor query = mSqLiteDatabase.query(DataMessageVo.USER_INFOM_TABLE_USER, null, null, null, null
                , null, null);
        mQueryUtil.initCursor(query);
        UserInfomSqliteVo vo = new UserInfomSqliteVo();
        if (query.moveToFirst()) {
            int id = mQueryUtil.queryInt("id");
            int id1 = mQueryUtil.queryInt("saffid");
            int copy = mQueryUtil.queryInt("copy");
            String userinfomvo = mQueryUtil.queryString("userinfomvo");
            String moid = mQueryUtil.queryString("moid");
            String skillbook = mQueryUtil.queryString("skillbook");
            String colligatebook = mQueryUtil.queryString("colligatebook");
            String casebook = mQueryUtil.queryString("casebook");
            String showDayOrNight = mQueryUtil.queryString("showDayOrNight");
            String userNextGo = mQueryUtil.queryString("userNextGo");
            String token = mQueryUtil.queryString("token");
            String userphone = mQueryUtil.queryString("userphone");
            String username = mQueryUtil.queryString("username");
            vo.setId(id);
            vo.setSaffid(id1);
            vo.setCopy(copy);
            vo.setUserinfome(userinfomvo);
            vo.setMoid(moid);
            vo.setSkillbook(skillbook);
            vo.setColligatebook(colligatebook);
            vo.setCasebook(casebook);
            vo.setShowDayOrNight(showDayOrNight);
            vo.setUserNextGo(userNextGo);
            vo.setToken(token);
            vo.setUserPhone(userphone);
            vo.setUserName(username);
        }
        return vo;
    }

    public void addUserInfom(UserInfomSqliteVo vo) {
        if (empty()) return;
        UserInfomSqliteVo userInfomVo = findUserInfomVo();
        if (StringUtil.isEmpty(userInfomVo.getUserinfome())) {
            String sql = "insert into " + DataMessageVo.USER_INFOM_TABLE_USER +
                    "(saffid,copy,userinfomvo,moid,skillbook,colligatebook,casebook,showDayOrNight,userNextGo," +
                    "token,userphone,username) values (" + vo.getSaffid() + "," +
                    vo.getCopy() + ",'" +
                    vo.getUserinfome() + "','" +
                    vo.getMoid() + "','" +
                    vo.getSkillbook() + "','" +
                    vo.getColligatebook() + "','" +
                    vo.getCasebook() + "','" +
                    vo.getShowDayOrNight() + "','" +
                    vo.getUserNextGo() + "','" +
                    vo.getToken() + "','" +
                    vo.getUserPhone() + "','" +
                    vo.getUserName() + "');";
            mSqLiteDatabase.execSQL(sql);
        } else {
            String sql1 = "update " + DataMessageVo.USER_INFOM_TABLE_USER +
                    " set saffid=" + vo.getSaffid() + ",copy=" + vo.getCopy() + ",userinfomvo='" +
                    isEmptyDB(userInfomVo.getUserinfome(), vo.getUserinfome()) + "',moid='" +
                    isEmptyDB(userInfomVo.getMoid(), vo.getMoid()) + "',skillbook='"
                    + isEmptyDB(userInfomVo.getSkillbook(), vo.getSkillbook()) + "',colligatebook='" +
                    isEmptyDB(userInfomVo.getColligatebook(), vo.getColligatebook()) + "',casebook='" +
                    isEmptyDB(userInfomVo.getCasebook(), vo.getCasebook()) + "',showDayOrNight='" +
                    isEmptyDB(userInfomVo.getShowDayOrNight(), vo.getShowDayOrNight()) + "',userNextGo='" +
                    isEmptyDB(userInfomVo.getUserNextGo(), vo.getUserNextGo()) + "',token='" +
                    isEmptyDB(userInfomVo.getToken(), vo.getToken()) + "',userphone='" +
                    isEmptyDB(userInfomVo.getUserPhone(), vo.getUserPhone()) + "',username='" +
                    isEmptyDB(userInfomVo.getUserName(), vo.getUserName()) + "'" +
                    " where id=" + userInfomVo.getId() +
                    " );";
            mSqLiteDatabase.execSQL(sql1);
        }

    }

    /**
     * @param vo          要更新的数据
     * @param userInfomVo 源数据库
     * @return
     */
    private ContentValues setValues(UserInfomSqliteVo vo, UserInfomSqliteVo userInfomVo) {
        ContentValues v = new ContentValues();
        v.put("saffid", vo.getSaffid());
        v.put("copy", vo.getCopy());
        v.put("userinfomvo", isEmptyDB(userInfomVo.getUserinfome(), vo.getUserinfome()));
        v.put("moid", isEmptyDB(userInfomVo.getMoid(), vo.getMoid()));
        v.put("skillbook", isEmptyDB(userInfomVo.getSkillbook(), vo.getSkillbook()));
        v.put("colligatebook", isEmptyDB(userInfomVo.getColligatebook(), vo.getColligatebook()));
        v.put("casebook", isEmptyDB(userInfomVo.getCasebook(), vo.getCasebook()));
        v.put("showDayOrNight", isEmptyDB(userInfomVo.getShowDayOrNight(), vo.getShowDayOrNight()));
        v.put("userNextGo", isEmptyDB(userInfomVo.getUserNextGo(), vo.getUserNextGo()));
        v.put("token", isEmptyDB(userInfomVo.getToken(), vo.getToken()));
        v.put("userphone", isEmptyDB(userInfomVo.getUserPhone(), vo.getUserPhone()));
        v.put("username", isEmptyDB(userInfomVo.getUserName(), vo.getUserName()));
        return v;
    }

    private String isEmptyDB(String paramer, String paramers) {
        return StringUtil.isEmpty(paramers) ? paramer : paramers;
    }

    public void UpDataUserToken(UserInfomVo.DataBean vo) {
        if (empty()) return;
        String s = EncryptionUtil.getInstance().putTContent(vo);
        if (StringUtil.isEmpty(s)) {
            s = null;
        }
        UserInfomSqliteVo infomVo = findUserInfomVo();
        String sql = "update " + DataMessageVo.USER_INFOM_TABLE_USER +
                " set userinfomvo='" + s
                + "', token='" + vo.getUser().getToken() +
                "'  where id=" + infomVo.getId() + ";";
        mSqLiteDatabase.execSQL(sql);
    }

    /**
     * 更新版本号
     *
     * @param version
     */
    public void upDateAddQuestion(int version) {
        if (empty()) return;
        UserInfomSqliteVo vo = findUserInfomVo();
        String sql = "update " + DataMessageVo.USER_INFOM_TABLE_USER +
                " set questionversion=" + version + " where id=" +
                vo.getId();
        mSqLiteDatabase.execSQL(sql);
    }

    private boolean empty() {
        if (mSqLiteDatabase == null)
            return true;
        if (mSqLiteDatabase.isReadOnly())
            return true;
        return false;
    }


}
