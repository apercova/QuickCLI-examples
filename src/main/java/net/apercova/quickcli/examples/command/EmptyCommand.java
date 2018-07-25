package net.apercova.quickcli.examples.command;

import net.apercova.quickcli.CLIArgument;
import net.apercova.quickcli.CLIArgumentException;
import net.apercova.quickcli.CLICommand;
import net.apercova.quickcli.Command;
import net.apercova.quickcli.CommandFactory;
import net.apercova.quickcli.ExecutionException;

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
		args = new String[]{"-s-h"};
		Command<Void> command = CommandFactory.createCommand(args, EmptyCommand.class);
		System.out.println(command);
		command.execute();
	}

}
