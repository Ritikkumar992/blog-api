package com.blogapp.paylods;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class CategoryDto {

	private Integer categoryId;

	@NotEmpty
	@Size(min = 4, message = "More than 4 words are required")
	private String categoryTitle;

	@NotEmpty
	@Size(min = 10, max = 200, message = "Min 10 and max 200 words are allowed")
	private String categoryDescription;

}
