package com.kryst.spring.crud.app.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kryst.spring.crud.app.model.BlogPosts;
import com.kryst.spring.crud.app.repository.BlogPostRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class BlogPostController {
    @Autowired
    BlogPostRepository blogPostRepository;

    @GetMapping("/blogposts")
    public ResponseEntity<List<BlogPosts>> getAllBlogPosts(@RequestParam(required = false) String title) {
        try {
            List<BlogPosts> blogPosts = new ArrayList<BlogPosts>();
            if (title == null)
                blogPostRepository.findAll().forEach(blogPosts::add);
            else
                blogPostRepository.findByTitleContaining(title).forEach(blogPosts::add);
            if (blogPosts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(blogPosts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/blogposts/{id}")
    public ResponseEntity<BlogPosts> getBlogPostById(@PathVariable("id") long id) {
        Optional<BlogPosts> blogPostData = blogPostRepository.findById(id);

        if (blogPostData.isPresent()) {
            return new ResponseEntity<>(blogPostData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/blogposts")
    public ResponseEntity<BlogPosts> createBlogPost(@RequestBody BlogPosts blogPost) {
        try {
            BlogPosts _blogpost = blogPostRepository
                    .save(new BlogPosts(blogPost.getTitle(), blogPost.getSubtitle(),blogPost.getBody(),blogPost.getAuthor(), blogPost.getTags()));
            return new ResponseEntity<>(_blogpost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/blogposts/{id}")
    public ResponseEntity<BlogPosts> updateBlogPost(@PathVariable("id") long id, @RequestBody BlogPosts blogPosts) {
        Optional<BlogPosts> blogPostData = blogPostRepository.findById(id);

        if (blogPostData.isPresent()) {
            BlogPosts _blogpost = blogPostData.get();
            _blogpost.setTitle(blogPosts.getTitle());
            _blogpost.setSubtitle(blogPosts.getSubtitle());
            _blogpost.setBody(blogPosts.getBody());
            _blogpost.setAuthor(blogPosts.getAuthor());
            _blogpost.setTags(blogPosts.getTags());
            return new ResponseEntity<>(blogPostRepository.save(_blogpost), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/blogposts/{id}")
    public ResponseEntity<HttpStatus> deleteBlogPost(@PathVariable("id") long id) {
        try {
            blogPostRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/blogposts")
    public ResponseEntity<HttpStatus> deleteAllBlogPosts() {
        try {
            blogPostRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
