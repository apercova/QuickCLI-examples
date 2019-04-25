package com.github.apercova.quickcli.examples.command;

import com.github.apercova.quickcli.annotation.CLIArgument;
import com.github.apercova.quickcli.exception.CLIArgumentException;
import com.github.apercova.quickcli.annotation.CLICommand;
import com.github.apercova.quickcli.Command;
import com.github.apercova.quickcli.CommandFactory;
import com.github.apercova.quickcli.exception.ExecutionException;

@CLICommand(value = "HelloCommand")
public class EmptyCommand extends Command<Void> {

    @CLIArgument(name = "-help", aliases = {"-h", "--h"}, required = false, usage = "Display usage options")
    private boolean help;

    @Override
    public Void execute() throws ExecutionException {
        if (help) {
            writer.println("---------- Options ----------------------------------------------------");
            printUsage();
        }
        return null;
    }

    public static void main(String[] args) throws CLIArgumentException, ExecutionException {

        args = new String[]{"--h"};
        EmptyCommand command = CommandFactory.create(args, EmptyCommand.class);

        System.out.println(command);
        System.out.println("Locale: " + command.getLocale());
        System.out.println();
        System.out.println("---Begin execution");
        command.execute();
        System.out.print("---Successfully executed");
    }

}
