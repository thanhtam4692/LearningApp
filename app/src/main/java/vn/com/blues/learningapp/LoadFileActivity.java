package vn.com.blues.learningapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class LoadFileActivity extends AppCompatActivity {

    ArrayList<Entry> listOQ = null;
    public static EditText start_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_file);
        getSupportActionBar().setTitle("File Information");

        Intent intent = getIntent();
        String file_path = intent.getExtras().getString("output_new_file_name");

        start_question = (EditText) findViewById(R.id.edit_startQuestion);

        if(intent.getExtras().getString("current_position") != null){
            start_question.setText(intent.getExtras().getString("current_position"));
        } else {
            start_question.setText("1");
        }
        Log.e("LF", file_path);

        File file = new File (file_path);
        String filename = file_path.split("/")[file_path.split("/").length - 1];
//        System.out.println(filename.split("\\.").length);
        String fileExtension = filename.split("\\.")[filename.split("\\.").length - 1];

        //Read text from file
        StringBuilder text = new StringBuilder();
        FileInputStream fis = null;
        BufferedReader bfr = null;
        ArrayList<Entry> listOfQuestions = new ArrayList<Entry>();
        try {
            if(!"txt".equals(fileExtension)) {
                throw new IOException();
            }
            fis = new FileInputStream(file);
            bfr = new BufferedReader(new InputStreamReader(fis));
            String line;
            String[] splited = null;
            Entry entry = null;
            while ((line = bfr.readLine()) != null) {
//                Log.e("LF", "qw");
//                splited = null;
//                line = line.replace("\"", "");
                splited = line.split(",");
//                System.out.println(splited[6]);
//                entry = null;
                if(splited.length != 7){
                    throw new IOException();
                }
                entry = new Entry(splited[0], splited[1], splited[2], splited[3], splited[4], splited[5], splited[6]);
//                System.out.println(entry.getQuestion());
                listOfQuestions.add(entry);
            }
        }
        catch (IOException e) {
            final Dialog dil = new Dialog(this);
            dil.setContentView(R.layout.diaglog);
            dil.setTitle("Result");
            Button butt_next = (Button) dil.findViewById(R.id.button_next);
            TextView txt_result = (TextView) dil.findViewById(R.id.txt_chooseResult);

            txt_result.setText("Invalid file");
            butt_next.setText("RETURN");

            dil.show();

            butt_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        finally {
            try {
                if(bfr != null){
                    bfr.close();
                }
                if(fis != null){
                    fis.close();
                }

            } catch (IOException e) {
//                e.printStackTrace();
            }
        }

        TextView txt_title = (TextView) findViewById(R.id.file_title);
        txt_title.setText(filename);

        TextView txt_nOQ = (TextView) findViewById(R.id.text_numOfQuestion);
        txt_nOQ.setText(Integer.toString(listOfQuestions.size() - 1));



        listOQ = listOfQuestions;
    }

    public void toGo(View v){
        Intent intent = new Intent(v.getContext(), EntryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("array_questions", listOQ);

        EditText et = (EditText) findViewById(R.id.edit_startQuestion);
        bundle.putInt("current_question", Integer.valueOf(et.getText().toString()));

        intent.putExtras(bundle);
        this.startActivityForResult(intent, 2);
    }

    public void toReturn(View v){
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_load_file, menu);
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
    public void onBackPressed() {
//        Log.e("Msg", "Back pressed");
//        Intent intent = new Intent();
//        setResult(24, intent);
        finish();
    }
}
