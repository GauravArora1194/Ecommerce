package search.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ProductDTO {

    private String id;

    private String code;

    private String name;

    private Double price;

    private Double tax;

    private Double discount;

    private String description;

    // private List<ColorVariant> colorVariants;

    private Integer stock;

    private String brand;

    private String imageUrl;

    private Set<CategoryDTO> categories;

}
