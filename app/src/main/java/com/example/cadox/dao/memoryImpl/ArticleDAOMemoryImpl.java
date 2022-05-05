package com.example.cadox.dao.memoryImpl;

import com.example.cadox.bo.Article;
import com.example.cadox.dao.ArticleDAO;

import java.util.ArrayList;
import java.util.List;

public class ArticleDAOMemoryImpl implements ArticleDAO {
    private static List<Article> articlesInMemory;

    static {
        articlesInMemory = new ArrayList<>();
        articlesInMemory.add(new Article(1, "Des lunettes de soleil (memory)", "RAY-BAN RB 4259 601/19 51/20",
                85.0f, (byte)3, "https://www.optical-center.fr/lunettes-de-soleil/lunettes-de-soleil-RAY-BAN-RB-4259-60119-5120-25318.html?gclid=EAIaIQobChMIitHizMWe5QIVloXVCh1X6gw_EAQYASABEgLu0PD_BwE"));
    }


    @Override
    public Article selectFirst() {
        return articlesInMemory.get(0);
    }

    @Override
    public void update(Article article) {
        for (int i=0; i < articlesInMemory.size();i++ ) {
            if (articlesInMemory.get(i).getId() == article.getId()){
                articlesInMemory.set(i, article);
                break;
            }
        }
    }
}
