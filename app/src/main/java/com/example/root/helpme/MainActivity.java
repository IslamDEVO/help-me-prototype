package com.example.root.helpme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String user = "123456";
    public static String current_user = null;
    public static String current_user_login = null;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Dialog dialog_method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Toast.makeText(getBaseContext(),"Not activated",Toast.LENGTH_SHORT).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //SharedPreferences
        //sharedPreferences = getSharedPreferences("current_user",MODE_PRIVATE);
        //editor = sharedPreferences.edit();

        //Dialog Method
        dialog_method = new Dialog(this);
        dialog_method.setContentView(R.layout.login_method);
        dialog_method.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Button Close
        TextView btn_close = (TextView) dialog_method.findViewById(R.id.dilog_method_btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_method.dismiss();
            }
        });

        //Button QR
        ImageView btn_qr = (ImageView) dialog_method.findViewById(R.id.dilog_method_btn_qr);
        btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();


            }
        });
        ImageView btn_qr_home_page = (ImageView) findViewById(R.id.home_btn_qr);
        btn_qr_home_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();


            }
        });

        //Button ID
        ImageView btn_id = (ImageView) dialog_method.findViewById(R.id.dilog_method_btn_id);
        btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String m_Text = "";

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Search By ID");
                builder.setMessage("Please enter the id, that you need information about you");
                // Set up the input
                final EditText input = new EditText(MainActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(input.getText().toString().equals(user)) {
                            current_user = input.getText().toString();
                            if(dialog_method.isShowing()) {
                                dialog_method.dismiss();
                            }else {
                                Toast.makeText(getBaseContext(),"No correct id",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
        ImageView btn_id_home_page = (ImageView) findViewById(R.id.home_btn_id);
        btn_id_home_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String m_Text = "";

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Search By ID");
                builder.setMessage("Please enter the id, that you need information about you");
                // Set up the input
                final EditText input = new EditText(MainActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(input.getText().toString().equals(user)) {
                            current_user = input.getText().toString();
                            if(dialog_method.isShowing()) {
                                dialog_method.dismiss();
                            }
                        }else {
                            Toast.makeText(getBaseContext(),"No correct id",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        //Button Fingerprint
        ImageView btn_fing = (ImageView) dialog_method.findViewById(R.id.dilog_method_btn_fingerprint);
        btn_fing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"Not activated",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView btn_fing_home_page = (ImageView) findViewById(R.id.home_btn_fingerprinting);
        btn_fing_home_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"Not activated",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.e("Scan*******", "Cancelled scan");

            } else {
                Log.e("Scan", "Scanned");

                /*Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();*/
                if(result.getContents().toString().equals(user)){
                    current_user = result.getContents().toString();
                    if(dialog_method.isShowing()) {
                        dialog_method.dismiss();
                    }
                }else {
                    Toast.makeText(getBaseContext(),"No correct id",Toast.LENGTH_LONG).show();
                }
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
            current_user_login = null;
            current_user = null;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem login = menu.findItem(R.id.action_login);
        MenuItem logout = menu.findItem(R.id.action_logout);
        if(current_user_login == null)
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_personal_information) {
            // Handle the camera action
            //String user_id = sharedPreferences.getString("id",null);
            if(current_user == null){
                dialog_method.show();
            }else {
                Intent intent = new Intent(getBaseContext(), Profile.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_medical_history) {
            //String user_id = sharedPreferences.getString("id",null);
            if(current_user == null){
                dialog_method.show();
            }else {
                Intent intent = new Intent(getBaseContext(), History.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_exit) {
            current_user = null;
            current_user_login = null;
            finish();
            System.exit(0);

        } /*else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
