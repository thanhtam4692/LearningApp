package vn.com.blues.learningapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Blues on 5/25/15.
 */
public class CustomGridViewAdapter extends ArrayAdapter<Entry> {
    Context context;
    int layoutResourceId;
    ArrayList<Entry> data = new ArrayList<Entry>();

    public CustomGridViewAdapter(Context context, int layoutResourceId, ArrayList<Entry> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
            holder.txt_ques = (TextView) row.findViewById(R.id.txt_question);
            holder.butt_anA = (Button) row.findViewById(R.id.button_anA);
            holder.butt_anB = (Button) row.findViewById(R.id.button_anB);
            holder.butt_anC = (Button) row.findViewById(R.id.button_anC);
            holder.butt_anD = (Button) row.findViewById(R.id.button_anD);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        Entry entry = data.get(position);
        StringBuilder sb = new StringBuilder();
        sb.append(entry.getId());
        sb.append(". ");
        sb.append(entry.getQuestion());
        holder.txt_ques.setText(sb.toString());
        holder.butt_anA.setText(entry.getAnswerA());
        holder.butt_anB.setText(entry.getAnswerB());
        holder.butt_anC.setText(entry.getAnswerC());
        holder.butt_anD.setText(entry.getAnswerD());

        return row;
    }

    static class RecordHolder {
        TextView txt_ques;
        Button butt_anA;
        Button butt_anB;
        Button butt_anC;
        Button butt_anD;

    }

}
