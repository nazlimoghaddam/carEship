package com.example.careshipapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivityController extends AppCompatActivity {

   Button customerBtn;
   Button staffBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.user_activity_controller);

      customerBtn =(Button) findViewById(R.id.customerButton);

      staffBtn = (Button) findViewById(R.id.staffButton);

      customerBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
          openCustomerLogin();
          }
      });


      staffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStaffLogin();
            }
        });


    }

      public void openCustomerLogin() {
        Intent intent = new Intent(this,MainActivityLoginPage.class);
        startActivity(intent);
      }


    public void openStaffLogin() {
        Intent intent = new Intent(this,StaffLoginPage.class);
        startActivity(intent);
    }


}

