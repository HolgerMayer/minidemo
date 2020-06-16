package com.example.minidemo;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.BindingResult;

@Controller
public class HelloController {

	 private final NamedCategoryRepository namedCategoryRepository;
	
	@Autowired
	public HelloController(NamedCategoryRepository namedCategoryRepository) {
	    this.namedCategoryRepository = namedCategoryRepository;
	}
	 
    @GetMapping("/hello")
	public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "hello";
	}
    
    @GetMapping("/list")
    public String showUpdateForm(Model model) {
        model.addAttribute("namecategories", namedCategoryRepository.findAll());
        return "listall";
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

        namedCategoryRepository.save(category);
        return "redirect:list";
    }
    

    @GetMapping("delete/{id}")
    public String deletenamedCategory(@PathVariable("id") long id, Model model) {
        NamedCategory category = namedCategoryRepository.findById(id);
        namedCategoryRepository.delete(category);
        model.addAttribute("namecategories", namedCategoryRepository.findAll());
        return "listall";
    }
    
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        NamedCategory category = namedCategoryRepository.findById(id);
        
        model.addAttribute("namedcategory", category);
        return "update-namedcategory";
    }
    

    @PostMapping("update/{id}")
    public String updateNamedCategory(@PathVariable("id") long id, @Valid NamedCategory category, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
         	return "add-namedcategory";
        }

        namedCategoryRepository.save(category);
        model.addAttribute("namecategories", namedCategoryRepository.findAll());
        return "listall";
    }

}
