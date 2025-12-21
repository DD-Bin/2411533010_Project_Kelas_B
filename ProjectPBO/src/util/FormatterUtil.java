package util;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatterUtil {
    public static String formatRupiah(double nominal) {
        Locale indo = new Locale("id", "ID");    
        NumberFormat formatter = NumberFormat.getCurrencyInstance(indo);
        return formatter.format(nominal);
    }
}