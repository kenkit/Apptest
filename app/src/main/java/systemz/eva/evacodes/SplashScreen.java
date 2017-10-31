package systemz.eva.evacodes;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 9000;
    private String url="http://169.254.55.70/cloud_commander/ok.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


////////////////////Problem over here
       final TextView textView = (TextView)findViewById(R.id.loading);
        textView.setText("Checking user info.");
////////////////////



        setDataToText(url);



        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, Main.class);
                startActivity(i);
                    //textView.setText(textView.getText()+".");


                textView.setText("Loading.");


                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    private void setDataToText(final String urlStr) {
       Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //A code to retrieve data is executed
                //Data is Converted and added to the string str_Data;
                String response_string =null;
                AsynchBgThread splash= new AsynchBgThread();
                TextView textView = (TextView)findViewById(R.id.loading);
                response_string =splash.getJSONObj(urlStr);
                textView.setText(response_string) ;

            }
        });
        t.start();

        try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); }
}

}