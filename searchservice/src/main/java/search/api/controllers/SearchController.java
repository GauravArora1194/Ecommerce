package search.api.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import search.api.dto.ProductDTO;
import search.api.service.ElasticSearchService;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping(path = "/search")
public class SearchController {

    private ElasticSearchService elasticSearchService;

    @Autowired
    public SearchController(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Object> getProductByCategory(int categoryId) {
        return null;
    }


    /**
     * Uses elastic search to search keyword in product base based on name, color, category and brand
     *
     * @param keyword
     * @return ProductDetails
     */
    @GetMapping("/{keyword}")
    public ResponseEntity<List<ProductDTO>> searchProduct(@PathVariable String keyword) throws Exception {
        return ResponseEntity.ok(elasticSearchService.search(keyword));
    }

}

