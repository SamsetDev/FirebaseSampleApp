package com.samset.firebaseappexample.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.samset.firebaseappexample.R;
import com.samset.firebaseappexample.User;

import java.util.ArrayList;

/**
 * Created by samset on 02/05/16.
 */
public class UserAdapter extends BaseAdapter {
    ArrayList<User> list;
    Context context;

    public UserAdapter(Context ctx, ArrayList<User> data) {
        this.list = data;
        this.context = ctx;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        MyViewHolder myViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.items, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        User user = list.get(position);

        Log.e("Main","data ada "+list.get(position).getFirstname());
        myViewHolder.fname.setText(user.firstname);
        myViewHolder.lname.setText(user.lastname);
        myViewHolder.mail.setText(user.email);
        myViewHolder.contact.setText(user.contact);

        return convertView;
    }

    public class MyViewHolder {
        TextView fname, lname, mail, contact;

        public MyViewHolder(View view) {
            fname = (TextView) view.findViewById(R.id.tvfname);
            lname = (TextView) view.findViewById(R.id.tvlname);
            mail = (TextView) view.findViewById(R.id.tvemail);
            contact = (TextView) view.findViewById(R.id.tvcontact);

        }
    }
}
