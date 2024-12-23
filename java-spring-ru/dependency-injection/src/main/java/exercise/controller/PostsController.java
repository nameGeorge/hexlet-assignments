package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public PostsController(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post getPost(@PathVariable long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post postDate) {
        List<Post> posts = postRepository.findAll();
        var post = posts.stream().filter(p -> p.equals(postDate)).findFirst();
        if(post.isPresent()) {
            throw new RuntimeException("Post already exists with id " + post.get().getId());
        } else {
            return postRepository.saveAndFlush(postDate);
        }
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post update(@RequestBody Post postData, @PathVariable long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        post.setBody(postData.getBody());
        post.setTitle(postData.getTitle());
        postRepository.saveAndFlush(post);
        return post;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        postRepository.deleteById(id);
        commentRepository.deleteByPostId(id);
    }

}
// END
