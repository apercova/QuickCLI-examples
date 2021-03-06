package com.github.apercova.quickcli.examples.command;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;

import com.github.apercova.quickcli.annotation.CLIArgument;
import com.github.apercova.quickcli.annotation.CLICommand;
import com.github.apercova.quickcli.annotation.CLIDatatypeConverter;
import com.github.apercova.quickcli.Command;
import com.github.apercova.quickcli.CommandFactory;
import com.github.apercova.quickcli.examples.converter.CustomCharsetConverter;
import com.github.apercova.quickcli.examples.converter.CustomMessageDigestConverter;
import com.github.apercova.quickcli.exception.CLIArgumentException;
import com.github.apercova.quickcli.exception.ExecutionException;

/**
 * {@link Command} implementation for jchecksum command.
 *
 * @author
 * <a href="https://twitter.com/apercova" target="_blank">{@literal @}apercova</a>
 * <a href="https://github.com/apercova" target="_blank">https://github.com/apercova</a>
 * @since 1.0
 *
 */
@CLICommand(value = "jchecksum", description = "Calculates checksum from file/text-caption")
public final class CheckSum extends Command<Void> {

    public static final String B64_ENCODING = "B64";
    public static final String HEX_ENCODING = "HEX";

    @CLIArgument(name = "-a", aliases = {"-algorithm"}, value = "MD5", usage = "Hashing algorithm")
    @CLIDatatypeConverter(CustomMessageDigestConverter.class)
    private MessageDigest alg;
    @CLIArgument(name = "-e", aliases = {"-encoding"}, usage = "Output encoding. options: [HEX,B64], default: HEX", value = "HEX")
    private String encoding;
    @CLIArgument(name = "-f", aliases = {"-file"}, usage = "File Path")
    private String file;
    @CLIArgument(name = "-t", aliases = {"-text"}, usage = "Text caption")
    private String text;
    @CLIArgument(name = "-cs", aliases = {"-charset"}, usage = "Encoding charset", value = "UTF-8")
    @CLIDatatypeConverter(CustomCharsetConverter.class)
    private Charset cs;
    @CLIArgument(name = "-la", usage = "List available algorithms")
    private Boolean listAlgs;
    @CLIArgument(name = "-lc", usage = "List available charsets")
    private Boolean listCs;
    @CLIArgument(name = "-h", aliases = {"-help"}, usage = "List available options")
    private Boolean showHelp;
    @CLIArgument(name = "-m", aliases = {"-match"}, usage = "Compares suplied pattern against checksum")
    private String match;

    @Override
    public Void execute() throws ExecutionException {
        if (showHelp) {
            printUsage();
        } else if (listAlgs) {
            listHashAlgorithms();
        } else if (listCs) {
            listCharsets();
        } else {
            calculateSum();
        }
        return null;
    }

    /**
     * Calculate sum based on command arguments.
     */
    private void calculateSum() {

        byte[] buffer = new byte[1024];

        if (file != null && !(file.length() == 0)) {
            BufferedInputStream is = null;
            try {
                is = new BufferedInputStream(
                        new FileInputStream(file),
                        buffer.length);
                int read = 0;
                while ((read = is.read(buffer)) > 0) {
                    alg.update(buffer, 0, read);
                }

            } catch (IOException e) {
                writer.printf(locale, "IO error at reading file: %s. %s%n", file, e.getMessage());
                printUsage();
                return;
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e2) {
                }
            }

        } else {
            text = (text == null) ? "" : text;
            alg.update(text.getBytes());
        }

        buffer = alg.digest();
        String sum = null;
        if (B64_ENCODING.equalsIgnoreCase(encoding)) {
            sum = DatatypeConverter.printBase64Binary(buffer);
        } else {
            sum = DatatypeConverter.printHexBinary(buffer).toLowerCase();
        }
        if (match != null && !match.isEmpty()) {
            writer.printf(locale, "%s%n", Pattern.matches(match, sum));
        } else {
            writer.printf(locale, "%s%n", sum);
        }

    }

    /**
     * Prints a list of JVM available {@link MessageDigest} hash algorithms.
     */
    private void listHashAlgorithms() {
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            List<Service> algos = new ArrayList<Service>();

            Set<Service> services = provider.getServices();
            for (Service service : services) {
                if (service.getType().equalsIgnoreCase(MessageDigest.class.getSimpleName())) {
                    algos.add(service);
                }
            }

            if (!algos.isEmpty()) {
                writer.printf(locale, " --- Provider %s, version %.2f --- %n", provider.getName(), provider.getVersion());
                for (Service service : algos) {
                    String algo = service.getAlgorithm();
                    writer.printf(locale, "Algorithm name: \"%s\"%n", algo);
                }
            }
        }
    }

    /**
     * Prints a list of JVM available {@link Charset}.
     */
    private void listCharsets() {
        for (String k : Charset.availableCharsets().keySet()) {
            writer.printf(locale, "Charset name: \"%s\"%n", k);
        }
    }

    /**
     * Execution entry point.
     *
     * @param args Command arguments.
     */
    public static void main(String[] args) {

        CheckSum command = null;
        try {
            command = CommandFactory.create(args, CheckSum.class);
            command.execute();
        } catch (CLIArgumentException e) {
            System.out.printf("%s%n", e.getMessage());
            if (command != null) {
                command.printUsage();
            }
        } catch (ExecutionException e) {
            System.out.printf("%s%n", e.getMessage());
            if (command != null) {
                command.printUsage();
            }
        } finally {
            try {
                if (command != null) {
                    command.close();
                }
            } catch (IOException e2) {
            }
        }

    }

}
