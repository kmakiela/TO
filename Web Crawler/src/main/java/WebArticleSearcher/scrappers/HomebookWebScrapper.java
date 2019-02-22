package WebArticleSearcher.scrappers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.select.Elements;

public class HomebookWebScrapper extends WebScrapper {
	
	private final String contentClass = "article-section hbk-text-wall";
	private final String dateClass = "article-footer";
	private final String datePrefix = "Data publikacji:";

	@Override
	protected String extractMainText() {
		Elements content = document.getElementsByClass(contentClass);
		return content.text();
	}

	@Override
	protected String extractAuthor() {
		return "homebook.pl";
	}

	@Override
	protected Date extractDate() {
		Elements dateP = document.getElementsByClass(dateClass);
		String data = dateP.text().substring(datePrefix.length()).trim();
		Date date = null;
		// 18.12.2018
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			date = format.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
