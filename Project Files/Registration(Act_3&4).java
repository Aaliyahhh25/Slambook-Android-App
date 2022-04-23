package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;
import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;

import com.example.login.R;

import java.util.Calendar;

public class Registration extends AppCompatActivity {
    final  int REQUEST_CODE_CAMERA=0;
    ImageView img;

    EditText username, password, confirm_pass, lname, fname, mname, email,
            bday, otherG, street, house_no, phone_no, ans1, ans2, ans3;

    CheckBox hobby_cb1, hobby_cb2, hobby_cb3, hobby_cb4, hobby_cb5, hobby_cb6,
            hobby_cb7, hobby_cb8, hobby_cb9, hobby_cb10, hobby_cb11, hobby_cb12;

    AutoCompleteTextView barangay_autocomplete, municipality_autocomplete , province_autocomplete;

    Spinner spnr1, spnr2, spnr3;
    String Questions[], Questions2[], Questions3[], rbselected="", cbselected = "";

    RadioGroup gender;
    RadioButton male, female, others;

    DatePickerDialog.OnDateSetListener setListener;

    Button register_btn, lgn_btn;





    Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);




        init();
        reglist();
    }

    private void init() {

        img = findViewById(R.id.addPicture);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirm_pass = (EditText) findViewById(R.id.confirm_pass);
        lname= (EditText) findViewById(R.id.lname);
        fname = (EditText) findViewById(R.id.fname);
        mname = (EditText) findViewById(R.id.mname);
        email = (EditText) findViewById(R.id.email);
        bday = (EditText) findViewById(R.id.bday);
        otherG = (EditText) findViewById(R.id.otherG);
        street = (EditText) findViewById(R.id.street);
        house_no = (EditText) findViewById(R.id.house_no);
        phone_no = (EditText) findViewById(R.id.phone_no);
        ans1 = (EditText) findViewById(R.id.ans1);
        ans2 = (EditText) findViewById(R.id.ans2);
        ans3 = (EditText) findViewById(R.id.ans3);



        gender = (RadioGroup) findViewById(R.id.gender);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        others = (RadioButton) findViewById(R.id.others);


        barangay_autocomplete = (AutoCompleteTextView) findViewById(R.id.barangay_autocomplete);
        municipality_autocomplete= (AutoCompleteTextView) findViewById(R.id.municipality_autocomplete);
        province_autocomplete = (AutoCompleteTextView) findViewById(R.id.province_autocomplete);

        hobby_cb1 = (CheckBox) findViewById(R.id.hobby_cb1);
        hobby_cb2 = (CheckBox) findViewById(R.id.hobby_cb2);
        hobby_cb3 = (CheckBox) findViewById(R.id.hobby_cb3);
        hobby_cb4 = (CheckBox) findViewById(R.id.hobby_cb4);
        hobby_cb5 = (CheckBox) findViewById(R.id.hobby_cb5);
        hobby_cb6 = (CheckBox) findViewById(R.id.hobby_cb6);
        hobby_cb7 = (CheckBox) findViewById(R.id.hobby_cb7);
        hobby_cb8 = (CheckBox) findViewById(R.id.hobby_cb8);
        hobby_cb9 = (CheckBox) findViewById(R.id.hobby_cb9);
        hobby_cb10 = (CheckBox) findViewById(R.id.hobby_cb10);
        hobby_cb11 = (CheckBox) findViewById(R.id.hobby_cb11);
        hobby_cb12 = (CheckBox) findViewById(R.id.hobby_cb12);

        spnr1 = (Spinner) findViewById(R.id.spnr1);
        spnr2 = (Spinner) findViewById(R.id.spnr2);
        spnr3 = (Spinner) findViewById(R.id.spnr3);
        Questions = getResources().getStringArray(R.array.Questions);
        Questions2 = getResources().getStringArray(R.array.Questions2);
        Questions3 = getResources().getStringArray(R.array.Questions3);

        register_btn = (Button) findViewById(R.id.register_btn);
        lgn_btn = (Button) findViewById(R.id.lgn_btn);





        // autocomplete textview
        String barangay_suggestion[] = {"San Miguel", "San Marcos", "Meysulao", "Bugion", "Frances", "Sapang Bayan", "Meyto", "Gugo", "Calizon", "Bulusan"};
        String municipality_suggestion[] = {"Calumpit", "Pulilan", "Plaridel", "Guiguinto", "Malolos", "Bocaue", "San Miguel", "San Jose Del Monte", "Baliuag", "Pandi"};
        String province_suggestion[] = {"Pampanga", "Bulacan", "Pangasinan", "Manila", "Cavite", "Laguna", "Batangas", "Quezon", "Rizal", "Romblon"};
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, barangay_suggestion);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(c, android.R.layout.simple_dropdown_item_1line, municipality_suggestion);
        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(c, android.R.layout.simple_dropdown_item_1line, province_suggestion);
        barangay_autocomplete.setAdapter(myAdapter);
        municipality_autocomplete.setAdapter(myAdapter2);
        province_autocomplete.setAdapter(myAdapter3);


        // Security questions
        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, Questions);
        ArrayAdapter<String> myAdapter5 = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, Questions2);
        ArrayAdapter<String> myAdapter6 = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, Questions3);
        myAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



    }

    private void reglist() {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,
                        REQUEST_CODE_CAMERA);
            }
        });

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        bday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Registration.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date =  day+"/"+month+"/"+year;
                        bday.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                bday.setText(date);
            }
        };

        View.OnClickListener onclick_Gender = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;

                if (rb.getId() == R.id.male) {
                    rbselected = male.getText().toString();
                    otherG.setVisibility(View.GONE);
                } else if (rb.getId() == R.id.female) {
                    rbselected = female.getText().toString();
                    otherG.setVisibility(View.GONE);
                } else if (rb.getId() == R.id.others) {
                    rbselected = others.getText().toString();
                    otherG.setText("");
                    otherG.setVisibility(View.VISIBLE);

                }
            }
        };
        male.setOnClickListener(onclick_Gender);
        female.setOnClickListener(onclick_Gender);
        others.setOnClickListener(onclick_Gender);

        View.OnClickListener onClick_cb = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;

                if (cb.isChecked()) {
                    cbselected += cb.getText().toString() + ",";
                } else {

                }

            }
        };

        hobby_cb1.setOnClickListener(onClick_cb);
        hobby_cb2.setOnClickListener(onClick_cb);
        hobby_cb3.setOnClickListener(onClick_cb);
        hobby_cb4.setOnClickListener(onClick_cb);
        hobby_cb5.setOnClickListener(onClick_cb);
        hobby_cb6.setOnClickListener(onClick_cb);
        hobby_cb7.setOnClickListener(onClick_cb);
        hobby_cb8.setOnClickListener(onClick_cb);
        hobby_cb9.setOnClickListener(onClick_cb);
        hobby_cb10.setOnClickListener(onClick_cb);
        hobby_cb11.setOnClickListener(onClick_cb);
        hobby_cb12.setOnClickListener(onClick_cb);


        spnr1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ques1 = spnr1.getSelectedItem().toString();
                String ques2 = spnr2.getSelectedItem().toString();
                String ques3 = spnr3.getSelectedItem().toString();

                if (ques1 == ques2 || ques1 == ques3) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("Warning!");
                    builder.setMessage("Please select another question");
                    builder.setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spnr2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ques1 = spnr1.getSelectedItem().toString();
                String ques2 = spnr2.getSelectedItem().toString();
                String ques3 = spnr3.getSelectedItem().toString();

                if (ques2 == ques1 || ques2 == ques3) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("Warning!");
                    builder.setMessage("Please select another question");
                    builder.setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spnr3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ques1 = spnr1.getSelectedItem().toString();
                String ques2 = spnr2.getSelectedItem().toString();
                String ques3 = spnr3.getSelectedItem().toString();


                if (ques3 == ques2 || ques3 == ques1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("Warning!");
                    builder.setMessage("Please select another question");
                    builder.setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String realcb = cbselected;
                String realrb = rbselected;


                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty() ||
                        confirm_pass.getText().toString().isEmpty() || fname.getText().toString().isEmpty() ||
                        lname.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                        bday.getText().toString().isEmpty() || street.getText().toString().isEmpty() ||
                        house_no.getText().toString().isEmpty() || barangay_autocomplete.getText().toString().isEmpty() ||
                        municipality_autocomplete.getText().toString().isEmpty() || province_autocomplete.getText().toString().isEmpty() ||
                        phone_no.getText().toString().isEmpty() || ans1.getText().toString().isEmpty() ||
                        ans2.getText().toString().isEmpty() || ans3.getText().toString().isEmpty() ||
                        realrb == "" || realcb == "") {

                    String show_fields="";
                    AlertDialog.Builder builder = new AlertDialog.Builder(c);
                    builder.setTitle("Info!");

                    if (username.getText().toString().isEmpty()) {
                        show_fields += "\n Username,";
                    }
                    if (password.getText().toString().isEmpty()) {
                        show_fields += "\n Password,";
                    }
                    if(fname.getText().toString().isEmpty() || lname.getText().toString().isEmpty()){
                        show_fields += "\n First and Last Name,";
                    }

                    if (confirm_pass.getText().toString().isEmpty()) {
                        show_fields += "\n Confirm Password,";
                    }
                    if (email.getText().toString().isEmpty()) {
                        show_fields += "\n Email,";
                    }
                    if (bday.getText().toString().isEmpty()) {
                        show_fields += "\n Birthday,";
                    }
                    if (street.getText().toString().isEmpty()) {
                        show_fields += "\n Street,";
                    }
                    if (house_no.getText().toString().isEmpty()) {
                        show_fields += "\n House Number,";
                    }
                    if (barangay_autocomplete.getText().toString().isEmpty()) {
                        show_fields += "\n Barangay,";
                    }
                    if (municipality_autocomplete.getText().toString().isEmpty()) {
                        show_fields += "\n Municipality,";
                    }
                    if (province_autocomplete.getText().toString().isEmpty()) {
                        show_fields += "\n Province,";
                    }
                    if (password.getText().toString().isEmpty()) {
                        show_fields += "\n Phone Number,";
                    }
                    if (realrb.isEmpty()) {
                        show_fields += "\n Gender,";
                    }
                    if (realcb.isEmpty()) {
                        show_fields += "\n Hobbies,";
                    }
                    if (ans1.getText().toString().isEmpty()) {
                        show_fields += "\n Question 1,";
                    }
                    if (ans2.getText().toString().isEmpty()) {
                        show_fields += "\n Question 2,";
                    }
                    if (ans3.getText().toString().isEmpty()) {
                        show_fields += "\n Question 3,";
                    }


                    builder.setMessage("Please fill in all required fields: " + show_fields);
                    builder.setCancelable(true);
                    AlertDialog dialog = builder.create();
                    dialog.show();


                } else {


                    if (password.getText().toString().equals(confirm_pass.getText().toString())) {
                        if (!otherG.getText().toString().isEmpty()) {
                            realrb = otherG.getText().toString();

                        } else {
                            realrb = rbselected;
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(c);
                        builder.setTitle("Heres your complete info!");
                        builder.setMessage("Username:" + username.getText().toString() + "\n" +
                                "Password: " + password.getText().toString() + "\n" +
                                "Name: " + fname.getText().toString() + " " + mname.getText().toString() + " " + lname.getText().toString() + "\n" +
                                "Email :" + email.getText().toString() + "\n" +
                                "Birthday :" + bday.getText().toString() + "\n" +
                                "Gender: " + realrb + "\n" +
                                "Street: " + street.getText().toString() + "\n" +
                                "House #: " + house_no.getText().toString() + "\n" +
                                "Barangay: " + barangay_autocomplete.getText().toString() + "\n" +
                                "Municipality: " + municipality_autocomplete.getText().toString() + "\n" +
                                "Province: " + province_autocomplete.getText().toString() + "\n" +
                                "Hobbies :" + realcb + "\n" +
                                "Phone Number: " + phone_no.getText().toString() + "\n" +
                                "Security Question 1 :" + ans1.getText().toString() + "\n" +
                                "Security Question 2 :" + ans2.getText().toString() + "\n" +
                                "Security Question 3 :" + ans3.getText().toString());

                        builder.setCancelable(true);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        Toast.makeText(c, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(c);
                        builder.setTitle("Info!");
                        builder.setMessage("You completed all the fields but your password does not match!");
                        builder.setCancelable(true);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }


                }

            }
        });

        lgn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c,MainActivity.class);
                startActivity(i);
            }
        });



    }


}
