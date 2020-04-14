package aggregator.newsrss.service;

import aggregator.newsrss.model.NewsSite;
import aggregator.newsrss.model.ParsingRule;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class XmlService implements ParsingService {
    @Override
    public List<NewsSite> getNews(String url, ParsingRule rule)  {
        final ArrayList<NewsSite> list = new ArrayList<NewsSite>();
        try {
            Connection.Response connection = null;
            connection = Jsoup.connect(url).execute();
            Document document = connection.parse();
            if(validateParsingRule(rule)){
                document.getElementsByTag(rule.getChannelTag()).forEach(getElementsByChannel(rule, list));
            }
        } catch (IOException ex) {
            Logger.getLogger(XmlService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private boolean validateParsingRule(ParsingRule rule ) {
        return !rule.getChannelTag().equals("")
                && !rule.getArticleTag().equals("")
                && ( !rule.getTitleTag().equals("")
                    || !rule.getDescriptionTag().equals("")
                    );
    }

    private Consumer<Element> getElementsByChannel(ParsingRule rule, ArrayList<NewsSite> list) {
        return value -> {
            try {
                    value.getElementsByTag(rule.getArticleTag()).forEach(getElementsByArticle(rule, list));
            } catch (Exception ex) {
                Logger.getLogger(XmlService.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
    }

    private Consumer<Element> getElementsByArticle(ParsingRule rule, ArrayList<NewsSite> list) {
        return e -> {
            try {
                String title = e.getElementsByTag(rule.getTitleTag()).tagName(rule.getTitleTag()).text();
                String description = e.getElementsByTag(rule.getDescriptionTag()).text();
                if (title.equals("") || description.equals(""))
                    return;
                NewsSite newsSite = new NewsSite();
                newsSite.setTitle(title);
                newsSite.setDescription(description);

                if (rule.getLinkTag()!=null && !rule.getLinkTag().equals("")) {
                    String Link="";
                    if (rule.getLinkAttr()!=null && !rule.getLinkAttr().equals("")) {
                        Link = e.getElementsByTag(rule.getLinkTag()).tagName(rule.getLinkTag()).attr(rule.getLinkAttr());
                    }
                    else {
                        Link =e.getElementsByTag(rule.getLinkTag()).tagName(rule.getLinkTag()).text();
                    }
                    if(!Link.equals("")) {
                        if(!Link.contains("http")) Link=rule.getLinkDomain()+Link;

                        newsSite.setLink(Link);
                    }
                }

                if (rule.getImageTag()!=null && !rule.getImageTag().equals("")) {
                    String ImageURL="";
                    if (rule.getImageAttr()!=null && !rule.getImageAttr().equals("")) {
                        ImageURL = e.getElementsByTag(rule.getImageTag()).tagName(rule.getImageTag()).attr(rule.getImageAttr());
                    }
                    else {
                        ImageURL=e.getElementsByTag(rule.getImageTag()).tagName(rule.getImageTag()).text();
                    }

                    if(!ImageURL.equals("")) {
                        if(!ImageURL.contains("http")) ImageURL=rule.getLinkDomain()+ImageURL;

                        newsSite.setImageURL(ImageURL);
                    }
                }

                if (!rule.getDatePublishedTag().equals("")) {
                    try {
                        Date pubDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
                                .parse(e.getElementsByTag(rule.getDatePublishedTag()).tagName(rule.getDatePublishedTag()).text());
                        newsSite.setPublishedDate(pubDate);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        Date pubDate = new Date();
                        newsSite.setPublishedDate(pubDate);
                    }
                } else {
                    Date pubDate = new Date();
                    newsSite.setPublishedDate(pubDate);
                }
                list.add(newsSite);
            } catch (Exception ex){
                Logger.getLogger(XmlService.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
    }
}
