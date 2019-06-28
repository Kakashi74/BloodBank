package com.shanaptech.bloodbank;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.shanaptech.bloodbank.Fragments.Forget_password;
import com.shanaptech.bloodbank.Fragments.HomeFragment;
import com.shanaptech.bloodbank.Fragments.Register_Fragment;
import com.shanaptech.bloodbank.Retrofit.ApiPostman;
import com.shanaptech.bloodbank.Retrofit.RetrofitClient;
import com.shanaptech.bloodbank.Utils.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shanaptech.bloodbank.Utils.SharedPreferenceManager.USER_API_TOKEN;

public class LoginActivity extends AppCompatActivity {

    EditText phone , paswword ;
    TextView textView ;
    CheckBox mRemember;
    Button Login , Regester ;
    FragmentManager fragmentManager;

    ApiPostman apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        apiServices = RetrofitClient.getClient().create(ApiPostman.class);


        fragmentManager = getSupportFragmentManager();

        phone = findViewById(R.id.phone_num);
        paswword = findViewById(R.id.password_txt);

        Login = findViewById(R.id.login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.fragment_container , new HomeFragment() , null).addToBackStack("main_fragment").commit();
            }
        });

        Regester = findViewById(R.id.register);

        textView = findViewById(R.id.alarm);
        mRemember = findViewById(R.id.check_box);
        if(mRemember.isChecked())
        {
            SharedPreferenceManager.SaveData(LoginActivity.this ,USER_API_TOKEN , true );
           }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.add(R.id.fragment_container , new Forget_password() , null).addToBackStack("main_fragment");

                fragmentTransaction.commit();

            }
        });

         Regester = findViewById(R.id.register);
         Regester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Set_fragment();

            }
        });


    }
    private void Set_fragment()
    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container , new Register_Fragment() , null).addToBackStack("main_fragment");

        fragmentTransaction.commit();
    }

    private void Login()
    {

        String phone_num = phone.getText().toString().trim();
        String pass = paswword.getText().toString().trim();

        apiServices.Post_info(phone_num , pass).enqueue(new Callback<com.shanaptech.bloodbank.Data.login.Login>() {
            @Override
            public void onResponse(Call<com.shanaptech.bloodbank.Data.login.Login> call, Response<com.shanaptech.bloodbank.Data.login.Login> response) {
                try{

                    if(response.body().getStatus() == 1)
                    {
                        SharedPreferenceManager.SaveData(LoginActivity.this,USER_API_TOKEN,response.body().getData().getApiToken());
                    }

                }
                catch (Exception e)
                {

                }


            }

            @Override
            public void onFailure(Call<com.shanaptech.bloodbank.Data.login.Login> call, Throwable t) {

            }
        });



    }

}
