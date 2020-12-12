package com.example.superpupermegaapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.superpupermegaapp.Model.Joke;
import com.example.superpupermegaapp.R;

import java.util.ArrayList;


public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.JokeViewHolder> {

    private ArrayList<Joke> jokes;

    public JokeAdapter(ArrayList<Joke> jokes){
        this.jokes = jokes;
    }

    @Override
    public JokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item, parent, false);
        return new JokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JokeViewHolder holder, int position) {
        Joke currentJoke = jokes.get(position);

        String title = currentJoke.getTextJoke();

        holder.textView.setText(title);

    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    public class JokeViewHolder extends RecyclerView.ViewHolder {
         TextView textView;

        public JokeViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewItem);
        }
    }
}
