package vn.com.blues.learningapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;


public class EntryActivity extends AppCompatActivity {

    private String theAnswer = "";
    private ArrayList listOfQuestion = null;
    private int currQuestion = 0;
    EntryActivity enAc = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        getSupportActionBar().setTitle("Question");

        Intent intent = getIntent();
        ArrayList<Entry> list = (ArrayList<Entry>) intent.getExtras().getSerializable("array_questions");
        listOfQuestion = list;
//        System.out.println(list.get(1).getQuestion());
        int current = intent.getExtras().getInt("current_question");
        currQuestion = current;
        Entry entry = list.get(current);
        theAnswer = entry.getTheAnswer().replace("\"", "");
        System.out.println(theAnswer);
        ArrayList<Entry> listWithOnlyOneEntry = new ArrayList<>();
        listWithOnlyOneEntry.add(entry);
        GridView gv = (GridView) this.findViewById(R.id.grid_entry);
        CustomGridViewAdapter customGridAdapter = new CustomGridViewAdapter(this, R.layout.eachentry, listWithOnlyOneEntry);
        gv.setAdapter(customGridAdapter);

    }

    private void chooseButton(View v, String x){
        final Dialog dil = new Dialog(this);
        dil.setContentView(R.layout.diaglog);
        dil.setTitle("Result");
        Button butt_next = (Button) dil.findViewById(R.id.button_next);
        TextView txt_result = (TextView) dil.findViewById(R.id.txt_chooseResult);
        if(x.equals(theAnswer)){
            txt_result.setText("You're right");
            butt_next.setText("NEXT");
            isRight = 1;
        } else {
            txt_result.setText("You're wrong");
            butt_next.setText("RETURN");
            isRight = 0;
        }
        dil.show();
        butt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRight == 1) {
                    if (currQuestion == (listOfQuestion.size() - 1)) {
                        Intent intent = new Intent(enAc, LoadFileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        enAc.startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(v.getContext(), EntryActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("array_questions", listOfQuestion);
                        bundle.putInt("current_question", currQuestion + 1);

                        intent.putExtras(bundle);
                        enAc.startActivity(intent);
                        finish();
                    }
                }
                dil.dismiss();
            }
        });
    }

    private int isRight = 0;
    public void chooseA(View v){
//        System.out.println(theAnswer);
        chooseButton(v, "A");
    }

    public void chooseB(View v){
//        System.out.println("a" + theAnswer + "a");
        chooseButton(v, "B");
    }

    public void chooseC(View v){
//        System.out.println("choose A");
        chooseButton(v, "C");
    }

    public void chooseD(View v){
        chooseButton(v, "D");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry, menu);
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

        LoadFileActivity.start_question.setText(String.valueOf(currQuestion));

        Intent intent = new Intent(this, LoadFileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);
        finish();
    }
}
