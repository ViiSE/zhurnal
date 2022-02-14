package com.github.viise.zhurnal.lg;

import com.github.viise.zhurnal.Log;
import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.tml.TmlDateTime;
import com.github.viise.zhurnal.tml.TmlMsg;
import com.github.viise.zhurnal.tml.TmlTrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class LgTrace implements Log<String> {

    private final Class<?> lgClass;
    private final Boolean useFullName;
    private final Template tmlDateTime;
    private final List<Template> tmls;

    /* Ctor 1*/
    public LgTrace(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, List<Template> tmls) {
        this.lgClass = lgClass;
        this.useFullName = useFullName;
        this.tmlDateTime = tmlDateTime;
        this.tmls = tmls;
    }

    /* Ctor 2 */
    public LgTrace(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, Template... tmls) {
        this(lgClass, useFullName, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 3 */
    public LgTrace(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, String msg) {
        this(lgClass, useFullName, tmlDateTime, new TmlMsg(msg));
    }

    /* Ctor 4 */
    public LgTrace(Class<?> lgClass, Template tmlDateTime, List<Template> tmls) {
        this(lgClass, false, tmlDateTime, tmls);
    }

    /* Ctor 5 */
    public LgTrace(Class<?> lgClass, List<Template> tmls) {
        this(lgClass, false, new TmlDateTime(), tmls);
    }

    /* Ctor 6 */
    public LgTrace(List<Template> tmls) {
        this(LgTrace.class, false, new TmlDateTime(), tmls);
    }

    /* Ctor 7 */
    public LgTrace(Template tmlDateTime, List<Template> tmls) {
        this(LgTrace.class, false, tmlDateTime, tmls);
    }

    /* Ctor 8 */
    public LgTrace(Class<?> lgClass, Template tmlDateTime, Template... tmls) {
        this(lgClass, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 9 */
    public LgTrace(Class<?> lgClass, Template... tmls) {
        this(lgClass, new TmlDateTime(), Arrays.asList(tmls));
    }

    /* Ctor 10 */
    public LgTrace(Template tmlDateTime, Template... tmls) {
        this(LgTrace.class, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 11 */
    public LgTrace(Template... tmls) {
        this(LgTrace.class, new TmlDateTime(), Arrays.asList(tmls));
    }

    /* Ctor 12 */
    public LgTrace(Class<?> lgClass, Template tmlDateTime, String msg) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 13 */
    public LgTrace(Class<?> lgClass, String msg) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 14 */
    public LgTrace(Template tmlDateTime, String msg) {
        this(LgTrace.class, tmlDateTime, msg);
    }

    /* Ctor 15 */
    public LgTrace(String msg) {
        this(LgTrace.class, new TmlDateTime(), msg);
    }

    /* Ctor 16 */
    public LgTrace(Class<?> lgClass, Template tmlDateTime, String msg, List<Object> params) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 17 */
    public LgTrace(Class<?> lgClass, String msg, List<Object> params) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 18 */
    public LgTrace(String msg, List<Object> params) {
        this(LgTrace.class, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 19 */
    public LgTrace(Class<?> lgClass, Template tmlDateTime, String msg, Object... params) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 20 */
    public LgTrace(Class<?> lgClass, String msg, Object... params) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 21 */
    public LgTrace(Template tmlDateTime, String msg, Object... params) {
        this(LgTrace.class, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 22 */
    public LgTrace(String msg, Object... params) {
        this(LgTrace.class, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    @Override
    public String print() {
        return new LgBasic(lgClass, useFullName, new TmlTrace(), tmlDateTime, tmls).print();
    }
}
