package com.example.login;

        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;
    Button btn1Login;

    String user1 = "Anna";
    String user2 = "Lorna";
    String user3 = "_Fe_";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        btnLogin = findViewById(R.id.button);
        btn1Login = findViewById(R.id.button2);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate user input
                validate(username.getText().toString(), password.getText().toString());
            }
        });

        btn1Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg1 = new AlertDialog.Builder(MainActivity.this);
                dlg1.setTitle("Register");
                dlg1.setMessage("You will be redirected to the registration screen");
                dlg1.create().show();

            }
        });

    }
    private void validate(String userName, String userPassword){
        if((userName.equals("Anna")) && (userPassword.equals("13579abcdeA"))){
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
