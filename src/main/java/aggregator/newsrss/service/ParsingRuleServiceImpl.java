package aggregator.newsrss.service;

import aggregator.newsrss.model.ParsingRule;
import aggregator.newsrss.repository.ParsingRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParsingRuleServiceImpl implements ParsingRuleService {
    @Autowired
    private ParsingRuleRepository repository;

    @Override
    public ParsingRule parsingTextRule(String rule) {
        ParsingRule parsingRule = new ParsingRule();

        try {
            for (String s : rule.split("\n")) {
                String[] entries = s.split("=");
                if(entries[1]!=null)
                    parseProperties(parsingRule, entries[0], entries[1].replace("\r", "").trim());
            }
        } catch (Exception e) {
            return parsingRule;
        }
        return parsingRule;
    }

    private ParsingRule parseProperties(ParsingRule parseRule, String kepProperty, String valueProperty){
            switch (kepProperty){
                case "content-type":
                    parseRule.setContentType(valueProperty);
                    break;
                case "channel-tag":
                    parseRule.setChannelTag(valueProperty);
                    break;
                case "channel-className":
                    parseRule.setChannelClassName(valueProperty);
                    break;
                case "article-class":
                    parseRule.setArticleClass(valueProperty);
                    break;
                case "article-tag":
                    parseRule.setArticleTag(valueProperty);
                    break;
                case "title-tag":
                    parseRule.setTitleTag(valueProperty);
                    break;
                case "title-class":
                    parseRule.setTitleClass(valueProperty);
                    break;
                case "description-class":
                    parseRule.setDescriptionClass(valueProperty);
                    break;
                case "description-tag":
                    parseRule.setDescriptionTag(valueProperty);
                    break;
                case "datePublished-class":
                    parseRule.setDatePublishedClass(valueProperty);
                    break;
                case "datePublished-tag":
                    parseRule.setDatePublishedTag(valueProperty);
                    break;
                case "link-class":
                    parseRule.setLinkClass(valueProperty);
                    break;
                case "link-tag":
                    parseRule.setLinkTag(valueProperty);
                    break;
                case "link-attr":
                    parseRule.setLinkAttr(valueProperty);
                    break;
                case "link-domain":
                    parseRule.setLinkDomain(valueProperty);
                    break;
                case "img-class":
                    parseRule.setImageClass(valueProperty);
                    break;
                case "img-tag":
                    parseRule.setImageTag(valueProperty);
                    break;
                case "img-attr":
                    parseRule.setImageAttr(valueProperty);
                    break;
            }
        return parseRule;
    }

    @Override
    public ParsingRule save(ParsingRule parsingRule) {
        return repository.save(parsingRule);
    }

    @Override
    public Optional<ParsingRule> update(Long id, ParsingRule parsingRuleRecord) {
        Optional<ParsingRule> parsingRuleInfo = repository.findById(id);
        return parsingRuleInfo
                .map(parsingRuleInf -> {
                            parsingRuleRecord.setId(parsingRuleInf.getId());
                            return repository.save(parsingRuleRecord);
                        }
                );
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<ParsingRule> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ParsingRule> findByAll() {
        return repository.findAll();
    }
}
