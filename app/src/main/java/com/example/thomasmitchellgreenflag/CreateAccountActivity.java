package com.example.thomasmitchellgreenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private static final Pattern PASSWORD_REGEX =
        Pattern.compile(
                "^" +               // start of string
                "(?=\\S+$)" +       // no white space
                ".{8,}" +           // at least 8 characters
                "(?=.*[a-z])" +     // at least one lower case
                "(?=.*[A-Z])" +     // at least one upper case
                "(?=.*[0-9])"
        );
    private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    EditText email;
    EditText password1;
    EditText password2;

    TextView emailErrorMessage;
    TextView passwordErrorMessage;
    TextView passwordErrorMessage2;

    TextView nextButton;

    private boolean emailAccepted;
    private boolean passwordAccepted;
    private boolean passwordsMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        btnBackButton = findViewById(R.id.btn_back_button);
        email = findViewById(R.id.et_email_textbox);
        password1 = findViewById(R.id.et_create_password);
        password2 = findViewById(R.id.et_repeat_password);

        nextButton = findViewById(R.id.btn_next);
        emailErrorMessage = findViewById(R.id.tv_email_already_has_account);
        passwordErrorMessage = findViewById(R.id.tv_password_invalid);
        passwordErrorMessage2 = findViewById(R.id.tv_passwords_do_not_match);

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
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Account successfully created!", Toast.LENGTH_LONG).show();
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                emailChanged();
                validateNext();
            }
        });

        password1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                passwordChanged();
                validateNext();
            }
        });

        password2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                passwordChanged();
                validateNext();
            }
        });

    }

    private void emailChanged() {
        String emailAddress = (String) email.getText().toString();

        if (emailAddress.matches(EMAIL_REGEX)){
            emailAccepted = true;

            email.setBackgroundResource(R.drawable.green_rectangle);

            Drawable tick = email.getContext().getResources().getDrawable(R.drawable.tick, null);
            email.setCompoundDrawablesWithIntrinsicBounds(null, null, tick, null);

            emailErrorMessage.setVisibility(View.VISIBLE);
        }
        else {
            emailAccepted = false;

            email.setBackgroundResource(R.drawable.red_rectangle_white_background);
            email.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

            emailErrorMessage.setVisibility(View.VISIBLE);
        }
    }

    private void passwordChanged() {
        String password = (String) password1.getText().toString();
        String passwordRepeated = (String) password1.getText().toString();

        passwordAccepted = true;

        if (!password.equals(passwordRepeated)) {
            passwordAccepted = false;

            passwordErrorMessage2.setVisibility(View.VISIBLE);
        }
        else {
            if (!password.matches(String.valueOf(PASSWORD_REGEX))) {

                passwordAccepted = false;

                password1.setBackgroundResource(R.drawable.red_rectangle_white_background);

                passwordErrorMessage.setVisibility(View.VISIBLE);
            }
        }

        if (password.equals(passwordRepeated)) {
            passwordErrorMessage2.setVisibility(View.INVISIBLE);

            password1.setBackgroundResource(R.drawable.green_rectangle);
            password2.setBackgroundResource(R.drawable.green_rectangle);

            Drawable tick = password1.getContext().getResources().getDrawable(R.drawable.tick, null);
            password1.setCompoundDrawablesWithIntrinsicBounds(null, null, tick, null);
            password2.setCompoundDrawablesWithIntrinsicBounds(null, null, tick, null);
        }
        else {
            passwordErrorMessage.setVisibility(View.VISIBLE);

            password1.setBackgroundResource(R.drawable.red_rectangle_white_background);
            password2.setBackgroundResource(R.drawable.red_rectangle_white_background);

            password1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            password2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    private void validateNext() {
        if (emailAccepted && passwordAccepted) {
            nextButton.setEnabled(true);
        }
        else {
            nextButton.setEnabled(false);
        }
    }






}