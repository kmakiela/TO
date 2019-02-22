package WebArticleSearcher;

import WebArticleSearcher.crawlers.DiagnozujmyWebCrawler;
import WebArticleSearcher.crawlers.HomebookWebCrawler;
import WebArticleSearcher.crawlers.IWebCrawler;
import WebArticleSearcher.crawlers.MilujcieWebCrawler;
import WebArticleSearcher.crawlers.PAPWebCrawler;
import WebArticleSearcher.crawlers.WprostWebCrawler;
import WebArticleSearcher.scrappers.DiagnozujmyWebScrapper;
import WebArticleSearcher.scrappers.HomebookWebScrapper;
import WebArticleSearcher.scrappers.IWebScrapper;
import WebArticleSearcher.scrappers.MilujcieWebScrapper;
import WebArticleSearcher.scrappers.PAPWebScrapper;
import WebArticleSearcher.scrappers.WprostWebScrapper;
import common.Website;

public class SearcherManager {

	public SearcherManager() {
	}
	
	public IWebCrawler getWebCrawler(Website website) throws UnsupportedOperationException{
		switch(website) {
		case PAP:
			return new PAPWebCrawler();
		//case Focus:
			//return new FocusWebCrawler();
		case MilujcieSie:
			return new MilujcieWebCrawler();
		case homebook:
			return new HomebookWebCrawler();
		case diagnozujmy:
			return new DiagnozujmyWebCrawler();
		case wprost:
			return new WprostWebCrawler();
		case All:
			throw new UnsupportedOperationException("All is not a valid Website name");
		default:
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
	public IWebScrapper getWebScrapper(Website website) throws UnsupportedOperationException{
		switch(website) {
			case PAP:
				return new PAPWebScrapper();
			//case Focus:
				//return new FocusWebScrapper();
			case MilujcieSie:
				return new MilujcieWebScrapper();
			case homebook:
				return new HomebookWebScrapper();
			case diagnozujmy:
				return new DiagnozujmyWebScrapper();
			case wprost:
				return new WprostWebScrapper();
			case All:
				throw new UnsupportedOperationException("All is not a valid Website name");
			default:
				throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
