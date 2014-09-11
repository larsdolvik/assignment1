package com.example.larsdolvik.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    public void showMap(View v){ //Showing a toast, and opens mapsactivity

        Toast.makeText(getApplicationContext(), "Opening map..", Toast.LENGTH_SHORT).show();

        Intent next = new Intent(this, MapsActivity.class);
        startActivity(next);
    }

    public void showName(View v){ //toasting name or text

        EditText etxt = (EditText)findViewById(R.id.nameField);

        if(etxt.length() > 0 )
            Toast.makeText(getApplicationContext(),"Hello " + etxt.getText().toString(),Toast.LENGTH_SHORT).show();

        else
            Toast.makeText(getApplicationContext(),"You must enter a name!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
