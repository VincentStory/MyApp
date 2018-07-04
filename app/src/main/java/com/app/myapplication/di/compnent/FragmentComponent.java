package com.app.myapplication.di.compnent;


import com.app.myapplication.di.module.FragmentModule;
import com.app.myapplication.di.module.PageModule;
import com.app.myapplication.di.scope.PerFragment;
import com.app.myapplication.ui.MainFragment;
import com.app.myapplication.ui.RegionFragment;

import dagger.Component;

/**
 * Created by jiayiyang on 17/4/14.
 */

@Component(dependencies = ApiComponent.class, modules = {FragmentModule.class, PageModule.class})
@PerFragment
public interface FragmentComponent {
    void inject(MainFragment mainFragment);
    void inject(RegionFragment mainFragment);
}
