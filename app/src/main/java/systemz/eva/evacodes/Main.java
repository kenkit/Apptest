package systemz.eva.evacodes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import static systemz.eva.evacodes.R.string.action_settings;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("E.v.A Systemz");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       // View inflatedView = getLayoutInflater().inflate(R.layout.content_main, null);



        setDataToText("http://169.254.55.70/cloud_commander/adds.html",0);

/////////////////
        WebView userinfo = (WebView) findViewById(R.id.userinfo);
        userinfo .getSettings().setJavaScriptEnabled(true);
        userinfo .loadData("<center><h1>Loading.........</center></h1>", "text/html; charset=utf-8", "UTF-8");
////////////////////////



        setDataToText("http://169.254.55.70/cloud_commander/userinfo.html",1);


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
        if (id == R.id.settings_main) {
            Intent i = new Intent(Main.this,SettingsActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mwitu) {


            Intent i = new Intent(Main.this,MainActivity.class);
            i.putExtra("id", 1);
            startActivity(i);

            // Handle the camera action
        } else if (id == R.id.nav_my_codes) {

            Intent i = new Intent(Main.this,MainActivity.class);
            i.putExtra("id", 2);
            startActivity(i);
            //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            //toolbar.setTitle("My Codes");

        } else if (id == R.id.nav_stats) {

            Intent i = new Intent(Main.this,StatsActivity.class);
            startActivity(i);

        }
         else if (id == R.id.nav_add_code) {

            Intent i = new Intent(Main.this,AddCodeActivity.class);
        startActivity(i);


    }
        else if (id == R.id.add_credits) {

            Intent i = new Intent(Main.this,AddCreditsActivity.class);
            startActivity(i);

        }
        else if (id == R.id.nav_settings) {

            Intent i = new Intent(Main.this,SettingsActivity.class);
            startActivity(i);


        }
        else {
            View inflatedView = getLayoutInflater().inflate(R.layout.app_bar_main, null);
            Toolbar toolbar = (Toolbar) inflatedView.findViewById(R.id.toolbar);
            toolbar.setTitle("EvA Systemz");
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setDataToText(final String urlStr,final int mode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //A code to retrieve data is executed
                //Data is Converted and added to the string str_Data;
                AsynchBgThread splash= new AsynchBgThread();
                String response_string =null;
                response_string =splash.getJSONObj(urlStr);


                if(mode==0) {
                    WebView webview2 = (WebView) findViewById(R.id.adds);
                    webview2.getSettings().setJavaScriptEnabled(true);
                    webview2.loadData(response_string, "text/html; charset=utf-8", "UTF-8");
                }
                else
                {
                    WebView mWebView = (WebView) findViewById(R.id.userinfo);
                    mWebView .getSettings().setJavaScriptEnabled(true);
                    mWebView .loadData(response_string, "text/html; charset=utf-8", "UTF-8");
                }
            }
        }).start();
    }
}


