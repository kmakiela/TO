package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

import WebArticleSearcher.SearcherManager;



public class WebArticleSearcher {
	public SearcherManager getManager() {
		return manager;
	}

	private SearcherManager manager;
	
	public WebArticleSearcher() {
		this.manager = new SearcherManager();
	}

	public ArrayList<Article> getArticleList(Website website) {
		if(website == Website.All) {
			ArrayList<Article> articleArr = new ArrayList<Article>();
			for(Website web : Website.values()) {
				if(web == Website.All) continue;
				articleArr.addAll(manager.getWebCrawler(web).getArticleList());
			}
			return articleArr;
		} else {
			try {
				return manager.getWebCrawler(website).getArticleList();
			}
			catch (UnsupportedOperationException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<Article> findArticlesWithKeyword(String keyword, Website website) {
		ArrayList<Article> articleArrHeaders = new ArrayList<Article>();
		ArrayList<Article> articleArrFull = new ArrayList<Article>();
		articleArrHeaders = getArticleList(website);
		for(Article article : articleArrHeaders) {
			Article filledArticle = fillArticleContent(article);
			if(filledArticle.contentContains(keyword))
				articleArrFull.add(filledArticle);
		}
		return articleArrFull;
	}
	public Article fillArticleContent(Article article) {
		Optional<Article> database_article = findArticleInDatabase(article);
		Article found_article = database_article.orElse(manager.getWebScrapper(article.getWebsite()).fillArticleContent(article));
		if(!database_article.isPresent()){
			insertIntoDatabase(found_article);
		}
		return found_article;
	}
	
	public ArrayList<Article> fillArticleContentInArray(ArrayList<Article> articleArray) {
		ArrayList<Article> fullArticleArray = new ArrayList<Article>();
		for(Article article : articleArray) {
			fullArticleArray.add(fillArticleContent(article));
		}
		return fullArticleArray;
	}
	

	public Optional<Article> insertIntoDatabase(Article article){ // article.getDate().toString()
		DateFormat format = new SimpleDateFormat();
		Optional<Article> returned_art = Article.create(article.getWebsite().toString(), article.getUrl(), article.getTitle(), format.format(article.getDate()), article.getAuthor(), article.getContent());
		return returned_art;
	}
	private Optional<Article> findArticleInDatabase(Article article){
		return Article.findByUrl(article.getUrl());
	}

}
