package org.example;

import org.example.telegram.MyTelegramPomodoroBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        new MyTelegramPomodoroBot();
    }
}