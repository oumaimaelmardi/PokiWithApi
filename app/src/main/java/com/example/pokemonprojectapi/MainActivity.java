package com.example.pokemonprojectapi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.pokemonprojectapi.models.Pokemon;
import com.example.pokemonprojectapi.models.PokemonResponse;
import com.example.pokemonprojectapi.pokeapi.PokeapiService;

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

public class MainActivity extends AppCompatActivity implements ListaPokemonAdapter.OnItemClickListener {
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;
    private int offset;


    private boolean aptoParaCargar;
    private static final String TAG = "POKEDEX";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setLogo(R.drawable.logo);
        actionBar.setDisplayUseLogoEnabled(true);*/




        recyclerView = findViewById(R.id.recyclerView);
        listaPokemonAdapter = new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
      //limit get data pokemon
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                             @Override
                                             public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                                 super.onScrolled(recyclerView, dx, dy);

                                                 if (dy > 0) {
                                                     int visibleItemCount = layoutManager.getChildCount();
                                                     int totalItemCount = layoutManager.getItemCount();
                                                     int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                                                     if (aptoParaCargar) {
                                                         if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                                                             Log.i(TAG, " we reached the end.");

                                                             aptoParaCargar = false;
                                                             offset += 100;
                                                             getData(offset);
                                                         }
                                                     }
                                                 }
                                             }
                                         });
        //

        retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build();
        aptoParaCargar = true;
        offset = 0;
        getData(offset);
        listaPokemonAdapter.setOnItemClickListener(this);


    }

    private void getData(int offset){
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonResponse> pokemonResponseCall= service.getListPokemon(100, offset);

        pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful()) {


                    PokemonResponse pokemonRespuesta = response.body();
                   ArrayList<Pokemon> listePokemon = pokemonRespuesta.getResults();
                     listaPokemonAdapter.adicionarListaPokemon(listePokemon);

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(Pokemon pokemon) {
        // Do your processing with the selected item here
        // Pass the selected Pokemon's name and image URL to the PokemonDetailActivity
        Intent intent = new Intent(this, PokemonDetailActivity.class);
        intent.putExtra("name", pokemon.getName());
        intent.putExtra("imageUrl", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + pokemon.getNumber() + ".png");
        // Start a new thread to make the API request

      //  intent.putExtra("height", pokemon.getNumber()+"/" +pokemon.getHeight());
        startActivity(intent);
        Log.d(TAG, "Selected Pokemon: " + pokemon.getName());
    }

    public static int generateRandomColor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}