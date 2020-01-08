package com.example.myfuelapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.myfuelapp.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class GetCar extends AsyncTask<String, Void, String> {

    private WeakReference<TextView> mReturnData;
    private WeakReference<TextView> update;

    GetCar(TextView carText, TextView update) {
        this.mReturnData = new WeakReference<>(carText);
        this.update = new WeakReference<>(update);
    }

    @Override
    protected String doInBackground(String... strings) {
        return com.example.myfuelapp.NetworkUtils.getBookInfo("https://www.fueleconomy.gov/ws/rest/ympg/shared/ympgVehicle/26425");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String mpg = null;


        try {
            HashMap<String, String> data = parseXml(s);


            try {
                mpg = data.get("avgMpg");

            } catch (Exception e) {
                e.printStackTrace();
            }

            if(mpg != null){
                mReturnData.get().setText(mpg);
                update.get().setText(mpg);



            } else {
                mReturnData.get().setText(R.string.no_results);
            }

        }catch (Exception e) {
            // If onPostExecute does not receive a proper JSON string,
            // update the UI to show failed results.
            mReturnData.get().setText(R.string.no_results);
        }

    }

    public HashMap<String, String> parseXml(String xml) {
        XmlPullParserFactory factory;
        String tagName = "";
        String text = "";
        HashMap<String, String> hm = new HashMap<String, String>();

        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            StringReader sr = new StringReader(xml);
            xpp.setInput(sr);
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.TEXT) {
                    text = xpp.getText(); //Pulling out node text
                } else if (eventType == XmlPullParser.END_TAG) {
                    tagName = xpp.getName();

                    hm.put(tagName, text);

                    text = ""; //Reset text for the next node
                }
                eventType = xpp.next();
            }
        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            Log.d("Exception attribute", e + "+" + tagName);
        }

        return hm;
    }
}
