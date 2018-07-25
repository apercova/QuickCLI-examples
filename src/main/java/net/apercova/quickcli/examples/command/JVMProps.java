package net.apercova.quickcli.examples.command;

import net.apercova.quickcli.CLIArgument;
import net.apercova.quickcli.CLICommand;
import net.apercova.quickcli.Command;
import net.apercova.quickcli.ExecutionException;

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
}
