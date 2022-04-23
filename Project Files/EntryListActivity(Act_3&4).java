package com.example.loginscreen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.login.R;

import java.util.ArrayList;

import static com.example.login.R.layout.each_recycler_layout;


public class EntryListActivity extends AppCompatActivity { Context c = this; ;
    TextView name;
    ImageButton logoutbtn;
    Button addNewEntryButton;

    final int REQUEST_CODE_ADDENTRY=1;
    final int REQUEST_CODE_EDITENTRY='e';
    int positionToBeEdited;
    public ArrayList<EntryClass> entryClasses = new ArrayList<>(); RecyclerView recyclerView; RecyclerView.LayoutManager layoutManager;CustomAdapter customAdapter;

    static String USER_CURRENT_LOGGED;
    //testing
    //ArrayList<Kape> kapeList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_entry); getSupportActionBar().hide();
        getEntryFromDatabase();
        init();
        listener();

        Intent i =getIntent();
        String na =i.getStringExtra("Name");
        name.setText(na);

    }


    private void init(){
        logoutbtn =findViewById(R.id.logoutbtn);
        name =findViewById(R.id.name);
        addNewEntryButton =findViewById(R.id.addNewEntrybtn);
        recyclerView = findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        customAdapter = new CustomAdapter(c, each_recycler_layout,entryClasses);
        recyclerView.setAdapter(customAdapter);

    }//init



    private void listener(){

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _finish();
            }
        });

           /* customAdapter.setOnConvertViewLongClickListener(new CustomAdapter.OnConvertViewLongClickListener() {
                @Override
                public void convertViewLongClickListener(int position) {
                    Toast.makeText(c,"",Toast.LENGTH_LONG).show();
                    entryClasses.remove(position);
                    customAdapter.notifyItemRemoved(position);
                    layoutManager.scrollToPosition(position);
                }
            });*/

     customAdapter.setOnClickListener(new CustomAdapter.OnClickListener() {
         @Override
         public void clickListener(int position,int palatandaan) {
             if(palatandaan==0) {

                 Intent fromEntryListToIndiv = new Intent(c, IndividialEntryActivity.class);
                 fromEntryListToIndiv.putExtra("Entry", (Parcelable) entryClasses.get(position));
                 startActivity(fromEntryListToIndiv);
             }
             else if(palatandaan==1){


                 positionToBeEdited=position;//used for on activityresult
                 Intent fromMainToEdit = new Intent(c,EditEntryActivity.class);
                 fromMainToEdit.putExtra("entry",(Parcelable) entryClasses.get(position));
                 startActivityForResult(fromMainToEdit,REQUEST_CODE_EDITENTRY);

             }
             else{

                 deleteEntry(position);
             }
         }
     });
     addNewEntryButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent mainToAddEntry = new Intent(c,AddEntryActivity.class);
             startActivityForResult(mainToAddEntry,REQUEST_CODE_ADDENTRY);

         }
     });
    }//listener ----------------
    private void deleteEntry(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Confirmation Dialog").setMessage("Remove this entry?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        entryClasses.remove(position);
                        customAdapter.notifyDataSetChanged();
                        layoutManager.scrollToPosition(position);
                    }
                }).setNegativeButton("No",null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }//



    private void getEntryFromDatabase(){//

        z_temporaryAddEntry();

    }
    private void z_temporaryAddEntry(){


        entryClasses.add(new EntryClass(R.raw.bob,"Sungjin","Singer",
                "January 16, 1993","Male","Korea","09111111111","Singing","Dont skip meals"));

        entryClasses.add(new EntryClass(R.raw.jae,"Jae","Guitarist",
                "September 15, 1992","Male","America","09222222222","Malding","eaJParkMusic on YT"));

        entryClasses.add(new EntryClass(R.raw.yk,"Younghyun","Bassist",
                "December 19, 1993","Male","Europe","09333333333","Eating","munch munch"));

        entryClasses.add(new EntryClass(R.raw.wp,"Wonpil","Pianist",
                "April 28, 1994","Male","Korea","09444444444","Crying","U can do it friends"));

        entryClasses.add(new EntryClass(R.raw.drum,"Dowoon","Drummer",
                "August 25, 1995","Male","Philippines","09555555555","Running","I am Drum"));

        entryClasses.add(new EntryClass(R.raw.bae,"Irene","Actress",
                "January 1, 1938","Female","Bulacan","09666666666","Acting","Double Patty"));

        entryClasses.add(new EntryClass(R.raw.seul,"Seulgi","Painter",
                "May 21, 2000","Feale","Antartica","09777777777","Sleeping","Ddeulgi"));

        entryClasses.add(new EntryClass(R.raw.wendi,"Wendy","Singer",
                "July 8, 1994","Feale","Canada","09888888888","Composing","RV OT5"));

        entryClasses.add(new EntryClass(R.raw.joy,"Joy","Model",
                "April 9, 1997","Female","Paris","09999999999","Exercising","Palaging mganda"));

        entryClasses.add(new EntryClass(R.raw.yerim,"Yeri","Comedian",
                "October 31, 2012","Female","England","0912341234","Existing","w/ British Accent"));

    }//temp

    //sytem function -----------------------------------------------------------------------------
    private void _finish(){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Confirmation Dialog").setMessage("Are you sure you want to logout?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                }).setNegativeButton("No", null);
        AlertDialog dialog = builder.create(); builder.show();
    }//

    @Override
    public void onBackPressed() {
         _finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADDENTRY && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            EntryClass entry = (EntryClass) bundle.getParcelable("entry");
            entryClasses.add(0,entry);
            customAdapter.notifyDataSetChanged();
            layoutManager.scrollToPosition(0);

        }
        else if(requestCode == REQUEST_CODE_EDITENTRY && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            EntryClass entry = (EntryClass) bundle.getParcelable("entry");
            entryClasses.remove(positionToBeEdited);
            entryClasses.add(positionToBeEdited,entry);
            customAdapter.notifyDataSetChanged();
            layoutManager.scrollToPosition(positionToBeEdited);

        }

    }
}//end of class