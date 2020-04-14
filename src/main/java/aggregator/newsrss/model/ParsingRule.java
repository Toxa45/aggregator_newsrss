package aggregator.newsrss.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parsing_rule")
public class ParsingRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Long id;

    @Column(length = 1000, name = "content_type")
    private String contentType = "";

    private String channelTag = "";
    @Column(length = 1000)
    private String channelClassName = "";
    @Column(length = 1000)
    private String articleClass = "";
    @Column(length = 1000)
    private String articleTag = "";


    @Column(length = 1000)
    private String titleClass = "";
    @Column(length = 1000)
    private String titleTag = "";
    @Column(length = 1000)
    private String descriptionClass = "";
    @Column(length = 1000)
    private String descriptionTag = "";
    @Column(length = 1000)
    private String datePublishedClass = "";
    @Column(length = 1000)
    private String datePublishedTag = "";
    @Column(length = 1000)
    private String linkClass = "";
    @Column(length = 1000)
    private String linkTag = "";
    @Column(length = 1000)
    private String linkAttr = "";
    @Column(length = 1000)
    private String linkDomain = "";

    @Column(length = 1000)
    private String imageClass = "";
    @Column(length = 1000)
    private String imageTag = "";
    @Column(length = 1000)
    private String imageAttr = "";

    @OneToOne(fetch=FetchType.EAGER, mappedBy="parsingRule")
    private Site site;
}
