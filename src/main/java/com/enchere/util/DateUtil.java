package com.enchere.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtil {

    public static Date getDateNow() {
        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        return Date.from(instant);
    }
}
