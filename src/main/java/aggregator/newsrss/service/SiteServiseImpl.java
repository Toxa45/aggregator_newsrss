package aggregator.newsrss.service;

import aggregator.newsrss.dto.SiteDTO;
import aggregator.newsrss.model.ParsingRule;
import aggregator.newsrss.model.Site;
import aggregator.newsrss.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteServiseImpl implements SiteServise {

    @Autowired
    private SiteRepository repository;

    @Autowired
    private ParsingRuleService parsingRuleService;

    @Autowired
    private NewsSiteService newsSiteService;

    @Override
    public Site save(SiteDTO siteDTO) {
        Site site = new Site();
        site.setName(siteDTO.getName());
        site.setUrl(siteDTO.getUrl());
        site.setId(siteDTO.getId());
        site.setParsingRuleText(siteDTO.getParsingRule());
        site = saveSite(site);
        parsingSite(site);
        return site;
    }

    private Site saveSite(Site site) {
        ParsingRule rule = parsingRuleService.parsingTextRule(site.getParsingRuleText());
        if(site.getParsingRule()!=null)
            rule.setId(site.getParsingRule().getId());
        rule = parsingRuleService.save(rule);
        site.setParsingRule(rule);
        site = repository.save(site);

        return site;
    }


    private void parsingSite(Site site) {
        ParsingService parsingService = null;
        ParsingRule parsingRule = site.getParsingRule();
        if (parsingRule.getContentType().equals("html")) {
            parsingService = new HtmlService();
        } else if (parsingRule.getContentType().equals("xml")) {
            parsingService = new XmlService();
        }
        newsSiteService.saveNewsSite(site,parsingService);
    }


    @Override
    public Optional<Site> update(Long id, SiteDTO siteDTORecord) {
        Optional<Site> siteInfo = repository.findById(id);
        return siteInfo
                .map(siteInf -> {
                            siteInf.setName(siteDTORecord.getName());
                            siteInf.setParsingRuleText(siteDTORecord.getParsingRule());
                            siteInf.setUrl(siteDTORecord.getUrl());
                            return saveSite(siteInf);
                        }
                );
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Site> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Site> findByAll() {

        return repository.findAll();
    }

    @Override
    public List<Site> findByAllPageable( int pageOffset, int pageLimit) {
        if(pageLimit==0) pageLimit=1;
        Pageable page =  PageRequest.of((int)pageOffset/pageLimit, pageLimit, Sort.Direction.ASC, "id");

        return repository.findAll(page).getContent();
    }

}
