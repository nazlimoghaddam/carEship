package com.example.careshipapp.user_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.careshipapp.R;
import com.google.android.material.button.MaterialButton;

public class MainActivityCreateAccount extends AppCompatActivity {


    EditText username, createPassword, passwordReentry, postAddress, zipCode;

    MaterialButton createAccountButton;
    DBHelperClass database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create_account);

        username = (EditText) findViewById(R.id.Username);
        createPassword = (EditText) findViewById(R.id.CreatePassword);
        passwordReentry = (EditText) findViewById(R.id.passwordReentry);

        postAddress = (EditText) findViewById(R.id.postAddress);
        zipCode = (EditText) findViewById(R.id.zipCode);

        createAccountButton = (MaterialButton) findViewById(R.id.CreateAccountButton);
        database = new DBHelperClass(this);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString();
                String passwrd = createPassword.getText().toString();
                String repeatPassword = passwordReentry.getText().toString();

                String address = postAddress.getText().toString();
                String code = zipCode.getText().toString();

                if(email.isEmpty() || passwrd.isEmpty() || repeatPassword.isEmpty() || code.isEmpty() || address.isEmpty()){
                    Toast.makeText(MainActivityCreateAccount.this, "Please enter non-empty values.", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(MainActivityCreateAccount.this,"please enter a valid email.",Toast.LENGTH_SHORT).show();
                }
                else if (zipCode.length() != 5)
                {
                    zipCode.setError("Please enter your 5 digit post code");
                    zipCode.requestFocus();
                }


                if(email.isEmpty() || passwrd.isEmpty() || repeatPassword.isEmpty()){
                    Toast.makeText(MainActivityCreateAccount.this, "Please enter non-empty values.", Toast.LENGTH_SHORT).show();
                }

                else if(passwrd.equals(repeatPassword)) {
                    Boolean userCheck = database.userExistsCheck(email, passwrd);
                    Boolean usernameCheck = database.usernameExistsCheck(email);

                    if(userCheck == false && usernameCheck == false){

                        database.insertData(email, passwrd,address, code);
                    }
                    Toast.makeText(MainActivityCreateAccount.this, "Account created successfully.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivityCreateAccount.this, MainActivityLoginPage.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivityCreateAccount.this, "Username or/and password are taken, please try again.", Toast.LENGTH_SHORT).show();
                }

                {
                    Toast.makeText(MainActivityCreateAccount.this, "The re-entered password is incorrect.", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}