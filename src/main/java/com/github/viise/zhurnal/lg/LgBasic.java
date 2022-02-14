package com.github.viise.zhurnal.lg;

import com.github.viise.zhurnal.Log;
import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.tml.TmlClass;
import com.github.viise.zhurnal.tml.TmlDateTime;
import com.github.viise.zhurnal.tml.TmlMsg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class LgBasic implements Log<String> {

    private final Class<?> lgClass;
    private final Boolean useFullName;
    private final Template tmlType;
    private final Template tmlDateTime;
    private final List<Template> tmls;

    /* Ctor 1*/
    public LgBasic(Class<?> lgClass, Boolean useFullName, Template tmlType, Template tmlDateTime, List<Template> tmls) {
        this.lgClass = lgClass;
        this.useFullName = useFullName;
        this.tmlType = tmlType;
        this.tmlDateTime = tmlDateTime;
        this.tmls = tmls;
    }

    /* Ctor 2 */
    public LgBasic(Class<?> lgClass, Boolean useFullName, Template tmlType, Template tmlDateTime, Template... tmls) {
        this(lgClass, useFullName, tmlType, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 3 */
    public LgBasic(Class<?> lgClass, Boolean useFullName, Template tmlType, Template tmlDateTime, String msg) {
        this(lgClass, useFullName, tmlType, tmlDateTime, new TmlMsg(msg));
    }

    /* Ctor 4 */
    public LgBasic(Class<?> lgClass, Template tmlType, Template tmlDateTime, List<Template> tmls) {
        this(lgClass, false, tmlType, tmlDateTime, tmls);
    }

    /* Ctor 5 */
    public LgBasic(Class<?> lgClass, Template tmlType, List<Template> tmls) {
        this(lgClass, false, tmlType, new TmlDateTime(), tmls);
    }

    /* Ctor 6 */
    public LgBasic(Template tmlType, List<Template> tmls) {
        this(LgBasic.class, false, tmlType, new TmlDateTime(), tmls);
    }

    /* Ctor 7 */
    public LgBasic(Template tmlType, Template tmlDateTime, List<Template> tmls) {
        this(LgBasic.class, false, tmlType, tmlDateTime, tmls);
    }

    /* Ctor 8 */
    public LgBasic(Class<?> lgClass, Template tmlType, Template tmlDateTime, Template... tmls) {
        this(lgClass, tmlType, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 9 */
    public LgBasic(Class<?> lgClass, Template tmlType, Template... tmls) {
        this(lgClass, tmlType, new TmlDateTime(), Arrays.asList(tmls));
    }

    /* Ctor 10 */
    public LgBasic(Template tmlType, Template tmlDateTime, Template... tmls) {
        this(LgBasic.class, tmlType, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 11 */
    public LgBasic(Template tmlType, Template... tmls) {
        this(LgBasic.class, tmlType, new TmlDateTime(), Arrays.asList(tmls));
    }

    /* Ctor 12 */
    public LgBasic(Class<?> lgClass, Template tmlType, Template tmlDateTime, String msg) {
        this(lgClass, tmlType, tmlDateTime, new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 13 */
    public LgBasic(Class<?> lgClass, Template tmlType, String msg) {
        this(lgClass, tmlType, new TmlDateTime(), new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 14 */
    public LgBasic(Template tmlType, Template tmlDateTime, String msg) {
        this(LgBasic.class, tmlType, tmlDateTime, msg);
    }

    /* Ctor 15 */
    public LgBasic(Template tmlType, String msg) {
        this(LgBasic.class, tmlType, new TmlDateTime(), msg);
    }

    /* Ctor 16 */
    public LgBasic(Class<?> lgClass, Template tmlType, Template tmlDateTime, String msg, List<Object> params) {
        this(lgClass, tmlType, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 17 */
    public LgBasic(Class<?> lgClass, Template tmlType, String msg, List<Object> params) {
        this(lgClass, tmlType, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 18 */
    public LgBasic(Template tmlType, String msg, List<Object> params) {
        this(LgBasic.class, tmlType, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 19 */
    public LgBasic(Class<?> lgClass, Template tmlType, Template tmlDateTime, String msg, Object... params) {
        this(lgClass, tmlType, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 20 */
    public LgBasic(Class<?> lgClass, Template tmlType, String msg, Object... params) {
        this(lgClass, tmlType, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 21 */
    public LgBasic(Template tmlType, Template tmlDateTime, String msg, Object... params) {
        this(LgBasic.class, tmlType, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 22 */
    public LgBasic(Template tmlType, String msg, Object... params) {
        this(LgBasic.class, tmlType, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder(tmlType.create())
                .append(" ")
                .append(tmlDateTime.create())
                .append(" ")
                .append(new TmlClass(lgClass, useFullName).create());
        tmls.forEach(tml -> sb.append(" ").append(tml.create()));
        return sb.toString();
    }
}
