package io.apercova.quickcli.examples.command;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Locale;

import io.apercova.quickcli.annotation.CLIArgument;
import io.apercova.quickcli.exception.CLIArgumentException;
import io.apercova.quickcli.annotation.CLICommand;
import io.apercova.quickcli.annotation.CLIDatatypeConverter;
import io.apercova.quickcli.Command;
import io.apercova.quickcli.CommandFactory;
import io.apercova.quickcli.examples.converter.CustomCharsetConverter;
import io.apercova.quickcli.exception.ExecutionException;

/**
 * Example of command value binding. Primitive types,
 * {@link String}, {@link BigDecimal} and {@link BigInteger} are supported by
 * default.
 *
 * @author
 * <a href="https://twitter.com/apercova" target="_blank">{@literal @}apercova</a>
 * <a href="https://github.com/apercova" target="_blank">https://github.com/apercova</a>
 *
 */
@CLICommand("bind-ex")
public class BindExamples extends Command<Void> {

    @CLIArgument(name = "--texto", aliases = {"--string"}, required = true)
    private String texto;
    @CLIArgument(name = "--bits", aliases = {"--byte"}, required = true)
    private byte bits;
    @CLIArgument(name = "--corto", aliases = {"--short"}, required = true)
    private short corto;
    @CLIArgument(name = "--booleano", aliases = {"--boolean"}, value = "true")
    private boolean booleano;
    @CLIArgument(name = "--entero", aliases = {"--int"}, value = "-1")
    private int entero;
    @CLIArgument(name = "--largo", aliases = {"--long"}, required = true)
    private long largo;
    @CLIArgument(name = "--flotante", aliases = {"--float"}, required = true)
    private float flotante;
    @CLIArgument(name = "--doble", aliases = {"--double"}, required = true)
    private double doble;
    @CLIArgument(name = "--bint", aliases = {"--big-integer"}, required = true)
    private BigInteger bint;
    @CLIArgument(name = "--bdec", aliases = {"--big-decimal"}, required = true)
    private BigDecimal bdec;

    @CLIArgument(name = "--cs", aliases = {"--charset"}, required = false, value = "utf-8")
    @CLIDatatypeConverter(CustomCharsetConverter.class)
    private Charset cs;

    @CLIArgument(name = "--help", usage = "List available options")
    private Boolean showHelp;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public byte getBits() {
        return bits;
    }

    public void setBits(byte bits) {
        this.bits = bits;
    }

    public short getCorto() {
        return corto;
    }

    public void setCorto(short corto) {
        this.corto = corto;
    }

    public boolean isBooleano() {
        return booleano;
    }

    public void setBooleano(boolean booleano) {
        this.booleano = booleano;
    }

    public int getEntero() {
        return entero;
    }

    public void setEntero(int entero) {
        this.entero = entero;
    }

    public long getLargo() {
        return largo;
    }

    public void setLargo(long largo) {
        this.largo = largo;
    }

    public float getFlotante() {
        return flotante;
    }

    public void setFlotante(float flotante) {
        this.flotante = flotante;
    }

    public double getDoble() {
        return doble;
    }

    public void setDoble(double doble) {
        this.doble = doble;
    }

    public BigInteger getBint() {
        return bint;
    }

    public void setBint(BigInteger bint) {
        this.bint = bint;
    }

    public BigDecimal getBdec() {
        return bdec;
    }

    public void setBdec(BigDecimal bdec) {
        this.bdec = bdec;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    public Charset getCharset() {
        return cs;
    }

    @Override
    public String toString() {
        return "BindExamples [texto=" + texto + ", bits=" + bits + ", corto=" + corto + ", booleano=" + booleano
                + ", entero=" + entero + ", largo=" + largo + ", flotante=" + flotante + ", doble=" + doble + ", bint="
                + bint + ", bdec=" + bdec + "]";
    }

    @Override
    public Void execute() throws ExecutionException {
        System.out.println(getBits());
        System.out.println(getCorto());
        System.out.println(getEntero());
        System.out.println(getLargo());
        System.out.println(getFlotante());
        System.out.println(getDoble());
        System.out.println(getBint());
        System.out.println(getBdec());
        System.out.println(getCharset());
        return null;
    }

    public static void main(String[] args) throws CLIArgumentException, ExecutionException {

        args = new String[]{
            "--string", "sha1",
            "--boolean",
            "--byte", String.valueOf(Byte.MAX_VALUE),
            "--short", String.valueOf(Short.MAX_VALUE),
            "--int", String.valueOf(Integer.MAX_VALUE),
            "--long", String.valueOf(Long.MAX_VALUE),
            "--float", String.valueOf(Float.MAX_VALUE),
            "--double", String.valueOf(Double.MAX_VALUE),
            "--big-integer", String.valueOf(Long.MAX_VALUE + Long.MAX_VALUE),
            "--big-decimal", String.valueOf(Long.MAX_VALUE * 1.23),
            "--cs", "iso-8859-1",
            "--help"
        };

        Command<Void> command = CommandFactory.create(args, BindExamples.class);

        System.out.println(command);
        System.out.println("Locale: " + command.getLocale());
        System.out.println();
        System.out.println("---Begin execution");
        command.execute();
        System.out.print("---Successfully executed");
    }

}
