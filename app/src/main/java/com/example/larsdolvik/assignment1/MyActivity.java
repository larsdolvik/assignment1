package com.example.larsdolvik.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity {
    DatabaseHandler db = new DatabaseHandler(this); //For the databases with name

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

        if(etxt.length() > 0 ) {
            Toast.makeText(getApplicationContext(), "Hello " + etxt.getText().toString(), Toast.LENGTH_SHORT).show();
            addNameDataToDB(etxt.getText().toString()); //adds input to database if stringlength > 0
         }
        else {
            Toast.makeText(getApplicationContext(), "You must enter a name!", Toast.LENGTH_SHORT).show();
        }
        getNameDataFromDB();                            //gets history from database
    }
    public void addNameDataToDB(String name) {
        db.addName(new Name(name));                      //adds name to the database
    }

    public void getNameDataFromDB() {                   //gets 3 latest name from database
        int namesInDatabase = db.getNameCount()-1; // We set it -1 so we don't get the name just
        int namesPrinted = 0;                      //written in the log that is shown

        TextView last3names = (TextView) findViewById(R.id.loglast3);

        last3names.setText("Here are the 3 latest names in the database:");

        if (namesInDatabase == 0)
            last3names.append("\nNo names have been added yet!");

        // Gets us the 3 newest names, from newest and backward.
        while (namesInDatabase != 0 && namesPrinted < 3) {
            Name temporary = db.getName(namesInDatabase);    //gets the newest name
            last3names.append("\n" + temporary.getName());   //displays

            namesInDatabase--;
            namesPrinted++;
        }
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
