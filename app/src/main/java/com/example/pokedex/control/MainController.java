package com.example.pokedex.control;

import android.util.Log;
import android.view.View;

import com.example.pokedex.R;
import com.example.pokedex.util.Constants;
import com.example.pokedex.util.HTTPSWebUtilDomi;
import com.example.pokedex.view.MainActivity;

public class MainController implements View.OnClickListener, HTTPSWebUtilDomi.OnResponseListener {

    private MainActivity activity;
    private HTTPSWebUtilDomi utilDomi;

    public MainController(MainActivity activity){
        this.activity = activity;
        this.activity.getSearchBtn().setOnClickListener(this);

        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.searchBtn:
                String nameOrID = activity.getNameOrIDET().getText().toString();
                new Thread(
                        ()->{
                            utilDomi.GETrequest(Constants.SEARCH_CALLBACK, "https://pokeapi.co/api/v2/pokemon/" + nameOrID);
                        }
                ).start();
                break;
        }
    }

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID){
            case Constants.SEARCH_CALLBACK:
                Log.e(">>>",response);
                break;
        }

    }
}
