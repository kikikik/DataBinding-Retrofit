package com.palmwifi.databindingdemo;

import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.palmwifi.databindingdemo.databinding.AcLoginBinding;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        AcLoginBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.ac_login);
//        AcLoginBinding binding = dataBinding.inflate(getLayoutInflater());
        TestBean user = new TestBean("account", "password");
        dataBinding.setUser(user);

        RxPermissions rxPermissions = new RxPermissions(LoginActivity.this);
        rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        Toast.makeText(LoginActivity.this, "同意权限", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "拒绝权限", Toast.LENGTH_SHORT).show();
                    }
                });

        findViewById(R.id.list).setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, ListActivity.class)));
    }

}
