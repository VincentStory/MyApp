package com.app.myapplication.di.compnent;


import com.app.myapplication.di.module.ApiModule;
import com.app.myapplication.di.scope.GlobalApis;
import com.common.app.AppComponent;

import dagger.Component;

/**
 * Created by Android_ZzT on 17/6/15.
 */

@Component(dependencies = AppComponent.class, modules = ApiModule.class)
@GlobalApis
public interface ApiComponent {


}
