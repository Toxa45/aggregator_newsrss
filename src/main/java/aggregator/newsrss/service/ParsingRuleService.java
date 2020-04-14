package aggregator.newsrss.service;

import aggregator.newsrss.model.NewsSite;
import aggregator.newsrss.model.ParsingRule;

import java.util.List;
import java.util.Optional;

public interface ParsingRuleService {
    public ParsingRule parsingTextRule(String rule);
    public ParsingRule save(ParsingRule parsingRule);

    public Optional<ParsingRule> update(Long id, ParsingRule parsingRuleRecord);

    public void delete(Long id);

    public Optional<ParsingRule> findById(Long id);
    public List<ParsingRule> findByAll();

}
