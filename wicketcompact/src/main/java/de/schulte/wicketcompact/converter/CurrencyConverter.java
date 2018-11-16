package de.schulte.wicketcompact.converter;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class CurrencyConverter implements IConverter<BigDecimal> {

    private DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.GERMANY);

    public CurrencyConverter() {
        this.format.setParseBigDecimal(true);
        this.format.setMinimumFractionDigits(2);
        this.format.setMaximumFractionDigits(2);
    }

    @Override
    public BigDecimal convertToObject(String s, Locale locale) throws ConversionException {
        try {
            return (BigDecimal) format.parseObject(s);
        } catch (ParseException e) {
            throw new RuntimeException(String.format("%s kann nicht in ein BigDecimal konvertiert werden"));
        }
    }

    @Override
    public String convertToString(BigDecimal bigDecimal, Locale locale) {
        return String.format("%s €", this.format.format(bigDecimal));
    }
}
