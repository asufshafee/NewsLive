package com.iteyes.newslive;

import java.io.Serializable;

/**
 * Created by AbbasHassan on 4/14/2017.
 */

public class NewsItem implements Serializable {


    String title;
    String link;
    String imagePath;
    String description;
    String date;



    @Override
    public String toString() {
        return title;
    }
}
