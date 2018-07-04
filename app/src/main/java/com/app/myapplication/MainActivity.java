package com.app.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.app.myapplication.bottombar.BottomBar;
import com.app.myapplication.bottombar.CustomTabEntity;
import com.app.myapplication.bottombar.TabEntity;
import com.app.myapplication.ui.MainContract;
import com.app.myapplication.ui.MainFragment;
import com.app.myapplication.ui.PlaceHolderFragment;
import com.app.myapplication.ui.RegionFragment;
import com.app.myapplication.ui.ToggleDrawerEvent;
import com.common.base.BaseActivity;
import com.common.base.IBaseActivity;
import com.common.util.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity implements IBaseActivity, MainContract.View {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    @BindView(R.id.main_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.entrance_bar)
    BottomBar mBottomBar;
    @BindView(R.id.main_container)
    FrameLayout mFrameLayout;
    @BindView(R.id.main_nav_view)
    NavigationView mNavigationView;
    @Inject
    MainFragment mainFragment;
    @Inject
    RegionFragment regionFragment;

    private SupportFragment[] mFragments = new SupportFragment[4];
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles;
    private int[] mIconSelectIds = {
            R.drawable.ic_home_selected, R.drawable.ic_category_selected,
            R.drawable.ic_dynamic_selected, R.drawable.ic_communicate_selected};
    private int[] mIconUnselectIds = {
            R.drawable.ic_home_unselected, R.drawable.ic_category_unselected,
            R.drawable.ic_dynamic_unselected, R.drawable.ic_communicate_unselected};


    //aaaaa
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initInject() {
        App.getInstance().getActivityComponent().inject(this);
    }

    @Override
    public void initViewAndEvent() {
        StatusBarUtil.setColorForDrawerLayout(this, getResources().getColor(R.color.theme_color_primary), mFrameLayout);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        //隐藏NavigationView右侧滚动条
        NavigationMenuView navigationMenuView = (NavigationMenuView) mNavigationView.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }
        mTitles = getResources().getStringArray(R.array.main_sections);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mBottomBar.setTabEntities(mTabEntities);

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
//                TabSelectedEvent event = new TabSelectedEvent(position);
//                EventBus.getDefault().post(event);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments[FIRST] = mainFragment;
        mFragments[SECOND] = regionFragment;
        mFragments[THIRD] = new PlaceHolderFragment();
        mFragments[FOURTH] =new PlaceHolderFragment();
        loadMultipleRootFragment(R.id.main_container, 0,
                mFragments[FIRST],
                mFragments[SECOND],
                mFragments[THIRD],
                mFragments[FOURTH]);
    }

    /**
     * DrawerLayout侧滑菜单开关
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ToggleDrawerEvent event) {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

}
