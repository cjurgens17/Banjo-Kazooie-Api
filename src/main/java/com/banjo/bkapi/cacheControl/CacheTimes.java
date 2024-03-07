package com.banjo.bkapi.cacheControl;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

@Getter
public class CacheTimes {
    public static final int SINGLE_ENTITY = 30;
    public static final int GROUPED_ENTITY = 60;
    public static final TimeUnit HOURS = TimeUnit.HOURS;
    public static final TimeUnit DAYS = TimeUnit.DAYS;
}
