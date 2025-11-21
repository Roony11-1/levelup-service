package com.patitofeliz.levelup_service.repository.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patitofeliz.levelup_service.model.blogs.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>
{

}
