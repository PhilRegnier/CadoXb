package com.example.cadox.dao;


import com.example.cadox.bo.Article;

public interface ArticleDAO {
    public Article selectFirst();
    public void update(Article article);
}
