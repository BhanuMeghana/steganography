package com.steganography.controller;

import com.steganography.service.SteganographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SteganographyController {

    @Autowired
    private SteganographyService steganographyService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/encode")
    public String encodeMessage(MultipartFile image, String message, Model model) {
        byte[] encodedImage = steganographyService.encodeMessage(image, message);
        model.addAttribute("image", encodedImage);
        return "result";
    }

    @PostMapping("/decode")
    public String decodeMessage(MultipartFile image, Model model) {
        String message = steganographyService.decodeMessage(image);
        model.addAttribute("message", message);
        return "result";
    }
}
