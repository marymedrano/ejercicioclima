package com.example.prueba1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView weatherTextView = (TextView) findViewById(R.id.weatherText);
        final EditText cityEditText = (EditText) findViewById(R.id.cityEditText);
        final Button weatherButton = (Button) findViewById(R.id.weatherButton);

        //LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.openweathermap.org/data/2.5/weather?q=London&APPID=12198b9facea441be4dde6b524c94b97";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new GsonBuilder().create();

                    // Define Response class to correspond to the JSON response returned
                    WeatherResponse weatherResponse = gson.fromJson(response, WeatherResponse.class);
                    weatherTextView.setText(weatherResponse.getName() + " -> " + weatherResponse.getMain().getTemp());
                }
            },
            new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    weatherTextView.setText("That didn't work!");
                }
            }
        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        weatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityEditText.getText() + "&APPID=12198b9facea441be4dde6b524c94b97";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Gson gson = new GsonBuilder().create();

                                // Define Response class to correspond to the JSON response returned
                                WeatherResponse weatherResponse = gson.fromJson(response, WeatherResponse.class);
                                weatherTextView.setText(weatherResponse.getName() + " -> " + weatherResponse.getMain().getTemp());
                            }
                        },
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                weatherTextView.setText("That didn't work!");
                            }
                        }
                );

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
}
