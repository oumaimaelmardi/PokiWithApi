package com.example.pokemonprojectapi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokemonprojectapi.models.Pokemon;

import java.util.ArrayList;




public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

    private ImageView fotoImageView;
    private TextView nombreTextView;
    private Pokemon pokemon;
    private OnItemClickListener listener;
    private ArrayList<Pokemon> dataset;

    private Context context;
    private CardView cardView;


    public ListaPokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view,listener);
    }









    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
       holder.nombreTextView.setText(p.getName());
        // Set the background color of the card


        // Set the name of the Pokemon

        // Set the image of the Pokemon

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + p.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);


      /**  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(context, PokemonDetailActivity.class);
                intent.putExtra("nombrePokemon", p.getName());
                intent.putExtra("numeroPokemon", p.getNumber());
                intent.putExtra("fotoPokemonUrl", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png");
                context.startActivity(intent);*/
               /** String message=dataset.get(1).getName();
                Log.i("info:",message);
                Log.d("MainActivity", "This is a debug message");
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder  {


        private ImageView fotoImageView;
        private TextView nombreTextView;

        public ViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);


            fotoImageView = itemView.findViewById(R.id.fotoImageView);
           nombreTextView = itemView.findViewById(R.id.nombreTextView);

            /***/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(dataset.get(position));
                    }
                }
            });
            ListaPokemonAdapter.this.listener=listener;


        }




    }
    public interface OnItemClickListener {
        void onItemClick(Pokemon pokemon);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
