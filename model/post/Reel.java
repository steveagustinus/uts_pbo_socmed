package model.post;

import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Comment;
import model.ContentState;
import model.ContentType;

public class Reel extends Post {
    private int duration;
    private int playedCount;

    public Reel(String postId, ContentState status, LocalDateTime timeupload, ContentType postType, ArrayList<Comment> comments, int duration, int playedCount) {
        super(postId, status, timeupload, postType, comments);
        this.duration = duration;
        this.playedCount = playedCount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlayedCount() {
        return playedCount;
    }

    public void setPlayedCount(int playedCount) {
        this.playedCount = playedCount;
    }
}
