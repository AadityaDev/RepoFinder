package com.aditya.repsfinder.services;

import androidx.annotation.NonNull;
import android.util.Log;

import com.google.common.util.concurrent.ListenableFuture;
import com.aditya.repsfinder.Utility;
import com.aditya.repsfinder.concurrency.ExecutorUtils;
import com.aditya.repsfinder.constants.AppAPI;
import com.aditya.repsfinder.network.RequestGenerator;
import com.aditya.repsfinder.network.RequestHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.Callable;

import okhttp3.Request;

public class RepoService {

    public ListenableFuture<JSONArray> getClosedRepositories(@NonNull final String orgName, @NonNull final String repoName) {
        return ExecutorUtils.getBackgroundPool().submit(new Callable<JSONArray>() {
            @Override
            public JSONArray call() throws Exception {
                Request request = RequestGenerator.get(AppAPI.CLOSED_REQUEST + orgName + AppAPI.SLASH + repoName + AppAPI.CLOSED_STATE);
                String body = RequestHandler.makeRequestAndValidate(request);
                JSONArray jsonArray = Utility.getResultJSONArray(body);
                return jsonArray;
            }
        });
    }

    public ListenableFuture<JSONArray> getOpenRepositories(@NonNull final String orgName, @NonNull final String repoName) {
        return ExecutorUtils.getBackgroundPool().submit(new Callable<JSONArray>() {
            @Override
            public JSONArray call() throws Exception {
                Request request = RequestGenerator.get(AppAPI.OPEN_REQUEST + orgName+ AppAPI.SLASH + repoName + AppAPI.OPEN_STATE);
                String body = RequestHandler.makeRequestAndValidate(request);
                JSONArray jsonArray = Utility.getResultJSONArray(body);
                return jsonArray;
            }
        });
    }

}
