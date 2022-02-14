package com.github.viise.zhurnal.lg;

import com.github.viise.zhurnal.Log;
import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.tml.TmlDateTime;
import com.github.viise.zhurnal.tml.TmlInfo;
import com.github.viise.zhurnal.tml.TmlMsg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class LgInfo implements Log<String> {

    private final Class<?> lgClass;
    private final Boolean useFullName;
    private final Template tmlDateTime;
    private final List<Template> tmls;

    /* Ctor 1*/
    public LgInfo(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, List<Template> tmls) {
        this.lgClass = lgClass;
        this.useFullName = useFullName;
        this.tmlDateTime = tmlDateTime;
        this.tmls = tmls;
    }

    /* Ctor 2 */
    public LgInfo(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, Template... tmls) {
        this(lgClass, useFullName, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 3 */
    public LgInfo(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, String msg) {
        this(lgClass, useFullName, tmlDateTime, new TmlMsg(msg));
    }

    /* Ctor 4 */
    public LgInfo(Class<?> lgClass, Template tmlDateTime, List<Template> tmls) {
        this(lgClass, false, tmlDateTime, tmls);
    }

    /* Ctor 5 */
    public LgInfo(Class<?> lgClass, List<Template> tmls) {
        this(lgClass, false, new TmlDateTime(), tmls);
    }

    /* Ctor 6 */
    public LgInfo(List<Template> tmls) {
        this(LgInfo.class, false, new TmlDateTime(), tmls);
    }

    /* Ctor 7 */
    public LgInfo(Template tmlDateTime, List<Template> tmls) {
        this(LgInfo.class, false, tmlDateTime, tmls);
    }

    /* Ctor 8 */
    public LgInfo(Class<?> lgClass, Template tmlDateTime, Template... tmls) {
        this(lgClass, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 9 */
    public LgInfo(Class<?> lgClass, Template... tmls) {
        this(lgClass, new TmlDateTime(), Arrays.asList(tmls));
    }

    /* Ctor 10 */
    public LgInfo(Template tmlDateTime, Template... tmls) {
        this(LgInfo.class, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 11 */
    public LgInfo(Template... tmls) {
        this(LgInfo.class, new TmlDateTime(), Arrays.asList(tmls));
    }

    /* Ctor 12 */
    public LgInfo(Class<?> lgClass, Template tmlDateTime, String msg) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 13 */
    public LgInfo(Class<?> lgClass, String msg) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 14 */
    public LgInfo(Template tmlDateTime, String msg) {
        this(LgInfo.class, tmlDateTime, msg);
    }

    /* Ctor 15 */
    public LgInfo(String msg) {
        this(LgInfo.class, new TmlDateTime(), msg);
    }

    /* Ctor 16 */
    public LgInfo(Class<?> lgClass, Template tmlDateTime, String msg, List<Object> params) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 17 */
    public LgInfo(Class<?> lgClass, String msg, List<Object> params) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 18 */
    public LgInfo(String msg, List<Object> params) {
        this(LgInfo.class, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 19 */
    public LgInfo(Class<?> lgClass, Template tmlDateTime, String msg, Object... params) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 20 */
    public LgInfo(Class<?> lgClass, String msg, Object... params) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 21 */
    public LgInfo(Template tmlDateTime, String msg, Object... params) {
        this(LgInfo.class, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 22 */
    public LgInfo(String msg, Object... params) {
        this(LgInfo.class, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    @Override
    public String print() {
        return new LgBasic(lgClass, useFullName, new TmlInfo(), tmlDateTime, tmls).print();
    }
}
