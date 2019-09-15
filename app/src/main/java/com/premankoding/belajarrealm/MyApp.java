package com.premankoding.belajarrealm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);
    }
}
