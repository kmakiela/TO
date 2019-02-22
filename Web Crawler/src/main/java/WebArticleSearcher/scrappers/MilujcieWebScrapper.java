package WebArticleSearcher.scrappers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.select.Elements;


public class MilujcieWebScrapper extends WebScrapper {
	
	private final String contentClass = "editor clearfix";
	private final String authorClass = "autor";
	private final String dateClass = "date";
	private final String datePrefix = "<i class=\"fa fa-clock-o\"";

	@Override
	protected String extractMainText() {
		Elements content = document.getElementsByClass(contentClass);
		if(content.hasText())
			return content.text();
		return "Brak tekstu artyku³u";
	}

	@Override
	protected String extractAuthor() {
		Elements autor = document.getElementsByClass(authorClass);
		return autor.first().text();
	}

	@Override
	protected Date extractDate() {
		Elements dateP = document.getElementsByClass(dateClass);
		String data = null;
		for(int i=0; i<dateP.size(); i++) {
			if(dateP.get(i).html().startsWith(datePrefix)) {
				data = dateP.get(i).text();
				break;
			}
		}
		if(data == null) return null;
		Date date = null;
		// Sty 07, 2019
		DateFormat format = new SimpleDateFormat("MMM d, yyyy");
		try {
			date = format.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
