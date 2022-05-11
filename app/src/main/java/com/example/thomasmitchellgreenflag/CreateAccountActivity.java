package com.example.thomasmitchellgreenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class CreateAccountActivity extends AppCompatActivity {

    ImageView btnBackButton;
    static final Pattern PASSWORD_PATTERN =
        Pattern.compile(
                "^" +               // start of string
                "(?=\\S+$)" +       // no white space
                ".{8,}" +           // at least 8 characters
                "(?=.*[a-z])" +     // at least one lower case
                "(?=.*[A-Z])" +     // at least one upper case
                "(?=.*[0-9])"
        );
    EditText email;
    EditText password1;
    EditText password2;

    TextView nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        btnBackButton = findViewById(R.id.btn_back_button);
        email = findViewById(R.id.et_email_textbox);
        password1 = findViewById(R.id.et_create_password);
        password2 = findViewById(R.id.et_repeat_password);

        nextButton = findViewById(R.id.btn_next);

        btnBackButton.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(CreateAccountActivity.this, MainActivity.class);
            startActivity(intent);
        });

        /**
         * this onClick doesn't work!! *****************************************************
         */
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmInputs(view);
            }
        });
    }

    protected boolean validateEmail() {

        // extract input from EditText
        String emailInput = email.getText().toString().trim();

        // check if the email field is empty
        if (emailInput.isEmpty()) {
            /**
             * code here to display empty email field error message (via TextView)
             * also make email EditText borders go red
             */
            return false;
        }

        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            /**
             * code here to display email address not valid error message (via TextView)
             * also make email EditText borders go red
             */
            return false;
        }

        else {
            /**
             * make borders of email EditText go green and show green tick
             */
            return true;
        }
    }

    protected boolean validatePassword() {
        String passwordInput = password1.getText().toString().trim();

        // check if password field is empty
        if (passwordInput.isEmpty()) {
            /**
             * code here for empty field error message
             * also make borders go red
             */
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            /**
             * code here for error message saying password doesn't meet requirements
             * also make borders go red
             */
            return false;
        }
        else {
            /**
             * make borders go green and show green tick
             */
            return true;
        }
    }

    protected boolean validatePasswordsMatch() {
        if (validatePassword()) {
            String passwordInput = password1.getText().toString().trim();
            String passwordRepeatedInput = password2.getText().toString().trim();

            if (passwordInput != passwordRepeatedInput) {
                /**
                 * code to display error message saying passwords don't match
                 */
                return false;
            }
            else {
                /**
                 * make borders go green and show green tick
                 */
                return true;
            }
        }
        else {
            return false;
        }
    }

    public void confirmInputs(View v) {
        if (!validateEmail() || !validatePassword() || !validatePasswordsMatch()) {
            return;
        }

        // all inputs are valid, so display toast message.
        Toast.makeText(this, "Account successfully created", Toast.LENGTH_SHORT).show();
    }
}