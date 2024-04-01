package com.everglowsy.projectfinal.controller;
import com.everglowsy.projectfinal.model.CategoryModel;
import com.everglowsy.projectfinal.model.ServiceHandledModel;
import com.everglowsy.projectfinal.service.CategoryService;
import com.everglowsy.projectfinal.service.ServiceHandledService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ServiceHandledService serviceHandledService;
    @GetMapping("/")
    public String index(Model model)
    {
        List<CategoryModel> list= categoryService.getAllCategory();
        List<ServiceHandledModel> list1= serviceHandledService.getAllServices();
        int halfsize = list.size()/2;
        List<CategoryModel> column1 = list.subList(0,halfsize);
        List<CategoryModel> column2 = list.subList(halfsize,list.size());
        model.addAttribute("categories",list);
        model.addAttribute("halfCategories1",column1);
        model.addAttribute("halfCategories2",column2);
        model.addAttribute("services",list1);
        return "publicTemplates/index";
    }

    @GetMapping("/admin/all")
    public String adminCategory()
    {
        return "adminTemplates/adminDashboard";
    }


    @GetMapping("/admin/Category")
    public String adminCategory( Model model)
    {

        model.addAttribute("categories",categoryService.getAllCategory());
        return "adminTemplates/Category/categoryDashboard";
    }
    @PostMapping("/saveCategory")
    public String saveCategory(@Valid @ModelAttribute("Category") CategoryModel categoryModel, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return "adminTemplates/Category/editCategory";
        }
       categoryService.saveCategory(categoryModel);
        return "redirect:admin/Category";

    }
    @GetMapping("/admin/Category/editCategory/{id}")
    public String editCategory(@PathVariable Long id,Model model)
    {  //(get)trouvé les detail de category au service pour particular id
        CategoryModel categoryModel = categoryService.getCategoryById(id);
        // (set) placer CategoryModel categoryModel comme model attribute à pre-populate la form
        model.addAttribute("categories",categoryModel);
        return "adminTemplates/Category/editCategory";
    }

   @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/Category";
    }

}
