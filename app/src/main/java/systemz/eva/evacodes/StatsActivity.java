package systemz.eva.evacodes;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ViewSwitcher;

import static systemz.eva.evacodes.R.id.toolbar;

public class StatsActivity extends AppCompatActivity {


    // All static variables

    private static String url="google.com";
    private static String response_string =null;




    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_main);



        View inflatedView = getLayoutInflater().inflate(R.layout.app_bar_main, null);
        Toolbar toolbar = (Toolbar) inflatedView.findViewById(R.id.toolbar);
        toolbar.setTitle("Stats");

        setDataToText("http://169.254.55.70/");





        };
    private void setDataToText(final String urlStr) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //A code to retrieve data is executed
                //Data is Converted and added to the string str_Data;
                AsynchBgThread splash= new AsynchBgThread();
                String response_string =null;
                response_string =splash.getJSONObj(urlStr);

                final WebView webView = (WebView) findViewById(R.id.stats_webview);
                webView .loadData(response_string, "text/html; charset=utf-8", "UTF-8");
            }
        }).start();
    }
    }
