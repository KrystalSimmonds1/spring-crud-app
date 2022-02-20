package com.kryst.spring.crud.app.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kryst.spring.crud.app.model.BlogPosts;

public interface BlogPostRepository extends JpaRepository<BlogPosts, Long> {
    List<BlogPosts> findByTags(String tags);
    List<BlogPosts> findByTitleContaining(String title);
}
