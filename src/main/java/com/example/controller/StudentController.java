package com.example.controller;


import com.example.entity.Student;
import com.example.model.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class StudentController {

    @Autowired
    private StudentModel studentModel;

    @GetMapping("/register")
    public String greetingForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/save")
    public String greetingSubmit(@Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        studentModel.save(student);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("students", studentModel.findAll());
        return "list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("student", studentModel.findById(id));
        return "register";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        studentModel.deleteById(id);
        redirect.addFlashAttribute("success", "Deleted contact successfully!");
        return "redirect:/success";
    }
}
