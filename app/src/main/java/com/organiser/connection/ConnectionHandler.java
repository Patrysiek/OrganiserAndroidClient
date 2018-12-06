package com.organiser.connection;


import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionHandler{

    private String url;
    private String postData;
    private HttpURLConnection conn;
    private final String SERVER_PROTOCOL = "http";
    private final String SERVER_ADDERSS = "OrganiserWebService";
    private final String SERVER_IP = "192.168.1.27";
    private final String SERVER_PORT = "8080";


    public ConnectionHandler(String url, String postData) throws Exception {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        assembeUrl(url);

        this.postData = postData;
        conn = initConnection();
    }

    private void assembeUrl(String url) {
        this.url = new StringBuilder()
                .append(SERVER_PROTOCOL)
                .append("://")
                .append(SERVER_IP)
                .append(":")
                .append(SERVER_PORT)
                .append("/")
                .append(SERVER_ADDERSS)
                .append("/")
                .append(url)
                .toString();
    }

    private  HttpURLConnection initConnection() throws Exception {

        conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        if (postData != null) {
            byte[] postDataBytes = postData.getBytes("UTF-8");
            conn.getOutputStream().write(postDataBytes);
        }

        return conn;
    }

    public  String readPage() throws IOException {
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer valueFromPage = new StringBuffer();
        int c;
        while ((c = in.read()) >= 0)
            valueFromPage.append((char) c);

        in.close();
        return  valueFromPage.toString();
    }

    public void disconnect(){
        conn.disconnect();
    }
    public void close() throws IOException {
        conn.getInputStream().close();
    }

}
