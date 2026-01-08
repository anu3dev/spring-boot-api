package com.learning.anu3dev.controller;

import com.learning.anu3dev.dto.OpenAiApiPostRequest;
import com.learning.anu3dev.service.OpenAiApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {
        "http://localhost:5173",
        "https://e2eonline.com"
})
@RestController
@RequestMapping("chatbot")
public class ChatBotController {

    @Autowired
    public OpenAiApiService chatBotService;

    @PostMapping("/v1")
    public String chatBotResponse(@RequestBody OpenAiApiPostRequest req){
        return chatBotService.getChatBotResponse(req);
    }
}
