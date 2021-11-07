package com.Serebriakov.web.command;

import java.util.HashMap;

public class CommandContainer {

    private static HashMap<String, Command> commands;

    static{
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("createUser", new CreateUserCommand());
    }

    public static Command getCommand(String command){
        return commands.get(command);
    }

}
