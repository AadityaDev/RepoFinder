package com.aditya.repsfinder.modal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RepoDetail implements Parcelable, Comparable<RepoDetail> {

    private Long id;
    private String url;
    private String repository_url;
    private String labels_url;
    private String comments_url;
    private String events_url;
    private String html_url;
    private String node_id;
    private int number;
    private String title;
    private UserDetail user;
    private List<Label> labels;
    private String state;
    private String locked;
    private UserDetail assignee;
    private List<UserDetail> assignees;
    private String milestone;
    private int comments;
    private String created_at;
    private String updated_at;
    private String closed_at;
    private String author_association;
    private PullRequest pull_request;
    private String body;

    protected RepoDetail(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        url = in.readString();
        repository_url = in.readString();
        labels_url = in.readString();
        comments_url = in.readString();
        events_url = in.readString();
        html_url = in.readString();
        node_id = in.readString();
        number = in.readInt();
        title = in.readString();
        user = in.readParcelable(UserDetail.class.getClassLoader());
        labels = in.createTypedArrayList(Label.CREATOR);
        state = in.readString();
        locked = in.readString();
        assignee = in.readParcelable(UserDetail.class.getClassLoader());
        assignees = in.createTypedArrayList(UserDetail.CREATOR);
        milestone = in.readString();
        comments = in.readInt();
        created_at = in.readString();
        updated_at = in.readString();
        closed_at = in.readString();
        author_association = in.readString();
        pull_request = in.readParcelable(PullRequest.class.getClassLoader());
        body = in.readString();
    }

    public static final Creator<RepoDetail> CREATOR = new Creator<RepoDetail>() {
        @Override
        public RepoDetail createFromParcel(Parcel in) {
            return new RepoDetail(in);
        }

        @Override
        public RepoDetail[] newArray(int size) {
            return new RepoDetail[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepository_url() {
        return repository_url;
    }

    public void setRepository_url(String repository_url) {
        this.repository_url = repository_url;
    }

    public String getLabels_url() {
        return labels_url;
    }

    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDetail getUser() {
        return user;
    }

    public void setUser(UserDetail user) {
        this.user = user;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public UserDetail getAssignee() {
        return assignee;
    }

    public void setAssignee(UserDetail assignee) {
        this.assignee = assignee;
    }

    public List<UserDetail> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<UserDetail> assignees) {
        this.assignees = assignees;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public String getAuthor_association() {
        return author_association;
    }

    public void setAuthor_association(String author_association) {
        this.author_association = author_association;
    }

    public PullRequest getPull_request() {
        return pull_request;
    }

    public void setPull_request(PullRequest pull_request) {
        this.pull_request = pull_request;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(url);
        dest.writeString(repository_url);
        dest.writeString(labels_url);
        dest.writeString(comments_url);
        dest.writeString(events_url);
        dest.writeString(html_url);
        dest.writeString(node_id);
        dest.writeInt(number);
        dest.writeString(title);
        dest.writeParcelable(user, flags);
        dest.writeTypedList(labels);
        dest.writeString(state);
        dest.writeString(locked);
        dest.writeParcelable(assignee, flags);
        dest.writeTypedList(assignees);
        dest.writeString(milestone);
        dest.writeInt(comments);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(closed_at);
        dest.writeString(author_association);
        dest.writeParcelable(pull_request, flags);
        dest.writeString(body);
    }

    @Override
    public int compareTo(RepoDetail o) {
        return 0;
    }
}
