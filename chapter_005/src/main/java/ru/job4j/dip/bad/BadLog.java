package ru.job4j.dip.bad;

/**
 * Логирование захардкожено
 */
public class BadLog {
    int div(int a, int b) {
        int ret = 0;
        try {
            ret = a / b;
        } catch (ArithmeticException e) {
            var lastStackTrace = e.getStackTrace()[0];
            System.out.println("Division by zero in line #" + lastStackTrace.getLineNumber() + " of "
                    + lastStackTrace.getClassName() + "." + lastStackTrace.getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void main(String[] args) {
        BadLog badLog = new BadLog();
        System.out.println(badLog.div(100, 0));
    }
}
