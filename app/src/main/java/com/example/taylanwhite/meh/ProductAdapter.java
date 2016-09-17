package com.example.taylanwhite.meh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by taylanwhite on 9/16/16.
 */

public class ProductAdapter extends ArrayAdapter<DailyProduct> {


    List<DailyProduct> productList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public ProductAdapter(Context context, List<DailyProduct> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        productList = objects;
    }

    @Override
    public DailyProduct getItem(int position) {
        return productList.get(position);
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

        DailyProduct item = getItem(position);

        vh.textViewName.setText(item.getTitle());
        vh.textViewPrice.setText(item.getPrice());
        vh.textViewSpecs.setText(item.getSpecs());
    //    Picasso.with(context).load(item.get()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewName;
        public final TextView textViewPrice;
        public final TextView textViewSpecs;

        private ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewName, TextView textViewPrice, TextView textViewSpecs) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewName = textViewName;
            this.textViewPrice = textViewPrice;
            this.textViewSpecs = textViewSpecs;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewPrice = (TextView) rootView.findViewById(R.id.textViewPrice);
            TextView textViewSpecs = (TextView) rootView.findViewById(R.id.textViewSpecs);

            return new ViewHolder(rootView, imageView, textViewName, textViewPrice, textViewSpecs);
        }
    }
}
