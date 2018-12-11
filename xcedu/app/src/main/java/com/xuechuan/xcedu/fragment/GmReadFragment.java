package com.xuechuan.xcedu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuechuan.xcedu.R;
import com.xuechuan.xcedu.base.BaseFragment;
import com.xuechuan.xcedu.utils.GmReadColorManger;
import com.xuechuan.xcedu.utils.Subsciber;
import com.xuechuan.xcedu.utils.TestObserver;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: ReadFragment
 * @Package com.xuechuan.xcedu.fragment
 * @Description: 通用做题界面
 * @author: L-BackPacker
 * @date: 2018.12.05 下午 5:04
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018.12.05
 */

public class GmReadFragment extends BaseFragment implements TestObserver {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View view;
    private String mTitle;
    private TextView mTvContent;
    private Context mContext;
    private GmReadColorManger mGmReadColorManger;

    public static GmReadFragment newInstance(String path) {
        GmReadFragment fragment = new GmReadFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, path);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_PARAM1);
        }
    }

/*    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bant_text_layout, container, false);
        initView(view);
        return view;
    }*/

    @Override
    protected int initInflateView() {
        return R.layout.fragment_bant_text_layout;
    }

    @Override
    protected void initCreateView(View view, Bundle savedInstanceState) {
        initView(view);
        registerOberovce();
    }

    private void registerOberovce() {
        mGmReadColorManger = GmReadColorManger.get_Instance(mContext);
        mGmReadColorManger.setGmBgColor(GmReadColorManger.DAYTIME);
        Subsciber subsciber = new GmReadFragment().subsciber();
        subsciber.attch(this);
        subsciber.notifyChanger();
    }

    private void initView(View view) {
        mContext = getActivity();

    }

    /**
     * 订阅者
     *
     * @return
     */
    @Override
    public Subsciber subsciber() {
        return new Subsciber();
    }

    /**
     * 具体观察者
     * 用于更新布局颜色
     */
    @Override
    public void refresh() {


    }
}
