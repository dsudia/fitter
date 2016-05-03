package com.roomforimproving.FitterSpark;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.*;
/*
 * Created by davidsudia on 5/3/16.
 */
public class AppStartTest {
    @Test
    public void testIndex() throws Exception {
        String strUrl = "http://localhost:4567/";

        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();

            assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
        } catch (IOException ioe) {
            System.err.println("Error creating HTTP connection");
            ioe.printStackTrace();
            throw ioe;
        }
    }
}