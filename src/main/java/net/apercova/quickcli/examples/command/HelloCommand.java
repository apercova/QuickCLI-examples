package net.apercova.quickcli.examples.command;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import net.apercova.quickcli.CLIArgument;
import net.apercova.quickcli.CLIArgumentException;
import net.apercova.quickcli.CLICommand;
import net.apercova.quickcli.Command;
import net.apercova.quickcli.CommandFactory;
import net.apercova.quickcli.ExecutionException;

@CLICommand(value="HelloCommand")
public class HelloCommand extends Command<Void> {

	@CLIArgument(name="name", aliases= {"n"}, required=true, usage="Name for greeting", value="World")
	private String name;
	
	@Override
	public Void execute() throws ExecutionException {
		ResourceBundle messages = ResourceBundle.getBundle(
				"net.apercova.quickcli.examples.command.i18n.messages", locale);
		System.out.println(MessageFormat.format(messages.getString("greeting"), name));
		return null;
	}
	
	public static void main(String[] args) throws CLIArgumentException, ExecutionException {
		args = new String[]{"name","alonso"};
		Command<Void> command = CommandFactory.createCommand(args, HelloCommand.class, new Locale("es"));
		command.execute();
	}

}
