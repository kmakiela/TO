package WebArticleSearcher.crawlers;
import org.jsoup.nodes.Element;
import common.Website;

public class FocusWebCrawler extends WebCrawler {
	// NOT WORKING
	private final static String urlArticles = "https://www.focus.pl/artykuly";
	private final static String urlBase = "https:/www.focus.pl";
	private final static String articlePrefix = "/artykul/";
	private final static String articleClass = "col-xs-12";
	
	public FocusWebCrawler() {
		super(urlArticles, Website.All);
	}
	
	protected String getLinkUrl(Element link) {
		return urlBase+link.attr("href");
	}

	protected String getTitleName(Element link) {
		return link.text();
	}

	protected boolean isLinkAnArtice(Element link) {
		return (link.attr("href").startsWith(articlePrefix) && link.parent().className().equals(articleClass));
	}

}
