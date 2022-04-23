package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login.R;

public class IndividialEntryActivity extends AppCompatActivity {
Context c = this;
EntryClass entryClass;
Button back;
ImageView dimage;
TextView name,remark,bday,gender,address,contact,hobbies,otherinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_each_entry); getSupportActionBar().hide();
        init();
        getEntryInfo();
        action();
    }//create
    private  void init(){
        dimage = findViewById(R.id.dimg);
        back = findViewById(R.id.back);
        name = findViewById(R.id.dnamevalue);
        remark = findViewById(R.id.dremarkvalue);
        bday = findViewById(R.id.dbdayvalue);
        gender = findViewById(R.id.dgendervalue);
        address = findViewById(R.id.daddressvalue);
        contact = findViewById(R.id.dcontactvalue);
        hobbies = findViewById(R.id.dhobbiesvalue);
        otherinfo = findViewById(R.id.dotherinfovalue);
    }
    private void getEntryInfo(){
        Bundle bundle = getIntent().getExtras();
        entryClass = bundle.getParcelable("Entry");
        displayEntryInfo();
    }
    private void displayEntryInfo(){

        //temporary condition pag alang pic na provided iba pag sa database galing ung picture tos wala
        if(entryClass.getImage()!=0){
            dimage.setImageResource(entryClass.getImage());
        }else{
            dimage.setImageResource(R.drawable.ic_baseline_account_circle_24);
        }
        name.setText(entryClass.getName());
        remark.setText(entryClass.getRemark());
        bday.setText(entryClass.getBirthday());
        gender.setText(entryClass.getGender());
        address.setText(entryClass.getAddress());
        contact.setText(entryClass.getContact());
        hobbies.setText(entryClass.getHobbies());
        otherinfo.setText(entryClass.getOtherInfo());
    }//
    private void action(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }//
}//class