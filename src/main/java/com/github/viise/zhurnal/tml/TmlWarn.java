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

import com.github.viise.zhurnal.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TmlWarn implements Template {

    private final Class<?> lgClass;
    private final Boolean useFullName;
    private final Template tmlDateTime;
    private final List<Template> tmls;

    /* Ctor 1*/
    public TmlWarn(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, List<Template> tmls) {
        this.lgClass = lgClass;
        this.useFullName = useFullName;
        this.tmlDateTime = tmlDateTime;
        this.tmls = tmls;
    }

    /* Ctor 2 */
    public TmlWarn(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, Template... tmls) {
        this(lgClass, useFullName, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 3 */
    public TmlWarn(Class<?> lgClass, Boolean useFullName, Template tmlDateTime, String msg) {
        this(lgClass, useFullName, tmlDateTime, new TmlMsg(msg));
    }

    /* Ctor 4 */
    public TmlWarn(Class<?> lgClass, Template tmlDateTime, List<Template> tmls) {
        this(lgClass, false, tmlDateTime, tmls);
    }

    /* Ctor 5 */
    public TmlWarn(Class<?> lgClass, List<Template> tmls) {
        this(lgClass, false, new TmlDateTime(), tmls);
    }

    /* Ctor 6 */
    public TmlWarn(List<Template> tmls) {
        this(TmlWarn.class, false, new TmlDateTime(), tmls);
    }

    /* Ctor 7 */
    public TmlWarn(Template tmlDateTime, List<Template> tmls) {
        this(TmlWarn.class, false, tmlDateTime, tmls);
    }

    /* Ctor 8 */
    public TmlWarn(Class<?> lgClass, Template tmlDateTime, Template... tmls) {
        this(lgClass, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 9 */
    public TmlWarn(Class<?> lgClass, Template... tmls) {
        this(lgClass, new TmlDateTime(), Arrays.asList(tmls));
    }

    /* Ctor 10 */
    public TmlWarn(Template tmlDateTime, Template... tmls) {
        this(TmlWarn.class, tmlDateTime, Arrays.asList(tmls));
    }

    /* Ctor 11 */
    public TmlWarn(Template... tmls) {
        this(TmlWarn.class, new TmlDateTime(), Arrays.asList(tmls));
    }

    /* Ctor 12 */
    public TmlWarn(Class<?> lgClass, Template tmlDateTime, String msg) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 13 */
    public TmlWarn(Class<?> lgClass, String msg) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 14 */
    public TmlWarn(Template tmlDateTime, String msg) {
        this(TmlWarn.class, tmlDateTime, msg);
    }

    /* Ctor 15 */
    public TmlWarn(String msg) {
        this(TmlWarn.class, new TmlDateTime(), msg);
    }

    /* Ctor 16 */
    public TmlWarn(Class<?> lgClass, Template tmlDateTime, String msg, List<Object> params) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 17 */
    public TmlWarn(Class<?> lgClass, String msg, List<Object> params) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 18 */
    public TmlWarn(String msg, List<Object> params) {
        this(TmlWarn.class, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 19 */
    public TmlWarn(Class<?> lgClass, Template tmlDateTime, String msg, Object... params) {
        this(lgClass, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 20 */
    public TmlWarn(Class<?> lgClass, String msg, Object... params) {
        this(lgClass, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 21 */
    public TmlWarn(Template tmlDateTime, String msg, Object... params) {
        this(TmlWarn.class, tmlDateTime, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 22 */
    public TmlWarn(String msg, Object... params) {
        this(TmlWarn.class, new TmlDateTime(), new Template[] { new TmlMsg(msg, params) });
    }

    @Override
    public String create() {
        return new TmlEntry(lgClass, useFullName, new TmlTypeWarn(), tmlDateTime, tmls).create();
    }
}
