package sg.edu.rp.c346.id21021397.crudebirthrate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvBirth = findViewById(R.id.lvBirth);
        client = new AsyncHttpClient();

    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Birth> alBirth = new ArrayList<Birth>();

        client.get("https://data.gov.sg/api/action/datastore_search?resource_id=2ba37efc-5411-4f1f-aecf-ea2455c9236d&limit=5", new JsonHttpResponseHandler() {

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
                        alBirth.add(birth);
                    }
                } catch (JSONException e) {

                }
                CustomAdapter aaBirth = new CustomAdapter(MainActivity.this,R.layout.row,alBirth);
                lvBirth.setAdapter(aaBirth);
            }
        });
    }
}