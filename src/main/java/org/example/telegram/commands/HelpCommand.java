package org.example.telegram.commands;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class HelpCommand extends ServiceCommands {
    public HelpCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        sendAnswer(absSender, chat.getId(),
                "Hello! I am The Pomodoro time manager!\n" +
                        "I will help you manage your time effectively\n" +
                        "to increase a \"dexterity\" of mind.\n" +
                        "Specify the planned number of tasks for today,\n" +
                        "and I will tell you when to work and when to rest\n" +
                        "Start with command '/begin'!");
    }
}
