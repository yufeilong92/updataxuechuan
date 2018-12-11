package com.xuechuan.xcedu.ui.bank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuechuan.xcedu.R;
import com.xuechuan.xcedu.base.BaseActivity;
import com.xuechuan.xcedu.fragment.GmReadFragment;
import com.xuechuan.xcedu.sqlitedb.TagSqliteHelp;
import com.xuechuan.xcedu.vo.SqliteVo.TagSqliteVo;
import com.xuechuan.xcedu.weight.ReaderViewPager;
import com.xuechuan.xcedu.weight.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: TextActivity
 * @Package com.xuechuan.xcedu.ui.bank
 * @Description: 新做题界面
 * @author: L-BackPacker
 * @date: 2018.12.05 下午 3:21
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018.12.05
 */
public class NewTextActivity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private ReaderViewPager mReaderViewPager;
    private ImageView mShadowView;
    private Button mBtPre;
    private Button mBtNext;
    private TextView mBtLoadAnwer;
    private RecyclerView mList;
    private LinearLayout mDragView;
    private SlidingUpPanelLayout mSlidingLayout;
    private List<String> lists;

    /*    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_text);
            initView();
        }*/
    private static String PARAMT_KEY = "com.xuechuan.xcedu.ui.bank.paramt_key";
    private static String PARAMT1_KEY = "com.xuechuan.xcedu.ui.bank.paramt_key1";
    private int mCourseid;

    public static Intent start_Intent(Context context, int paramt, String paramt1) {
        Intent intent = new Intent(context, NewTextActivity.class);
        intent.putExtra(PARAMT_KEY, paramt);
        intent.putExtra(PARAMT1_KEY, paramt);
        return intent;
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_new_text);
        if (getIntent()!=null){
            mCourseid = getIntent().getIntExtra(PARAMT_KEY, 0);
        }
        initView();
        lists = getLists();
        findAllSkillData();
        initReadViewPager();

    }

    private void findAllSkillData() {
        TagSqliteHelp help = TagSqliteHelp.get_Instance(mContext);
        help.initOpenUserInfom();
        ArrayList<TagSqliteVo> tagSqliteVos = help.finTagAllWithCourserid(mCourseid);


    }

    private void initReadViewPager() {
        mReaderViewPager.setAdapter(new GmFragmentAdpater(getSupportFragmentManager(), mContext, lists));
        mReaderViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mShadowView.setTranslationX(mReaderViewPager.getWidth() - positionOffsetPixels

                );
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
    }

    private void initView() {
        mContext = this;
        mReaderViewPager = (ReaderViewPager) findViewById(R.id.readerViewPager);
        mShadowView = (ImageView) findViewById(R.id.shadowView);
        mBtPre = (Button) findViewById(R.id.bt_pre);
        mBtNext = (Button) findViewById(R.id.bt_next);
        mBtLoadAnwer = (TextView) findViewById(R.id.bt_load_anwer);
        mList = (RecyclerView) findViewById(R.id.list);
        mDragView = (LinearLayout) findViewById(R.id.dragView);
        mSlidingLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mBtPre.setOnClickListener(this);
        mBtNext.setOnClickListener(this);
        mDragView.setOnClickListener(this);
    }

    public List<String> getLists() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            list.add("当前页码为=" + i);
        }

        return list;
    }

    private class GmFragmentAdpater extends FragmentPagerAdapter {

        private Context mContext;
        private List<?> mListDatas;

        public GmFragmentAdpater(FragmentManager fm, Context mContext, List<?> mListDatas) {
            super(fm);
            this.mListDatas = mListDatas;
            this.mContext = mContext;
        }

        @Override
        public Fragment getItem(int position) {
            return GmReadFragment.newInstance(String.valueOf(mListDatas.get(position)));
        }

        @Override
        public int getCount() {
            return mListDatas.size();
        }
    }
}
