package aggregator.newsrss.controller;

import aggregator.newsrss.dto.SiteDTO;
import aggregator.newsrss.exception.ErrorType;
import aggregator.newsrss.exception.SampleException;
import aggregator.newsrss.model.Site;
import aggregator.newsrss.service.SiteServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static aggregator.newsrss.dto.SiteDTO.from;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/api/site")
public class SiteController {

    @Autowired
    private SiteServise siteServise;

    @GetMapping
    public ResponseEntity<List<SiteDTO>> getAll(@RequestHeader HttpHeaders requestHeader) {
        return ok(from(siteServise.findByAll()));
    }

    @GetMapping(params ={"page_limit"})
    public  ResponseEntity<List<SiteDTO>> getAllPageable( @RequestParam(name ="page_offset",defaultValue="0", required = false) int  pageOffset,
                                                             @RequestParam(name ="page_limit",defaultValue="20", required = false) int  pageLimit  ){
        return ok(from(siteServise.findByAllPageable(pageOffset, pageLimit)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SiteDTO> getById(@RequestHeader HttpHeaders requestHeader, @PathVariable Long id) {
        return siteServise.findById(id).map(SiteDTO::from).map(ResponseEntity::ok)
                .orElseThrow(() -> new SampleException(
                        String.format(ErrorType.ENTITY_NOT_FOUND.getDescription(), id)
                ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SiteDTO> update(@RequestBody SiteDTO siteRecord, @PathVariable Long id)
    {
        return siteServise.update(id,siteRecord).map(SiteDTO::from).map(ResponseEntity::ok)
                .orElseThrow(() -> new SampleException(
                        String.format(ErrorType.ENTITY_NOT_UPDATED.getDescription(), siteRecord.toString())
                ));
    }

    @PostMapping
    public ResponseEntity<SiteDTO> add(@RequestBody SiteDTO siteNew)
    {
        return ok(from(siteServise.save(siteNew)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        siteServise.delete(id);
        return ResponseEntity.noContent().build();
    }
}
