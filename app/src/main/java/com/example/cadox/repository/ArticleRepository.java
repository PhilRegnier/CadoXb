package com.example.cadox.repository;

import com.example.cadox.bo.Article;
import com.example.cadox.dao.ArticleDAO;
import com.example.cadox.dao.DAOFactory;
import com.example.cadox.dao.DAOType;

public class ArticleRepository {
    private ArticleDAO activeDao;

    /////////////////////SINGLETON/////////////////////
    private static ArticleRepository instance;
    private ArticleRepository(){
        activeDao = DAOFactory.createArticleDAO(DAOType.MEMORY);
    }

    public static ArticleRepository getInstance(){
        if (instance == null){
            instance = new ArticleRepository();
        }
        return instance;
    }

    //////////////////GESTION ARTICLE//////////////////

    /**
     * Retourne le premier article exposé par la source de données
     * @return
     */
    public Article getFirstArticle(){
        return activeDao.selectFirst();
    }

    /**
     * Met à jour l'article à la position courante
     * @param article
     */
    public void replace(Article article){ activeDao.update(article);}
}
