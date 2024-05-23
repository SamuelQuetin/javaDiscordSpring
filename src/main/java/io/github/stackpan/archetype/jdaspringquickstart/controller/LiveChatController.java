package io.github.stackpan.archetype.jdaspringquickstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class LiveChatController {

    @GetMapping(value = "/lastImage")
    public String getLastImage(){
        return "TEST";
    }
}
