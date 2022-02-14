package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.Template;

import java.util.Arrays;
import java.util.List;

public final class TmlRoot implements Template {

    private final String name;
    private final List<Template> children;

    public TmlRoot(String name, Template... children) {
        this(name, Arrays.asList(children));
    }

    public TmlRoot(String name, List<Template> children) {
        this.name = name;
        this.children = children;
    }

    @Override
    public String create() {
        StringBuilder sb = new StringBuilder("[").append(new TmlBasic(name).create().toUpperCase()).append(" ");
        if (children != null) {
            children.forEach(child -> sb.append(child.create()).append(" "));
        }
        sb.setLength(sb.length() - 1);
        sb.append("]");

        return sb.toString();
    }
}
