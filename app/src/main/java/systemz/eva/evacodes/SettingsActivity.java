package systemz.eva.evacodes;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {
    // All static variables
    static String URL = "http://api.androidhive.info/music/music.xml";
    // XML node keys
    static final String KEY_CODE = "code"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_CODE_HASH = "code_item";
    static final String KEY_USER= "userid";
    static final String KEY_DATE = "date";
    static final String KEY_THUMB_URL = "thumb_url";


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_settings);

        View inflatedView = getLayoutInflater().inflate(R.layout.app_bar_main, null);
        Toolbar toolbar = (Toolbar) inflatedView.findViewById(R.id.toolbar);
        toolbar.setTitle("EvA Systemz Server");

    };
}
