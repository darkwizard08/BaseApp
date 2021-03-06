package ru.kazantsev.baseapp.net;

import android.util.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Rufim on 07.07.2015.
 */
public abstract class HTTPExecutor implements Callable<CachedResponse> {

    private static final String TAG = HTTPExecutor.class.getSimpleName();

    protected final Request request;
    protected CachedResponse cachedResponse;

    public HTTPExecutor(Request request) {
        this.request = request;
    }

    protected abstract void configConnection(HttpURLConnection connection);

    protected abstract CachedResponse prepareResponse() throws IOException;

    @Override
    public CachedResponse call() throws IOException {
        HttpURLConnection connection;
        cachedResponse = null;
        do {
            try {
                if (Request.Method.PUT.equals(request.getMethod()) ||
                        Request.Method.POST.equals(request.getMethod())) {
                    connection = (HttpURLConnection) request.getBaseUrl().openConnection();
                    connection.setDoOutput(true);
                } else {
                    connection = (HttpURLConnection) request.getUrl().openConnection();
                }
                connection.setRequestMethod(request.getMethod().name());
            } catch (IOException ex) {
                Log.e(TAG, "Error on establish connection" + request.getUrl(), ex);
                continue;
            }
            configConnection(connection);
            Map<String, String> headers = request.getHeaders();
            for (Map.Entry<String, String> header : headers.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }
            GZIPOutputStream zos = null;
            InputStream is = null;
            OutputStreamWriter osw = null;
            RandomAccessFile raf = null;
            try {
                if (Request.Method.POST.equals(request.getMethod())) {
                    osw = new OutputStreamWriter(connection.getOutputStream(), request.getEncoding());
                    osw.write(request.encodeParams());
                    osw.flush();
                    osw.close();
                }
                if (Request.Method.PUT.equals(request.getMethod())) {
                    osw = new OutputStreamWriter(connection.getOutputStream(), request.getEncoding());
                    osw.write(request.getContent());
                    osw.flush();
                    osw.close();
                }
                connection.connect();
                is = connection.getInputStream();
                byte[] buffer = new byte[1024 * 8];
                cachedResponse = prepareResponse();
                if (cachedResponse == null) {
                    throw new IOException("Error on create html cache file using path " + cachedResponse.getAbsolutePath());
                }
                if (cachedResponse.getRequest().isArchiveResult()) {
                    zos = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(cachedResponse)));
                    while (cachedResponse.readStream(is, zos, buffer)) ;
                    cachedResponse.setArched(true);
                } else {
                    raf = new RandomAccessFile(cachedResponse, "rw");
                    while (cachedResponse.readStream(is, raf, buffer)) ;
                }
                cachedResponse.isDownloadOver = true;
            } catch (Exception ex) {
                Log.e(TAG, "Error on download html using url " + request.getUrl(), ex);
                continue;
            } finally {
                if (osw != null) osw.close();
                if (zos != null) zos.close();
                if (raf != null) raf.close();
                if (is != null) is.close();
            }
        } while (request.canReconnect() && cachedResponse == null);
        Log.i(TAG, "Request completed using url: " + request.getUrl() + " bytes received " + cachedResponse.length() +
                " Path: " + cachedResponse.getAbsolutePath());
        return cachedResponse;
    }


}
