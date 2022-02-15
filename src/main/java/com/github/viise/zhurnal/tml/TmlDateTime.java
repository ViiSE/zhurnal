package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.Template;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TmlDateTime implements Template {

    private final LocalDateTime localDateTime;
    private final DateTimeFormatter dtf;

    public TmlDateTime(LocalDateTime localDateTime, DateTimeFormatter dtf) {
        this.localDateTime = localDateTime;
        this.dtf = dtf == null ? DateTimeFormatter.ISO_DATE_TIME : dtf;
    }

    public TmlDateTime(LocalDateTime localDateTime, String pattern) {
        this(localDateTime, DateTimeFormatter.ofPattern(pattern));
    }

    public TmlDateTime(LocalDateTime localDateTime) {
        this(localDateTime, DateTimeFormatter.ISO_DATE_TIME);
    }

    public TmlDateTime() {
        this(LocalDateTime.now());
    }

    @Override
    public String create() {
        return String.format("[%s]", new TmlBasic(localDateTime != null ? dtf.format(localDateTime) : null).create());
    }
}
