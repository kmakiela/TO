package WebArticleSearcher.scrappers;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class PAPWebScrapper extends WebScrapper{
	/*
	 * NEEDS fixing: mainTextElements -> useless, can use this.mainText // DONE
	 */
    private final static ArrayList<String> authorPrefix = new ArrayList<String>(Arrays.asList("autorka", "autor"));
    private final static String dateClassName = "moreInfo";
    private final static String dateSuffix = "aktualizacja";
    
    private String mainText = "";

    public PAPWebScrapper(){
    	super();
    }
    
    protected String extractMainText() {
        // format: "<div property="schema:text" ..."
        Elements dive = document.getElementsByTag("div");
        String outText = "";
        for (Element div : dive) {
            String divPropety = div.attr("property");
            if(!divPropety.equals("schema:text")) continue;
            outText += div.text();
            mainText += div.text();
        }
        return outText;
    }

    protected String extractAuthor() { // must be called after extractMainText();
        for (String authorText : authorPrefix) {
        	int ind = mainText.lastIndexOf(authorText);
            if(ind != -1) {
                return mainText.substring(ind+authorText.length()+1).trim();
            }
        }

        return null;
    }

    protected Date extractDate() {
        Elements iters = document.getElementsByClass(dateClassName);
        String dateString;
        Date date = null;
        for (Element iter : iters) {
            String iterText = iter.text();
            int endOfDate = iterText.indexOf(dateSuffix);
            if(endOfDate>0)
                dateString = iterText.substring(0, endOfDate);
            else
                dateString = iterText;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd, hh:mm");
            try {
                date = format.parse(dateString);
            } catch (ParseException e) {
                continue;
            }
            return date;
        }
        return null;
    }

}
