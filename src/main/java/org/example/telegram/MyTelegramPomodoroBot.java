package org.example.telegram;

import org.example.telegram.commands.BeginCommand;
import org.example.telegram.commands.HelpCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.concurrent.TimeUnit;

public class MyTelegramPomodoroBot extends TelegramLongPollingCommandBot {
    public static final String BOT_TOKEN = "5687444350:AAGd07oXyHR1XULjh8ch1PvmsPvP3oHhxBE";
    public static final String BOT_USERNAME = "MySuperPomodoroBot";
    public static final int WORK = 25;
    public static final int BREAK = 5;
    public static String chatId;

    public MyTelegramPomodoroBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            botsApi.registerBot(this);
            register(new BeginCommand("begin", "Начало"));
            register(new HelpCommand("help", "Справка"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        chatId = update.getMessage().getChatId().toString();
        if (update.hasMessage() && isNumeric(update.getMessage().getText()) && BeginCommand.isBegin){
            String message;
            BeginCommand.isBegin = false;
            int count = Integer.parseInt(update.getMessage().getText()); // количество дел
            for (int i = 1; i <= count ; i++) {
                message = "Задача № " + i + " началась";
                sendMessage(message);
                try {
                    TimeUnit.MINUTES.sleep(WORK);
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
                message = "Задача № " + i + " завершилась";
                sendMessage(message);
                if (count - i != 0){
                    message = "Время отдыхать!";
                    sendMessage(message);
                    try {
                        TimeUnit.MINUTES.sleep(BREAK);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    message = "Время работать";
                    sendMessage(message);
                }
            }
        } else {
            String message = "Я не понимаю";
            sendMessage(message);
        }
    }

    private void sendMessage (String messageText){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public static boolean isNumeric (String strNum){
        if (strNum == null){
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe){
            return false;
        }
        return true;
    }
}
