package com.example.musculaction.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musculaction.R;

public class CategoryAdapter extends BaseAdapter {

    Context context;
    String[] categNames;
    int[] categId;

    LayoutInflater inflater;

    public CategoryAdapter(Context context, String[] categNames, int[] categId) {
        this.context = context;
        this.categNames = categNames;
        this.categId = categId;
    }

    @Override
    public int getCount() {
        return categNames.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null){
            view = inflater.inflate(R.layout.category_item,null);
        }

        ImageView imageView = view.findViewById(R.id.category_image);
        TextView textView = view.findViewById(R.id.category_title);

        imageView.setImageResource(categId[i]);
        textView.setText(categNames[i]);

        return view;
    }
}
