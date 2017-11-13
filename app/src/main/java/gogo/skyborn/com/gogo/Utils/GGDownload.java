package gogo.skyborn.com.gogo.Utils;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadListener;

public class GGDownload extends AsyncTask<String, String, String> {
    private GGOnDownloadListener mGgOnDownload;
    private String mUrl;

    public GGDownload(GGOnDownloadListener mGgOnDownload,String mUrl){
        this.mGgOnDownload = mGgOnDownload;
        this.mUrl = mUrl;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... strings) {
        String response = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(this.mUrl);
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestProperty("Accept-Charset", "application/x-www-form-urlencoded; charset=utf-8");
                httpURLConnection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
                if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    StringBuilder stringBuilder = new StringBuilder();
                    String linea;
                    while ((linea = bufferedReader.readLine()) != null) {
                        stringBuilder.append(linea).append("\n");
                    }
                    bufferedReader.close();
                    response = stringBuilder.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String o) {
        super.onPostExecute(o);
        if(mGgOnDownload != null) {
            mGgOnDownload.onDownloadSuccess(o);
        }
    }
}