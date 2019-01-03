package com.example.root.helpme;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText id;
    EditText password;
    EditText con_password;
    Button check;
    Button login;
    AlertDialog.Builder builder;
    AlertDialog.Builder builder2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = (EditText) findViewById(R.id.login_page_id);
        password = (EditText) findViewById(R.id.login_page_password);
        con_password = (EditText) findViewById(R.id.login_page_con_password);
        check = (Button) findViewById(R.id.login_page_btn_check);
        login = (Button) findViewById(R.id.login_page_btn_login);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginHelper LH = new LoginHelper();
                if(LH.check_id(id.getText().toString())){
                    if(LH.have_password(id.getText().toString())){
                        password.setVisibility(View.VISIBLE);
                        login.setVisibility(View.VISIBLE);
                    }else {
                        password.setHint("Create password");
                        password.setVisibility(View.VISIBLE);
                        con_password.setVisibility(View.VISIBLE);
                        login.setVisibility(View.VISIBLE);
                    }

                }else {
                    Toast.makeText(getBaseContext(), "Not valid ID",Toast.LENGTH_LONG).show();
                    id.setText(null);
                }

                builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Enter your phone number");
                // Set up the input
                final EditText input = new EditText(LoginActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);
                builder.setMessage("Write correct phone number to sede confirming code to activate the account");
                // Set up the buttons
                builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //m_Text = input.getText().toString();
                        Toast.makeText(getBaseContext(), "Send code sccessful",Toast.LENGTH_LONG).show();
                        builder2.show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                //builder2
                builder2 = new AlertDialog.Builder(LoginActivity.this);
                builder2.setTitle("SMS code");
                // Set up the input
                final EditText code = new EditText(LoginActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
                builder2.setView(code);
                builder2.setMessage("Enter SMS code here to contianu");
                // Set up the buttons
                builder2.setPositiveButton("Activat", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //m_Text = input.getText().toString();
                        Toast.makeText(getBaseContext(), "Login sccessful",Toast.LENGTH_LONG).show();
                        LoginActivity.this.finish();
                    }
                });
                builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });




            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(con_password.getVisibility() == View.INVISIBLE){
                    if(id.getText().toString().equals("123456") && password.getText().toString().equals("123456")){
                        MainActivity.current_user = "123456";
                        MainActivity.current_user_login = "123456";
                        LoginActivity.this.finish();
                        Toast.makeText(getBaseContext(), "Successful login",Toast.LENGTH_LONG).show();
                    }
                }else{
                    if(id.getText().toString().equals("122333")) {
                        if(password.getText().toString().equals(con_password.getText().toString())){
                            Toast.makeText(getBaseContext(), "Enter Code",Toast.LENGTH_LONG).show();
                            builder.show();

                        }else {
                            Toast.makeText(getBaseContext(), "Invaled confirm password ",Toast.LENGTH_LONG).show();
                        }
                    }

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            return true;
        }else if (id == R.id.action_arabic){
            Toast.makeText(getBaseContext(),"Not activated",Toast.LENGTH_SHORT).show();
        }else if (id == R.id.action_login) {
            //Toast.makeText(getBaseContext(),"Not activated",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }else if(id == R.id.action_logout){
            MainActivity.current_user_login = null;
            MainActivity.current_user = null;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem login = menu.findItem(R.id.action_login);
        MenuItem logout = menu.findItem(R.id.action_logout);
        if(MainActivity.current_user_login == null)
        {
            login.setVisible(true);
            logout.setVisible(false);
        }
        else
        {
            logout.setVisible(true);
            login.setVisible(false);
        }
        return true;
    }
}
