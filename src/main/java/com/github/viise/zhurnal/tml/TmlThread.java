package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.Template;

public final class TmlThread implements Template {

    private final Long id;
    private final String name;
    private final Boolean isAlive;
    private final Boolean isInterrupted;

    public TmlThread(Long id, String name, Boolean isAlive, Boolean isInterrupted) {
        this.id = id;
        this.name = name;
        this.isAlive = isAlive;
        this.isInterrupted = isInterrupted;
    }

    public TmlThread(Thread thd) {
        this(
                thd != null ? thd.getId() : null,
                thd != null ? thd.getName() : null,
                thd != null && thd.isAlive(),
                thd != null && thd.isInterrupted()
        );

    }

    @Override
    public String create() {
        return new TmlRoot(
                "thread",
                new TmlChild(
                        "id",
                        id
                ),
                new TmlChild(
                        "name",
                        name
                ),
                new TmlChild(
                        "is_alive",
                        isAlive
                ),
                new TmlChild(
                        "is_interrupted",
                        isInterrupted
                )
        ).create();
    }
}
