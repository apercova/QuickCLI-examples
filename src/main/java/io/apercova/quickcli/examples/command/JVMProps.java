package io.apercova.quickcli.examples.command;

import io.apercova.quickcli.CLIArgument;
import io.apercova.quickcli.CLIArgumentException;
import io.apercova.quickcli.CLICommand;
import io.apercova.quickcli.Command;
import io.apercova.quickcli.CommandFactory;
import io.apercova.quickcli.ExecutionException;

/**
 * Example of action command listing all JVM properties.
 *  
 * @author <a href="https://twitter.com/apercova" target="_blank">{@literal @}apercova</a> <a href="https://github.com/apercova" target="_blank">https://github.com/apercova</a>
 *
 */
@CLICommand(value="osversion", description="Prints OS Version")
public class JVMProps extends Command<Void> {
	
	@CLIArgument(name="--prop-name")
	private String propName;
	
	public Void execute() throws ExecutionException {
		writer.println("-----Begin JVM Properties-----");
		if(propName != null && propName.length() != 0) {
			String name = String.valueOf(propName);
			name = name.substring(0, (name.length() >= 35? 35: name.length()));
			writer.printf(locale, "%-35s|%s%n", name, System.getProperties().getProperty(name));
		}else {
			for(Object propName: System.getProperties().keySet()) {
				String name = String.valueOf(propName);
				name = name.substring(0, (name.length() >= 35? 35: name.length()));
				writer.printf(locale, "%-35s|%s%n", name, System.getProperties().getProperty(name));
			}
		}
		writer.println("-----End JVM Properties-----");
		return null;
	}
	
	public static void main(String[] args) throws CLIArgumentException, ExecutionException {
		
		Command<Void> command = CommandFactory.createCommand(args, JVMProps.class);
		
		System.out.println(command);
		System.out.println("Locale: " + command.getLocale());
		System.out.println();
		System.out.println("---Begin execution");
		command.execute();
		System.out.print("---Successfully executed");
	}
}
