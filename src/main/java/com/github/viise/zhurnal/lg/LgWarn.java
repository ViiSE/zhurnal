package com.github.viise.zhurnal.lg;

import com.github.viise.zhurnal.Log;
import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.tml.TmlDateTime;
import com.github.viise.zhurnal.tml.TmlMsg;
import com.github.viise.zhurnal.tml.TmlWarn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class LgWarn implements Log<String> {

    private final Class<?> lgClass;
    private final Boolean useFullName;
    private final Template tmlDateTime;
    private final List<Template> tmls;

    /* Ctor 1*/
    public LgWarn(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, List<Template> tmls) {
        this.lgClass = lgClass;
        this.useFullName = useFullName;
        this.tmlDateTime = tmlDateTime;
        this.tmls = tmls;
    }

    /* Ctor 2 */
    public LgWarn(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, Template... tmls) {
        this(lgClass, useFullName, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 3 */
    public LgWarn(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, String msg) {
        this(lgClass, useFullName, tmlDateTime, new TmlMsg(msg));
    }

    /* Ctor 4 */
    public LgWarn(Class<?> lgClass, Template tmlDateTime, List<Template> tmls) {
        this(lgClass, false, tmlDateTime, tmls);
    }

    /* Ctor 5 */
    public LgWarn(Class<?> lgClass, List<Template> tmls) {
        this(lgClass, false, new TmlDateTime(), tmls);
    }

    /* Ctor 6 */
    public LgWarn(List<Template> tmls) {
        this(LgWarn.class, false, new TmlDateTime(), tmls);
    }

    /* Ctor 7 */
    public LgWarn(Template tmlDateTime, List<Template> tmls) {
        this(LgWarn.class, false, tmlDateTime, tmls);
    }

    /* Ctor 8 */
    public LgWarn(Class<?> lgClass, Template tmlDateTime, Template... tmls) {
        this(lgClass, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 9 */
    public LgWarn(Class<?> lgClass, Template... tmls) {
        this(lgClass, new TmlDateTime(), Arrays.asList(tmls));
    }

    /* Ctor 10 */
    public LgWarn(Template tmlDateTime, Template... tmls) {
        this(LgWarn.class, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 11 */
    public LgWarn(Template... tmls) {
        this(LgWarn.class, new TmlDateTime(), Arrays.asList(tmls));
    }

    /* Ctor 12 */
    public LgWarn(Class<?> lgClass, Template tmlDateTime, String msg) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 13 */
    public LgWarn(Class<?> lgClass, String msg) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 14 */
    public LgWarn(Template tmlDateTime, String msg) {
        this(LgWarn.class, tmlDateTime, msg);
    }

    /* Ctor 15 */
    public LgWarn(String msg) {
        this(LgWarn.class, new TmlDateTime(), msg);
    }

    /* Ctor 16 */
    public LgWarn(Class<?> lgClass, Template tmlDateTime, String msg, List<Object> params) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 17 */
    public LgWarn(Class<?> lgClass, String msg, List<Object> params) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 18 */
    public LgWarn(String msg, List<Object> params) {
        this(LgWarn.class, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 19 */
    public LgWarn(Class<?> lgClass, Template tmlDateTime, String msg, Object... params) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 20 */
    public LgWarn(Class<?> lgClass, String msg, Object... params) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 21 */
    public LgWarn(Template tmlDateTime, String msg, Object... params) {
        this(LgWarn.class, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 22 */
    public LgWarn(String msg, Object... params) {
        this(LgWarn.class, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    @Override
    public String print() {
        return new LgBasic(lgClass, useFullName, new TmlWarn(), tmlDateTime, tmls).print();
    }
}
