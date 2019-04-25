package com.github.apercova.quickcli.examples.command;

import com.github.apercova.quickcli.annotation.CLIArgument;
import com.github.apercova.quickcli.Command;
import com.github.apercova.quickcli.CommandFactory;
import com.github.apercova.quickcli.annotation.CLICommand;
import com.github.apercova.quickcli.exception.CLIArgumentException;
import com.github.apercova.quickcli.exception.ExecutionException;

/**
 * Example of action command listing all JVM properties.
 *
 * @author
 * <a href="https://twitter.com/apercova" target="_blank">{@literal @}apercova</a>
 * <a href="https://github.com/apercova" target="_blank">https://github.com/apercova</a>
 *
 */
@CLICommand(value = "osversion", description = "Prints OS Version")
public class JVMProps extends Command<Void> {

    @CLIArgument(name = "--prop-name")
    private String propName;

    @Override
    public Void execute() throws ExecutionException {
        writer.println("-----Begin JVM Properties-----");
        if (propName != null && propName.length() != 0) {
            String propName_ = String.valueOf(propName);
            propName_ = propName_.substring(0, (propName_.length() >= 35 ? 35 : propName_.length()));
            writer.printf(locale, "%-35s|%s%n", propName_, System.getProperties().getProperty(propName_));
        } else {
            for (Object sysProp : System.getProperties().keySet()) {
                String sysProp_ = String.valueOf(sysProp);
                sysProp_ = sysProp_.substring(0, (sysProp_.length() >= 35 ? 35 : sysProp_.length()));
                writer.printf(locale, "%-35s|%s%n", sysProp, System.getProperties().getProperty(sysProp_));
            }
        }
        writer.println("-----End JVM Properties-----");
        return null;
    }

    public static void main(String[] args) throws CLIArgumentException, ExecutionException {

        JVMProps command = CommandFactory.create(args, JVMProps.class);

        System.out.println(command);
        System.out.println("Locale: " + command.getLocale());
        System.out.println();
        System.out.println("---Begin execution");
        command.execute();
        System.out.print("---Successfully executed");
    }
}
