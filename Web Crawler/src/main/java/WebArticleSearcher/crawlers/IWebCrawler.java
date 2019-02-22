package WebArticleSearcher.crawlers;
import java.util.ArrayList;

import common.Article;

public interface IWebCrawler {
	public ArrayList<Article> getArticleList();
}
