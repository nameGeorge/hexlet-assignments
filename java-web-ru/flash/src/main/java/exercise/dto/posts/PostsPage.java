package exercise.dto.posts;

import exercise.dto.BasePage;
import exercise.model.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class PostsPage extends BasePage {
    private List<Post> posts;

    public PostsPage(List<Post> posts) {
        super();
        this.posts = posts;
    }

    public PostsPage(List<Post> posts, String flash, String flashType) {
        super(flash, flashType);
        this.posts = posts;
    }

}