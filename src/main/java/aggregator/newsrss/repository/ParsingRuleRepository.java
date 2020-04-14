package aggregator.newsrss.repository;

import aggregator.newsrss.model.ParsingRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface ParsingRuleRepository extends JpaRepository<ParsingRule, Long> {
}
