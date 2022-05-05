package com.example.cadox.dao;


import com.example.cadox.dao.memoryImpl.ArticleDAOMemoryImpl;

public abstract class DAOFactory {
    public static ArticleDAO createArticleDAO(DAOType type){
        ArticleDAO dao = null;
        switch (type){
            case MEMORY:
                dao = new ArticleDAOMemoryImpl();
        }
        return dao;
    }
}
