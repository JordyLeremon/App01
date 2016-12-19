package org.esiea.leremon.app01;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.view.View.*;
import android.widget.Toast;
import android.net.Uri;


public class MainActivity extends AppCompatActivity implements OnClickListener{
    private DatePickerDialog dpd = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_hello_world = (Button) findViewById(R.id.btn_hello_world);
        btn_hello_world.setText("Hello");
        btn_hello_world.setOnClickListener(this);









        DatePickerDialog.OnDateSetListener odsl = new DatePickerDialog.OnDateSetListener() {
            @Override

            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                TextView tv_hello_world = (TextView) findViewById(R.id.tv_hello_world);
                tv_hello_world.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };
                dpd = new DatePickerDialog(this, odsl, 2016, 11, 18);

            }
    public void Go(View v) {
        //on creer une nouvelle intent on definit la class de depart ici this et la class d'arrivé ici SecondActivite
        Intent intent = new Intent(this, SecondeActivity.class);
        //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici SecondActivite
        startActivity(intent);
    }
    public void geo(View v){

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Londre"));
        startActivity(intent);

    }
    public void Load(View v){

        IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(),intentFilter);
        GetBiersServices.startActionget_all_biers(this);

    }

        @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Hello", Toast.LENGTH_SHORT).show();
                dpd.show();
                notification_test();
            }
    private void search(){
        Toast.makeText(this, R.string.Search,Toast.LENGTH_LONG).show();
    }
    private void help(){
        Toast.makeText(this,R.string.Help,Toast.LENGTH_LONG).show();
    }
    public boolean onOptionsItemSelected(MenuItem item){
       switch (item.getItemId()){
           case R.id.app_bar_search:
               search();
               return true;
           case R.id.action_aide:
               help();
               return true;
        }
        return super.onOptionsItemSelected(item);
        }

    public void notification_test(){
        NotificationCompat.Builder noti = new NotificationCompat.Builder(this);
        noti.setSmallIcon(R.mipmap.ic_launcher);
        noti.setContentTitle("Notification");
        noti.setContentText("Inscription");
        NotificationManager notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notiManager.notify(2, noti.build());
    }

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.toast_me, menu);
        return true;
    }
    public static final String BIERS_UPDATE="com.octip.cours.inf4042_11.BIERS_UPDATE";

        public final class BierUpdate extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("TAG",getIntent().getAction());
            Toast.makeText(getApplicationContext(),"En cours de Telechargement....", Toast.LENGTH_SHORT).show();
            }



    }
        }
