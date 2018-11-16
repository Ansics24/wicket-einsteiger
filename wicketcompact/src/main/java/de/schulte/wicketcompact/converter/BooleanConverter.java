package de.schulte.wicketcompact.converter;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.util.Locale;

public class BooleanConverter implements IConverter<Boolean> {

    @Override
    public Boolean convertToObject(String s, Locale locale) throws ConversionException {
        return Boolean.parseBoolean(s);
    }

    @Override
    public String convertToString(Boolean aBoolean, Locale locale) {
        return aBoolean ? "ja" : "nein";
    }

}
