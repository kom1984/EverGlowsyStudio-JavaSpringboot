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
        //model.addAttribute("all-service-category",serviceHandledService. getServiceCategory());
        return "publicTemplates/index";
    }

    @GetMapping("/adminDashboard")
    public String adminCategory()
    {
        return "adminTemplates/adminDashboard";
    }

    @GetMapping("/adminDashboard/Category")
    public String adminCategory( Model model)
    {

        model.addAttribute("categories",categoryService.getAllCategory());
        return "adminTemplates/Category/adminCategory";
    }

    @GetMapping("/adminDashboard/Category/add")
    public String addCategory(Model model)
    {

        model.addAttribute("Category",new CategoryModel());
        return "adminTemplates/Category/addCategory";
    }




    @PostMapping("/saveCategory")
    public String saveCategory(@Valid @ModelAttribute("Category") CategoryModel categoryModel, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return "adminTemplates/Category/addCategory";
        }
       categoryService.saveCategory(categoryModel);
        return "redirect:/adminDashboard/Category";

    }
    @GetMapping("/adminDashboard/Category/editCategory/{id}")
    public String editCategory(@PathVariable Long id, Model model)
    {  //(get)trouver les detail de Category au service pour particular id
        CategoryModel categoryModel = categoryService.getCategoryById(id);
        // (set) placer Category categoryModel comme model attribute à pre-populate la form
        model.addAttribute("Category",categoryModel);
        return "adminTemplates/Category/addCategory";
    }
    @GetMapping("/adminDashboard/Category/deleteCategory/{id}")
    public String deleteCategory(@PathVariable Long id)
    {
        categoryService.deleteCategory(id);
        return "redirect:/adminDashboard/Category";
    }


}
