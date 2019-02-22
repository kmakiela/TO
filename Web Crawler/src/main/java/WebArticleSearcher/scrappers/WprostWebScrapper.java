package WebArticleSearcher.scrappers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.select.Elements;


public class WprostWebScrapper extends WebScrapper {
	
	private final String contentHeadClass = "art-lead";
	private final String contentRestClass = "art-text-inner";
	private final String dateClass = "art-details-datetime";
	private final String dateAttr = "title";
	private final String datePrefix = "Data dodania: ";

	@Override
	protected String extractMainText() {
		Elements contentHead = document.getElementsByClass(contentHeadClass);
		Elements contentRest = document.getElementsByClass(contentRestClass);
		
		return contentHead.text() + contentRest.text();
	}

	@Override
	protected String extractAuthor() {
		return "Wprost.pl";
	}

	@Override
	protected Date extractDate() {
		Elements dateP = document.getElementsByClass(dateClass);
		String data = null;
		data = dateP.first().childNode(1).attr(dateAttr).substring(datePrefix.length());
		Date date = null;
		// 2019-01-09 18:08
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			date = format.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
