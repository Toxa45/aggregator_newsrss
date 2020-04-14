package aggregator.newsrss.service;

import aggregator.newsrss.model.ParsingRule;
import aggregator.newsrss.model.Site;
import aggregator.newsrss.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledPardingSite {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private NewsSiteService newsSiteService;

    @Autowired
    private HtmlService htmlService;
    @Autowired
    private XmlService xmlService;

    @Scheduled(fixedDelay=50000)
    public void reloadSites(){
        List<Site> sites = siteRepository.findAll();
        for (Site site : sites) {
            ParsingRule parsingRule = site.getParsingRule();
            if(parsingRule!=null) {
                if (parsingRule.getContentType().equals("html")) {
                    newsSiteService.saveNewsSite(site, htmlService);
                } else if (parsingRule.getContentType().equals("xml")) {
                    newsSiteService.saveNewsSite(site, xmlService);
                }
            }
        }
    }
}
