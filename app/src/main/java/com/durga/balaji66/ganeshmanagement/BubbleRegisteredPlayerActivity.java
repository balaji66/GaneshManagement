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

import com.durga.balaji66.ganeshmanagement.Adapters.GameAdapter;
import com.durga.balaji66.ganeshmanagement.Apis.APIUrl;
import com.durga.balaji66.ganeshmanagement.Models.Game;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BubbleRegisteredPlayerActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMessages;
    private GameAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buble_registered_player);
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

        Call<List<Game>> call = APIUrl.getmInstance().getApi().registeredPlayerBubble();

        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(@NonNull Call<List<Game>> call, @NonNull Response<List<Game>> response) {

                progressDialog.dismiss();
                List<Game> list = response.body();
                assert list != null;
                for(Game g : list)
                {
                    Log.d("candidate_name",g.getCandidate_name());
                    Log.d("father_name",g.getFather_name());
                }

                adapter = new GameAdapter(list, BubbleRegisteredPlayerActivity.this);
                recyclerViewMessages.setAdapter(adapter);
            }
            @Override
            public void onFailure(@NonNull Call<List<Game>> call, @NonNull Throwable t) {
                progressDialog.dismiss();

                final AlertDialog alertDialog =new AlertDialog.Builder(BubbleRegisteredPlayerActivity.this).create();
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
                //Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

                //Toast.makeText(getApplicationContext(),"Check Your Internet Connection",Toast.LENGTH_LONG).show();

            }
        });
    }
}
