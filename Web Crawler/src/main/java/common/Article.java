package common;

import DatabaseConnection.QueryExecutor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class Article {

	public static final String TABLE_NAME = "article";
	private Website website;
	private String url;
	private Date date;
	private String content;
    private StringProperty title;
    private StringProperty author;


    public static Optional<Article> create(String website, String url, String title, String date, String author, String content){
		String insert = String.format("INSERT INTO %s (website, url, title, date, author, content) VALUES ('%s', '%s','%s', '%s','%s', '%s')", TABLE_NAME, website, url, title.replace("'", ""), date, author, content.replace("'", ""));
		//System.out.println("insert:: "+insert);
		try{
			int id = QueryExecutor.createAndObtainId(insert);
			Optional<Article> article = Article.findById(id);
			return article;
		} catch(SQLException e){
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public static Optional<Article> findById(final int id){
		String find = String.format("SELECT * FROM %s WHERE id = %d", TABLE_NAME, id);
		try{
			ResultSet set = QueryExecutor.read(find);
			Date date = null;
			// date format : Wed Jan 23 19:26:00 CET 2019
			DateFormat format = new SimpleDateFormat();
			try {
				date = format.parse(set.getString("date"));
			} catch (ParseException e) {
				//e.printStackTrace();
			}
			return Optional.of(Article.builder().website(Website.valueOf(set.getString("website"))) .url(set.getString("url")).title(set.getString("title")).date(date).author(set.getString("author")).content(set.getString("content")).build());
		}catch(SQLException e){
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public static Optional<Article> findByUrl(String url){
		String find = String.format("SELECT * FROM %s WHERE url = '%s'", TABLE_NAME, url);
		try{
			ResultSet set = QueryExecutor.read(find);
			int id = QueryExecutor.readIdFromResultSet(set);
			if(id!=-1){
				return Article.findById(id);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	public static class ArticleBuilder {
		private Website website;
		private String url;
		private StringProperty title;
		private Date date;
		private StringProperty author;
		private String content;



        private ArticleBuilder() {}
		private ArticleBuilder(Article article) {
			website = article.website;
			url = article.url;
			title = article.title;
			date = article.date;
			author = article.author;
			content = article.content;

		}
		
		public ArticleBuilder website(Website website) {
			this.website = website;
			return this;
		}
		
		public ArticleBuilder url(String url) {
			this.url = url;
			return this;
		}
		
		public ArticleBuilder title(String title) {
			this.title = new SimpleStringProperty(title);
			return this;
		}
		public ArticleBuilder title(StringProperty title) {
			this.title = title;
			return this;
		}
		
		public ArticleBuilder date(Date date) {
			this.date = date;
			return this;
		}
		
		public ArticleBuilder author(String author) {
			this.author = new SimpleStringProperty(author);
			return this;
		}
		public ArticleBuilder author(StringProperty author) {
			this.author = author;
			return this;
		}
		
		public ArticleBuilder content(String content) {
			this.content = content;
			return this;
		}
		
		public Article build() {
			if(website == null || url == null || title == null) {
				throw new IllegalArgumentException("Not enough arguments");
			}
			return new Article(website, url, title, date, author, content);
		}
	}
	
	public static ArticleBuilder builder() {
		return new ArticleBuilder();
	}
	public static ArticleBuilder builder(Article article) {
		return new ArticleBuilder(article);
	}
	
	public Article(Website website, String url, String title) {
		this.website = website;
		this.url = url;
		this.title = new SimpleStringProperty(title);
		this.date  = null;
		this.content = null;
		this.author = null;
	}

	public Article(Website website, String url, String title, Date date, String author, String content) {
		this.website = website;
		this.url = url;
		this.date = date;
		this.content = content;
		this.title = new SimpleStringProperty(title);
		this.author = new SimpleStringProperty(author);
	}
	public Article(Website website, String url, StringProperty title, Date date, StringProperty author, String content) {
		this.website = website;
		this.url = url;
		this.date = date;
		this.content = content;
		this.title = title;
		this.author = author;
	}
	
	public boolean contentContains(String word) {
		return this.content.contains(word);
	}
	
	public String toFancyString() {
		return String.format("Article: %s\n\tfrom %s, url: %s\n\tDate: %s\n\tAuthor: %s\n\tContent: %s", title.getValue(), website.fullName, url,
				(date != null) ? date.toString() : "NULL",
				(author != null) ? author.getValue() : "NULL",
				(content != null) ?content : "NULL");
	}
	
	public Website getWebsite() {
		return website;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getTitle() {
		if(title == null) return null;
		return title.getValue();
	}


	public final StringProperty getTitleProperty(){
	    return title;
    }

    public final StringProperty getAuthorProperty() {
    	return author;
    }

	public Date getDate() {
		return date;
	}

	public String getAuthor() {
		if(author == null) return null;
		return author.getValue();
	}
	
	public String getContent() {
		return content;
	}
}

//	public void setAdditionalData(Date date, String author, String content) {
//		this.date = date;
//		this.author = author;
//		this.content = content;
//	}