package com.durga.balaji66.ganeshmanagement;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.durga.balaji66.ganeshmanagement.Apis.APIUrl;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mLogin;
    private TextInputEditText mPhone, mPassword;
    public final String TAG="TestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();
        initializeListeners();
        int tid=android.os.Process.myTid();
        Log.d(TAG,"priority before change = " + android.os.Process.getThreadPriority(tid));
        Log.d(TAG,"priority before change = "+Thread.currentThread().getPriority());
        android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        Log.d(TAG,"priority after change = " + android.os.Process.getThreadPriority(tid));
        Log.d(TAG,"priority after change = " + Thread.currentThread().getPriority());
        if (!new UserSharedPreferenceManager(this).isUserLogOut()) {
            startHomeActivity();
        }

    }
    public void startHomeActivity() {
        Intent accountsIntent = new Intent(LoginActivity.this, GamesListActivity.class);
        startActivity(accountsIntent);
        finish();
    }
    public void initializeViews()
    {
        mLogin= findViewById(R.id.buttonLogin);
        mPhone = findViewById(R.id.textInputEditTextPhone);
        mPassword= findViewById(R.id.textInputEditTextPassword);
    }
    public void initializeListeners()
    {
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonLogin:
                if(inputValidation())
                {
                    signIn();
                }
                break;
        }
    }

    public boolean inputValidation()
    {
        if(mPhone.getText().toString().equals(""))
        {
            mPhone.setError("Mobile Number Must Not Be Empty");
        }
        else if(mPhone.getText().toString().length() > 10 || mPhone.getText().toString().length() <10 )
        {
            mPhone.setError("Enter Valid 10 digit Mobile Number");
        }
        else if(mPassword.getText().toString().equals(""))
        {
            mPassword.setError("Password Must Not be Empty");
        }
        else
        {
            return true;
        }
        return false;

    }

    public void signIn()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String phone = mPhone.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        Call<ResponseBody> call = APIUrl.getmInstance().getApi().AdminLogin(phone, password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if(response.code() == 200)
                {
                    progressDialog.dismiss();
                    attemptLoginActivity();

                }
                else if( response.code() == 401)
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Invalid Mobile Number or password",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Check After Some Time",Toast.LENGTH_LONG).show();

                }
            }

            public void attemptLoginActivity() {
                String email = mPhone.getText().toString();
                String password = mPassword.getText().toString();
                saveLoginDetails(email, password);
                startHomeActivity();
            }
            public void saveLoginDetails(String email, String password) {
                new UserSharedPreferenceManager(LoginActivity.this).saveLoginDetails(email, password);
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                final AlertDialog alertDialog =new AlertDialog.Builder(LoginActivity.this).create();
                alertDialog.setTitle("No Internet");
                alertDialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
                alertDialog.setIcon(R.drawable.ic_no_internet);
                alertDialog.setCancelable(false);
                alertDialog.setMessage("check Internet Connection");
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                Toast.makeText(getApplicationContext(),"Check Your Internet Connection",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
