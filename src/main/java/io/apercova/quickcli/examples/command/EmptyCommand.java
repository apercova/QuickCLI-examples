package io.apercova.quickcli.examples.command;

import io.apercova.quickcli.annotation.CLIArgument;
import io.apercova.quickcli.exception.CLIArgumentException;
import io.apercova.quickcli.annotation.CLICommand;
import io.apercova.quickcli.Command;
import io.apercova.quickcli.CommandFactory;
import io.apercova.quickcli.exception.ExecutionException;

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
        Command<Void> command = CommandFactory.create(args, EmptyCommand.class);

        System.out.println(command);
        System.out.println("Locale: " + command.getLocale());
        System.out.println();
        System.out.println("---Begin execution");
        command.execute();
        System.out.print("---Successfully executed");
    }

}
