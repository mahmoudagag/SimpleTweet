package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tweet {
    public String body;
    public String createdAt;
    public long id;
    public User user;

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = convertTime(jsonObject.getString("created_at"));
        tweet.id = jsonObject.getLong("id");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray)  throws JSONException{
        List<Tweet> tweets = new ArrayList<>();
        for(int i=0;i< jsonArray.length();i++){
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
    public static String convertTime(String createdAt){
        Date date = new Date();
        /*int day = Integer.parseInt(createdAt.substring(8,10));
        if(day != date.getDate()){
            return Integer.toString(date.getDate()-day)+ 'd';
        }*/
        int hour = Integer.parseInt(createdAt.substring(11,13));
        if (hour-4 != date.getHours()){
            return Integer.toString(date.getHours()-hour+4)+ 'h';
        }
        int minute = Integer.parseInt(createdAt.substring(14,16));
        return Integer.toString(date.getMinutes()-minute)+ 'm';
        /*int year = Integer.parseInt(createdAt.substring(-4));
        if(date.getYear()!= year){
            return date.getYear()-year + "y";
        }
        int month = Integer.parseInt(createdAt.substring(-4));*/
    }

}
