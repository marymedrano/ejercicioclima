package com.example.prueba1;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        final TextView helloTextView = (TextView) findViewById(R.id.text_view_id);

        //LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=12198b9facea441be4dde6b524c94b97";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new GsonBuilder().create();

                        // Define Response class to correspond to the JSON response returned
                        WeatherResponse weatherResponse = gson.fromJson(response, WeatherResponse.class);
                        helloTextView.setText("Response is: " + weatherResponse.getMessage());
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        helloTextView.setText("That didn't work!");
                    }
                }
        );

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    class WeatherResponse {
        private int cod;
        private String message;
        private int cnt;
        private Location city;

        public WeatherResponse() {
        }

        public int getCod() {
            return cod;
        }

        public void setCod(int cod) {
            this.cod = cod;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        public Location getCity() {
            return city;
        }

        public void setCity(Location city) {
            this.city = city;
        }
    }

    class Location {
        private int id;

        private String name;

        private Coord coord;

        private String country;

        public Location() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Coord getCoord() {
            return coord;
        }

        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

    class Coord {
        private float lat;

        private float lon;

        public Coord() {
        }

        public float getLat() {
            return lat;
        }

        public void setLat(float lat) {
            this.lat = lat;
        }

        public float getLon() {
            return lon;
        }

        public void setLon(float lon) {
            this.lon = lon;
        }
    }
}
