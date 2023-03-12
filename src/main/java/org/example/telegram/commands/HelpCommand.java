package org.example.telegram.commands;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class HelpCommand extends ServiceCommands{
    public HelpCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        sendAnswer(absSender, chat.getId(),
                "Привет, я тайм-менеджер Pomodoro!\n" +
                        "Я помогу тебе эффективно управлять временем\n" +
                        "для повышения \"ловкости\" мысли\n" +
                        "Укажи запланироавнное количество задач на сегодня,\n" +
                        "а я подскажу тебе когда работать, а когда отдыхать\n" +
                        "Начни командой '/begin'!");
    }
}
