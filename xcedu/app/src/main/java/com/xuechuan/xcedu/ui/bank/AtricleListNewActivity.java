package com.xuechuan.xcedu.ui.bank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.xuechuan.xcedu.R;
import com.xuechuan.xcedu.base.BaseActivity;
import com.xuechuan.xcedu.sqlitedb.QuestionChapterSqliteHelp;
import com.xuechuan.xcedu.vo.ChildrenBeanVo;
import com.xuechuan.xcedu.vo.SkillTextVo;
import com.xuechuan.xcedu.vo.SqliteVo.QuestionChapterSqliteVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: AtricleListNewActivity
 * @Package com.xuechuan.xcedu.ui.bank
 * @Description: 章节目录
 * @author: L-BackPacker
 * @date: 2018.12.11 下午 4:51
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018.12.11
 */

public class AtricleListNewActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 科目id
     */
    public static final String COURSEID = "courseid";
    /**
     * 所属类型1,题库(练习题)；2,历年真题；3,独家密卷
     */
    public static final String MOLD = "mold";
    private String mCouersid;
    private RecyclerView mRlvNewtreeContent;
    private Context mContext;
    private String mMold;
    private ImageView mIvNetEmptyContent;


    /**
     * @param context
     * @param courseid 科目id
     * @return
     */
    public static Intent newInstance(Context context, String courseid, String mold) {
        Intent intent = new Intent(context, AtricleListActivity.class);
        intent.putExtra(COURSEID, courseid);
        intent.putExtra(MOLD, mold);
        return intent;
    }

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atricle_list_new);
        initView();
    }*/

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        if (getIntent() != null) {
            mCouersid = getIntent().getStringExtra(COURSEID);
            mMold = getIntent().getStringExtra(MOLD);

        }
        setContentView(R.layout.activity_atricle_list_new);
        initView();
        initData();
    }

    private void initData() {
        QuestionChapterSqliteHelp help = QuestionChapterSqliteHelp.get_Instance(mContext);
        help.initOpenUserInfom();
        ArrayList<QuestionChapterSqliteVo> all = help.findQuestionChapterItemAllWiteCoureseid(mCouersid, mMold, "0");

        if (all == null) {
            mRlvNewtreeContent.setVisibility(View.GONE);
            mIvNetEmptyContent.setVisibility(View.VISIBLE);
            return;
        } else {
            mRlvNewtreeContent.setVisibility(View.VISIBLE);
            mIvNetEmptyContent.setVisibility(View.GONE);
        }
        List<SkillTextVo.DatasBean> datas = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            SkillTextVo.DatasBean bean = new SkillTextVo.DatasBean();
            QuestionChapterSqliteVo v = all.get(i);
            bean.setId(v.getId());
            bean.setParentid(v.getParentid());
            bean.setQnum(v.getQuestionnum());
            bean.setTitle(v.getChaptername());
            ArrayList<QuestionChapterSqliteVo> chine = help.QueryCourseidZWmoidParenid(v.getParentid(), v.getMold());
            if (chine != null && !chine.isEmpty()) {//有章节
                bean.setRnum(chine.size());
                bean.setIsend(false);
                ArrayList<ChildrenBeanVo> vos = new ArrayList<ChildrenBeanVo>();
                for (int k = 0; k < chine.size(); k++) {
                    QuestionChapterSqliteVo sqliteVo = chine.get(k);
                    ChildrenBeanVo vo = new ChildrenBeanVo();
                    vo.setId(sqliteVo.getId());
                    vo.setParentid(sqliteVo.getParentid());
                    vo.setChildren(null);
                    vo.setIsend(true);
                    vo.setQnum(sqliteVo.getQuestionnum());
                    vo.setRnum(0);
                    vo.setTitle(sqliteVo.getChaptername());
                    vos.add(vo);
                }

            } else {//无章节
                bean.setRnum(0);
                bean.setIsend(true);
            }

            datas.add(bean);
        }
        Log.e("====", "initData: "+datas );
    }


    private void initView() {
        mContext = this;
        mRlvNewtreeContent = (RecyclerView) findViewById(R.id.rlv_newtree_content);
        mIvNetEmptyContent = (ImageView) findViewById(R.id.iv_net_empty_content);
        mIvNetEmptyContent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
