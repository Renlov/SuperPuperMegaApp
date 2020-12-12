package com.example.superpupermegaapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.superpupermegaapp.R;


public class WebFragment extends Fragment {
    String url = "http://www.icndb.com/api/";
    WebView webView;

    public WebFragment() {
    }

    public static WebFragment newInstance(){
        return new WebFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_web,
                container, false);
        webView = (WebView)view.findViewById(R.id.webView);

        if(savedInstanceState != null){
            webView.restoreState(savedInstanceState);
        } else {

            try {
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(url);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return view;
    }

}
