package com.example.loginscreen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.login.R;

public class AddEntryActivity extends AppCompatActivity {
    Context c = this;
    final  int REQUEST_CODE_CAMERA=0;
    EntryClass newEntry;
    ImageView img;
    Button cancel,add;
    EditText name,remark,bday,address,contact,hobbies,otherInfo;
    RadioGroup radioGroup;
    String gender="";
    Bitmap addedImage=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry); getSupportActionBar().hide();
        init();
        action();
    }
    private void init(){
        img = findViewById(R.id.addPic3);
        name = (EditText) findViewById(R.id.name3);
        remark =  (EditText)findViewById(R.id.remark3);
        bday =(EditText) findViewById(R.id.birthday3);
        radioGroup = findViewById(R.id.rggender3);
        address = (EditText) findViewById(R.id.address3);
        contact = (EditText)findViewById(R.id.contact3);
        hobbies = (EditText)findViewById(R.id.hobbies3);
        otherInfo =(EditText) findViewById(R.id.otherInfo3);
        cancel = findViewById(R.id.cancel3);
        add = findViewById(R.id.addEntry3);
    }
    private  void action(){

      img.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              startActivityForResult(cameraIntent,
                      REQUEST_CODE_CAMERA);
          }
      });

      cancel.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });
      add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              validateEntryForm();
          }
      });
     radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, int checkedId) {
              if(checkedId==R.id.maleradiobtn){
                  gender = "Male";
              }
              else if(checkedId==R.id.femaleradiobtn){
                  gender="Female";
              }
              else{
                  gender="Other";
              }
          }
      });
    }//action listener

    private void validateEntryForm(){
        String name= this.name.getText().toString(),remark = this.remark.getText().toString(),bday= this.bday.getText().toString(),
                address = this.address.getText().toString(),contact = this.contact.getText().toString(),
        hobbies = this.hobbies.getText().toString(),otherInfo = this.otherInfo.getText().toString();
        if(!name.isEmpty()&&!remark.isEmpty()&&!bday.isEmpty()&&!gender.isEmpty()&&!hobbies.isEmpty()){
            _alertDialog(0);
            newEntry = new EntryClass(0,name,remark,bday,gender,address,contact,hobbies,otherInfo);
            Intent intent = new Intent();
            intent.putExtra("entry",(Parcelable) newEntry);
            setResult(RESULT_OK,intent);
            finish();
        }
        else{
           _alertDialog(1);
        }

    }//
    private void _alertDialog(int RESULT){//0 success// 1 incomplete//
        AlertDialog.Builder builder = new AlertDialog.Builder(c); String title="",message="";
        if(RESULT==0) {
            return;
        }
        if(RESULT==1){
           title= "Error Message";
           message="Please fill all the required field!";
       }
       builder.setTitle(title).setMessage(message).setCancelable(true).setPositiveButton("Ok",null);
       AlertDialog dialog = builder.create(); dialog.show();
        /*final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 15000);*/
    }//





    private void scanIfExist(){//sa database na to on hold muna

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            addedImage = (Bitmap) bundle.get("data");
            img.setImageBitmap(addedImage);


        }
    }
}