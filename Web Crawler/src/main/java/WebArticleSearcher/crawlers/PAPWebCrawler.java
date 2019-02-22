package WebArticleSearcher.crawlers;
import org.jsoup.nodes.Element;

import common.Website;


// compile 'org.jsoup:jsoup:1.11.3'
public class PAPWebCrawler extends WebCrawler {
	
	private final static String url = "https://www.pap.pl";
	private final static String articlePrefix = "/aktualnosci/news%";
	private final static String articleClass = "title";

	public PAPWebCrawler() {
		super(url, Website.PAP);
	}


	protected String getLinkUrl(Element link) {
		return url+link.attr("href");
	}

	protected String getTitleName(Element link) {
		return link.text();
	}

	protected boolean isLinkAnArtice(Element link) {
		return (link.attr("href").startsWith(articlePrefix) && link.parent().className().equals(articleClass));
	}

}
	
	// HISTORY: (for later use)
//	private String extractAuthor(String text) {
//		int poz= -1;
//		for(String autorPrefix : authorPrefix) {
//			poz = Math.max(text.lastIndexOf(autorPrefix)+autorPrefix.length()+1, poz);
//		}
//		System.out.println(text.substring(poz));
//		int endOfAuthor = text.indexOf(authorSuffix);
//		return text.substring(poz, endOfAuthor);		
//	}



//	public ArrayList<Article> getArticleList() {
//		ArrayList<Article> articleTitleList = new ArrayList<Article>();
//		try {
//			Document doc = Jsoup.connect(url).get();
//			Elements links = doc.getElementsByTag("a");
//			for (Element link : links) {
//			  String linkHref = link.attr("href");
//			  String linkText = link.text();
//			  if(linkHref.startsWith(articleProfix) && link.parent().className().equals(articleClass)) {
//				  //System.out.println("Link: " + linkHref + ", tekst: " + linkText + ", KLASA: " + link.parent().className());
//				  articleTitleList.add(Article.builder().website(Website.PAP).url(url+linkHref).title(linkText).build());
//				  //articleTitleList.add(new Article(Website.PAP, url+linkHref, linkText));
//			  }
//			}
//			return articleTitleList;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}