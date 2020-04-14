package aggregator.newsrss.dto;

import aggregator.newsrss.model.Site;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SiteDTO {
    private Long id;
    @Size(min = 1, message = "URL должен быть не менее 1 символа!")
    @URL(message = "Неверный URL!")
    private String url;

    @Size(min = 1, message =  "Имя должно быть не менее 1 символа!")
    private String name;

    private String parsingRule;

    public static SiteDTO from(Site site) {
        return SiteDTO.builder()
                .id(site.getId())
                .url(site.getUrl())
                .name(site.getName())
                .parsingRule(site.getParsingRuleText())
                .build();
    }

    public static List<SiteDTO> from(List<Site> siteList) {
        return siteList.stream().map(SiteDTO::from).collect(Collectors.toList());
    }
}
