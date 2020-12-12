package com.example.superpupermegaapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.superpupermegaapp.Adapter.JokeAdapter;
import com.example.superpupermegaapp.Model.Joke;
import com.example.superpupermegaapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;

public class JokeFragment extends Fragment {

    private RecyclerView recyclerView;
    private JokeAdapter jokeAdapter;
    private ArrayList<Joke> jokes;
    private RequestQueue requestQueue;
    final String url = "http://api.icndb.com/jokes/random/";
    private EditText editText;
    private Button button;
    Parcelable parcelable;

    JokeFragment() {

    }

    public static JokeFragment newInstance() {
        return new JokeFragment();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("StoreRecyclerView", recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_joke,
                container, false);

        jokes = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView1);
        editText = view.findViewById(R.id.editTextJoke);
        button = view.findViewById(R.id.bottomJoke);

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        requestQueue = Volley.newRequestQueue(getContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jokes.clear();
                getJokes();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private void getJokes() {
        String count = editText.getText().toString();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url + count, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("value");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String title = jsonObject.getString("joke");

                        Joke joke = new Joke();
                        joke.setTextJoke(title);

                        jokes.add(joke);
                    }

                    jokeAdapter = new JokeAdapter(jokes);
                    recyclerView.setAdapter(jokeAdapter);

                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}