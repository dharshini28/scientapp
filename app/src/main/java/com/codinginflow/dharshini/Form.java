package com.codinginflow.dharshini;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Form extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,Bundle savedInstanceState) {
        final EditText name, rollno;
        Button submit;
        View view = inflater.inflate(R.layout.activity_form,container,false);
        Spinner spinner = view.findViewById(R.id.spinner);
        Spinner spinner2 = view.findViewById(R.id.spinner2);
        name = view.findViewById(R.id.et1);
        rollno = view.findViewById(R.id.et2);
        submit = view.findViewById(R.id.b1);
        submit.setOnClickListener(this);
        TextView currentdate = view.findViewById(R.id.tv3);
        final TextView futuredat = view.findViewById(R.id.tv4);

        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(calendar.getTime());
        currentdate.setText(date);
        final Date d = new Date();
        calendar.add(Calendar.DATE,5);
        final String end_date = dateFormat.format(calendar.getTime());
        Toast.makeText(getActivity(),end_date,Toast.LENGTH_SHORT);

        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) ArrayAdapter.createFromResource(getContext(),R.array.department,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,View view,int position,long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),R.array.tool,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,View view,int position,long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DATE,5);
                String end_date = dateFormat.format(calendar.getTime());
                Date currentdate = d;
                try {
                    Date futuredate = dateFormat.parse(end_date);
                    if( !currentdate.after(futuredate) ) {
                        long diff = futuredate.getTime() - currentdate.getTime();
                        long secondsinmilli = 1000;
                        long minutesinmilli = secondsinmilli*1000;
                        long hoursinmilli = minutesinmilli*1000;
                        long daysinmilli = hoursinmilli*1000;

                        long elapsedDays = diff/daysinmilli;
                        long daysleft = 5-elapsedDays;
                        futuredat.setText(""+daysleft);
                    } else {

                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Controllerdb db = new Controllerdb(getActivity());
                SQLiteDatabase database;
                database = db.getWritableDatabase();
                database.execSQL("INSERT INTO UserDetails(username,daysleft)VALUES('" + name.getText() + "','" +futuredat.getText() + "')");
                Toast.makeText(getActivity(),end_date,Toast.LENGTH_SHORT);
            }
        });
        return view;

    }

    @Override
    public void onClick(View v) {

    }
}
