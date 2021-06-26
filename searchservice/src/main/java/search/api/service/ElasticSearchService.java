package search.api.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import search.api.dto.ProductDTO;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static search.api.constants.ElasticSearchConstants.INDEX;
import static search.api.constants.ElasticSearchConstants.TYPE;

@Slf4j
@Service
public class ElasticSearchService {

    private RestHighLevelClient client;

    private ObjectMapper objectMapper;

    @Autowired
    public ElasticSearchService(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    private static final AWSCredentials AWS_CREDENTIALS = new DefaultAWSCredentialsProviderChain().getCredentials();

    public String createNewProduct(ProductDTO product) {

        Map<String, Object> documentMapper = objectMapper.convertValue(product, Map.class);

        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, product.getId()).source(documentMapper);

        IndexResponse indexResponse = null;
        try {
            indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Result from rest client {}", indexResponse.getResult().toString());
        return indexResponse.getResult().name();
    }


    public List<ProductDTO> search(String value) throws IOException {
        SearchRequest searchRequest = new SearchRequest(INDEX);
        MultiMatchQueryBuilder matchQueryBuilder = new MultiMatchQueryBuilder(value).field("name", 5).field("description",
                1).field("categories.name", 1).field("categories.description", 1).field("brand", 2);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(matchQueryBuilder);
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        log.info("Result from rest client for search {}", searchResponse);
        return getSearchResult(searchResponse);
    }

    private List<ProductDTO> getSearchResult(SearchResponse response) {

        SearchHit[] searchHit = response.getHits().getHits();
        return Arrays.stream(searchHit).map(hit -> (objectMapper.convertValue(hit.getSourceAsMap(), ProductDTO.class))).collect(Collectors.toList());

    }

    public String deleteProductDocument(String id) {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, id);
        DeleteResponse response = null;
        try {
            response = client.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Result from rest client delete op {}", response.getResult());
        return response.getResult().name();
    }

}
