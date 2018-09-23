package com.deitel.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.deitel.myapplication.R;
import com.deitel.myapplication.model.User;

import java.util.List;


public class UserAdapter extends ArrayAdapter<User> {

    List<User> contactList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public UserAdapter(Context context, List<User> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        contactList = objects;
    }

    @Override
    public User getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        User item = getItem(position);

        vh.textViewFirstName.setText(item.getFirstName());
        vh.textViewLastName.setText(item.getLastName());

        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final TextView textViewFirstName;
        public final TextView textViewLastName;

        private ViewHolder(RelativeLayout rootView, TextView textViewFirstName, TextView textViewLastName) {
            this.rootView = rootView;
            this.textViewFirstName = textViewFirstName;
            this.textViewLastName = textViewLastName;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView textViewFirstName = (TextView) rootView.findViewById(R.id.textViewFirstName);
            TextView textViewLastName = (TextView) rootView.findViewById(R.id.textViewLastName);
            return new ViewHolder(rootView, textViewFirstName, textViewLastName);
        }

    }
}
