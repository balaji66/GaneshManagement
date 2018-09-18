package com.durga.balaji66.ganeshmanagement;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.durga.balaji66.ganeshmanagement.Apis.APIUrl;
import com.durga.balaji66.ganeshmanagement.Models.Game;
import com.durga.balaji66.ganeshmanagement.Adapters.GameAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnxietyRegisteredPlayersActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMessages;
    private GameAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anxiety_registered_players);
        initializeViews();
        recyclerViewMessages.setHasFixedSize(true);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(this));
        signIn();

    }

    public void initializeViews()
    {
        recyclerViewMessages=findViewById(R.id.recyclerView);
    }

        public void signIn()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<SomeClass> call = APIUrl.getmInstance().getApi().registeredPlayerAnxiety();

        call.enqueue(new Callback<SomeClass>() {
            @Override
            public void onResponse(@NonNull Call<SomeClass> call, @NonNull Response<SomeClass> response) {
                ArrayList<Game> user_array;

                progressDialog.dismiss();
                //List<Game> list = response.body();
                assert response.body() != null;
                user_array= new ArrayList<>(response.body().getDetails());
                adapter = new GameAdapter(user_array, AnxietyRegisteredPlayersActivity.this);
                recyclerViewMessages.setAdapter(adapter);
                //adapter = new GameAdapter(list, AnxietyRegisteredPlayersActivity.this);
                //recyclerViewMessages.setAdapter(adapter);
            }
            @Override
            public void onFailure(@NonNull Call<SomeClass> call, @NonNull Throwable t) {
                progressDialog.dismiss();

                final AlertDialog alertDialog =new AlertDialog.Builder(AnxietyRegisteredPlayersActivity.this).create();
                alertDialog.setTitle("No Internet");
                alertDialog.requestWindowFeature(Window.FEATURE_RIGHT_ICON);
                alertDialog.setIcon(R.drawable.ic_no_internet);
                alertDialog.setCancelable(false);
                alertDialog.setMessage("check Internet Connection");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

                //Toast.makeText(getApplicationContext(),"Check Your Internet Connection",Toast.LENGTH_LONG).show();

            }
        });
    }
}
