package com.everglowsy.projectfinal.controller;

import com.everglowsy.projectfinal.model.CategoryModel;
import com.everglowsy.projectfinal.model.ServiceHandledModel;
import com.everglowsy.projectfinal.repository.CategoryRepository;
import com.everglowsy.projectfinal.service.CategoryService;
import com.everglowsy.projectfinal.service.ServiceHandledService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ServiceHandledController {
    @Autowired
    private ServiceHandledService serviceHandledService;
    @GetMapping("/adminDashboard/Service")
    public String adminService( Model model)
    {

        model.addAttribute("services",serviceHandledService.getAllServices());
        return "adminTemplates/Service/adminService";
    }
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/adminDashboard/Service/addService")
    public String addService(Model model)
    {
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("services",new ServiceHandledModel());
        return "adminTemplates/Service/addService";
    }
    @GetMapping("/saveServices")
    public String validerServices(Model model)
    {

        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("services",new ServiceHandledModel());
        return "adminTemplates/Service/addService";
    }



    @PostMapping("/saveServices")
    public String saveService(@Valid @ModelAttribute("services") ServiceHandledModel serviceHandledModel, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return "adminTemplates/Service/addService";
        }
        serviceHandledService.saveService(serviceHandledModel);
        return "redirect:/adminDashboard/Service";

    }

    @GetMapping("/adminDashboard/Service/editService/{id}")
    public String editService(@PathVariable Long id, Model model)
    {  //(get)trouver les detail de Service  pour particular id
        ServiceHandledModel serviceHandledModel = serviceHandledService.getServiceById(id);
        // (set) placer Service ServiceHandledModel comme model attribute à pre-populate la form
        model.addAttribute("services",serviceHandledModel);
        return "adminTemplates/Service/editService";
    }

    @GetMapping("/adminDashboard/Service/deleteService/{id}")
    public String deleteService(@PathVariable Long id)
    {
        serviceHandledService.deleteService(id);
        return "redirect:/adminDashboard/Service";
    }
}
