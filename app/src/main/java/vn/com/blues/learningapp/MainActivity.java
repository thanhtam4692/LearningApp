package vn.com.blues.learningapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import ar.com.daidalos.afiledialog.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Learning App");

        Button button_import = (Button) findViewById(R.id.button_import);
    }

    public void toImport(View v){
//        Log.e("Msg","What you have to print");
//        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//        alertDialog.setTitle("Alert");
//        alertDialog.setMessage("Alert message to be shown");
//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//        alertDialog.show();
        Intent intent = new Intent(this, FileChooserActivity.class);
        ArrayList<String> extensions = new ArrayList<String>();
        extensions.add("txt");
        intent.putStringArrayListExtra("input_regex_filter", extensions);
        this.startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 0){
            Log.e("Msg", data.getExtras().getString("output_new_file_name"));
            Intent intent = new Intent(this, LoadFileActivity.class);
//          Bundle bundle = new Bundle();
//		    bundle.putSerializable(OUTPUT_FILE_OBJECT, file);
//          bundle.putString("output_new_file_name", data.getExtras().getString("output_new_file_name"));
            intent.putExtras(data.getExtras());
            this.startActivityForResult(intent, 1);
        } else {
//            Log.e("Msg", "second returned");
        }

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
}
