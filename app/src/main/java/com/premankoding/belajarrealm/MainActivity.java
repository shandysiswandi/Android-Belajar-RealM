package com.premankoding.belajarrealm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Realm realm;

        Button button = findViewById(R.id.btn);
        final TextView textView = findViewById(R.id.tv);
        final EditText et1, et2;
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        realm = Realm.getDefaultInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(@NonNull Realm realm) {
                        ModelApp modelApp = new ModelApp();
                        modelApp.setName(et1.getText().toString());
                        modelApp.setAge(Integer.parseInt(et2.getText().toString()));
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        RealmResults<ModelApp> modelApps = realm.where(ModelApp.class).findAll();
                        String result = "";
                        for (ModelApp modelApp: modelApps){
                            result += modelApp.toString();
                        }

                        Log.d("TAG", "onSuccess: "+result);

                        textView.setText(result);
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(@NonNull Throwable error) {
                        Log.d("TAG", "OnError: "+ error);
                    }
                });
            }
        });
    }
}



























