package io.apercova.quickcli.examples.command;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import io.apercova.quickcli.Command;
import io.apercova.quickcli.CommandFactory;
import io.apercova.quickcli.annotation.CLIArgument;
import io.apercova.quickcli.annotation.CLICommand;
import io.apercova.quickcli.exception.CLIArgumentException;
import io.apercova.quickcli.exception.ExecutionException;

@CLICommand(value = "HelloCommand")
public class HelloTask extends Command<String> {

    private static final String MESSAGES_BOUNDLE = "io.apercova.quickcli.examples.command.i18n.messages";

    @CLIArgument(name = "name", aliases = {"n"}, required = true, usage = "Name for greeting", value = "World")
    private String name;

    @Override
    public String execute() throws ExecutionException {
        ResourceBundle messages = ResourceBundle.getBundle(MESSAGES_BOUNDLE, locale);
        return MessageFormat.format(messages.getString("greeting"), name);
    }

    public static void main(String[] args) throws CLIArgumentException, ExecutionException {

        args = new String[]{"name", "alonso"};
        Command<String> command = CommandFactory.create(args, HelloTask.class, new Locale("es"));

        System.out.println(command);
        System.out.println("Locale: " + command.getLocale());
        System.out.println();
        System.out.println("---Begin execution");
        System.out.println(command.execute());
        System.out.print("---Successfully executed");
    }

}
