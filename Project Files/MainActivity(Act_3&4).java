package com.example.loginscreen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;

public class MainActivity extends AppCompatActivity {
    //variables
    EditText username, password;
    Button loginbtn, regisbtn;
    String [] user = {"Anna","Lorna","_Fe_"};
    String [] pass = {"13579abcdeA","Th3Q41ckBr0wnF0x","p@zzW0rd"};
    Context c = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        regLis();
    }

    private void init() {
//initialization
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginbtn = (Button) findViewById((R.id.loginbtn));
        regisbtn = (Button) findViewById(R.id.regisbtn);

    }

    private void regLis() {
//eventListerners
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(c,"All field is required!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(username.getText().toString().equals(user[0]) && password.getText().toString().equals(pass[0])){
                            Intent i = new Intent(c,EntryListActivity.class);
                            i.putExtra("Name", "Anna Lhisa");
                            startActivity(i);
                    }
                    else if(username.getText().toString().equals(user[1]) &&password.getText().toString().equals(pass[1])){
                        Intent i = new Intent(c,EntryListActivity.class);
                        i.putExtra("Name", "Lorna Cruz");
                        startActivity(i);
                    }
                    else if(username.getText().toString().equals(user[2]) &&password.getText().toString().equals(pass[2])){
                        Intent i = new Intent(c,EntryListActivity.class);
                        i.putExtra("Name", "Fe Cabral");
                        startActivity(i);
                    }
                    else{

                             AlertDialog.Builder builder= new AlertDialog.Builder(c);
                             builder.setTitle("Login Unsuccessful: ")
                                .setMessage("The Username and Password is Incorrect!")
                                .setCancelable(true)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
//                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//
//                                    }
//                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            }
        });

        regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(c,Registration.class);
                startActivity(i);
            }
        });


    }
}










