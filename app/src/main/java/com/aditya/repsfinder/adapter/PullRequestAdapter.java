package com.aditya.repsfinder.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aditya.repsfinder.modal.RepoDetail;
import com.aditya.repsfinder.R;
import com.aditya.repsfinder.utils.StringUtils;

import java.util.List;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder>{

    private Context context;
    private List<RepoDetail> connections;

    public PullRequestAdapter(@NonNull Context context, @NonNull List<RepoDetail> connections){
        this.context=context;
        this.connections=connections;
    }

    @Override
    public PullRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_card, parent, false);
        return new PullRequestViewHolder(context,view);
    }

    @Override
    public void onBindViewHolder(PullRequestViewHolder holder, int position) {
        final RepoDetail pullRequest=connections.get(position);
        if(pullRequest!=null){
            if(StringUtils.isNotEmptyOrNull(pullRequest.getTitle())){
                holder.repoTitle.setText(pullRequest.getTitle());
            }
            if(StringUtils.isNotEmptyOrNull(pullRequest.getCreated_at())){
                holder.createdAt.setText("Created At: "+pullRequest.getCreated_at());
            }
            if(pullRequest.getNumber()!=-1){
                holder.prNumber.setText("PR Number: "+pullRequest.getNumber());
            }
            if(pullRequest.getUser()!=null&&StringUtils.isNotEmptyOrNull(pullRequest.getUser().getLogin())){
                holder.userName.setText("User: "+pullRequest.getUser().getLogin());
            }
            if(pullRequest.getPull_request()!=null) {
                holder.repoURL.setText("Patch URL => " + pullRequest.getPull_request().getPatch_url());
            }
//            if(pullRequest.getPull_request()!=null&&StringUtils.isNotEmptyOrNull(pullRequest.getPull_request().getPatch_url())){
//                holder.repoURL.setText("Patch URL => "+pullRequest.getPull_request().getPatch_url());
//            }
        }
    }

    @Override
    public int getItemCount() {
        return connections.size();
    }


    public static class PullRequestViewHolder extends RecyclerView.ViewHolder{

        private TextView repoTitle;
        private TextView createdAt;
        private TextView repoURL;
        private TextView prNumber;
        private TextView userName;

        public PullRequestViewHolder(@NonNull Context context, @NonNull View itemView) {
            super(itemView);
            repoTitle =(TextView)itemView.findViewById(R.id.title);
            createdAt=(TextView)itemView.findViewById(R.id.createdAt);
            repoURL=(TextView)itemView.findViewById(R.id.url);
            prNumber = (TextView) itemView.findViewById(R.id.pr);
            userName = (TextView) itemView.findViewById(R.id.userName);
        }
    }

}
