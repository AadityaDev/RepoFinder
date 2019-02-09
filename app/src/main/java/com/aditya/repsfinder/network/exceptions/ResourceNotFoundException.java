package com.aditya.repsfinder.network.exceptions;

import androidx.annotation.Nullable;

import com.aditya.repsfinder.constants.AppConstants;

import org.json.JSONException;
import org.json.JSONObject;

public class ResourceNotFoundException extends HttpException {
    private String mBody;

    public ResourceNotFoundException() {
        super(AppConstants.MESSAGE_RESOURCE_NOT_FOUND);
        mBody = null;
    }

    public ResourceNotFoundException(String body) {
        super(AppConstants.MESSAGE_RESOURCE_NOT_FOUND);
        try {
            JSONObject jsonObject = new JSONObject(body);
            mBody = jsonObject.getString("error");
        } catch (JSONException e) {
            mBody = null;
        }
    }

    @Nullable
    public String getBody() {
        return mBody;
    }
}
