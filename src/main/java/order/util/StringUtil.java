package order.util;

import java.text.NumberFormat;

public class StringUtil {

    public static String numberFormat(String number) {
        return NumberFormat.getInstance().format(number);
    }

    public static String numberFormat(long number) {
        return NumberFormat.getInstance().format(number);
    }
}
