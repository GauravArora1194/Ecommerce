package search.api.constants;

public class ElasticSearchConstants {
    private ElasticSearchConstants() throws IllegalAccessException {
        throw new IllegalAccessException("Not to be initialised");
    }

    public static final String INDEX = "product1";
    public static final String TYPE = "_doc";
}
