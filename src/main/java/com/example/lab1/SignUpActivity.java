package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity {

    public static final String PHONE_PATTERN = "[+]380[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]";
    public static final String PASSWORD_PATTERN = ".{8,}";
    public static final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private FirebaseAuth auth;
    private EditText emailField;
    private EditText nameField;
    private EditText phoneField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        emailField = findViewById(R.id.signUpEmailAddress);
        nameField = findViewById(R.id.signUpPersonName);
        phoneField = findViewById(R.id.signUpPhone);
        passwordField = findViewById(R.id.signUpPassword);

        findViewById(R.id.signUpPhone).setOnClickListener(view -> {
            final String name = nameField.getText().toString();

            isValidName(name);
        });
        findViewById(R.id.signUpEmailAddress).setOnClickListener(view -> {
            final String phone = phoneField.getText().toString();

            isValidPhone(phone);
        });
        findViewById(R.id.signUpPassword).setOnClickListener(view -> {
            final String email = emailField.getText().toString();

            isValidEmail(email);
        });
        findViewById(R.id.signUpButton).setOnClickListener(view -> {
            final String email = emailField.getText().toString();
            final String name = nameField.getText().toString();
            final String phone = phoneField.getText().toString();
            final String password = passwordField.getText().toString();

            isValidName(name);
            isValidPhone(phone);
            isValidEmail(email);
            isValidPassword(password);
            isValidForSignUp(email,name,phone,password);
        });
        findViewById(R.id.signInLink).setOnClickListener(view -> {
            this.onBackPressed();
        });
    }

    private void isValidEmail(final String email){
        if (email.isEmpty()){
            emailField.setError(getString(R.string.enter_email));
            emailField.requestFocus();
        }if(!email.matches(EMAIL_PATTERN)) {
            Toast.makeText(SignUpActivity.this, getString(R.string.enter_email_valid),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void isValidName(final String name){
        if(name.isEmpty()){
            nameField.setError(getString(R.string.enter_name));
            nameField.requestFocus();
        }
    }

    private void isValidPhone(final String phone){
        if(phone.isEmpty()){
            phoneField.setError(getString(R.string.enter_phone));
            phoneField.requestFocus();
        }if(!phone.matches(PHONE_PATTERN)) {
            Toast.makeText(SignUpActivity.this, getString(R.string.enter_phone_valid),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void isValidPassword(final String password){
        if(password.isEmpty()){
            passwordField.setError(getString(R.string.enter_password));
            passwordField.requestFocus();
        }if(!password.matches(PASSWORD_PATTERN)){
            Toast.makeText(SignUpActivity.this, getString(R.string.enter_password_valid),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void isValidForSignUp(final String email,final String name,final String phone, final String password){
        if(!(email.isEmpty() && password.isEmpty())){
            signUp(email,name,phone,password);
        }else {
            Toast.makeText(SignUpActivity.this, getString(R.string.wrong),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void signUp(final String email,final String name,final String phone, final String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(name).build();

                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(task1 -> {
                                    if (task.isSuccessful()) {
                                        forSignUpSuccess(email, password);
                                    }
                                });
                    } else {
                        forSignUpError();
                    }
                });
    }

    private void forSignUpSuccess(final String email, final String password){
        signIn(email, password);
    }

    private void forSignUpError(){
        Toast.makeText(SignUpActivity.this,"Error",Toast.LENGTH_LONG).show();
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
        Toast.makeText(SignUpActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();
    }
}