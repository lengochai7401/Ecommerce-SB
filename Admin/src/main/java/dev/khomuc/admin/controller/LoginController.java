package dev.khomuc.admin.controller;

import dev.khomuc.library.dto.AdminDto;
import dev.khomuc.library.model.Admin;
import dev.khomuc.library.service.impl.AdminServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AdminServiceImpl adminService;
    @GetMapping("/login")
    public String loginForm(){

        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }
    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto") AdminDto adminDto,
                              BindingResult result,
                              Model model){
        try {
            model.addAttribute("adminDto", adminDto);
            if(result.hasErrors()) {

                return "register";
            };
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username);
            if(admin != null){
                model.addAttribute("emailError", "Your email has been registered!");
                return "register";
            }
            if(!adminDto.getPassword().equals(adminDto.getRepeatPassword())){
                model.addAttribute("passwordError", "Password does not match!");
                return "register";
            }
            adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
            adminService.save(adminDto);

        }catch (Exception e){
            model.addAttribute("errors", e.getMessage());
        }
        model.addAttribute("success","Registration successful, please log in!");
        return "register";
    }
}
