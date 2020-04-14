package aggregator.newsrss.service;

import aggregator.newsrss.model.NewsSite;
import aggregator.newsrss.model.Site;

import java.util.List;
import java.util.Optional;

public interface NewsSiteService {
    public NewsSite save(NewsSite newsSite);

    public Optional<NewsSite> update(Long id, NewsSite newsSiteRecord);

    public void delete(Long id);

    public Optional<NewsSite> findById(Long id);

    List<NewsSite> findByAll();

    List<NewsSite> findByAllAndSiteId(Long siteId);

    List<NewsSite> findByAllAndSiteIdAndTitle(Long siteId, String titleFilter);

    List<NewsSite> findByAllAndTitle(  String titleFilter);

    List<NewsSite> findByAllPageable(int pageOffset, int pageLimit);

    List<NewsSite> findByAllPageableAndSiteId(int pageOffset, int pageLimit, Long siteId);

    List<NewsSite> findByAllPageableAndSiteIdAndTitle(int pageOffset, int pageLimit, Long siteId, String titleFilter);

    List<NewsSite> findByAllPageableAndTitle(int pageOffset, int pageLimit, String titleFilter);

    public void saveNewsSite(Site site, ParsingService parsingService);
}
