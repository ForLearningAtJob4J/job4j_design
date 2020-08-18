package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = Byte.MAX_VALUE;
        short sh = Short.MAX_VALUE;
        int i = Integer.MAX_VALUE;
        long l = Long.MAX_VALUE;

        double d = Double.MAX_VALUE;
        float f = Float.MAX_VALUE;

        boolean bool = true;
        char c = 'A';

        LOG.debug("\n\tbyte:{}\n\tshort:{}\n\tinteger:{}\n\tlong:{}\n\tdouble:{}\n\tfloat:{}\n\tboolean:{}\n\tchar: {}",
                b, sh, i, l, d, f, bool, c);
    }
}