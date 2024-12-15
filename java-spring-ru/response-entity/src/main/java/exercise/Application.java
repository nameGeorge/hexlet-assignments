package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "10") Integer limit) {
        List<Post> posts = this.posts.stream().limit(limit).toList();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(this.posts.size()))
                .body(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        Optional<Post> post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post post) {
        Optional<Post> oldPost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (oldPost.isPresent()) {
            oldPost.get().setTitle(post.getTitle());
            oldPost.get().setBody(post.getBody());
            return ResponseEntity.ok(oldPost.get());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(post);
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
