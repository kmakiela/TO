package common.model;

import common.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ArticleSet {

    private ObservableList<Article> articles;

    public ArticleSet(List<Article> articles){
        this.articles = FXCollections.observableArrayList(articles);
    }

    public final ObservableList<Article> getArticles() { return articles; }

}

