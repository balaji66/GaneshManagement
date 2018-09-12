package com.durga.balaji66.ganeshmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class GamesListActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mAnxiety,mBalloon, mBubble;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);
        initializeViews();
        initializeListeners();
    }

    public void initializeViews()
    {
        mAnxiety = findViewById(R.id.buttonAnxiety);
        mBalloon = findViewById(R.id.buttonBalloon);
        mBubble = findViewById(R.id.buttonBubble);
    }
    public void initializeListeners()
    {
        mAnxiety.setOnClickListener(this);
        mBalloon.setOnClickListener(this);
        mBubble.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                alertDialogLogout();
                break;
            case R.id.listOFRegisteredPlayers:
                Intent intent =new Intent(GamesListActivity.this,ListOfRegisteredPlayersActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }

    public void alertDialogLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new UserSharedPreferenceManager(getApplicationContext()).clear();
                Intent intent = new Intent(GamesListActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setMessage("Are You want to Logout");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonAnxiety:
                Intent intentAnxiety =new Intent(GamesListActivity.this,AnxietyRegisteredPlayersActivity.class);
                startActivity(intentAnxiety);
                break;
            case R.id.buttonBalloon:
                Intent intentBalloon =new Intent(GamesListActivity.this,BalloonRegisteredPlalyersActivity.class);
                startActivity(intentBalloon);
                break;
            case R.id.buttonBubble:
                Intent intentBubble =new Intent(GamesListActivity.this,BubbleRegisteredPlayerActivity.class);
                startActivity(intentBubble);

                break;
        }
    }

    @Override
    public void onBackPressed() {
        alertDialogBackPressed();
    }

    public void alertDialogBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert Dialog");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setMessage("Are You sure want to Exit");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
