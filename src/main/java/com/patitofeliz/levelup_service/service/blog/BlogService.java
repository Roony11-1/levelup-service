package com.patitofeliz.levelup_service.service.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.blogs.Blog;
import com.patitofeliz.levelup_service.repository.blog.BlogRepository;

@Service
public class BlogService 
{
    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> findAll()
    {
        return blogRepository.findAll();
    }
}
