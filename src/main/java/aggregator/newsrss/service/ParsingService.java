package aggregator.newsrss.service;

import aggregator.newsrss.model.NewsSite;
import aggregator.newsrss.model.ParsingRule;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface ParsingService {
    public List<NewsSite> getNews(String url, ParsingRule rule);
}
