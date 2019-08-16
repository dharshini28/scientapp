package com.codinginflow.dharshini;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Notifications extends Fragment {

    ArrayList<String> Id = new ArrayList<String>();
    ArrayList<String> username = new ArrayList<String>();
    ArrayList<String> daysleft = new ArrayList<String>();
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notification,container,false);
        lv = (ListView) view.findViewById(R.id.lv);
        Controllerdb controllerdb = new Controllerdb(getActivity());
        SQLiteDatabase db;
            db = controllerdb.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM  UserDetails",null);
            Id.clear();
            username.clear();
            daysleft.clear();
            if( cursor.moveToFirst() ) {
                do {
                    Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                    username.add(cursor.getString(cursor.getColumnIndex("username")));
                    daysleft.add(cursor.getString(cursor.getColumnIndex("daysleft")));
                } while (cursor.moveToNext());
            }
            CustomAdapter ca = new CustomAdapter(getActivity(),Id,username,daysleft);
            lv.setAdapter(ca);
            cursor.close();
            return view;
        }
    }


