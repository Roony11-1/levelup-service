package com.patitofeliz.levelup_service.service.blog;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.blogs.Blog;
import com.patitofeliz.levelup_service.repository.blog.BlogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService 
{
    private final BlogRepository blogRepository;

    public List<Blog> findAll()
    {
        return blogRepository.findAll();
    }
}
