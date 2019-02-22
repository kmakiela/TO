package WebArticleSearcher.crawlers;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import common.Article;
import common.Website;

public abstract class WebCrawler implements IWebCrawler {
	
	private String basePageUrl;
	private Website webType;
	
	public WebCrawler(String basePageUrl, Website web) {
		this.basePageUrl = basePageUrl;
		webType = web;
	}
	
	public ArrayList<Article> getArticleList() {
		ArrayList<Article> articleTitleList = new ArrayList<Article>();
		try {
			Document doc = Jsoup.connect(basePageUrl).get();
			Elements links = doc.getElementsByTag("a");
			for (Element link : links) {
				//System.out.println("Link: " + link.text() + ",\tKLASA: " + link.parent().className());
				if(isLinkAnArtice(link)) {
					//System.out.println(getTitleName(link));
					articleTitleList.add(Article.builder().website(webType).url(getLinkUrl(link)).title(getTitleName(link)).build());
				}
			}
			return articleTitleList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 protected abstract String getLinkUrl(Element link);
	 protected abstract String getTitleName(Element link);
	 protected abstract boolean isLinkAnArtice(Element link);
}
