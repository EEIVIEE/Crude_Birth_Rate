package sg.edu.rp.c346.id21021397.crudebirthrate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvBirth;
    AsyncHttpClient client;
    Spinner spBirth;
    Button btnSearch;
    String period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvBirth = findViewById(R.id.lvBirth);
        client = new AsyncHttpClient();
        spBirth = findViewById(R.id.spBirth);
        btnSearch = findViewById(R.id.btnPeriod);
        spBirth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        period = "1960s";
                        break;
                    case 1:
                        period = "1970s";
                        break;
                    case 2:
                        period = "1980s";
                        break;
                    case 3:
                        period = "1990s";
                        break;
                    case 4:
                        period = "2000s";
                        break;
                    case 5:
                        period = "2010s";
                        break;
                    default:
                        period = "";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Birth> alBirth = new ArrayList<Birth>();
        CustomAdapter aaBirth = new CustomAdapter(MainActivity.this,R.layout.row,alBirth);
        client.get("https://data.gov.sg/api/action/datastore_search?resource_id=2ba37efc-5411-4f1f-aecf-ea2455c9236d", new JsonHttpResponseHandler() {

            String value;
            String year;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject jsonObjItems = response.getJSONObject("result");
                    JSONArray jsonArrFields = jsonObjItems.getJSONArray("records");
                    for (int i = 0; i < jsonArrFields.length();i++){
                        JSONObject jsonObjFields = jsonArrFields.getJSONObject(i);
                        value = jsonObjFields.getString("value");
                        year = jsonObjFields.getString("year");
                        Birth birth = new Birth(value,year);
                        if (period.equals("1960s")){
                            if (Integer.parseInt(year) < 1970 && Integer.parseInt(year) >= 1960){
                                alBirth.add(birth);
                            }
                        }else if(period.equals("1970s")){
                            if (Integer.parseInt(year) < 1980 && Integer.parseInt(year) >= 1970){
                                alBirth.add(birth);
                            }
                        }else if(period.equals("1980s")){
                            if (Integer.parseInt(year) < 1990 && Integer.parseInt(year) >= 1980){
                                alBirth.add(birth);
                            }
                        }else if(period.equals("1990s")){
                            if (Integer.parseInt(year) < 2000 && Integer.parseInt(year) >= 1990){
                                alBirth.add(birth);
                            }
                        }else if(period.equals("2000s")){
                            if (Integer.parseInt(year) < 2010 && Integer.parseInt(year) >= 2000){
                                alBirth.add(birth);
                            }
                        }else if(period.equals("2010s")){
                            if (Integer.parseInt(year) < 2020 && Integer.parseInt(year) >= 2010){
                                alBirth.add(birth);
                            }
                        }

                    }
                } catch (JSONException e) {

                }

                lvBirth.setAdapter(aaBirth);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alBirth.clear();

                client.get("https://data.gov.sg/api/action/datastore_search?resource_id=2ba37efc-5411-4f1f-aecf-ea2455c9236d", new JsonHttpResponseHandler() {

                    String value;
                    String year;

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            JSONObject jsonObjItems = response.getJSONObject("result");
                            JSONArray jsonArrFields = jsonObjItems.getJSONArray("records");
                            for (int i = 0; i < jsonArrFields.length();i++){
                                JSONObject jsonObjFields = jsonArrFields.getJSONObject(i);
                                value = jsonObjFields.getString("value");
                                year = jsonObjFields.getString("year");
                                Birth birth = new Birth(value,year);
                                if (period.equals("1960s")){
                                    if (Integer.parseInt(year) < 1970 && Integer.parseInt(year) >= 1960){
                                        alBirth.add(birth);
                                    }
                                }else if(period.equals("1970s")){
                                    if (Integer.parseInt(year) < 1980 && Integer.parseInt(year) >= 1970){
                                        alBirth.add(birth);
                                    }
                                }else if(period.equals("1980s")){
                                    if (Integer.parseInt(year) < 1990 && Integer.parseInt(year) >= 1980){
                                        alBirth.add(birth);
                                    }
                                }else if(period.equals("1990s")){
                                    if (Integer.parseInt(year) < 2000 && Integer.parseInt(year) >= 1990){
                                        alBirth.add(birth);
                                    }
                                }else if(period.equals("2000s")){
                                    if (Integer.parseInt(year) < 2010 && Integer.parseInt(year) >= 2000){
                                        alBirth.add(birth);
                                    }
                                }else{
                                    if (Integer.parseInt(year) < 2020 && Integer.parseInt(year) >= 2010){
                                        alBirth.add(birth);
                                    }
                                }

                            }
                        } catch (JSONException e) {

                        }

                        lvBirth.setAdapter(aaBirth);
                    }
                });
            }
        });

    }
}