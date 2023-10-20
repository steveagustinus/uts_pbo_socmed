import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.ContentState;
import model.User;
import model.post.Feed;
import model.post.Post;
import model.post.Reel;
import model.post.Story;

public class Controller {
    public Controller() { }

    public String showUserPost(User user) {
        if (user == null) { return null; }

        ArrayList<Post> pinnedPosts = new ArrayList<Post>();
        ArrayList<Post> showedPosts = new ArrayList<Post>();
        ArrayList<Post> archivedPosts = new ArrayList<Post>();
        ArrayList<Post> deletedPosts = new ArrayList<Post>();

        for (Post post : user.getPosts()) {
            if (post.getStatus().equals(ContentState.PINNED)) { pinnedPosts.add(post); continue; }
            if (post.getStatus().equals(ContentState.SHOWED)) { showedPosts.add(post); continue; }
            if (post.getStatus().equals(ContentState.ARCHIVED)) { archivedPosts.add(post); continue; }
            if (post.getStatus().equals(ContentState.DELETED)) { deletedPosts.add(post); continue; }
        }

        sortPosts(pinnedPosts);
        sortPosts(showedPosts);
        sortPosts(archivedPosts);

        String output = "";
        output += postToString(pinnedPosts);
        output += postToString(showedPosts);
        output += postToString(archivedPosts);
        output += "Deleted post: " + deletedPosts.size();

        return output;
    }

    public String showPost(String postId) {
        Post post = null;

        mainloop:for (User user : Database.users) {
            for (Post userPost : user.getPosts()) {
                if (userPost.getPostId().equals(postId)) {
                    post = userPost;
                    break mainloop;
                }
            }
        }

        return postToStringDetailed(post);
    }

    public int changePostState(User user, Post post, ContentState status) {
        if (!user.getPosts().contains(post)) { return -3; }
        switch (status) {
            case ARCHIVED:
                post.setStatus(status);
                return 0;
            case DELETED:
                if (!post.getStatus().equals(ContentState.ARCHIVED)) {
                    return -1;
                }
                post.setStatus(ContentState.DELETED);
            case SHOWED:
                post.setStatus(status);
                return 0;
            case PINNED:
                if (!post.getStatus().equals(ContentState.SHOWED)) {
                    return -2;
                }
                if (countPinnedFeed(user) >= 3) { return -3; }
                post.setStatus(status);
                return 0;
        }
        return -99;
    }

    public String showUserBestPost(User user) {
        ArrayList<Post> userPosts = user.getPosts();
        int bestPostIndex = 0;

        for (int i = 1; i < userPosts.size(); i++) {
            Post currentPost = userPosts.get(i);
            
            if (getLikes(currentPost) >= getLikes(userPosts.get(bestPostIndex))) {
                if (getLikes(currentPost) == getLikes(userPosts.get(bestPostIndex))) {
                    if (currentPost.getTimeupload().isAfter(userPosts.get(bestPostIndex).getTimeupload())) {
                        bestPostIndex = i;
                    }
                }
                else {
                    bestPostIndex = i;
                }
            }
        }

        return postToStringDetailed(userPosts.get(bestPostIndex));
    }

    public int getLikes(Post post) {
        if (post instanceof Reel) {
            return ((Reel) post).getPlayedCount();
        }
        else if (post instanceof Story) {
            return ((Story) post).getViews();
        }
        else if (post instanceof Feed) {
            return ((Feed) post).getLikes();
        }

        return -1;
    }

    public int countPinnedFeed(User user) {
        int count = 0;
        for (Post post : user.getPosts()) {
            if (post instanceof Feed) {
                if (post.getStatus().equals(ContentState.PINNED)) {
                    count++;
                }
            }
        }

        return count;
    }

    public void sortPosts(ArrayList<Post> posts) {
        if (posts.size() <= 1) { return; }
        for (int i = 0; i < posts.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < posts.size(); j++) {
                LocalDateTime post1Time = posts.get(i).getTimeupload();
                LocalDateTime post2Time = posts.get(i).getTimeupload();
                if (post1Time.isAfter(post2Time)) { minIndex = j; }
            }

            Post temp = posts.get(i);
            posts.set(i, posts.get(minIndex));
            posts.set(minIndex, temp);
        }
    }

    public String postToStringDetailed(Post post) {
        if (post == null) { return ""; }
        String output = "";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
        output += "[" + post.getStatus() + "] ";
        output += "[" + post.getTimeupload().format(formatter) + "] ";
        output += "[ID: " + post.getPostId() + "] : ";

        if (post instanceof Reel) {
            output += "[Reel] ";
            output += "Played: " + ((Reel) post).getPlayedCount();
        }
        else if (post instanceof Story) {
            output += "[Story] ";
            output += "Views: " + ((Story) post).getViews();
        }
        else if (post instanceof Feed) {
            output += "[Feed] ";
            output += "Likes: " + ((Feed) post).getLikes();
        }

        return output;
    }

    public String postToString(ArrayList<Post> posts) {
        String output = "";
        for (Post post : posts) {
            output += postToString(post) + "\r\n";
        }
        return output;
    }

    public String postToString(Post post) {
        if (post == null) { return ""; }
        String output = "";
        
        output += "[" + post.getStatus() + "] ";

        if (post instanceof Reel) {
            output += "[Reel] ";
            output += "Played: " + ((Reel) post).getPlayedCount();
        }
        else if (post instanceof Story) {
            output += "[Story] ";
            output += "Views: " + ((Story) post).getViews();
        }
        else if (post instanceof Feed) {
            output += "[Feed] ";
            output += "Likes: " + ((Feed) post).getLikes();
        }

        return output;
    }

    public User getUserById(String id) {
        for (User user : Database.users) {
            if (user.getUserId().equals(id)) { return user; }
        }
        return null;
    }
}
