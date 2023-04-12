package com.example.pokemonprojectapi.pokeapi;

import com.example.pokemonprojectapi.models.Pokemon;
import com.example.pokemonprojectapi.models.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeapiService {
    @GET("pokemon")
    Call<PokemonResponse> getListPokemon(@Query("limit") int limit, @Query("offset") int offset);
    @GET("{dexNumOrName}/")
    Call<Pokemon> getPokemon(@Path("dexNumOrName") String dexNumOrName);
}
