package WebArticleSearcher.scrappers;

import java.io.IOException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import common.Article;

public abstract class WebScrapper implements IWebScrapper {

	protected Document document;
	
	public Article fillArticleContent(Article article) {
        try {
            document = Jsoup.connect(article.getUrl()).get();
            Date date = article.getDate();
            String author = article.getAuthor();
            String content = article.getContent();
            if(date == null) date = extractDate();
            if(author == null) author = extractAuthor();
            if(content == null) content = extractMainText();
            return Article.builder(article).date(date).author(author).content(content).build();
           
        } catch (IOException e) {
            e.printStackTrace();
            return Article.builder(article).content("Error:" + e.getMessage()).build();
        }
    }

	protected abstract String extractMainText();
	protected abstract String extractAuthor();
	protected abstract Date extractDate();
}
