package systemz.eva.evacodes;
import android.util.Log;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;




public class AsynchBgThread  {
    String Datastring = "Error Connecting";
    static String responses = "";
    AsyncHttpClient client;

    public String getJSONObj(String url) {
        //  RequestParams params;
        //params = new RequestParams();
        //params.add("username", "user");
        // params.add("password", "password");


        client = new SyncHttpClient();
        client.get(url, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                Datastring="Connect Error! code:"+statusCode;
                if (statusCode == 401) {
                    Datastring = responseString;

                    Log.d("onFailure: ", responseString);
                }
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {
                Datastring = responseString;
                Log.d("onSuccess: ", responseString);
            }


        });

        return Datastring;
    }

}
