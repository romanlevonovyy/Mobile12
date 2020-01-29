package com.vitalik.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    private EditText emailId;
    private EditText password;
    private Button btnSignIn;
    private TextView tvSignUp;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = getApplicationEx().getAuth();
        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        tvSignUp = findViewById(R.id.sign_up_message);
        btnSignIn = findViewById(R.id.btnSignIn);

        final String emptyField = getString(R.string.empty);
        final String emailPatt = getString(R.string.emailPattern);
        final String passwordPatt = getString(R.string.passwordPattern);

        mAuthStateListener = firebaseAuth -> {
            FirebaseUser mFirebaseUser = auth.getCurrentUser();
            if (mFirebaseUser != null) {
                Toast.makeText(SignIn.this, "You are logged in!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SignIn.this, MainActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(SignIn.this, "Please login!", Toast.LENGTH_SHORT).show();
            }
        };
        btnSignIn.setOnClickListener(v -> {
            String email = emailId.getText().toString();
            String pwd = password.getText().toString();
            if (email.isEmpty()) {
                emailId.setError(emptyField);
                emailId.requestFocus();
            } else if (pwd.isEmpty()) {
                password.setError(emptyField);
                password.requestFocus();
            } else if (!email.matches(emailPatt)) {
                Toast.makeText(SignIn.this, "Invalid email!",
                        Toast.LENGTH_SHORT).show();
            } else if (!pwd.matches(passwordPatt)) {
                Toast.makeText(SignIn.this, "Password must contain at least 8 symbols!",
                        Toast.LENGTH_SHORT).show();
            } else if (email.isEmpty() && pwd.isEmpty()) {
                Toast.makeText(SignIn.this, "Empty fields", Toast.LENGTH_SHORT).show();
            } else if (!(email.isEmpty() && pwd.isEmpty())) {
                auth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(SignIn.this, task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(SignIn.this, "Error!, Login again.", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intToHome = new Intent(SignIn.this, MainActivity.class);
                        startActivity(intToHome);
                    }
                });
            } else {
                Toast.makeText(SignIn.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
            }
        });

        tvSignUp.setOnClickListener(v -> {
            Intent intSignUp = new Intent(SignIn.this, SignUp.class);
            startActivity(intSignUp);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthStateListener);
    }

    private ApplicationEx getApplicationEx() {
        return ((ApplicationEx) getApplication());
    }
}
