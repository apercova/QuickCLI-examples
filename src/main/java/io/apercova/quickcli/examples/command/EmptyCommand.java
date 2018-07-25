package io.apercova.quickcli.examples.command;

import io.apercova.quickcli.CLIArgument;
import io.apercova.quickcli.CLIArgumentException;
import io.apercova.quickcli.CLICommand;
import io.apercova.quickcli.Command;
import io.apercova.quickcli.CommandFactory;
import io.apercova.quickcli.ExecutionException;

@CLICommand(value="HelloCommand")
public class EmptyCommand extends Command<Void> {

	@CLIArgument(name="-help", aliases= {"-h","--h"}, required=false, usage="Display usage options")
	private boolean help;
	
	@Override
	public Void execute() throws ExecutionException {
		if(help) {
			writer.println("---------- Options ----------------------------------------------------");
			printUsage();
		}
		return null;
	}
	
	public static void main(String[] args) throws CLIArgumentException, ExecutionException {
		
		args = new String[]{"--h"};
		Command<Void> command = CommandFactory.createCommand(args, EmptyCommand.class);

		System.out.println(command);
		System.out.println("Locale: " + command.getLocale());
		System.out.println();
		System.out.println("---Begin execution");
		command.execute();
		System.out.print("---Successfully executed");
	}

}
