package WebArticleSearcher;

import org.junit.Assert;
import org.junit.Test;

import WebArticleSearcher.crawlers.IWebCrawler;
import WebArticleSearcher.crawlers.PAPWebCrawler;
import common.Website;
import common.Article;
import common.WebArticleSearcher;

import java.util.ArrayList;
import java.util.Date;

public class WebArticleSearcherTest {

//    @Test
//    public void createWebSearcherTest(){
//        WebArticleSearcher webSearcher = new WebArticleSearcher();
//        IWebCrawler searcher = webSearcher.getManager().getWebCrawler(Website.PAP);
//        Assert.assertEquals(searcher.getClass(), PAPWebCrawler.class);
//        //Assert.assertTrue(webSearcher.getManager().getWebScrapper(Website.All)==null);
//
//    }
//    
//    @Test(expected = UnsupportedOperationException.class)
//    public void testUnsupportedOperationExceptionForWebsiteAll() {
//    	WebArticleSearcher webSearcher = new WebArticleSearcher();
//    	webSearcher.getManager().getWebCrawler(Website.All);
//    }
//
//    @Test
//    public void getArticleListTest(){
//        WebArticleSearcher searcher = new WebArticleSearcher();
//        ArrayList<Article> articleList = searcher.getArticleList(Website.PAP);
//        Assert.assertTrue(!articleList.isEmpty());
//        for(Article article : articleList){
//            Assert.assertEquals(article.getWebsite(), Website.PAP);
//            Assert.assertTrue(!article.getTitle().isEmpty());
//            Assert.assertTrue(!article.getUrl().isEmpty());
//        }
//    }
//    @Test
//    public void getFullContentTest(){
//        WebArticleSearcher webSearcher = new WebArticleSearcher();
//        ArrayList<Article> articleList = webSearcher.getArticleList(Website.PAP);
//        Article article = webSearcher.fillArticleContent(articleList.get(0));
//        Assert.assertTrue(article.getUrl()!=null);
//        Assert.assertTrue(article.getDate()!=null);
//        Assert.assertTrue(article.getContent()!=null);
//        Assert.assertTrue(article.getContent().length()>0);
//        Assert.assertEquals(article.getDate().getClass(), Date.class);
//    }

}
