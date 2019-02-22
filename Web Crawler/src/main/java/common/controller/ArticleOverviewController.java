package common.controller;


import common.Article;
import java.time.LocalDate;
import java.util.ArrayList;

import common.model.ArticleSet;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ArticleOverviewController {

    private ArticleAppController articleController;
    private ArticleSet data;

    @FXML
    private TableView<Article> articlesTable;

    @FXML
    private TableColumn<Article, LocalDate> dateColumn;

    @FXML
    private TableColumn<Article, String> titleColumn;

    @FXML
    private TableColumn<Article, String> authorColumn;


    @FXML
    private void initialize() {
        articlesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        dateColumn.setCellValueFactory(dataValue -> (ObservableValue<LocalDate>) dataValue.getValue().getDate());
        titleColumn.setCellValueFactory(dataValue -> dataValue.getValue().getTitleProperty());
        authorColumn.setCellValueFactory(dataValue -> dataValue.getValue().getAuthorProperty());
    }


    public void setData(ArticleSet articles) {
        this.data = articles;
        articlesTable.setItems(data.getArticles());
    }

    public void setAppController(ArticleAppController appController) {
        this.articleController = appController;
    }
}


