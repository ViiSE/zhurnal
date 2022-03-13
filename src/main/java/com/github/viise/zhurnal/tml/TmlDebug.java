/*
 * Copyright 2022 ViiSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.viise.zhurnal.tml;

import com.github.viise.zhurnal.Level;
import com.github.viise.zhurnal.Template;
import com.github.viise.zhurnal.TemplateLeveled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Debug leveled template.
 */
public final class TmlDebug implements TemplateLeveled {

    private final Class<?> lgClass;
    private final Boolean useFullName;
    private final Template tmlTimestamp;
    private final List<Template> tmls;

    /**
     * Ctor 1.
     * @param lgClass Log class.
     * @param useFullName Whether to use the fully qualified log class name in the resulting string template or not.
     * @param tmlTimestamp {@link TmlTimestamp}.
     * @param tmls Templates, which will be used in the resulting log output.
     */
    public TmlDebug(Class<?> lgClass, Boolean useFullName, Template tmlTimestamp, List<Template> tmls) {
        this.lgClass = lgClass;
        this.useFullName = useFullName;
        this.tmlTimestamp = tmlTimestamp;
        this.tmls = tmls;
    }

    /* Ctor 2 */
    public TmlDebug(Class<?> lgClass, Boolean useFullName, Template tmlTimestamp, Template... tmls) {
        this(lgClass, useFullName, tmlTimestamp, Arrays.asList(tmls));
    }

    /* Ctor 3 */
    public TmlDebug(Class<?> lgClass, Boolean useFullName, Template tmlTimestamp, String msg) {
        this(lgClass, useFullName, tmlTimestamp, new TmlMsg(msg));
    }

    /* Ctor 4 */
    public TmlDebug(Class<?> lgClass, Boolean useFullName, String msg) {
        this(lgClass, useFullName, new TmlTimestamp(), new TmlMsg(msg));
    }

    /* Ctor 5 */
    public TmlDebug(Boolean useFullName, String msg) {
        this(TmlDebug.class, useFullName, new TmlTimestamp(), new TmlMsg(msg));
    }

    /* Ctor 6 */
    public TmlDebug(Class<?> lgClass, Boolean useFullName, String msg, List<Object> params) {
        this(lgClass, useFullName, new TmlTimestamp(), new TmlMsg(msg, params));
    }

    /* Ctor 7 */
    public TmlDebug(Class<?> lgClass, Boolean useFullName, String msg, Object... params) {
        this(lgClass, useFullName, new TmlTimestamp(), new TmlMsg(msg, params));
    }

    /* Ctor 8 */
    public TmlDebug(Boolean useFullName, String msg, List<Object> params) {
        this(TmlDebug.class, useFullName, new TmlTimestamp(), new TmlMsg(msg, params));
    }

    /* Ctor 9 */
    public TmlDebug(Boolean useFullName, String msg, Object... params) {
        this(TmlDebug.class, useFullName, new TmlTimestamp(), new TmlMsg(msg, params));
    }

    /* Ctor 10 */
    public TmlDebug(Class<?> lgClass, Template tmlTimestamp, List<Template> tmls) {
        this(lgClass, true, tmlTimestamp, tmls);
    }

    /* Ctor 11 */
    public TmlDebug(Class<?> lgClass, List<Template> tmls) {
        this(lgClass, true, new TmlTimestamp(), tmls);
    }

    /* Ctor 12 */
    public TmlDebug(List<Template> tmls) {
        this(TmlDebug.class, true, new TmlTimestamp(), tmls);
    }

    /* Ctor 13 */
    public TmlDebug(Template tmlTimestamp, List<Template> tmls) {
        this(TmlDebug.class, true, tmlTimestamp, tmls);
    }

    /* Ctor 14 */
    public TmlDebug(Class<?> lgClass, Template tmlTimestamp, Template... tmls) {
        this(lgClass, tmlTimestamp, Arrays.asList(tmls));
    }

    /* Ctor 15 */
    public TmlDebug(Class<?> lgClass, Template... tmls) {
        this(lgClass, new TmlTimestamp(), Arrays.asList(tmls));
    }

    /* Ctor 16 */
    public TmlDebug(Template tmlTimestamp, Template... tmls) {
        this(TmlDebug.class, tmlTimestamp, Arrays.asList(tmls));
    }

    /* Ctor 17 */
    public TmlDebug(Template... tmls) {
        this(TmlDebug.class, new TmlTimestamp(), Arrays.asList(tmls));
    }

    /* Ctor 18 */
    public TmlDebug(Class<?> lgClass, Template tmlTimestamp, String msg) {
        this(lgClass, tmlTimestamp, new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 19 */
    public TmlDebug(Class<?> lgClass, String msg) {
        this(lgClass, new TmlTimestamp(), new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 20 */
    public TmlDebug(Template tmlTimestamp, String msg) {
        this(TmlDebug.class, tmlTimestamp, msg);
    }

    /* Ctor 21 */
    public TmlDebug(String msg) {
        this(TmlDebug.class, new TmlTimestamp(), msg);
    }

    /* Ctor 22 */
    public TmlDebug(Class<?> lgClass, Template tmlTimestamp, String msg, List<Object> params) {
        this(lgClass, tmlTimestamp, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 23 */
    public TmlDebug(Class<?> lgClass, String msg, List<Object> params) {
        this(lgClass, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 24 */
    public TmlDebug(String msg, List<Object> params) {
        this(TmlDebug.class, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 25 */
    public TmlDebug(Class<?> lgClass, Template tmlTimestamp, String msg, Object... params) {
        this(lgClass, tmlTimestamp, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 26 */
    public TmlDebug(Class<?> lgClass, String msg, Object... params) {
        this(lgClass, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 27 */
    public TmlDebug(Template tmlTimestamp, String msg, Object... params) {
        this(TmlDebug.class, tmlTimestamp, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 28 */
    public TmlDebug(String msg, Object... params) {
        this(TmlDebug.class, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    @Override
    public String create() {
        return new TmlEntry(lgClass, useFullName, Level.DEBUG, tmlTimestamp, tmls).create();
    }

    @Override
    public Level level() {
        return Level.DEBUG;
    }
}
