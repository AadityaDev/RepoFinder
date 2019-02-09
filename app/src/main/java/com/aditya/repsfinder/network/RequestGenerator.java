package com.aditya.repsfinder.network;

import android.util.Log;

import com.google.gson.Gson;
import com.aditya.repsfinder.constants.AppAPI;

import java.io.File;

import androidx.annotation.NonNull;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestGenerator {

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("application/json; charset=utf-8");

    private static void addDefaultHeaders(@NonNull Request.Builder builder) {
        builder.header(AppAPI.Headers.ACCEPT_KEY, AppAPI.Headers.ACCEPT_VALUE);
        builder.removeHeader(AppAPI.Headers.CAN_RENDER_HTML);
    }

    public static Request get(@NonNull String url) {
        Request.Builder builder = new Request.Builder().url(url);
        Log.d("Get With Token", builder.build().toString());
        return builder.build();
    }

    public static Request get(@NonNull String url, @NonNull String token) {
        Request.Builder builder = new Request.Builder().url(url);
        addDefaultHeaders(builder);
        builder.addHeader("Authorization", token);
        builder.addHeader("Content-Type", "application/json");
        Log.d("Get With Token", token);
        return builder.build();
    }

    public static Request jsonBody(String url, StringBuilder sb) {
        Request.Builder builder = new Request.Builder().url(url);
        addDefaultHeaders(builder);
        RequestBody body = RequestBody.create(MEDIA_TYPE_MARKDOWN, sb.toString());
        String s = builder.post(body).build().toString();
        return builder.post(body).build();
    }

    public static Request postWithToken(@NonNull String url, @NonNull String params, @NonNull String authToken) {
        Request.Builder builder = new Request.Builder().url(url);
        addDefaultHeaders(builder);
        Log.d("JobDetailActivity", "Header" + builder.toString());
        builder.addHeader(AppAPI.Headers.AUTHORIZATION_KEY, authToken);
        builder.addHeader(AppAPI.Headers.ACCEPT_KEY, "application/vnd.myrefers.v0+json");
        builder.addHeader(AppAPI.Headers.CONTENT_TYPE, AppAPI.Headers.ACCEPT_JSON);
        RequestBody body = RequestBody.create(MEDIA_TYPE_MARKDOWN, params.toString());
        Gson gson = new Gson();
        String s = gson.toJson(builder.post(body));
        return builder.post(body).build();
    }

    public static Request putWithToken(@NonNull String url, @NonNull String params, @NonNull String authToken) {
        Request.Builder builder = new Request.Builder().url(url);
        addDefaultHeaders(builder);
        builder.addHeader(AppAPI.Headers.AUTHORIZATION_KEY, authToken);
        builder.addHeader(AppAPI.Headers.ACCEPT_KEY, "application/vnd.myrefers.v0+json");
        builder.addHeader(AppAPI.Headers.CONTENT_TYPE, AppAPI.Headers.ACCEPT_JSON);
        RequestBody body = RequestBody.create(MEDIA_TYPE_MARKDOWN, params.toString());
        Gson gson = new Gson();
        String s = gson.toJson(builder.put(body));
        return builder.put(body).build();
    }

    public static Request multipartPost(@NonNull String url, @NonNull File file, @NonNull String authToken) {
        Request.Builder builder = new Request.Builder().url(url);
        addDefaultHeaders(builder);
        builder.addHeader(AppAPI.Headers.AUTHORIZATION_KEY, authToken);
        builder.addHeader(AppAPI.Headers.ACCEPT_KEY, "application/vnd.myrefers.v0+json");
        builder.addHeader(AppAPI.Headers.CONTENT_TYPE, AppAPI.Headers.ACCEPT_JSON);
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
        multipartBuilder.addFormDataPart("resume", file.getName(), RequestBody.create(MediaType.parse("html/text"), file));
        multipartBuilder.setType(MultipartBody.FORM);
        return builder.post(multipartBuilder.build()).build();
    }
}
