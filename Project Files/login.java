package com.example.slambook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class login extends AppCompatActivity {

    EditText edt1, edt2;
    Button btn1, btn2;
    Context c = this;

    ArrayList<UserAdmin> UserAdmin = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    private void init() {


        edt1 = (EditText) findViewById(R.id.ed1);
        edt2 = (EditText) findViewById(R.id.ed2);
        btn1 = (Button) findViewById(R.id.btnReg);
        btn2 = (Button) findViewById(R.id.btnLog);


        UserAdmin.add(new UserAdmin("Anna", "13579abcdeA", "Anna Lisa"));
        UserAdmin.add(new UserAdmin("Lorna", "Th3Q41ckBr0wnF0x", "Lorra Dee"));
        UserAdmin.add(new UserAdmin("_Fe_", "p@zzW0rd", "Fe Rari"));

//        UserAdmin.add( new Users(R.drawable.anna, "Anna", "13579abcdeA", "Anna Lisa"));
//        UserAdmin.add( new Users(R.drawable.anna, "Lorna", "Th3Q41ckBr0wnF0x", "Lorna Dee"));
//        UserAdmin.add( new Users(R.drawable.anna, "_Fe_", "pazzword", "Fe Rari"));


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                private void validate(String userName, String userPassword){
                    if((Username.equals("Anna")) && (userPassword.equals("13579abcdeA"))){
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    }
                    else if((userName.equals("Lorna")) && (userPassword.equals("Th3Q41ckBr0wnF0x"))) {
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    }
                    else if((userName.equals("_Fe_")) && (userPassword.equals("p@zzW0rd"))) {
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    }

                    else{
                        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                        dlg.setTitle("Username or password is incorrect");
                        dlg.setMessage("Please try again.");
                        dlg.setPositiveButton("OK",null);
                        dlg.setCancelable(true);
                        dlg.create().show();

                    }
                }
            }
                    Intent i = new Intent(c, EntryListScreen.class);
                    i.putExtra("name", UserAdmin.get(0).getName());
                    startActivity(i);


                }else if (edt1.getText().toString().equals(UserAdmin.get(1).getUsername()) && edt2.getText().toString().equals(UserAdmin.get(1).getPassword())) {
                    Intent i = new Intent(c, EntryListScreen.class);
                    i.putExtra("name", UserAdmin.get(1).getName());
                    startActivity(i);


                }else if (edt1.getText().toString().equals(UserAdmin.get(2).getUsername()) && edt2.getText().toString().equals(UserAdmin.get(2).getPassword())) {
                    Intent i = new Intent(c, EntryListScreen.class);
                    i.putExtra("name", UserAdmin.get(2).getName());
                    startActivity(i);


                }else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("Incorrect!");
                    builder.setMessage("Username or Password is incorrect");
                    builder.setCancelable(true);
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, MainActivity.class);
                startActivity(i);

            }
        });

    }
}