package hk.ust.cse.comp107x.greetfriend;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.joda.time.DateTimeUtils;

import java.util.Calendar;

public class MainActivity extends ListActivity {

    private static final String TAG = "MainActivity";
    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        names = getResources().getStringArray(R.array.friends);
        setListAdapter(new ArrayAdapter<>(this,R.layout.friend_item,names));
        Log.i(TAG,"in onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"in onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"in onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"in onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"in onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"in onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"in onDestroy()");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String greetingMessage = selectGreeting();
        Log.d(TAG, greetingMessage);

        Intent in = new Intent(this, ShowMessage.class);
        in.putExtra("message", String.format("%s %s!", greetingMessage, names[(int) id]));
        startActivity(in);
    }

    private String selectGreeting(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(DateTimeUtils.currentTimeMillis());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        Log.d(TAG, Integer.toString(hour));

        String messageGreeting="";

        if(hour>=6 && hour< 12)
            messageGreeting += getString(R.string.greetMorningString);
        if(hour>=12 && hour< 17)
            messageGreeting += getString(R.string.greetAfternoonString);
        if(hour>=17 && hour< 21)
            messageGreeting +=  getString(R.string.greetEveningString);
        if(hour>=21 || hour<6)
            messageGreeting += getString(R.string.greetNightString);

        return messageGreeting;
    }

}
