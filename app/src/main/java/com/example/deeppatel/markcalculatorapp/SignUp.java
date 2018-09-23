package com.example.deeppatel.markcalculatorapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deeppatel.markcalculatorapp.db.DataBaseHelper;
import com.example.deeppatel.markcalculatorapp.model.Data;

public class SignUp extends Activity {

    EditText edtId;
    EditText edtpassword;
    EditText edtConfirmPassword;
    Button btncreate;
    Button btncancel;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtId = findViewById(R.id.edt_ID);
        edtpassword = findViewById(R.id.edt_password);
        edtConfirmPassword = findViewById(R.id.edt_confirmPassword);
        btncreate = findViewById(R.id.btn_create);
        btncancel = findViewById(R.id.btn_cancel);

        create();
        cancel();
    }

    public void create(){
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edtId.getText().toString());
                String password = edtpassword.getText().toString();
                String confirm_password = edtConfirmPassword.getText().toString();

                if(confirm_password.equals(password)){
                    Data data = new Data();
                    db = new DataBaseHelper(getApplicationContext());
                    data = db.save(data);

                    Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_LONG).show();

                    edtpassword.setText("");
                    edtConfirmPassword.setText("");
                }
            }
        });
    }

    public void cancel(){
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
