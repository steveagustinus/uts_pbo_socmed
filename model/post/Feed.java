package model.post;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Comment;
import model.ContentState;
import model.ContentType;

public class Feed extends Post {
    private String caption;
    private int likes;

    public Feed(String postId, ContentState status, LocalDateTime timeupload, ContentType postType, ArrayList<Comment> comments, String caption, int likes) {
        super(postId, status, timeupload, postType, comments);
        this.caption = caption;
        this.likes = likes;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
