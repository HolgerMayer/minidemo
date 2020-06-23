package com.example.minidemo;


import java.util.List;

import org.springframework.data.domain.Page;

public interface NamedCategoryService {
	List<NamedCategory> getAllNamedCategories();
	void saveNamedCategory(NamedCategory namedCategory);
	NamedCategory getNamedCategoryById(long id);
	void deleteNamedCategoryById(long id);
	Page<NamedCategory> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
