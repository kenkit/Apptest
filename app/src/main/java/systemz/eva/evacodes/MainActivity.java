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

public class MainActivity extends AppCompatActivity {
	// All static variables



    static String URL = "http://api.androidhive.info/music/music.xml";
    // XML node keys
    static final String KEY_CODE = "code"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_CODE_HASH = "code_item";
    static final String KEY_USER= "userid";
    static final String KEY_DATE = "date";
    static final String KEY_THUMB_URL = "thumb_url";

    ListView list;
    LazyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");

        View inflatedView = getLayoutInflater().inflate(R.layout.app_bar_main, null);
        Toolbar toolbar = (Toolbar) inflatedView.findViewById(R.id.toolbar);
        toolbar.setTitle("EvA Systemz");

        if (id==1){
            inflatedView = getLayoutInflater().inflate(R.layout.app_bar_main, null);
            toolbar = (Toolbar) inflatedView.findViewById(R.id.toolbar);
            toolbar.setTitle("*New*");
            URL="main";
            Log.d("Debug", URL);
        }
        else if (id==2){
            inflatedView = getLayoutInflater().inflate(R.layout.app_bar_main, null);
            toolbar = (Toolbar) inflatedView.findViewById(R.id.toolbar);
            toolbar.setTitle("Reserved codes");
            URL="mycodes";
            Log.d("Debug", URL);
        }



        ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

        XMLParser parser = new XMLParser();
        String xml=null;

                xml =parser.getXmlFromUrl(URL); // getting XML from URL

        Document doc = parser.getDomElement(xml); // getting DOM element
        NodeList nl = doc.getElementsByTagName(KEY_CODE);
        // looping through all song nodes <song>
        for (int i = 0; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key => value
            map.put(KEY_ID, parser.getValue(e, KEY_ID));
            map.put(KEY_CODE_HASH, parser.getValue(e, KEY_CODE_HASH));
            map.put(KEY_USER, parser.getValue(e, KEY_USER));
            map.put(KEY_DATE, parser.getValue(e, KEY_DATE));
            map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));

            // adding HashList to ArrayList
            songsList.add(map);
        }


        list=(ListView)findViewById(R.id.list);


        // Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, songsList);
        list.setAdapter(adapter);


        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //view.setSelected(true);
                //view.bringToFront();
              //  ListView k= (ListView) view.findViewWithTag(KEY_CODE);

               // Log.d("listitem:::::::: ", ""+ k.toString());

            }
        });
    }
}