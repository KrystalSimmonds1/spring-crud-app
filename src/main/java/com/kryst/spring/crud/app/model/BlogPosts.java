package com.kryst.spring.crud.app.model;
import javax.persistence.*;

@Entity
@Table(name = "blog_posts")

public class BlogPosts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    private String subtitle;

    private String body;

    private String author;

    private String tags;
    public BlogPosts() {
    }

    public BlogPosts(String title, String subtitle, String body, String author, String tags) {
        this.title = title;
        this.subtitle = subtitle;
        this.body = body;
        this.author = author;
        this.tags = tags;
    }
    public long getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", written by" + author + "]";
    }
}

