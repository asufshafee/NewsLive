package com.iteyes.newslive;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by AbbasHassan on 4/15/2017.
 */

public class NewsAdapter extends BaseAdapter {

    Context context;
    ArrayList<NewsItem> newsList;

    public NewsAdapter(Context context, ArrayList<NewsItem> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public NewsItem getItem(int i) {
        return newsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = View.inflate(context, R.layout.news_list_item_layout, null);
        }

        NewsItem currentNews = newsList.get(i);

        ImageView iv1 = (ImageView) view.findViewById(R.id.imageview_1);
        TextView tvTitle = (TextView) view.findViewById(R.id.textview_1);
        TextView tvDate = (TextView) view.findViewById(R.id.textview_2);
        TextView tvDescription = (TextView) view.findViewById(R.id.textview_3);

        Picasso.with(context).load(currentNews.imagePath).placeholder(R.drawable.placeholder).into(iv1);
        tvTitle.setText(currentNews.title);
        tvDate.setText(currentNews.date);
        tvDescription.setText(currentNews.description);


        return view;
    }
}
