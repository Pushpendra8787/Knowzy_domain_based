package com.example.knowzy_domain_based.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.knowzy_domain_based.R;
import com.example.knowzy_domain_based.ui.auth.LoginActivity;
import com.example.knowzy_domain_based.ui.home.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 1200;

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable navigationTask = this::routeUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler.postDelayed(navigationTask, SPLASH_DELAY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 🔥 Prevent memory leaks / duplicate navigation
        handler.removeCallbacks(navigationTask);
    }

    private void routeUser() {

        if (isFinishing() || isDestroyed()) return;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Class<?> destination = getDestination(user);

        Intent intent = new Intent(this, destination);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish();
    }

    private Class<?> getDestination(FirebaseUser user) {

        if (user != null && user.isEmailVerified()) {
            return HomeActivity.class;
        } else {
            return LoginActivity.class;
        }
    }
}