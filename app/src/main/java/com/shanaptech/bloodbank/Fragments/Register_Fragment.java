package com.shanaptech.bloodbank.Fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shanaptech.bloodbank.Data.Governorates.Governorates;
import com.shanaptech.bloodbank.Utils.DateModel;
import com.shanaptech.bloodbank.R;
import com.shanaptech.bloodbank.Retrofit.ApiPostman;
import com.shanaptech.bloodbank.Retrofit.RetrofitClient;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Fragment extends Fragment {


    private EditText name, email, bloodtype,  phone, password, ensure_passowrd;
    private TextView birthday , last_donate ;

    private Spinner GovernrateSpinner, CitySpinner, BloodtypeSpinner;

    private Button Register;
    private ApiPostman apiservices;
    private List<String> governstxts = new ArrayList<>();
    private List<Integer> governsids = new ArrayList<>();

    private List<String> citiestxts = new ArrayList<>();
    private List<Integer> citiesids = new ArrayList<>();

    private List<String> bloodtxt = new ArrayList<>();
    private List<Integer> bloodid = new ArrayList<>();
    final Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener mdatelistener;


     FragmentManager fragmentManager ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_fragment, container, false);
        fragmentManager = getFragmentManager();

        apiservices = RetrofitClient.getClient().create(ApiPostman.class);
        name = v.findViewById(R.id.name);
        email = v.findViewById(R.id.mail);
        birthday = v.findViewById(R.id.bd);
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showbirthdate();
            }
        });

        bloodtype = v.findViewById(R.id.name);
        BloodtypeSpinner = v.findViewById(R.id.Bloodtype_spinner);
        last_donate = v.findViewById(R.id.last_donate);
        last_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           showLastDonate();
            }
        });

        phone = v.findViewById(R.id.phone);
        password = v.findViewById(R.id.password);
        ensure_passowrd = v.findViewById(R.id.ensure_password);

        GovernrateSpinner = v.findViewById(R.id.spinner);
        CitySpinner = v.findViewById(R.id.spinner2);

        Register = v.findViewById(R.id.register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.add(R.id.fragment_container , new HomeFragment()).addToBackStack("main_fragment");

                fragmentTransaction.commit();

            }
        });

        getGovernrates();
        getTypeBlood();


        return v;

    }

    private void getGovernrates() {

        apiservices.getGovernorates().enqueue(new Callback<Governorates>() {
            @Override
            public void onResponse(Call<Governorates> call, Response<Governorates> response) {

                try {
                    if (response.body().getStatus() == 1) {

                        governstxts.add("المحافظة");
                        governsids.add(0);

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            governstxts.add(response.body().getData().get(i).getName());
                            governsids.add(response.body().getData().get(i).getId());

                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_spinner_item, governstxts);

                        GovernrateSpinner.setAdapter(adapter);

                        GovernrateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                if (position != 0) {
                                    getCities(governsids.get(position));

                                } else {
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                } catch (NullPointerException e) {
                    Toast.makeText(getActivity(), " Error occured" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Governorates> call, Throwable t) {

            }
        });

    }

    private void getCities(Integer city_id) {
        apiservices.getcities(city_id).enqueue(new Callback<Governorates>() {
            @Override
            public void onResponse(Call<Governorates> call, Response<Governorates> response) {

                try {
                    if (response.body().getStatus() == 1) {

                        citiestxts.add("المدينة");
                        citiesids.add(0);
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            citiestxts.add(response.body().getData().get(i).getName());
                            citiesids.add(response.body().getData().get(i).getId());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_spinner_item, citiestxts);

                        CitySpinner.setAdapter(adapter);
                        CitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                citiesids.get(CitySpinner.getSelectedItemPosition());
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //citiesids.get(CitySpinner.getSelectedItemPosition());

                    }
                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<Governorates> call, Throwable t) {

            }
        });
    }

    private void getTypeBlood() {
        apiservices.getBloodTypes().enqueue(new Callback<Governorates>() {
            @Override
            public void onResponse(Call<Governorates> call, Response<Governorates> response) {

                try {
                    if (response.body().getStatus() == 1) {


                        for (int i = 0; i < response.body().getData().size(); i++) {

                            bloodtxt.add(response.body().getData().get(i).getName());
                            bloodid.add(response.body().getData().get(i).getId());

                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_spinner_item, bloodtxt);

                        BloodtypeSpinner.setAdapter(adapter);
                        BloodtypeSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Toast.makeText(getActivity(), " Selected" + position, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } catch (Exception e) {

                }
            }
            @Override
            public void onFailure(Call<Governorates> call, Throwable t) {

            }
        });
    }
    private void showbirthdate()
    {
        int year = myCalendar.get(Calendar.YEAR);
        int month = myCalendar.get(Calendar.MONTH);
        int day = myCalendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dialog = new DatePickerDialog(getActivity() ,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth
                , mdatelistener , year , month , day);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(
                        Color.TRANSPARENT));
        dialog.show();
        mdatelistener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1 ;
                String date = dayOfMonth + " / " + month +" / " + year ;
                birthday.setText(date);
            }
        };
    }

    private void showLastDonate() {
        int year = myCalendar.get(Calendar.YEAR);
        int month = myCalendar.get(Calendar.MONTH);
        int day = myCalendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth
                , mdatelistener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(
                        Color.TRANSPARENT));
        dialog.show();
        mdatelistener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + " / " + month + " / " + year;
                last_donate.setText(date);
            }
        };
    }
    }
