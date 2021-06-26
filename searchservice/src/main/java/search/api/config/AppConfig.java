package search.api.config;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    public DozerBeanMapper dozerMapper(@Value(value = "classpath*:dozer/*-mappings.xml") Resource[] resourceArray) throws IOException {
        List<String> mappingFileUrlList = new ArrayList<>();
        for (Resource resource : resourceArray) {
            mappingFileUrlList.add(String.valueOf(resource.getURL()));
        }
        // To resolve dozer known issue: not able to map java.time apis
        mappingFileUrlList.add("dozerJdk8Converters.xml");
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        beanMapper.setMappingFiles(mappingFileUrlList);
        return beanMapper;
    }
}
