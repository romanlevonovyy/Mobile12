package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        auth = FirebaseAuth.getInstance();
        emailField = findViewById(R.id.signInEmailAddress);
        passwordField = findViewById(R.id.signInPassword);

        findViewById(R.id.signInButton).setOnClickListener( view -> {
            final String email = emailField.getText().toString();
            final String password = passwordField.getText().toString();

            signIn(email,password);
        });

        findViewById(R.id.signUpLink).setOnClickListener(view -> {
            final Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void signIn(final String email, final String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            forSignInSuccess();
                        } else {
                            forSignInError();
                        }
                });
    }

    private void forSignInSuccess(){
        final Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    private void forSignInError(){
        Toast.makeText(SignInActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();
    }
}