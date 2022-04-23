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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.login.R;

public class EditEntryActivity extends AppCompatActivity {

    Context c = this; final  int REQUEST_CODE_CAMERA=0;
    EntryClass newEntry;
    ImageView img;
    Button cancel,edit;
    EditText name,remark,bday,address,contact,hobbies,otherInfo;
    RadioGroup radioGroup; RadioButton female,male,other;
    String gender="";
    Bitmap addedImage=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry); getSupportActionBar().hide();
        init();
        getEntryToBeEdited_Display();
        action();
    }
    private void init(){
        img = findViewById(R.id.addPic4);
        name = (EditText) findViewById(R.id.editNameEntry);
        remark =  (EditText)findViewById(R.id.remark4);
        bday =(EditText) findViewById(R.id.birthday4);
        radioGroup = findViewById(R.id.rggender4);
        address = (EditText) findViewById(R.id.address4);
        contact = (EditText)findViewById(R.id.contact4);
        hobbies = (EditText)findViewById(R.id.hobbies4);
        otherInfo =(EditText) findViewById(R.id.otherInfo4);
        female = findViewById(R.id.femaleradiobtn4);
        male = findViewById(R.id.maleradiobtn4);
        other = findViewById(R.id.otherradiobtn4);
        cancel = findViewById(R.id.cancel4);
        edit = findViewById(R.id.editEntry4);

    }
    private  void getEntryToBeEdited_Display(){
        Bundle bundle = getIntent().getExtras();
        newEntry = bundle.getParcelable("entry");
        if(newEntry.getImage()!=0){//check if there is an image resource file //
            img.setImageResource(newEntry.getImage());
        }
        name.setText(newEntry.getName());
        remark.setText(newEntry.getRemark());
        bday.setText(newEntry.getBirthday());
        gender = newEntry.getGender();
        if(gender.equalsIgnoreCase("Male")){
            male.setChecked(true);
        }
        else if(gender.equalsIgnoreCase("Female")){
            female.setChecked(true);
        }
        else if(gender.equalsIgnoreCase("Other")){
            other.setChecked(true);
        }

        address.setText(newEntry.getAddress());
        contact.setText(newEntry.getContact());
        hobbies.setText(newEntry.getHobbies());
        otherInfo.setText(newEntry.getOtherInfo());


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
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEntryForm();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.maleradiobtn4){
                    gender = "Male";
                }
                else if(checkedId==R.id.femaleradiobtn4){
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
            newEntry = new EntryClass(newEntry.getImage(),name,remark,bday,gender,address,contact,hobbies,otherInfo);
            Intent intent = new Intent();
            intent.putExtra("entry",(Parcelable) newEntry);
            setResult(RESULT_OK,intent);
            finish();
        }
        else{
            _alertDialog(1);
        }

    }//
    private void _alertDialog(int RESULT){
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

    }





    private void scanIfExist(){

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