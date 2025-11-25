package com.patitofeliz.levelup_service.controller.blog;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patitofeliz.levelup_service.model.blogs.Blog;
import com.patitofeliz.levelup_service.service.blog.BlogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/blog")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class BlogController 
{
    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<List<Blog>> findAll()
    {
        List<Blog> blogs = blogService.findAll();

        if (blogs.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(blogs);
    }
}

//hola papito
  