package com.github.tntkhang.mvptemplate;

import android.app.Application;

import com.github.tntkhang.mvptemplate.di.AppComponent;
import com.github.tntkhang.mvptemplate.di.DaggerAppComponent;
import com.github.tntkhang.mvptemplate.networking.NetworkModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

public class BaseApplication extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeDependencies();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());

            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this);
        }
    }

    private void initializeDependencies() {
        appComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
