package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.Template;

import java.util.concurrent.TimeUnit;

public final class TmlDuration implements Template {

    private final Long duration;
    private final TimeUnit unit;

    public TmlDuration(Long duration, TimeUnit unit) {
        this.duration = duration;
        this.unit = unit;
    }

    public TmlDuration(Long duration) {
        this(duration, TimeUnit.MILLISECONDS);
    }

    @Override
    public String create() {
        return new TmlRoot(
                "duration",
                new TmlChild(
                        "value",
                        duration
                ),
                new TmlChild(
                        "unit",
                        unit != null ? unit.name() : "null"
                )
        ).create();
    }
}
