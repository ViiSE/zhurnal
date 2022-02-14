package com.github.viise.zhurnal.lg;

import com.github.viise.zhurnal.Log;
import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.tml.TmlDateTime;
import com.github.viise.zhurnal.tml.TmlError;
import com.github.viise.zhurnal.tml.TmlMsg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class LgError implements Log<String> {

    private final Class<?> lgClass;
    private final Boolean useFullName;
    private final Template tmlDateTime;
    private final List<Template> tmls;

    /* Ctor 1*/
    public LgError(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, List<Template> tmls) {
        this.lgClass = lgClass;
        this.useFullName = useFullName;
        this.tmlDateTime = tmlDateTime;
        this.tmls = tmls;
    }

    /* Ctor 2 */
    public LgError(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, Template... tmls) {
        this(lgClass, useFullName, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 3 */
    public LgError(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, String msg) {
        this(lgClass, useFullName, tmlDateTime, new TmlMsg(msg));
    }

    /* Ctor 4 */
    public LgError(Class<?> lgClass, Template tmlDateTime, List<Template> tmls) {
        this(lgClass, false, tmlDateTime, tmls);
    }

    /* Ctor 5 */
    public LgError(Class<?> lgClass, List<Template> tmls) {
        this(lgClass, false, new TmlDateTime(), tmls);
    }

    /* Ctor 6 */
    public LgError(List<Template> tmls) {
        this(LgError.class, false, new TmlDateTime(), tmls);
    }

    /* Ctor 7 */
    public LgError(Template tmlDateTime, List<Template> tmls) {
        this(LgError.class, false, tmlDateTime, tmls);
    }

    /* Ctor 8 */
    public LgError(Class<?> lgClass, Template tmlDateTime, Template... tmls) {
        this(lgClass, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 9 */
    public LgError(Class<?> lgClass, Template... tmls) {
        this(lgClass, new TmlDateTime(), Arrays.asList(tmls));
    }

    /* Ctor 10 */
    public LgError(Template tmlDateTime, Template... tmls) {
        this(LgError.class, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 11 */
    public LgError(Template... tmls) {
        this(LgError.class, new TmlDateTime(), Arrays.asList(tmls));
    }

    /* Ctor 12 */
    public LgError(Class<?> lgClass, Template tmlDateTime, String msg) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 13 */
    public LgError(Class<?> lgClass, String msg) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 14 */
    public LgError(Template tmlDateTime, String msg) {
        this(LgError.class, tmlDateTime, msg);
    }

    /* Ctor 15 */
    public LgError(String msg) {
        this(LgError.class, new TmlDateTime(), msg);
    }

    /* Ctor 16 */
    public LgError(Class<?> lgClass, Template tmlDateTime, String msg, List<Object> params) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 17 */
    public LgError(Class<?> lgClass, String msg, List<Object> params) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 18 */
    public LgError(String msg, List<Object> params) {
        this(LgError.class, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 19 */
    public LgError(Class<?> lgClass, Template tmlDateTime, String msg, Object... params) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 20 */
    public LgError(Class<?> lgClass, String msg, Object... params) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 21 */
    public LgError(Template tmlDateTime, String msg, Object... params) {
        this(LgError.class, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 22 */
    public LgError(String msg, Object... params) {
        this(LgError.class, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    @Override
    public String print() {
        return new LgBasic(lgClass, useFullName, new TmlError(), tmlDateTime, tmls).print();
    }
}
