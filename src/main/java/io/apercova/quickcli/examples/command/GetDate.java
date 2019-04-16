package io.apercova.quickcli.examples.command;

import java.util.Locale;

import io.apercova.quickcli.Command;
import io.apercova.quickcli.CommandFactory;
import io.apercova.quickcli.annotation.CLIArgument;
import io.apercova.quickcli.annotation.CLICommand;
import io.apercova.quickcli.exception.CLIArgumentException;
import io.apercova.quickcli.exception.ExecutionException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Example of task command that retrieves a formated Date. Format and Locale are
 * parameterized as args. Local getters are important to retrieve instance
 * values, not parent ones.
 *
 * @author
 * <a href="https://twitter.com/apercova" target="_blank">{@literal @}apercova</a>
 * <a href="https://github.com/apercova" target="_blank">https://github.com/apercova</a>
 */
@CLICommand(value = "currdate", description = "Retrieves current date.")
public class GetDate extends Command<String> {

    @CLIArgument(name = "-f", value = "yyyy-MM-dd'T'hh:mm:ss.SSS zZ")
    private String format;

    public GetDate() {
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public String execute() throws ExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static void main(String[] args) throws CLIArgumentException, ExecutionException {

        args = new String[]{"-f", "EEEE, dd MMM yyyy HH:mm:ss z"};
        Command<String> command = CommandFactory.create(args, GetDate.class, Locale.FRENCH);

        System.out.println(command);
        System.out.println("Locale: " + command.getLocale());
        System.out.println();
        System.out.println("---Begin execution");
        System.out.println(command.execute());
        System.out.print("---Successfully executed");
    }
}
