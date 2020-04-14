package aggregator.newsrss.repository;

import aggregator.newsrss.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SiteRepository extends JpaRepository<Site, Long> {
}