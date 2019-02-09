package com.aditya.repsfinder.network;

import android.util.Log;

import com.aditya.repsfinder.Factory;
import com.aditya.repsfinder.constants.AppConstants;
import com.aditya.repsfinder.network.exceptions.AccessDeniedException;
import com.aditya.repsfinder.network.exceptions.ApiStatusException;
import com.aditya.repsfinder.network.exceptions.HttpException;
import com.aditya.repsfinder.network.exceptions.ResourceNotFoundException;
import com.aditya.repsfinder.network.exceptions.ServerErrorException;
import com.aditya.repsfinder.network.exceptions.UnauthorizedAccessException;
import com.aditya.repsfinder.network.exceptions.UnsupportedFileTypeException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestHandler {

    private static String TAG="RequestHandler";

    public static String makeRequestAndValidate(Request request) throws JSONException, IOException, HttpException {
        return doRequest(request, true);
    }

    private static String doRequest(Request request, boolean doValidate) throws IOException, HttpException, JSONException {
        Log.d(TAG, request.method() + " : " + request.url());
        OkHttpClient httpClient = Factory.getOkHTTPClient();
        Response response;
        String body = new String();
        response = httpClient.newCall(request).execute();
        if (response == null) {
            return null;
        }
        Log.d(TAG, response.body() + " : " + body);
        body = response.body().string();
        Log.d(TAG, response.code() + " : " + body);
        if (!response.isSuccessful()) {
            if (response.code() == 400) {
                throw new HttpException(AppConstants.BAD_REQUEST);
            } else if (response.code() == 401 || response.code() == 1028) {
                throw new AccessDeniedException();
            } else if (response.code() == 404) {
                throw new ResourceNotFoundException(body);
            } else if (response.code() == 403) {
                throw new UnauthorizedAccessException();
            } else if (response.code() == 415) {
                throw new UnsupportedFileTypeException();
            } else if (response.code() >= 500) {
                throw new ServerErrorException();
            } else {
                throw new HttpException(AppConstants.COULDNT_REACH_OUR_SERVERS);
            }
        } else {
//            if (doValidate) {
//                validateResponse(body);
//            }
            return body;
        }
    }

    private static void validateResponse(String body) throws ApiStatusException, JSONException {
        JSONObject response = new JSONObject();
        try {
            response.put("result",body);
            if (response.isNull(AppConstants.NotificationConstant.STATUS)) {
                return;
            }
            String status = response.getString(AppConstants.NotificationConstant.STATUS);
            if (!status.equals(AppConstants.NotificationConstant.SUCCESS)) {
                if (response.isNull(AppConstants.NotificationConstant.ERRORS)) {
                    return;
                } else {
                    JSONArray errors = response.getJSONObject(AppConstants.NotificationConstant.ERRORS).getJSONArray(AppConstants.NotificationConstant.ERRS);
                    StringBuilder error = new StringBuilder(2);
                    for (int i = 0; i < errors.length(); i++) {
                        error.append(errors.getJSONObject(i).getString(AppConstants.NotificationConstant.MSG));
                        error.append(". ");
                    }
                    throw new ApiStatusException(error.toString());
                }
            }
        } catch (JSONException ignored) {
            throw new JSONException(body);
        }
    }
}
