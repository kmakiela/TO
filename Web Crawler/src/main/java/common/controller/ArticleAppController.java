package common.controller;


import java.io.IOException;
import java.util.ArrayList;

import common.Article;
import common.Main;
import common.WebArticleSearcher;
import common.Website;
import common.model.ArticleSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ArticleAppController {

    private Stage primaryStage;

    public ArticleAppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initRootLayout() {
        try {
            this.primaryStage.setTitle("Artykuły");

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ArticleOverview.fxml"));
            BorderPane rootLayout = loader.load();

            // set initial data into controller
            ArticleOverviewController controller = loader.getController();
            controller.setAppController(this);

            WebArticleSearcher articleSearcher = new WebArticleSearcher();
            ArrayList<Article> articlePapList = articleSearcher.getArticleList(Website.PAP);

            ArticleSet artset = new ArticleSet(articlePapList);
            controller.setData(artset);

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }

    }
}
