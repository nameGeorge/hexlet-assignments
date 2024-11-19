package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postPath(Long postId) {
        return "/posts/" + postId;
    }

    public static String postsPath() {
        return "/posts";
    }
    // END
}