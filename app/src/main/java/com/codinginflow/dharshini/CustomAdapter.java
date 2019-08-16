package com.codinginflow.dharshini;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    Controllerdb controldb;
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> username = new ArrayList<String>();
    private ArrayList<String> daysleft = new ArrayList<String>();
    public CustomAdapter(Context  context,ArrayList<String> Id,ArrayList<String> username, ArrayList<String> daysleft)
    {
        this.mContext = context;
        this.Id = Id;
        this.username = username;
        this.daysleft = daysleft;
    }

    @Override
    public int getCount() {
        return Id.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final    viewHolder holder;
        controldb =new Controllerdb(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.notificationslayout, null);
            holder = new viewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.tvid);
            holder.username = (TextView) convertView.findViewById(R.id.name);
            holder.daysleft = (TextView) convertView.findViewById(R.id.daysleft);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.id.setText(Id.get(position));
        holder.username.setText(username.get(position));
        holder.daysleft.setText(daysleft.get(position));
        return convertView;
    }
    public class viewHolder {
        TextView id;
        TextView username;
        TextView daysleft;

    }
}