package search.api.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CategoryDTO {

	private String code;

	private String name;

	private String description;
	
	private Set<CategoryDTO> superCategories;

}
