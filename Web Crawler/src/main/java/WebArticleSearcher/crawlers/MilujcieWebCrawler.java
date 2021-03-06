package WebArticleSearcher.crawlers;

import org.jsoup.nodes.Element;

import common.Website;

public class MilujcieWebCrawler extends WebCrawler {
	private final static String url = "https://milujciesie.org.pl";
	private final static String articleClass = "post-title";
	
	public MilujcieWebCrawler() {
		super(url, Website.MilujcieSie);
	}
	
	protected String getLinkUrl(Element link) {
		return link.attr("href");
	}

	protected String getTitleName(Element link) {
		return link.text();
	}

	protected boolean isLinkAnArtice(Element link) {
		return (link.parent().className().equals(articleClass) && !getTitleName(link).contains("Mi�ujcie si�! nr"));
	}

}
