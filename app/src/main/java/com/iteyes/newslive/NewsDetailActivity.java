package com.iteyes.newslive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    ImageView iv1;
    TextView tvTitle;
    TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        iv1 = (ImageView) findViewById(R.id.imageview_1);
        tvTitle = (TextView) findViewById(R.id.textview_1);
        tvDescription = (TextView) findViewById(R.id.textview_2);

        NewsItem newsItem = (NewsItem) getIntent().getSerializableExtra("news_item");
        Picasso.with(this).load(newsItem.imagePath).placeholder(R.drawable.placeholder).into(iv1);
        tvTitle.setText(newsItem.title);
        tvDescription.setText(newsItem.description);
    }
}
