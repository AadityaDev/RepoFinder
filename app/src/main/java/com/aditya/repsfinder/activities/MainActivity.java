package com.aditya.repsfinder.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.aditya.repsfinder.Factory;
import com.aditya.repsfinder.R;
import com.aditya.repsfinder.adapter.PullRequestAdapter;
import com.aditya.repsfinder.concurrency.ExecutorUtils;
import com.aditya.repsfinder.constants.AppConstants;
import com.aditya.repsfinder.modal.RepoDetail;
import com.aditya.repsfinder.uicomponents.ItemClickSupport;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private List<RepoDetail> repoDetailsOpen;
    private List<RepoDetail> repoDetailsClose;
    private PullRequestAdapter pullRequestAdapter;
    private TextView errorMessage;
    private Bundle bundle;
    private String orgName;
    private String repoName;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fillOpenView();
                    return true;
                case R.id.navigation_dashboard:
                    fillCloseView();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Factory.setUpThreadPolicy();
        Factory.getAppDB(getApplicationContext());
        bundle = getIntent().getExtras();
        if(bundle!=null){
            orgName = bundle.getString(AppConstants.ORG_NAME);
            repoName = bundle.getString(AppConstants.REPO_NAME);
            Log.d(TAG,"Bundle contains "+orgName+" => "+repoName);
        }else{
            Log.d(TAG,"Bundle is empty");
        }
        errorMessage = (TextView) findViewById(R.id.errorMessage);
        recyclerView = (RecyclerView) findViewById(R.id.repos);
        fillOpenView();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void fillOpenPullRequests(){
        ListenableFuture<JSONArray> result = Factory.getSkillScoreService().getOpenRepositories(orgName,repoName);
        Futures.addCallback(result, new FutureCallback<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    Log.d(TAG, "result is: " + result.toString());
                    for (int i = 0; i < result.length(); i++) {
                        Gson gson = new Gson();
                        RepoDetail repoDetail = gson.fromJson(result.get(i).toString(), RepoDetail.class);
                        Log.d(TAG,"index: "+i+" ->"+repoDetail.getTitle());
                        repoDetailsOpen.add(repoDetail);
                    }
                }catch (Exception e){
                    Log.d(TAG,"exception");
                    errorMessage.setText("No result found!");
                }
//                Factory.getAppDB(getApplicationContext()).repoDetailDAO().insertAll(repoDetailsOpen);
                pullRequestAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                errorMessage.setText("No result found!");
                Log.d(TAG,"error is: "+t.toString());
            }
        },ExecutorUtils.getUIThread());

    }

    private void fillClosePullRequests(){
        ListenableFuture<JSONArray> result = Factory.getSkillScoreService().getClosedRepositories(orgName,repoName);
        Futures.addCallback(result, new FutureCallback<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {
                try {
                    Log.d(TAG, "result is: " + result.toString());
                    for (int i = 0; i < result.length(); i++) {
                        Gson gson = new Gson();
                        RepoDetail repoDetail = gson.fromJson(result.get(i).toString(), RepoDetail.class);
                        Log.d(TAG,"index: "+i+" ->"+repoDetail.getTitle());
                        repoDetailsClose.add(repoDetail);
                    }
                }catch (Exception e){
                    errorMessage.setText("No result found!");
                    Log.d(TAG,"exception");
                }
                pullRequestAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                errorMessage.setText("No result found!");
                Log.d(TAG,"error is: "+t.toString());
            }
        },ExecutorUtils.getUIThread());
    }

    private void fillOpenView(){
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        repoDetailsOpen = new ArrayList<>();
        repoDetailsClose = new ArrayList<>();
        pullRequestAdapter = new PullRequestAdapter(getApplicationContext(), repoDetailsOpen);

        fillOpenPullRequests();
        recyclerView.setAdapter(pullRequestAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Log.d(TAG,"Clicked: "+repoDetailsOpen.get(position).getTitle());
            }
        });
    }

    private void fillCloseView(){
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        repoDetailsOpen = new ArrayList<>();
        repoDetailsClose = new ArrayList<>();
        pullRequestAdapter = new PullRequestAdapter(getApplicationContext(), repoDetailsClose);

        fillClosePullRequests();
        recyclerView.setAdapter(pullRequestAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Log.d(TAG,"Clicked2: "+repoDetailsClose.get(position).getTitle());
            }
        });
    }

}
