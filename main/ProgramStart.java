import model.ContentState;

public class ProgramStart {
    Controller controller = new Controller();

    public ProgramStart() {
        System.out.println("showUserPost()");
        System.out.println(controller.showUserPost(controller.getUserById("USER-02")));
        System.out.println("\r\nshowPost()");
        System.out.println(controller.showPost("STORY002"));
        System.out.println("\r\nchangePostState() (returns 0 if success)");
        System.out.print("status: ");
        System.out.println(controller.changePostState(
            controller.getUserById("USER-01"), 
            controller.getUserById("USER-01").getPosts().get(0),
            ContentState.DELETED
        ));
        System.out.println("\r\nshowUserBestPost()");
        System.out.println(controller.showUserBestPost(controller.getUserById("USER-03")));
    }
}
