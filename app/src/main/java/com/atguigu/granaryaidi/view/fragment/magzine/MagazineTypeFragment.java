package com.atguigu.granaryaidi.view.fragment.magzine;

import android.widget.GridView;

import com.atguigu.granaryaidi.Base.BaseFragment;
import com.atguigu.granaryaidi.R;
import com.atguigu.granaryaidi.view.adapter.magzine.MagazineTypeAdapter;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/9.
 */

public class MagazineTypeFragment extends BaseFragment {

    @InjectView(R.id.gv_magazine_type)
    GridView gvMagazineType;
    private MagazineTypeAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.magazine_type_fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        adapter = new MagazineTypeAdapter(context);
        //设置适配器
        gvMagazineType.setAdapter(adapter);
        //添加数据
    }

    @Override
    protected void initListener() {

    }

}
