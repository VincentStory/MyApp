package com.app.myapplication.di.module;


import com.app.myapplication.di.scope.PerActivity;
import com.app.myapplication.ui.MainFragment;
import com.app.myapplication.ui.RegionFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiayiyang on 17/4/14.
 */

@Module
public class PageModule {
    //main
    @Provides
    @PerActivity
    MainFragment provideMainFragment() {
        return new MainFragment();
    }

   //main
    @Provides
    @PerActivity
    RegionFragment provideRegionFragment() {
        return new RegionFragment();
    }


}
