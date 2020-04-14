package aggregator.newsrss.service;

import aggregator.newsrss.dto.SiteDTO;
import aggregator.newsrss.model.Site;

import java.util.List;
import java.util.Optional;

public interface SiteServise {
    public Site save(SiteDTO site);

    public Optional<Site> update(Long id, SiteDTO siteRecord);

    public void delete(Long id);

    public Optional<Site> findById(Long id);
    public List<Site> findByAll();

    public List<Site> findByAllPageable(int pageOffset, int pageLimit);
}
