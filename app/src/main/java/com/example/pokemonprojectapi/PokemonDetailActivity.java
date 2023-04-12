package com.example.pokemonprojectapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokemonprojectapi.models.Pokemon;
import com.example.pokemonprojectapi.models.PokemonResponse;
import com.example.pokemonprojectapi.pokeapi.PokeapiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonDetailActivity extends AppCompatActivity {

    private ImageView pokemonImageView;
    private Retrofit retrofit;
    private TextView pokemonNameTextView;
    private TextView heightTextView;
    private TextView weightTextView;
    private Button typeTextView;

    private ProgressBar stateperView;
    private ProgressBar attackView;
    private ProgressBar deffenceView;
    private ProgressBar spaView;
    private ProgressBar spdView;

    public Pokemon pokemon;
    public LinearLayout linearView;
    private static final String TAG = "POKEDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pokemon_detail);
        linearView=findViewById(R.id.linear);
        // Set the background color of the card
        int backgroundColor = generateRandomColor();
        linearView.setBackgroundColor(backgroundColor);

        // This line must be added before setting the content view


// Set the content view
       // setContentView(R.layout.activity_pokemon_detail);



        // Get the selected Pokemon's name and image URL from the Intent extras
        String name = getIntent().getStringExtra("name");

        String imageUrl = getIntent().getStringExtra("imageUrl");
      /*  retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/pokemon/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        heightTextView = findViewById(R.id.heightText);
        getData();*/

       // String height = getIntent().getStringExtra("height");

        // Display the selected Pokemon's name and image

        pokemonNameTextView = findViewById(R.id.pokemonNameTextView);
        pokemonNameTextView.setText(name);
        pokemonImageView = findViewById(R.id.pokemonImageView);
        pokemonNameTextView.setText(name);
        heightTextView = findViewById(R.id.heightText);
        stateperView = findViewById(R.id.stateper);
        attackView=findViewById(R.id.atk);
        deffenceView=findViewById(R.id.def);
        spaView=findViewById(R.id.spa);
        spdView=findViewById(R.id.spd);


        //heightTextView.setText(height);
        weightTextView = findViewById(R.id.weightText);
        typeTextView = findViewById(R.id.typeText);


          new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String  pokemonUrl="https://pokeapi.co/api/v2/pokemon/" + name;
                    // Create a URL object from the pokemonUrl string
                    URL url = new URL(pokemonUrl);


                    // Create a new HttpURLConnection object
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // Set the request method to GET
                    connection.setRequestMethod("GET");

                    // Get the response code
                    int responseCode = connection.getResponseCode();

                    // If the response code is OK (200)
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Get the response body as an InputStream
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        StringBuilder response = new StringBuilder();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        // Parse the JSON response
                        JSONObject pokemon = new JSONObject(response.toString());
                        JSONArray types = pokemon.getJSONArray("types");
                        String type = types.getJSONObject(0).getJSONObject("type").getString("name");

                        ///base States/

                        JSONArray stats = pokemon.getJSONArray("stats");
                        int stat = stats.getJSONObject(0).getInt("base_stat");
                        int stat2 = stats.getJSONObject(1).getInt("base_stat");
                        int stat3 = stats.getJSONObject(2).getInt("base_stat");
                        int stat4 = stats.getJSONObject(3).getInt("base_stat");
                        int stat5 = stats.getJSONObject(4).getInt("base_stat");

                        ///
                        int height = pokemon.getInt("height");
                        int weight = pokemon.getInt("weight");
                        Log.d(TAG, "Selected stat : " + stat);
                        Log.d(TAG, "Selected stat : " + stat2);
                        Log.d(TAG, "Selected stat : " + stat3);
                        Log.d(TAG, "Selected stat : " + stat4);
                        Log.d(TAG, "Selected stat : " + stat5);
                       // String type = pokemon.getString("type");

                        // Update the UI on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                heightTextView.setText( height+" inch");
                                weightTextView.setText(weight+" lbs");
                                typeTextView.setText(type);

                                stateperView.setProgress(stat);
                                ObjectAnimator progressAnimator = ObjectAnimator.ofInt(stateperView, "progress", 0, stat);
                                progressAnimator.setDuration(600);
                                progressAnimator.setInterpolator(new LinearInterpolator());
                                progressAnimator.start();

                                ObjectAnimator progressAnimator2 = ObjectAnimator.ofInt(attackView, "progress", 0, stat2);
                                progressAnimator2.setDuration(300);
                                progressAnimator2.setInterpolator(new LinearInterpolator());
                                progressAnimator2.start();

                                ObjectAnimator progressAnimator3 = ObjectAnimator.ofInt(deffenceView, "progress", 0, stat3);
                                progressAnimator3.setDuration(400);
                                progressAnimator3.setInterpolator(new LinearInterpolator());
                                progressAnimator3.start();

                                ObjectAnimator progressAnimator4 = ObjectAnimator.ofInt(spaView, "progress", 0, stat4);
                                progressAnimator4.setDuration(500);
                                progressAnimator4.setInterpolator(new LinearInterpolator());
                                progressAnimator4.start();

                                ObjectAnimator progressAnimator5 = ObjectAnimator.ofInt(spdView, "progress", 0, stat5);
                                progressAnimator5.setDuration(550);
                                progressAnimator5.setInterpolator(new LinearInterpolator());
                                progressAnimator5.start();

                                attackView.setProgress(stat2);
                                deffenceView.setProgress(stat3);
                                spaView.setProgress(stat4);
                                spdView.setProgress(stat5);
                            }
                        });
                    } else {
                        // If the response code is not OK, log an error message
                        Log.e("HTTP Request", "Error getting response, code: " + responseCode);
                    }

                    // Disconnect the HttpURLConnection object
                    connection.disconnect();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();




        Glide.with(this).load(imageUrl).into(pokemonImageView);

    }
   /* private void getData(){
        String name = getIntent().getStringExtra("name");
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<Pokemon> pokemonRequestCall = service.getPokemon(name);
        pokemonRequestCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response)  {



                    Pokemon pokemonRespuesta = response.body();
                   // heightTextView = findViewById(R.id.heightText);
                   Log.i("info", String.valueOf(pokemonRespuesta.getHeight()));
                    heightTextView.setText(pokemonRespuesta.getHeight()+ " ft ");


            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.i("info", "erroooooor");

            }
        });
    }*/




    private int generateRandomColor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
    }

