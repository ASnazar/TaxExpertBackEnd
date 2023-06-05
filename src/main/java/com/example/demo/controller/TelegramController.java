package com.example.demo.controller;

import com.example.demo.service.TelegramBot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TelegramController {
    private final TelegramBot telegramBot;

    public TelegramController(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody String telegramMessage) {
        String chatId = "-1001910134526"; // ID чату, куди відправити повідомлення
        telegramBot.sendMessage(chatId, telegramMessage);
    }
}
