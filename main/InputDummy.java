import java.time.LocalDateTime;
import java.util.ArrayList;

import model.ContentState;
import model.ContentType;
import model.User;
import model.post.Feed;
import model.post.Post;
import model.post.Reel;
import model.post.Story;

public class InputDummy {
    public InputDummy() {
        User user1 = new User("USER-01", "Andi", "123", "hai saya Andi", null);
        ArrayList<Post> user1Posts = new ArrayList<Post>();
        user1Posts.add(new Story("STORY001", ContentState.SHOWED, LocalDateTime.of(2022, 9, 13, 13, 0, 0), ContentType.VIDEO, null, 12, 550));
        user1Posts.add(new Reel("REEL001", ContentState.PINNED, LocalDateTime.of(2022, 9, 15, 11, 22, 21), ContentType.VIDEO, null, 50, 1200));
        user1Posts.add(new Reel("REEL002", ContentState.PINNED, LocalDateTime.of(2022, 9, 18, 10, 2, 1), ContentType.VIDEO, null, 120, 2022));
        user1.setPosts(user1Posts);
        Database.users.add(user1);

        User user2 = new User("USER-02", "Budi", "123", "hai saya Budi", null);
        ArrayList<Post> user2Posts = new ArrayList<Post>();
        user2Posts.add(new Story("STORY002", ContentState.DELETED, LocalDateTime.of(2022, 8, 23, 5, 11, 0), ContentType.PICTURE, null, 5, 120));
        user2Posts.add(new Reel("REEL003", ContentState.SHOWED, LocalDateTime.of(2022, 8, 25, 15, 2, 43), ContentType.VIDEO, null, 122, 90));
        user2Posts.add(new Feed("FEED001", ContentState.ARCHIVED, LocalDateTime.of(2022, 9, 18, 10, 3, 1), ContentType.PICTURE, null, "Hello world", 13));
        user2.setPosts(user2Posts);
        Database.users.add(user2);
        
        // case: bestpost
        User user3 = new User("USER-03", "Caca", "123", "hai saya Caca", null);
        ArrayList<Post> user3Posts = new ArrayList<Post>();
        user3Posts.add(new Story("STORY002", ContentState.DELETED, LocalDateTime.of(2022, 8, 23, 5, 11, 0), ContentType.PICTURE, null, 5, 120));
        user3Posts.add(new Reel("REEL003", ContentState.SHOWED, LocalDateTime.of(2022, 8, 25, 15, 2, 43), ContentType.VIDEO, null, 122, 900));
        user3Posts.add(new Feed("FEED001", ContentState.ARCHIVED, LocalDateTime.of(2022, 9, 18, 10, 3, 1), ContentType.PICTURE, null, "Hello world", 900));
        user3.setPosts(user3Posts);
        Database.users.add(user3);
    }
}
