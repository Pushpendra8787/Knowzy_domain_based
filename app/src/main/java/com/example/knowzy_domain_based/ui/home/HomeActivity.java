package com.example.knowzy_domain_based.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knowzy_domain_based.R;
import com.example.knowzy_domain_based.ui.auth.LoginActivity;
import com.example.knowzy_domain_based.ui.emergency.EmergencyActivity;
import com.example.knowzy_domain_based.ui.home.adapter.RecentAdapter;
import com.example.knowzy_domain_based.ui.home.model.RecentCase;
import com.example.knowzy_domain_based.ui.input.InputActivity;
import com.example.knowzy_domain_based.ui.profile.ProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Button btnStart, btnEmergency;
    private ImageView btnMenu, btnNotification, btnProfile;

    private RecyclerView rvRecent;
    private TextView tvConfidence;
    private ProgressBar progressConfidence;

    private Animation pressAnim, releaseAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        initAnimations();
        setupRecycler();
        setupClickListeners();
        setupDummyStatus();
    }

    private void initViews() {
        btnStart = findViewById(R.id.btnStart);
        btnEmergency = findViewById(R.id.btnEmergency);

        btnMenu = findViewById(R.id.btnMenu);
        btnNotification = findViewById(R.id.btnNotification);
        btnProfile = findViewById(R.id.btnProfile);

        rvRecent = findViewById(R.id.rvRecent);

        tvConfidence = findViewById(R.id.tvConfidence);
        progressConfidence = findViewById(R.id.progressConfidence);
    }

    private void initAnimations() {
        pressAnim = AnimationUtils.loadAnimation(this, R.anim.scale_press);
        releaseAnim = AnimationUtils.loadAnimation(this, R.anim.scale_release);
    }

    private void setupRecycler() {

        rvRecent.setLayoutManager(new LinearLayoutManager(this));

        List<RecentCase> list = new ArrayList<>();

        list.add(new RecentCase("Harassment Case", "Completed • 92%", "2h ago"));
        list.add(new RecentCase("Traffic Issue", "In Progress • 60%", "5h ago"));
        list.add(new RecentCase("Property Dispute", "Completed • 88%", "1d ago"));
        list.add(new RecentCase("Online Fraud", "Pending", "2d ago"));

        RecentAdapter adapter = new RecentAdapter(list);
        rvRecent.setAdapter(adapter);
    }

    private void setupClickListeners() {

        applyTouchEffect(btnStart, InputActivity.class);
        applyTouchEffect(btnEmergency, EmergencyActivity.class);

        findViewById(R.id.btnVoice).setOnClickListener(v -> {
            // TODO Voice
        });

        findViewById(R.id.btnText).setOnClickListener(v ->
                startActivity(new Intent(this, InputActivity.class)));

        findViewById(R.id.btnKnowRights).setOnClickListener(v -> {});
        findViewById(R.id.btnLegalHelp).setOnClickListener(v -> {});
        findViewById(R.id.btnHelpline).setOnClickListener(v -> {});
        findViewById(R.id.btnNearby).setOnClickListener(v -> {});

        btnMenu.setOnClickListener(v -> openMenu());
        btnNotification.setOnClickListener(v -> openNotifications());
        btnProfile.setOnClickListener(v -> openProfile());
    }

    private void setupDummyStatus() {
        int confidence = 82;
        progressConfidence.setProgress(confidence);
        tvConfidence.setText(confidence + "%");
    }

    private void applyTouchEffect(View view, Class<?> destination) {

        view.setOnTouchListener((v, event) -> {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    v.startAnimation(pressAnim);
                    return true;

                case MotionEvent.ACTION_UP:
                    v.startAnimation(releaseAnim);

                    v.postDelayed(() ->
                            startActivity(new Intent(HomeActivity.this, destination)), 100);

                    return true;

                case MotionEvent.ACTION_CANCEL:
                    v.startAnimation(releaseAnim);
                    return true;
            }

            return false;
        });
    }

    private void openMenu() {
        // Next: Navigation Drawer
    }

    private void openNotifications() {
        // Next
    }

    private void openProfile() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}