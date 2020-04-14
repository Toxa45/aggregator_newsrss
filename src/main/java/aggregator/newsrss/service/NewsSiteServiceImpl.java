package aggregator.newsrss.service;

import aggregator.newsrss.exception.SampleException;
import aggregator.newsrss.model.NewsSite;
import aggregator.newsrss.model.ParsingRule;
import aggregator.newsrss.model.Site;
import aggregator.newsrss.repository.NewsSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsSiteServiceImpl implements NewsSiteService{

    @Autowired
    private NewsSiteRepository repository;

    @Override
    public NewsSite save(NewsSite newsSite) {
        return  repository.save(newsSite);
    }

    @Override
    public Optional<NewsSite> update(Long id, NewsSite newsSiteRecord) {
        Optional<NewsSite> newsSiteInfo = repository.findById(id);
        return newsSiteInfo
                .map(newsSiteInf -> {
                            newsSiteRecord.setId(newsSiteInf.getId());
                            return repository.save(newsSiteRecord);
                        }
                );
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<NewsSite> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<NewsSite> findByAll() {
        return repository.findAll();
    }

    @Override
    public List<NewsSite> findByAllAndSiteId(Long siteId) {
        return repository.findAllByAndSite_Id(siteId);
    }

    @Override
    public List<NewsSite> findByAllAndSiteIdAndTitle(Long siteId,String  titleFilter) {
        return repository.findAllByAndSite_IdAndTitleIsContainingIgnoreCase(siteId,titleFilter);
    }

    @Override
    public List<NewsSite> findByAllAndTitle(String  titleFilter) {
        return repository.findAllByAndTitleIsContainingIgnoreCase(titleFilter);
    }

    @Override
    public List<NewsSite> findByAllPageable(int pageOffset, int pageLimit ) {
        Pageable page =  PageRequest.of((int)pageOffset/pageLimit, pageLimit,Sort.by(
                Sort.Order.desc("publishedDate"),
                Sort.Order.desc("id"))
        );
        return repository.findAll(page).getContent();
    }

    @Override
    public List<NewsSite> findByAllPageableAndSiteId(int pageOffset, int pageLimit,Long siteId) {
        Pageable page =  PageRequest.of((int)pageOffset/pageLimit, pageLimit,Sort.by(
                                                                                    Sort.Order.desc("publishedDate"),
                                                                                    Sort.Order.desc("id"))
                                        );
        return repository.findAllByAndSite_Id(siteId,page);
    }

    @Override
    public List<NewsSite> findByAllPageableAndSiteIdAndTitle(int pageOffset, int pageLimit,Long siteId,String  titleFilter) {
        Pageable page =  PageRequest.of((int)pageOffset/pageLimit, pageLimit, Sort.by(
                Sort.Order.desc("publishedDate"),
                Sort.Order.desc("id"))
        );
        return repository.findAllByAndSite_IdAndTitleIsContainingIgnoreCase(siteId,titleFilter,page);
    }

    @Override
    public List<NewsSite> findByAllPageableAndTitle(int pageOffset, int pageLimit,String  titleFilter) {
        Pageable page =  PageRequest.of((int)pageOffset/pageLimit, pageLimit,Sort.by(
                Sort.Order.desc("publishedDate"),
                Sort.Order.desc("id"))
        );
        return repository.findAllByAndTitleIsContainingIgnoreCase(titleFilter,page);
    }


    @Override
    public void saveNewsSite(Site site, ParsingService parsingService) {
        try {
            if(parsingService == null) throw new SampleException("Не задан сервис парсинга. parsingService is null");
            List<NewsSite> newsSiteList = new ArrayList<>();
            ParsingRule parsingRule = site.getParsingRule();
            newsSiteList = parsingService.getNews(site.getUrl(), parsingRule);
            for (NewsSite newsSite : newsSiteList) {
                if(newsSite.getLink()==null) newsSite.setLink("");
                String link = newsSite.getLink();
                if(!link.equals("")){
                    int indexParam = link.indexOf('?');
                    if (indexParam>0)
                        link = link.substring(0,indexParam+1);
                }
                List<NewsSite> savedNewsSites = repository.findBySiteAndTitleAndLinkIsContainingIgnoreCase (site, newsSite.getTitle(), link );
                if (savedNewsSites.size() == 0) {
                    newsSite.setSite(site);
                    repository.save(newsSite);
                }
            }
        } catch (SampleException e) {
            e.printStackTrace();
        }
    }
}
