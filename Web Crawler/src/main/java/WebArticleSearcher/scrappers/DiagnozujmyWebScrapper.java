package WebArticleSearcher.scrappers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.select.Elements;

public class DiagnozujmyWebScrapper extends WebScrapper {
	
	private final String contentClass = "entry-content";
	private final String authorClass = "author vcard";
	private final String dateClass = "published";
	private final String sourcePrefix = "èrÛd≥";

	@Override
	protected String extractMainText() {
		Elements content = document.getElementsByClass(contentClass);
		int sourcesInd = content.text().lastIndexOf(sourcePrefix);
		if(sourcesInd != -1) {
			return (content.text().substring(0, sourcesInd));
		} 
		return content.text();
	}

	@Override
	protected String extractAuthor() {
		Elements autor = document.getElementsByClass(authorClass);
		return autor.text();
	}

	@Override
	protected Date extractDate() {
		Elements dateP = document.getElementsByClass(dateClass);
		Date date = null;
		// Sty 9, 2019
		DateFormat format = new SimpleDateFormat("MMM d, yyyy");
		try {
			date = format.parse(dateP.text());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
