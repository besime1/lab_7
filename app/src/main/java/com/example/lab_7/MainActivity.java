package com.example.lab_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    static MainActivity instance = null;

    public ListView listView;
    private Adapter listAdapter;
    private FrameLayout frameLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        frameLayout = findViewById(R.id.frame_layout);
        listAdapter = new Adapter(this);
        listView.setAdapter(listAdapter);

        FetchCharacters fetchCharacters = new FetchCharacters();
        fetchCharacters.execute();

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            if (frameLayout == null) {
                // FrameLayout is not present, indicating that the device is a phone
                Toast.makeText(MainActivity.this, "Clicked item " + i + " on phone", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, EmptyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("itemData", (String) adapterView.getItemAtPosition(i));
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                // FrameLayout is present, indicating that the device is a tablet
                Toast.makeText(MainActivity.this, "Clicked item " + i + " on tablet", Toast.LENGTH_SHORT).show();
                DetailsFragment detailsFragment = new DetailsFragment((String) adapterView.getItemAtPosition(i));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, detailsFragment);
                fragmentTransaction.commitNow();
            }
        });

    }

    public static MainActivity getInstance() {
        if (instance != null) {
            return instance;
        }
        return new MainActivity();
    }

    public void setCharacters(String[] characters) {
        for (String param : characters) {
            System.out.println("param = " + param);
        }
        listAdapter.setData(characters);
    }

}

class FetchCharacters extends AsyncTask<String, Integer, String> {
    private static final String URL = "https://swapi.dev/api/people/?format=json";

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            String jsonPayload = buffer.toString();
            JSONObject response = new JSONObject(jsonPayload);
            if (response != null) {
                JSONArray characters = response.getJSONArray("results");
                String[] params = new String[characters.length()];
                for (int i = 0; i < characters.length(); i++) {
                    JSONObject temp = characters.getJSONObject(i);
                    params[i] = temp.getString("name") + "," + temp.getString("height") + "," + temp.getString("mass");
                }
                MainActivity.getInstance().runOnUiThread(() -> MainActivity.getInstance().setCharacters(params));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}