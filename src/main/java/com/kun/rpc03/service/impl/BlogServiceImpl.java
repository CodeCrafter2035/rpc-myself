package com.kun.rpc03.service.impl;


import com.kun.rpc03.service.BlogService;
import com.kun.rpc03.common.Blog;

public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).title("我的博客").useId(22).build();
        System.out.println("客户端查询了"+id+"博客");
        return blog;
    }
}
