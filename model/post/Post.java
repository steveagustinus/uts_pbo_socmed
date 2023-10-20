package model.post;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Comment;
import model.ContentState;
import model.ContentType;

public class Post {
    private String postId;
    private ContentState status;
    private LocalDateTime timeupload;
    private ContentType postType;
    private ArrayList<Comment> comments;
    
    public Post(String postId, ContentState status, LocalDateTime timeupload, ContentType postType, ArrayList<Comment> comments) {
        this.postId = postId;
        this.status = status;
        this.timeupload = timeupload;
        this.postType = postType;
        this.comments = comments;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public ContentState getStatus() {
        return status;
    }

    public void setStatus(ContentState status) {
        this.status = status;
    }

    public LocalDateTime getTimeupload() {
        return timeupload;
    }

    public void setTimeupload(LocalDateTime timeupload) {
        this.timeupload = timeupload;
    }

    public ContentType getPostType() {
        return postType;
    }

    public void setPostType(ContentType postType) {
        this.postType = postType;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
