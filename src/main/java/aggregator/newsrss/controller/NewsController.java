package aggregator.newsrss.controller;

import aggregator.newsrss.model.NewsSite;
import aggregator.newsrss.service.NewsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsSiteService  newsSiteService;

    @GetMapping
    public ResponseEntity<List<NewsSite>> getAll(@RequestHeader HttpHeaders requestHeader ) {
        return ResponseEntity.ok(newsSiteService.findByAll());
    }

    @GetMapping(params ={"site_id"})
    public ResponseEntity<List<NewsSite>> getAll(@RequestHeader HttpHeaders requestHeader,
                                                 @RequestParam(name ="site_id",defaultValue="0", required = true) Long  siteId) {
        return ResponseEntity.ok(newsSiteService.findByAllAndSiteId(siteId));
    }

    @GetMapping(params ={"site_id","title_filter"})
    public ResponseEntity<List<NewsSite>> getAll(@RequestHeader HttpHeaders requestHeader,
                                                 @RequestParam(name ="site_id",defaultValue="0",required = true) Long  siteId,
                                                 @RequestParam(name ="title_filter", defaultValue="",required = true) String  titleFilter) {
        return ResponseEntity.ok(newsSiteService.findByAllAndSiteIdAndTitle(siteId,titleFilter));
    }

    @GetMapping(params ={"title_filter"})
    public ResponseEntity<List<NewsSite>> getAll(@RequestHeader HttpHeaders requestHeader,
                                                 @RequestParam(name ="title_filter", defaultValue="",required = true) String  titleFilter) {
        return ResponseEntity.ok(newsSiteService.findByAllAndTitle(titleFilter));
    }




    @GetMapping(params ={"page_limit"})
    public  ResponseEntity<List<NewsSite>> getByPageableGym(@RequestHeader HttpHeaders requestHeader,
                                                            @RequestParam(name ="page_offset",defaultValue="0", required = false) int  pageOffset,
                                                            @RequestParam(name ="page_limit",defaultValue="20", required = false) int  pageLimit  ){
        return ok(newsSiteService.findByAllPageable(pageOffset, pageLimit));
    }


    @GetMapping(params ={"page_limit","site_id"})
    public ResponseEntity<List<NewsSite>> getAll(@RequestHeader HttpHeaders requestHeader,
                                                 @RequestParam(name ="page_offset",defaultValue="0", required = false) int  pageOffset,
                                                 @RequestParam(name ="page_limit",defaultValue="20", required = false) int  pageLimit,
                                                 @RequestParam(name ="site_id",defaultValue="0", required = true) Long  siteId) {
        return ResponseEntity.ok(newsSiteService.findByAllPageableAndSiteId(pageOffset, pageLimit, siteId));
    }

    @GetMapping(params ={"page_limit","site_id","title_filter"})
    public ResponseEntity<List<NewsSite>> getAll(@RequestHeader HttpHeaders requestHeader,
                                                 @RequestParam(name ="page_offset",defaultValue="0", required = false) int  pageOffset,
                                                 @RequestParam(name ="page_limit",defaultValue="20", required = false) int  pageLimit,
                                                 @RequestParam(name ="site_id",defaultValue="0",required = true) Long  siteId,
                                                 @RequestParam(name ="title_filter", defaultValue="",required = true) String  titleFilter) {
        return ResponseEntity.ok(newsSiteService.findByAllPageableAndSiteIdAndTitle(pageOffset, pageLimit, siteId,titleFilter));
    }

    @GetMapping(params ={"page_limit","title_filter"})
    public ResponseEntity<List<NewsSite>> getAll(@RequestHeader HttpHeaders requestHeader,
                                                 @RequestParam(name ="page_offset",defaultValue="0", required = false) int  pageOffset,
                                                 @RequestParam(name ="page_limit",defaultValue="20", required = false) int  pageLimit,
                                                 @RequestParam(name ="title_filter", defaultValue="",required = true) String  titleFilter) {
        return ResponseEntity.ok(newsSiteService.findByAllPageableAndTitle(pageOffset, pageLimit, titleFilter));
    }


}
