package WebArticleSearcher.crawlers;

import org.jsoup.nodes.Element;

import common.Website;

public class DiagnozujmyWebCrawler extends WebCrawler{
	private final static String url = "https://diagnozujmy.pl/";
	private final static String articleClass = "entry-title";
		
		public DiagnozujmyWebCrawler() {
			super(url, Website.diagnozujmy);
		}
		
		protected String getLinkUrl(Element link) {
			return link.attr("href");
		}

		protected String getTitleName(Element link) {
			return link.text();
		}

		protected boolean isLinkAnArtice(Element link) {
			return (link.attr("href").startsWith(url) && link.parent().className().equals(articleClass));
		}

}
