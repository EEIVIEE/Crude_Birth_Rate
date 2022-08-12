package sg.edu.rp.c346.id21021397.crudebirthrate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Birth> birthList;

    public CustomAdapter(Context context, int resource, ArrayList<Birth> birth){
        super(context,resource,birth);

        parent_context = context;
        layout_id = resource;
        birthList = birth;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);


        TextView tvValue = rowView.findViewById(R.id.tvValue);
        TextView tvYear = rowView.findViewById(R.id.tvYear);

        Birth currentBirth = birthList.get(position);


        tvValue.setText("Birth rate: " + currentBirth.getValue());
        tvYear.setText("Year: " + currentBirth.getYear());

        return rowView;
    }
}
