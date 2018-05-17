package com.shenkar.gadyezra;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

import com.shenkar.gadyezra.storage.NamedateDb;
import com.shenkar.gadyezra.storage.NamedateEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Birthday extends AppCompatActivity {

    RecyclerView dateListView;
    public static Calendar DateInput;
    public static String Name;

    public List<NamedateEntity> birthdayList = new ArrayList<>();
    public RecyclerView recyclerView;
    public birthdayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);
        recyclerView = (RecyclerView) findViewById(R.id.dateListView);
        mAdapter = new birthdayAdapter(birthdayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void addUser(View view) {
        EditText textView3 = findViewById(R.id.textView3);
        Name = textView3.getText().toString();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formatDate = df.format(DateInput.getTime());
        NamedateEntity newUser = new NamedateEntity();
        newUser.setRecord(Name,formatDate);
        Log.d("tag", "New User: " + Name + formatDate);
        birthdayList.add(newUser);
        NamedateDb.getInstance(this).writeToNamedateDB(newUser);
        mAdapter.notifyDataSetChanged();

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Dialog, this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            DateInput = Calendar.getInstance();
            DateInput.set(year,month,day);
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
