package com.example.minidemo;


import javax.validation.Valid;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.BindingResult;
import org.springframework.data.domain.Page;

@Controller
public class HelloController {

	private static final Logger log = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private NamedCategoryService categoryService;
	
	 
    @GetMapping("/hello")
	public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "hello";
	}
    
    @GetMapping("/list")
    public String showUpdateForm(Model model) {
        model.addAttribute("namecategories", categoryService.getAllNamedCategories());
        return "listall";
    }
    
    @GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
        int pageSize = 5;

        Page < NamedCategory > page = categoryService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < NamedCategory > listCategories = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listCategories", listCategories);
        
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("namedCategories", listCategories);
        return "listPaged";
    }
    
    @GetMapping("signup")
    public String showSignUpForm(NamedCategory category) {
        return "add-namedcategory";
    }
    
    @PostMapping("add")
    public String addStudent(@Valid NamedCategory category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-namedcategory";
        }

        categoryService.saveNamedCategory(category);
        return "redirect:list";
    }
    

    @GetMapping("delete/{id}")
    public String deletenamedCategory(@PathVariable("id") long id, Model model) {
        categoryService.deleteNamedCategoryById(id);
        model.addAttribute("namecategories", categoryService.getAllNamedCategories());
        return "listall";
    }
    
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        NamedCategory category = categoryService.getNamedCategoryById(id);
        
        model.addAttribute("namedcategory", category);
        return "update-namedcategory";
    }
    

    @PostMapping("update/{id}")
    public String updateNamedCategory(@PathVariable("id") long id, @Valid NamedCategory category, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
         	return "add-namedcategory";
        }

        category.setId(id);
         
        categoryService.saveNamedCategory(category);
        model.addAttribute("namecategories", categoryService.getAllNamedCategories());
        return "listall";
    }

}
