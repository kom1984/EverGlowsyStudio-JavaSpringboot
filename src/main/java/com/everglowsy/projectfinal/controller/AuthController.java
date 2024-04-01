package com.everglowsy.projectfinal.controller;

import com.everglowsy.projectfinal.model.CategoryModel;
import com.everglowsy.projectfinal.model.UserModel;
import com.everglowsy.projectfinal.service.AuthService;
import com.everglowsy.projectfinal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthController{
    @Autowired
private AuthService authService;

        @GetMapping("/appointment")
        public String login(Model model, String error, String logout) {
            if (error != null)
                model.addAttribute("error", " username and password - invalid.");

            if (logout != null)
                model.addAttribute("message", " logged out -success.");

            return "publicTemplates/login";
        }
    @GetMapping("/admin")
    public String loginAdmin(Model model, String error, String logout) {

        if (error != null)
            model.addAttribute("error", " Email et Mot de passe - invalide.");

        if (logout != null)
            model.addAttribute("message", " logged out -success.");

        return "publicTemplates/login";
    }
        @GetMapping("/signup")
        public String signup(Model model) {

            model.addAttribute("userForm", new UserModel());

            return "publicTemplates/signup";
        }

        @PostMapping("/signup")
        public String signup(@ModelAttribute("userForm") UserModel userForm) {

            authService.createNewUser(userForm);

            return "publicTemplates/login";
        }
    /*@PostMapping("/admin")
    public String signin(@RequestParam("email") String email,
                         @RequestParam("password") String password) {

        System.out.println("email:"+email);

        System.out.println("Password: " + password);

        // Redirect to another page after processing login
        return "/admin/all";
    }*/

        @Autowired
        private UserService userService;

        @GetMapping({"/admin/User"})
        public String admin(Model model, Authentication authentication) {
            final List<UserModel> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "/adminTemplates/User/userDashboard";
        }
    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("User") UserModel userModel, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return "adminTemplates/User/editUser";
        }
        userService.saveUser(userModel);
        return "redirect:admin/User";

    }

    @GetMapping("/adminDashboard/User/editUser/{id}")
    public String editUser(@PathVariable Long id, Model model)
    {  //(get)trouvé les detail de User au service pour particular id
        UserModel userModel = userService.getUserById(id);
        // (set) placer UserModel UserModel comme model attribute à pre-populate la form
        model.addAttribute("users",userModel);
        return "adminTemplates/User/editUser";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.removeUser(id);
        return "redirect:/adminDashboard/User";
    }


}

