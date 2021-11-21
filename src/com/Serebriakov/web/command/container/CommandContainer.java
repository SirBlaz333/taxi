package com.Serebriakov.web.command.container;

import com.Serebriakov.web.command.*;

import java.util.HashMap;

public class CommandContainer {

    private static HashMap<String, Command> commands;

    static{
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("createUser", new CreateUserCommand());
        commands.put("createReceipt", new CreateReceiptCommand());
        commands.put("confirmReceipt", new ConfirmReceiptCommand());
        commands.put("deleteReceipt", new DeleteReceiptCommand());
        commands.put("chooseSeveralCars", new SeveralCarsWithSameTypeCommand());
        commands.put("chooseCarsWithAnotherType", new OptionsCarsWithAnotherTypeCommand());
        commands.put("updateReceipts", new UpdateReceiptsListCommand());
    }

    public static Command getCommand(String command){
        return commands.get(command);
    }

}
