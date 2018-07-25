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
public class HelloTask extends Command<String> {

	@CLIArgument(name="name", aliases= {"n"}, required=true, usage="Name for greeting", value="World")
	private String name;
	
	@Override
	public String execute() throws ExecutionException {
		ResourceBundle messages = ResourceBundle.getBundle(
				"net.apercova.quickcli.examples.command.i18n.messages", locale);
		return MessageFormat.format(messages.getString("greeting"), name);
	}
	
	public static void main(String[] args) throws CLIArgumentException, ExecutionException {
		args = new String[]{"name","alonso"};
		Command<String> command = CommandFactory.createCommand(args, HelloTask.class, new Locale("es"));
		System.out.println(command.execute());
	}

}
