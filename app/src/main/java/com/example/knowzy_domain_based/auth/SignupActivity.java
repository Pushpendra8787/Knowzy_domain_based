package com.example.knowzy_domain_based.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.knowzy_domain_based.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etEmail, etPassword;
    Button btnSignup;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignup = findViewById(R.id.btnSignup);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnSignup.setOnClickListener(v -> {

            String first = etFirstName.getText().toString().trim();
            String last = etLastName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            // VALIDATION
            if (TextUtils.isEmpty(first)) {
                etFirstName.setError("Enter First Name");
                return;
            }

            if (TextUtils.isEmpty(last)) {
                etLastName.setError("Enter Last Name");
                return;
            }

            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Enter Email");
                return;
            }

            if (TextUtils.isEmpty(pass) || pass.length() < 6) {
                etPassword.setError("Min 6 characters");
                return;
            }

            btnSignup.setEnabled(false);

            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();

                            if (user != null) {

                                // 🔹 EMAIL VERIFY SEND
                                user.sendEmailVerification();

                                String uid = user.getUid();

                                // 🔹 SAVE DATA
                                Map<String, Object> userMap = new HashMap<>();
                                userMap.put("firstName", first);
                                userMap.put("lastName", last);
                                userMap.put("email", email);

                                db.collection("Users").document(uid)
                                        .set(userMap)
                                        .addOnSuccessListener(unused -> {

                                            // ✅ BUTTON TEXT CHANGE (MAIN FEATURE)
                                            btnSignup.setText("Verify Email & Login");

                                            // OPTIONAL: disable button
                                            btnSignup.setEnabled(false);

                                            // ✅ TOAST
                                            Toast.makeText(this,
                                                    "Account Created! Check Gmail to verify.",
                                                    Toast.LENGTH_LONG).show();

                                            // 🔁 AUTO REDIRECT AFTER 2 SEC
                                            btnSignup.postDelayed(() -> {
                                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                                finish();
                                            }, 2000);

                                        })
                                        .addOnFailureListener(e -> {
                                            btnSignup.setEnabled(true);
                                            Toast.makeText(this,
                                                    "Firestore Error: " + e.getMessage(),
                                                    Toast.LENGTH_LONG).show();
                                        });
                            }

                        } else {
                            btnSignup.setEnabled(true);
                            Toast.makeText(this,
                                    "Signup Failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}