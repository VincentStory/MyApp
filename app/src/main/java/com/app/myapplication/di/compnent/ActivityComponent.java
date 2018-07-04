package com.app.myapplication.di.compnent;



import com.app.myapplication.MainActivity;
import com.app.myapplication.di.module.ActivityModule;
import com.app.myapplication.di.module.PageModule;
import com.app.myapplication.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by jiayiyang on 17/3/23.
 */

@Component(dependencies = {ApiComponent.class}, modules = {ActivityModule.class, PageModule.class})
@PerActivity
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
