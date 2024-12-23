package exercise.controller;

import exercise.model.Post;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {

    private CommentRepository commentRepository;

    public CommentsController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment getComment(@PathVariable long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment commentData) {
        List<Comment> comments = commentRepository.findAll();
        var comment = comments.stream().filter(p -> p.equals(commentData)).findFirst();
        if(comment.isPresent()) {
            throw new RuntimeException("Comment already exists with id " + comment.get().getId());
        } else {
            return commentRepository.saveAndFlush(commentData);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment update(@RequestBody Comment commentData, @PathVariable long id) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        comment.setBody(commentData.getBody());
        commentRepository.saveAndFlush(comment);
        return comment;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        commentRepository.deleteById(id);
    }


}
// END
