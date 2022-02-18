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

public final class TmlTrace implements TemplateLeveled {

    private final Class<?> lgClass;
    private final Boolean useFullName;
    private final Template tmlDateTime;
    private final List<Template> tmls;

    /* Ctor 1*/
    public TmlTrace(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, List<Template> tmls) {
        this.lgClass = lgClass;
        this.useFullName = useFullName;
        this.tmlDateTime = tmlDateTime;
        this.tmls = tmls;
    }

    /* Ctor 2 */
    public TmlTrace(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, Template... tmls) {
        this(lgClass, useFullName, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 3 */
    public TmlTrace(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, String msg) {
        this(lgClass, useFullName, tmlDateTime, new TmlMsg(msg));
    }

    /* Ctor 4 */
    public TmlTrace(Class<?> lgClass, Template tmlDateTime, List<Template> tmls) {
        this(lgClass, true, tmlDateTime, tmls);
    }

    /* Ctor 5 */
    public TmlTrace(Class<?> lgClass, List<Template> tmls) {
        this(lgClass, true, new TmlTimestamp(), tmls);
    }

    /* Ctor 6 */
    public TmlTrace(List<Template> tmls) {
        this(TmlTrace.class, true, new TmlTimestamp(), tmls);
    }

    /* Ctor 7 */
    public TmlTrace(Template tmlDateTime, List<Template> tmls) {
        this(TmlTrace.class, true, tmlDateTime, tmls);
    }

    /* Ctor 8 */
    public TmlTrace(Class<?> lgClass, Template tmlDateTime, Template... tmls) {
        this(lgClass, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 9 */
    public TmlTrace(Class<?> lgClass, Template... tmls) {
        this(lgClass, new TmlTimestamp(), Arrays.asList(tmls));
    }

    /* Ctor 10 */
    public TmlTrace(Template tmlDateTime, Template... tmls) {
        this(TmlTrace.class, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 11 */
    public TmlTrace(Template... tmls) {
        this(TmlTrace.class, new TmlTimestamp(), Arrays.asList(tmls));
    }

    /* Ctor 12 */
    public TmlTrace(Class<?> lgClass, Template tmlDateTime, String msg) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 13 */
    public TmlTrace(Class<?> lgClass, String msg) {
        this(lgClass, new TmlTimestamp(), new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 14 */
    public TmlTrace(Template tmlDateTime, String msg) {
        this(TmlTrace.class, tmlDateTime, msg);
    }

    /* Ctor 15 */
    public TmlTrace(String msg) {
        this(TmlTrace.class, new TmlTimestamp(), msg);
    }

    /* Ctor 16 */
    public TmlTrace(Class<?> lgClass, Template tmlDateTime, String msg, List<Object> params) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 17 */
    public TmlTrace(Class<?> lgClass, String msg, List<Object> params) {
        this(lgClass, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 18 */
    public TmlTrace(String msg, List<Object> params) {
        this(TmlTrace.class, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 19 */
    public TmlTrace(Class<?> lgClass, Template tmlDateTime, String msg, Object... params) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 20 */
    public TmlTrace(Class<?> lgClass, String msg, Object... params) {
        this(lgClass, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 21 */
    public TmlTrace(Template tmlDateTime, String msg, Object... params) {
        this(TmlTrace.class, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 22 */
    public TmlTrace(String msg, Object... params) {
        this(TmlTrace.class, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    @Override
    public String create() {
        return new TmlEntry(lgClass, useFullName, Level.TRACE, tmlDateTime, tmls).create();
    }

    @Override
    public Level level() {
        return Level.TRACE;
    }
}
