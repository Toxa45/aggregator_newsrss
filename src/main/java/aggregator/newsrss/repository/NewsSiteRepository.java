package aggregator.newsrss.repository;

import aggregator.newsrss.model.NewsSite;
import aggregator.newsrss.model.Site;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface NewsSiteRepository extends JpaRepository<NewsSite, Long> {
    List<NewsSite> findBySiteAndTitleAndLinkIsContainingIgnoreCase (Site site, String Title, String Link );

    List<NewsSite> findAllByAndSite_Id(Long siteId, Pageable page);
    List<NewsSite> findAllByAndSite_Id(Long siteId);

    List<NewsSite> findAllByAndSite_IdAndTitleIsContainingIgnoreCase(Long siteId, String title,Pageable page);
    List<NewsSite> findAllByAndSite_IdAndTitleIsContainingIgnoreCase(Long siteId,String title);


    List<NewsSite> findAllByAndTitleIsContainingIgnoreCase(String title,Pageable page);
    List<NewsSite> findAllByAndTitleIsContainingIgnoreCase(String title);
}