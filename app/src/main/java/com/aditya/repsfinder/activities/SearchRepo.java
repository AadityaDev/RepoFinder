package com.aditya.repsfinder.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aditya.repsfinder.R;
import com.aditya.repsfinder.constants.AppConstants;
import com.aditya.repsfinder.utils.StringUtils;

public class SearchRepo extends AppCompatActivity {

    private EditText organizationName;
    private EditText repoName;
    private Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_repo);

        organizationName = (EditText)findViewById(R.id.orgName);
        repoName = (EditText) findViewById(R.id.repoName);
        search = (Button) findViewById(R.id.search);

        organizationName.setText("prestodb");
        repoName.setText("presto");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchRepoByOrganizationAndName();
            }
        });
    }

    private void searchRepoByOrganizationAndName(){
        if(StringUtils.isNotEmptyOrNull(organizationName.getText().toString())){
            if(StringUtils.isNotEmptyOrNull(repoName.getText().toString())){
                Bundle bundle = new Bundle();
                bundle.putString(AppConstants.ORG_NAME,organizationName.getText().toString());
                bundle.putString(AppConstants.REPO_NAME,repoName.getText().toString());
                Intent openSearchResultPage = new Intent(getApplicationContext(),MainActivity.class);
                openSearchResultPage.putExtras(bundle);
                startActivity(openSearchResultPage);
            }else {
                Toast.makeText(getApplicationContext(),"Please enter repo name",Toast.LENGTH_LONG);
            }
        }else {
            Toast.makeText(getApplicationContext(),"Please enter organization name",Toast.LENGTH_LONG);
        }
    }

}
