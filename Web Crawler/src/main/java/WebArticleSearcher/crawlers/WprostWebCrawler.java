package WebArticleSearcher.crawlers;

import org.jsoup.nodes.Element;

import common.Website;

public class WprostWebCrawler extends WebCrawler {

	private final static String url = "https://www.wprost.pl";
	private final static String urlClass = "news-title"; // inner href class
	private final static String badUrlPrefix = "/wyszukaj";
	private final static String fullUrlPrefix = "https";
	
	public WprostWebCrawler() {
		super(url, Website.wprost);
	}
	
	protected String getLinkUrl(Element link) {
		if(link.attr("href").startsWith(fullUrlPrefix)) return link.attr("href");
		return url+link.attr("href");
	}

	protected String getTitleName(Element link) {
		return link.attr("title");
	}

	protected boolean isLinkAnArtice(Element link) {
		return (link.attr("class").equals(urlClass) && !link.attr("href").startsWith(badUrlPrefix));
	}

}
