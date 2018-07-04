package com.app.myapplication.ui;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.app.myapplication.App;
import com.app.myapplication.R;
import com.common.base.BaseFragment;
import com.common.base.BaseMvpFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;

/**
 * Created by Android_ZzT on 17/7/6.
 */

public class RegionFragment extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initInject() {
        App.getInstance().getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewAndEvent() {

    }
}
