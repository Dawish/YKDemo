package com.eastelsoft.tv.util.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import com.eastelsoft.tv.util.Utility;

import android.text.TextUtils;

public class JavaHttpUtility {

	private static final int CONNECT_TIMEOUT = 10 * 1000;

    private static final int READ_TIMEOUT = 10 * 1000;

    private static final int DOWNLOAD_CONNECT_TIMEOUT = 15 * 1000;

    private static final int DOWNLOAD_READ_TIMEOUT = 60 * 1000;

    private static final int UPLOAD_CONNECT_TIMEOUT = 15 * 1000;

    private static final int UPLOAD_READ_TIMEOUT = 5 * 60 * 1000;
    
    private static Proxy getProxy() {
        String proxyHost = System.getProperty("http.proxyHost");
        String proxyPort = System.getProperty("http.proxyPort");
        if (!TextUtils.isEmpty(proxyHost) && !TextUtils.isEmpty(proxyPort)) {
            return new Proxy(java.net.Proxy.Type.HTTP,
                    new InetSocketAddress(proxyHost, Integer.valueOf(proxyPort)));
        } else {
            return null;
        }
    }
    
    public String executeNormalTask(HttpMethod httpMethod, String url, Map<String, String> param)
            throws Exception {
        switch (httpMethod) {
            case Post:
                return doPost(url, param);
            case Get:
                return doGet(url, param);
        }
        return "";
    }

    public String doPost(String urlAddress, Map<String, String> param) throws Exception {
        String errorStr = "���س�ʱ...";
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection uRLConnection;
            uRLConnection = (HttpURLConnection) url.openConnection();

            uRLConnection.setDoInput(true);
            uRLConnection.setDoOutput(true);
            uRLConnection.setRequestMethod("POST");
            uRLConnection.setUseCaches(false);
            uRLConnection.setConnectTimeout(CONNECT_TIMEOUT);
            uRLConnection.setReadTimeout(READ_TIMEOUT);
            uRLConnection.setInstanceFollowRedirects(false);
            uRLConnection.setRequestProperty("Connection", "Keep-Alive");
            uRLConnection.setRequestProperty("Charset", "UTF-8");
            uRLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            uRLConnection.connect();

            DataOutputStream out = new DataOutputStream(uRLConnection.getOutputStream());
            out.write(Utility.encodeUrl(param).getBytes());
            out.flush();
            out.close();
            return handleResponse(uRLConnection);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String handleResponse(HttpURLConnection httpURLConnection) throws Exception {
    	String errorStr = "���س�ʱ...";
        int status = 0;
        try {
            status = httpURLConnection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
            httpURLConnection.disconnect();
        }

        if (status != HttpURLConnection.HTTP_OK) {
            return handleError(httpURLConnection);
        }

        return readResult(httpURLConnection);
    }

    private String handleError(HttpURLConnection urlConnection) throws Exception {

        String result = readError(urlConnection);

        return result;
    }

    private String readResult(HttpURLConnection urlConnection) throws Exception {
        InputStream is = null;
        BufferedReader buffer = null;
        String errorStr = "���س�ʱ...";
        try {
            is = urlConnection.getInputStream();

            String content_encode = urlConnection.getContentEncoding();

            if (null != content_encode && !"".equals(content_encode) && content_encode
                    .equals("gzip")) {
                is = new GZIPInputStream(is);
            }

            buffer = new BufferedReader(new InputStreamReader(is));
            StringBuilder strBuilder = new StringBuilder();
            String line;
            while ((line = buffer.readLine()) != null) {
                strBuilder.append(line);
            }
//            AppLogger.d("result=" + strBuilder.toString());
            return strBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
        	is.close();
            buffer.close();
            urlConnection.disconnect();
        }

    }

    private String readError(HttpURLConnection urlConnection) throws Exception {
        InputStream is = null;
        BufferedReader buffer = null;
        String errorStr = "���س�ʱ...";

        try {
            is = urlConnection.getErrorStream();

            if (is == null) {
            }

            String content_encode = urlConnection.getContentEncoding();

            if (null != content_encode && !"".equals(content_encode) && content_encode
                    .equals("gzip")) {
                is = new GZIPInputStream(is);
            }

            buffer = new BufferedReader(new InputStreamReader(is));
            StringBuilder strBuilder = new StringBuilder();
            String line;
            while ((line = buffer.readLine()) != null) {
                strBuilder.append(line);
            }
            return strBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            is.close();
            buffer.close();
            urlConnection.disconnect();
        }

    }

    public String doGet(String urlStr, Map<String, String> param) throws Exception {
    	String errorStr = "���س�ʱ...";
        InputStream is = null;
        try {

            StringBuilder urlBuilder = new StringBuilder(urlStr);
            urlBuilder.append("?").append(Utility.encodeUrl(param));
            System.out.println("url : "+urlBuilder.toString());
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection urlConnection;
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(false);
            urlConnection.setConnectTimeout(CONNECT_TIMEOUT);
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setRequestProperty("Connection", "Keep-Alive");
            urlConnection.setRequestProperty("Charset", "UTF-8");
            urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");

            urlConnection.connect();

            return handleResponse(urlConnection);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
