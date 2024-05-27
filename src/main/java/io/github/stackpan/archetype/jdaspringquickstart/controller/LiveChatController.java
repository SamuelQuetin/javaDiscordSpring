package io.github.stackpan.archetype.jdaspringquickstart.controller;

import io.github.stackpan.archetype.jdaspringquickstart.dto.ResponseDto;
import io.github.stackpan.archetype.jdaspringquickstart.service.LiveChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class LiveChatController {
    @Autowired
    private LiveChatService liveChatService;

    @GetMapping(value = "/lastImage")
    public ResponseDto getLastImage(){
        return liveChatService.getResponseDto();
    }
}
