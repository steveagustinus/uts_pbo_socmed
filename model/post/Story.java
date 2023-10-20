package model.post;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Comment;
import model.ContentState;
import model.ContentType;

public class Story extends Post {
    private int duration;
    private int views;
    
    public Story(String postId, ContentState status, LocalDateTime timeupload, ContentType postType, ArrayList<Comment> comments, int duration, int views) {
        super(postId, status, timeupload, postType, comments);
        this.duration = duration;
        this.views = views;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
