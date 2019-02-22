package common;

import DatabaseConnection.ConnectionProvider;
import common.controller.ArticleAppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class Main extends Application{

	private static WebArticleSearcher articleSearcher = new WebArticleSearcher();
	
	public static void main(String[] args) throws SQLException {
		ConnectionProvider.init("jdbc:sqlite:web_crawler.db");
		/**
		
		ArrayList<Article> artList = articleSearcher.getArticleList(Website.All);
		for(Article article : artList) {
			System.out.println(article.toFancyString());
		}
		System.out.println("FILLING LIST");
		ArrayList<Article> artListFull = articleSearcher.fillArticleContentInArray(artList);
		for(Article article : artListFull) {
			//System.out.println(article.toFancyString());
		}
		System.out.println("Liczba artyku³ów: " + artListFull.size());
		
		ArrayList<Article> artListKEY = articleSearcher.findArticlesWithKeyword("Prezydenta", Website.All);
		for(Article article : artListFull) {
			//System.out.println(article.toFancyString());
		}
		System.out.println(artListFull.size()+"/ " + artListKEY.size());
		**/
		/*
		System.out.println("Start Main");
		ConnectionProvider.init("jdbc:sqlite:web_crawler.db");

		ArrayList<Article> articlePapList = articleSearcher.getArticleList(Website.PAP);
		for(Article article : articlePapList) {
			System.out.println(article.toFancyString());
		}
		System.out.println(articlePapList.size());

		Article OneArt = articleSearcher.fillArticleContent(articlePapList.get(2));

		System.out.println(OneArt.toFancyString());



        ConnectionProvider.close();
        */
		launch(args);
		ConnectionProvider.close();
    }

	private Stage primaryStage;

    private ArticleAppController appController;

    @Override
	public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("My first JavaFX app");

        this.appController = new ArticleAppController(primaryStage);
        this.appController.initRootLayout();

    }


}
