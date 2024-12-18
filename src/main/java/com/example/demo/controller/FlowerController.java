package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Flower;
import com.example.demo.respository.FlowerRepository;

@Controller

@RequestMapping("/flowers")
public class FlowerController {

    @Autowired
    private FlowerRepository flowerRepository;

    @GetMapping
    public String getAllCustomers(Model model) {
        List<Flower> flowers = flowerRepository.findAll();
        String redFlower = "";
        double totalPrice = 0.0;

        for (Flower flower : flowers) {
            if (flower.getColor().equals("Red")) {
                if (redFlower == "") {
                    redFlower = flower.getName();
                } else {
                    redFlower += "  " + flower.getName();
                }
            }

            totalPrice += flower.getPrice();
        }

        model.addAttribute("flowers", flowers);
        model.addAttribute("redFlowers", redFlower);
        model.addAttribute("total", totalPrice);
        return "flowers";

    }

    @GetMapping("/add")
    public String showForm(@ModelAttribute Flower flower, Model model) {
        model.addAttribute("flower", flower.getId() == null ? new Flower() : flower);
        model.addAttribute("colors", Arrays.asList("Special color", "Red", "Yellow"));
        return "form";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute Flower flower, RedirectAttributes redirectAttributes) {
        flowerRepository.save(flower);
        return "redirect:/flowers";
    }
}
