package com.github.apercova.quickcli.examples.command;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import com.github.apercova.quickcli.Command;
import com.github.apercova.quickcli.CommandFactory;
import com.github.apercova.quickcli.annotation.CLIArgument;
import com.github.apercova.quickcli.annotation.CLICommand;
import com.github.apercova.quickcli.exception.CLIArgumentException;
import com.github.apercova.quickcli.exception.ExecutionException;

@CLICommand(value = "HelloCommand")
public class HelloTask extends Command<String> {

    private static final String MESSAGES_BOUNDLE = "com.github.apercova.quickcli.examples.command.i18n.messages";

    @CLIArgument(name = "name", aliases = {"n"}, required = true, usage = "Name for greeting", value = "World")
    private String name;

    @Override
    public String execute() throws ExecutionException {
        ResourceBundle messages = ResourceBundle.getBundle(MESSAGES_BOUNDLE, locale);
        return MessageFormat.format(messages.getString("greeting"), name);
    }

    public static void main(String[] args) throws CLIArgumentException, ExecutionException {

        args = new String[]{"name", "alonso"};
        HelloTask command = CommandFactory.create(args, HelloTask.class, new Locale("es"));

        System.out.println(command);
        System.out.println("Locale: " + command.getLocale());
        System.out.println();
        System.out.println("---Begin execution");
        System.out.println(command.execute());
        System.out.print("---Successfully executed");
    }

}
