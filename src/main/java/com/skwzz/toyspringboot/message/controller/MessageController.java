package com.skwzz.toyspringboot.message.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RequiredArgsConstructor
@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageSource messageSource;

    @GetMapping("/hello")
    public String hello(){
        return messageSource.getMessage("hello", null, null);
    }

    @GetMapping("/hello/name")
    public String helloName(@RequestParam String lang){
        Locale locale = Locale.forLanguageTag(lang);
        return messageSource.getMessage("hello.name", new String[]{"기웅"}, locale);
    }
}
