package net.apercova.quickcli.examples.converter;

import java.nio.charset.Charset;

import net.apercova.quickcli.DatatypeConverter;
import net.apercova.quickcli.DatatypeConverterException;

public class SimpleCharsetConverter implements DatatypeConverter<Charset> {

	public Charset parse(String value) throws DatatypeConverterException {
		try {
			return Charset.forName(value);
		} catch (Exception e) {
			throw new DatatypeConverterException(e);
		}
	}

	public String format(Charset value) throws DatatypeConverterException {
		try {
			return value.displayName();
		} catch (Exception e) {
			throw new DatatypeConverterException(e);
		}
	}

}
