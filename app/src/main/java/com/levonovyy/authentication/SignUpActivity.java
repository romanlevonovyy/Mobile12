package com.levonovyy.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {
    private EditText emailId;
    private EditText password;
    private EditText name;
    private EditText phoneNumber;
    private Button signUp;
    private TextView signIn;
    private FirebaseAuth mFirebaseAuth;
    private static Integer minLength = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();

        emailId = findViewById(R.id.sign_up_screen_email);
        password = findViewById(R.id.sign_up_screen_password);
        name = findViewById(R.id.sign_up_screen_name);
        phoneNumber = findViewById(R.id.sign_up_screen_phone);
        signUp = findViewById(R.id.sign_up_screen_button);
        signIn = findViewById(R.id.sign_up_screen_login);

        signUp.setOnClickListener(view -> {
            final String yourName = name.getText().toString().trim();
            final String phone = phoneNumber.getText().toString().trim();
            final String email = emailId.getText().toString().trim();
            final String pwd = password.getText().toString().trim();


            if (yourName.isEmpty()) {
                name.setError(getString(R.string.enter_name));
                name.requestFocus();
            } else if (phone.isEmpty()) {
                phoneNumber.setError(getString(R.string.enter_phone));
                phoneNumber.requestFocus();

            } else if (phone.length() != 10) {
                phoneNumber.setError(getString(R.string.input_error_phone_invalid));
                phoneNumber.requestFocus();
                return;
            } else if (email.isEmpty()) {
                emailId.setError(getString(R.string.enter_email));
                emailId.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailId.setError(getString(R.string.input_error_email_invalid));
                emailId.requestFocus();
                return;
            } else if (pwd.isEmpty()) {
                password.setError(getString(R.string.enter_pwd));
                password.requestFocus();
            } else if (password.length() < minLength) {
                password.setError(getString(R.string.minimum_length));
                password.requestFocus();
                return;
            } else if (!(email.isEmpty() && pwd.isEmpty())) {
                mFirebaseAuth.createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(SignUpActivity.this, task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "SignUp Unsuccessful",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest
                                .Builder().setDisplayName(yourName).build();
                        assert user != null;
                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(task1 -> {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(SignUpActivity
                                                .this, WelcomeActivity.class));
                                    }
                                });
                    }

                });
            } else {
                Toast.makeText(SignUpActivity.this, "Error Occurred!",
                        Toast.LENGTH_SHORT).show();
            }


        });

        signIn.setOnClickListener(view -> {
            Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP
                    | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(i);
        });


    }
}