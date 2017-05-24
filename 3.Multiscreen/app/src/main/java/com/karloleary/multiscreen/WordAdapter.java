package com.karloleary.multiscreen;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.karloleary.tourguide.Destination;

import java.util.ArrayList;

/**
 * Created by karl on 29/03/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int color;

    public WordAdapter(Activity context, ArrayList<Word> words, int c) {
        super(context, 0, words);
        color = c;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null)
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        Word www = getItem(position);

        TextView miwokTextView = (TextView)itemView.findViewById(R.id.miwok_textview);
        miwokTextView.setText(www.getMiwokTranslation());

        TextView defaultTextView = (TextView)itemView.findViewById(R.id.default_textview);
        defaultTextView.setText(www.getDefaultTranslation());

        ImageView iconImageView = (ImageView) itemView.findViewById(R.id.icon_imageview);
        if (www.getImageResourceId() != 0) {
            iconImageView.setImageResource(www.getImageResourceId());
            iconImageView.setVisibility(View.VISIBLE);
        }
        else
            iconImageView.setVisibility(View.GONE);

        View vvv = itemView.findViewById(R.id.text_layout);
        vvv.setBackgroundResource(color);

        return itemView;
    }
}
