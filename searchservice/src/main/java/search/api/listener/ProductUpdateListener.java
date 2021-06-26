package search.api.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import search.api.dto.ProductDTO;
import search.api.dto.ProductListenerDto;
import search.api.service.ElasticSearchService;

import java.io.IOException;

@Component
@Slf4j
public class ProductUpdateListener {
    @Autowired
    private ElasticSearchService elasticSearchService;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @SqsListener(value = "${ecom.sqs.product-updates.url}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    @Transactional(rollbackFor = Exception.class)
    public void listen(String message) {
        try {
            log.info("Received message {}", message);
            ProductListenerDto productUpdateDto;
            try {
                productUpdateDto = new ObjectMapper().readValue(message, ProductListenerDto.class);
            } catch (IOException e) {
                log.error("Exception parsing the message from listener {}", message);
                return;
            }
            ProductDTO productDTO = dozerBeanMapper.map(productUpdateDto, ProductDTO.class);
            switch (productUpdateDto.getOperation()) {
                case PRODUCT_CREATED:
                case PRODUCT_UPDATED:
                    elasticSearchService.createNewProduct(productDTO);
                    break;
                case PRODUCT_DELETED:
                    elasticSearchService.deleteProductDocument(productUpdateDto.getId());
                    break;
            }
        } catch (Exception e){
            log.error("Dropping the message {}", message, e);
        }
    }
}
