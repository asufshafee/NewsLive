package com.iteyes.newslive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<NewsItem> newsItemList;
    ListView lvNews;
    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsItemList = new ArrayList<>();

        lvNews = (ListView) findViewById(R.id.listview_news);

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest("https://www.geo.tv/rss/1/0", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "data received successfully", Toast.LENGTH_SHORT).show();
                Document doc = Jsoup.parse(response);
                Elements itemElements = doc.getElementsByTag("item");
                for(int i=0;i<itemElements.size();i++){
                    Element item = itemElements.get(i);
                    String title = removeCdata(item.child(0).text());
                    String pubDate = item.child(2).text();
                    String guid = item.child().text();
                    String description = item.child(4).text();
                    Document doc2 = Jsoup.parse(description);
                    String imageLink = doc2.getElementsByTag("img").first().attr("src");
                    String txt = doc2.getElementsByTag("p").text();

                    NewsItem news = new NewsItem();
                    news.title = title;
                    news.description = txt;
                    news.link = guid;
                    news.imagePath = imageLink;
                    news.date = pubDate;
                    newsItemList.add(news);

                    Log.i("mytag", "title: " + title);
                    Log.i("mytag", "pubdate: " + pubDate);
                    Log.i("mytag", "guid:  " + guid);
                    Log.i("mytag", "description: " + description);
                    Log.i("mytag", "image:  " + imageLink);
                    Log.i("mytag", "text:  " + txt);
                }
                Log.i("mytag", "items found: " + itemElements.size());
                Log.i("mytag", "items in news List: " + newsItemList.size());
                adapter = new NewsAdapter(MainActivity.this, newsItemList);
                lvNews.setAdapter(adapter);
                lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        NewsItem currentNews = newsItemList.get(i);
                        Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
                        intent.putExtra("news_item", currentNews);
                        startActivity(intent);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "request failed", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(this, "Request sent. Please Wait", Toast.LENGTH_SHORT).show();
        queue.add(request);



    }

    String removeCdata(String data){
        data = data.replace("<![CDATA[", "");
        data = data.replace("]]>", "");
        return data;
    }

}
