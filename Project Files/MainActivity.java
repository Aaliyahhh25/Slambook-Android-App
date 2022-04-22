package com.example.slambook;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
// VARIABLES

    Button btnReg, btnLog;
    DatePickerDialog datePicker;
    RadioGroup rgroup;
    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8, ed9, ed10, ed11, ed12, ed13, ed14, ed15;
    RadioButton rb1, rb2, rb3;
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11, cb12;
    int day, month, year;

    ImageButton img1;
    AutoCompleteTextView ac1, ac2, ac3;
    Spinner sp1, sp2, sp3;
    String Questions1[], Questions2[], Questions3[], rbchoice="", cbchoice = "";
    final int REQUEST_CODE_CAMERA = 2;
    Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        reglist();
    }

    private void init() {
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        ed3 = (EditText) findViewById(R.id.ed3);
        ed4 = (EditText) findViewById(R.id.ed4);
        ed5 = (EditText) findViewById(R.id.ed5);
        ed6 = (EditText) findViewById(R.id.ed6);
        ed7 = (EditText) findViewById(R.id.ed7);
        ed8 = (EditText) findViewById(R.id.ed8);
        ed9 = (EditText) findViewById(R.id.ed9);
        ed10 = (EditText) findViewById(R.id.ed10);
        ed11 = (EditText) findViewById(R.id.ed11);
        ed12 = (EditText) findViewById(R.id.ed12);
        ed13 = (EditText) findViewById(R.id.ed13);
        ed14 = (EditText) findViewById(R.id.ed14);
        ed15 = (EditText) findViewById(R.id.ed15);
        btnReg = (Button) findViewById(R.id.btnReg);
        btnLog = (Button) findViewById(R.id.btnLog);

        rgroup = (RadioGroup) findViewById(R.id.rgroup);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);

        ac1 = (AutoCompleteTextView) findViewById(R.id.ac1);
        ac2 = (AutoCompleteTextView) findViewById(R.id.ac2);
        ac3 = (AutoCompleteTextView) findViewById(R.id.ac3);

        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cb4 = (CheckBox) findViewById(R.id.cb4);
        cb5 = (CheckBox) findViewById(R.id.cb5);
        cb6 = (CheckBox) findViewById(R.id.cb6);
        cb7 = (CheckBox) findViewById(R.id.cb7);
        cb8 = (CheckBox) findViewById(R.id.cb8);
        cb9 = (CheckBox) findViewById(R.id.cb9);
        cb10 = (CheckBox) findViewById(R.id.cb10);
        cb11 = (CheckBox) findViewById(R.id.cb11);
        cb12 = (CheckBox) findViewById(R.id.cb12);

        sp1 = (Spinner) findViewById(R.id.sp1);
        sp2 = (Spinner) findViewById(R.id.sp2);
        sp3 = (Spinner) findViewById(R.id.sp3);

        Questions1 = getResources().getStringArray(R.array.Questions1);
        Questions2 = getResources().getStringArray(R.array.Questions2);
        Questions3 = getResources().getStringArray(R.array.Questions3);


        datePicker = new DatePickerDialog(c, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker v, int year, int month, int dayOfMonth) {
                ed8.setText(dayOfMonth + "/" + month + "/" + year);
            }
        }, year, month, day);


        // autocomplete
        String barangay[] = {"Longos", "Sumapang Matanda", "Dampol", "Catmon", "Mabolo", "Sta. Isabel", "Liang", "Dakila", "Confradia", "Balite"};
        String municipality[] = {"Malolos", "Apalit", "Plaridel", "Macabebe", "Calumpit", "Hagonoy", "San Fernando", "Quezon", "Roxas", "Porac"};
        String province[] = {"Pamapanga", "Tarlac", "Bulacan", "Samar", "Zambales", "Batangas", "Palawan", "Bukidnon", "Batanes", "Rizal"};
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, barangay);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(c, android.R.layout.simple_dropdown_item_1line, municipality);
        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(c, android.R.layout.simple_dropdown_item_1line, province);
        ac1.setAdapter(myAdapter);
        ac2.setAdapter(myAdapter2);
        ac3.setAdapter(myAdapter3);


        // Security questions
        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, Questions1);
        ArrayAdapter<String> myAdapter5 = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, Questions2);
        ArrayAdapter<String> myAdapter6 = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, Questions3);
        myAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


    }

    private void reglist() {
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,REQUEST_CODE_CAMERA);

            }

        });


        ed8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show();

            }
        });


        View.OnClickListener Gender = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;

                if (rb.getId() == R.id.rb1) {
                    rbchoice = rb1.getText().toString();
                    ed9.setVisibility(View.GONE);
                } else if (rb.getId() == R.id.rb2) {
                    rbchoice = rb2.getText().toString();
                    ed9.setVisibility(View.GONE);
                } else if (rb.getId() == R.id.rb3) {

                    ed9.setText("");
                    ed9.setVisibility(View.VISIBLE);

                }
            }
        };
        rb1.setOnClickListener(Gender);
        rb2.setOnClickListener(Gender);
        rb3.setOnClickListener(Gender);

        View.OnClickListener hobbiez = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;

                if (cb.isChecked()) {
                    cbchoice += cb.getText().toString() + ",";
                } else {

                }

            }
        };
        cb1.setOnClickListener(hobbiez);
        cb2.setOnClickListener(hobbiez);
        cb3.setOnClickListener(hobbiez);
        cb4.setOnClickListener(hobbiez);
        cb5.setOnClickListener(hobbiez);
        cb6.setOnClickListener(hobbiez);
        cb7.setOnClickListener(hobbiez);
        cb8.setOnClickListener(hobbiez);
        cb9.setOnClickListener(hobbiez);
        cb10.setOnClickListener(hobbiez);
        cb11.setOnClickListener(hobbiez);
        cb12.setOnClickListener(hobbiez);


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item1 = sp1.getSelectedItem().toString();
                String item2 = sp2.getSelectedItem().toString();
                String item3 = sp3.getSelectedItem().toString();

                if (item1 == item2 || item1 == item3) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(c);
                    bld.setTitle("Error:");
                    bld.setTitle("Please select another question.");
                    bld.create();
                    bld.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item1 = sp1.getSelectedItem().toString();
                String item2 = sp2.getSelectedItem().toString();
                String item3 = sp3.getSelectedItem().toString();

                if (item2 == item1 || item2 == item3) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(c);
                    bld.setTitle("Error:");
                    bld.setTitle("Please select another question.");
                    bld.create();
                    bld.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item1 = sp1.getSelectedItem().toString();
                String item2 = sp2.getSelectedItem().toString();
                String item3 = sp3.getSelectedItem().toString();

                if (item3 == item2 || item3 == item1) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(c);
                    bld.setTitle("Error:");
                    bld.setMessage("Your password did not match.");
                    bld.create();
                    bld.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c,login.class);
                startActivity(i);

            }
        });
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String realcb = cbchoice;
                String realrb = rbchoice;


                if (ed1.getText().toString().isEmpty() ||
                        ed2.getText().toString().isEmpty() ||
                        ed3.getText().toString().isEmpty() ||
                        ed4.getText().toString().isEmpty() ||
                        ed5.getText().toString().isEmpty() ||
                        ed7.getText().toString().isEmpty() ||
                        ed8.getText().toString().isEmpty() ||
                        ed10.getText().toString().isEmpty() ||
                        ed11.getText().toString().isEmpty() ||
                        ac1.getText().toString().isEmpty() ||
                        ac2.getText().toString().isEmpty() ||
                        ac3.getText().toString().isEmpty() ||
                        ed12.getText().toString().isEmpty() ||
                        ed13.getText().toString().isEmpty() ||
                        ed14.getText().toString().isEmpty() ||
                        ed15.getText().toString().isEmpty() ||
                        realrb == "" ||
                        realcb == "") {
                    String validate="";


                    if (ed1.getText().toString().isEmpty()) {
                        validate += "\n Username";
                    }
                    if (ed2.getText().toString().isEmpty()) {
                        validate += "\n Password";
                    }

                    if (ed3.getText().toString().isEmpty()) {
                        validate += "\n Confirm Password";
                    }
                    if (ed3.getText().toString().isEmpty()) {
                        validate += "\n Confirm Password";
                    }
                    if (ed4.getText().toString().isEmpty()) {
                        validate += "\n Last Name";
                    }
                    if (ed5.getText().toString().isEmpty()) {
                        validate += "\n First Name";
                    }
                    if (ed7.getText().toString().isEmpty()) {
                        validate += "\n Email";
                    }
                    if (ed8.getText().toString().isEmpty()) {
                        validate += "\n Birthday";
                    }

                    if (ed10.getText().toString().isEmpty()) {
                        validate += "\n Street";
                    }
                    if (ed11.getText().toString().isEmpty()) {
                        validate += "\n House Number";
                    }
                    if (ac1.getText().toString().isEmpty()) {
                        validate += "\n Barangay";
                    }
                    if (ac2.getText().toString().isEmpty()) {
                        validate += "\n Municipality";
                    }
                    if (ac3.getText().toString().isEmpty()) {
                        validate += "\n Province";
                    }
                    if (ed12.getText().toString().isEmpty()) {
                        validate += "\n Phone Number,";
                    }
                    if (realrb.isEmpty() ) {
                        validate += "\n Gender";
                    }
                    if (realcb.isEmpty()) {
                        validate += "\n Hobbies";
                    }
                    if (ed13.getText().toString().isEmpty()) {
                        validate += "\n Question 1";
                    }
                    if (ed14.getText().toString().isEmpty()) {
                        validate += "\n Question 2";
                    }
                    if (ed15.getText().toString().isEmpty()) {
                        validate += "\n Question 3";
                    }

                    // Alert dialog if empty
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("Error:");
                    builder.setMessage("Please fill in all the required fields: " + validate);
                    builder.setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.show();


                }

                // kapag nag match ung password at others ung pinili sa gender
                else {

                    if (ed2.getText().toString().equals(ed3.getText().toString())) {
                        if (!ed9.getText().toString().isEmpty()) {
                            realrb = ed9.getText().toString();

                        } else {
                            realrb = rbchoice;
                        }


                        //DISPLAYING ALL GATHERED INFO
                        AlertDialog.Builder builder = new AlertDialog.Builder(c);
                        builder.setTitle("Account Information:");
                        builder.setMessage("Username:" + ed1.getText().toString() + "\n" +
                                "Password:" + ed2.getText().toString() + "\n" +
                                "Name:" + ed4.getText().toString() + ", " + ed5.getText().toString() + " " + ed6.getText().toString() + "\n" +
                                "Email:" + ed7.getText().toString() + "\n" +
                                "Birthday:" + ed8.getText().toString() + "\n" +
                                "Gender:" + realrb + "\n" +
                                "Street:" + ed10.getText().toString() + "\n" +
                                "House No.:" + ed11.getText().toString() + "\n" +
                                "Barangay:" + ac1.getText().toString() + "\n" +
                                "Municipality:" + ac2.getText().toString() + "\n" +
                                "Province:" + ac3.getText().toString() + "\n" +
                                "Hobbies:" + realcb + "\n" +
                                "Phone Number:" + ed12.getText().toString() + "\n" +
                                "Answer to Security Question 1:" + ed13.getText().toString() + "\n" +
                                "Answer to Security Question 2:" + ed14.getText().toString() + "\n" +
                                "Answer to Security Question 3:" + ed15.getText().toString());

                        builder.setCancelable(true);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        Toast.makeText(c, "Registration Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(c);
                        builder.setTitle("Error:");
                        builder.setMessage("Your password did not match.");
                        builder.setCancelable(true);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }


                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CAMERA && resultCode== RESULT_OK){
            Bundle extra = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extra.get("data");
            img1.setImageBitmap(imgBitmap);
            img1.setMinimumWidth(150);
            img1.setMinimumHeight(150);


        }

    }
}


