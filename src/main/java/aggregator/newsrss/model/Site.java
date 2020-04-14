package aggregator.newsrss.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "site")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Long id;

    @Column(name = "name", length = 250)
    @Size(min = 1, message = "Имя должно быть не менее 1 символа!")
    private String name;

    @Size(min = 1, message = "URL должен быть не менее 1 символа!")
    @URL(message = "Неверный URL!")
    @Column(name = "url", length = 1000)
    private String url;

    @OneToOne(fetch = FetchType.EAGER, cascade =  {CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "parsing_rule_id")
    private ParsingRule parsingRule;

    @Column(name = "parsing_rule_text",length = 1000 )
    private String parsingRuleText;

    @OneToMany(mappedBy = "site", cascade = {CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.LAZY )
    private List<NewsSite> newsSiteList;

}
