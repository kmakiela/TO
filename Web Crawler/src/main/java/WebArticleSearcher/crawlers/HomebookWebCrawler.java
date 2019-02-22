package WebArticleSearcher.crawlers;

import org.jsoup.nodes.Element;

import common.Website;

public class HomebookWebCrawler extends WebCrawler{
	private final static String urlArticles = "https://www.homebook.pl/artykuly";
	private final static String urlBase = "https://www.homebook.pl";
	private final static String articlePrefix = "/artykuly/";
	private final static String articleClass = "article-thumb article-thumb--1-1";
		
		public HomebookWebCrawler() {
			super(urlArticles, Website.homebook);
		}
		
		protected String getLinkUrl(Element link) {
			return urlBase + link.attr("href");
		}

		protected String getTitleName(Element link) {
			return link.attr("title");
		}

		protected boolean isLinkAnArtice(Element link) {
			return (link.attr("href").startsWith(articlePrefix) && link.parent().className().equals(articleClass) && link.hasAttr("title"));
		}

}
