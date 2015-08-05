package com.mq.gae.voucher.admin.api.vouchers;

import java.util.Random;

/**
 * Author: Gennadii Cherniaiev
 * Date: 8/4/2015
 */
public class CodeGenerator {
    private static final long base = 1_000_000_000;
    private Random r = new Random();

    public String generate() {
        long value = (long) (base / 10 * r.nextDouble() + base);
        return String.valueOf(value);
    }

}
