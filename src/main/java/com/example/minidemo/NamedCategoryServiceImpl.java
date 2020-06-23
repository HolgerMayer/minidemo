package com.example.minidemo;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NamedCategoryServiceImpl implements NamedCategoryService {

	
	private static final Logger log = LoggerFactory.getLogger(NamedCategoryServiceImpl.class);

	
	@Autowired
	private NamedCategoryRepository namedCategoryRepository;

	@Override
	public List<NamedCategory> getAllNamedCategories() {
		return namedCategoryRepository.findAll();
	}
	

	@Override
	public void saveNamedCategory(NamedCategory namedCategory) {
		this.namedCategoryRepository.save(namedCategory);
	}

	@Override
	public NamedCategory getNamedCategoryById(long id) {
		Optional<NamedCategory> optional = namedCategoryRepository.findById(id);
		NamedCategory category = null;
		if (optional.isPresent()) {
			category = optional.get();
		} else {
			throw new RuntimeException(" NamedCategory not found for id :: " + id);
		}
		return category;
	}

	@Override
	public void deleteNamedCategoryById(long id) {
		this.namedCategoryRepository.deleteById(id);
	}

	@Override
	public Page<NamedCategory> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
	      
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<NamedCategory> result = this.namedCategoryRepository.findAll(pageable);
		
	    log.info("-------------------------------");
	    log.info("pageNo :{}",pageNo);
	    log.info("pageSize :{}",pageSize);
	    log.info("sortField :{}",sortField);
	    log.info("sortDirection :{}",sortDirection);
        log.info("NamedCategories found with findPaginated() {}", result.getTotalElements());
        log.info("-------------------------------");
		
		return result;
	}
}
