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
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUp extends AppCompatActivity {

    private EditText emailId;
    private EditText password;
    private EditText nameField;
    private EditText phoneField;
    private Button btnSignUp;
    private TextView tvSignIn;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = getApplicationEx().getAuth();
        nameField = findViewById(R.id.input_name);
        phoneField = findViewById(R.id.phone);
        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        tvSignIn = findViewById(R.id.sign_in_message);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(v -> {
            String email = emailId.getText().toString();
            String pwd = password.getText().toString();
            String name = nameField.getText().toString();
            String phone = phoneField.getText().toString();

            String emptyField = getString(R.string.empty);
            String emailPatt = getString(R.string.emailPattern);
            String passwordPatt = getString(R.string.passwordPattern);
            String phonePatt = getString(R.string.phonePattern);

            if (email.isEmpty()) {
                emailId.setError(emptyField);
                emailId.requestFocus();
            } else if (pwd.isEmpty()) {
                password.setError(emptyField);
                password.requestFocus();
            } else if (name.isEmpty()) {
                nameField.setError(emptyField);
                nameField.requestFocus();
            } else if (phone.isEmpty()) {
                phoneField.setError(emptyField);
                phoneField.requestFocus();
            } else if (!phone.matches(phonePatt)) {
                Toast.makeText(SignUp.this, "Phone must contain 13 symbols and start with +380",
                        Toast.LENGTH_LONG).show();
            } else if (!email.matches(emailPatt)) {
                Toast.makeText(SignUp.this, "Invalid email!",
                        Toast.LENGTH_SHORT).show();
            } else if (!pwd.matches(passwordPatt)) {
                Toast.makeText(SignUp.this, "Password must contain at least 8 symbols!",
                        Toast.LENGTH_SHORT).show();
            } else if (email.isEmpty() && pwd.isEmpty() && name.isEmpty() && phone.isEmpty()) {
                Toast.makeText(SignUp.this, "Empty fields", Toast.LENGTH_SHORT).show();
            } else {
                registerUser();
            }
        });

        tvSignIn.setOnClickListener(v -> {
            Intent i = new Intent(SignUp.this, SignIn.class);
            startActivity(i);
        });
    }

    private void registerUser() {
        auth.createUserWithEmailAndPassword(emailId.getText().toString(),
                password.getText().toString()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = auth.getCurrentUser();
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(nameField.getText().toString()).build();
                user.updateProfile(profileUpdates).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        startActivity(new Intent(SignUp.this, MainActivity.class));
                        Toast.makeText(SignUp.this,
                                "Registered successfully", Toast.LENGTH_LONG).show();
                    }
                });

            } else {
                Toast.makeText(SignUp.this,
                        task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private ApplicationEx getApplicationEx() {
        return ((ApplicationEx) getApplication());
    }
}
