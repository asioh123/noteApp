package com.example.assy.noteapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView noteText;
    String valueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteText = (TextView)findViewById(R.id.noteText);

        SharedPreferences editor = getPreferences(MODE_PRIVATE);

        //SharedPreferences get back string
        valueText = editor.getString("text",null);
        //set text on the textView
        noteText.setText(valueText);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //when the app close it saves in SharedPreferences
        valueText = noteText.getText().toString();
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString("text", valueText);
        editor.apply();
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

        //get the item undo from the menu
        if (id == R.id.undo)
        {
            //reset all the text in the TextField and in SharedPreferences
            noteText.setText("");
            valueText="";
            SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
            editor.putString("text", valueText);
            editor.apply();
        }

        return super.onOptionsItemSelected(item);
    }
}
