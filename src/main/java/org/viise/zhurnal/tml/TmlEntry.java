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

package org.viise.zhurnal.tml;

import org.viise.zhurnal.Level;
import org.viise.zhurnal.Template;
import org.viise.zhurnal.TemplateLeveled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TmlEntry implements TemplateLeveled {

    private final Class<?> lgClass;
    private final Boolean useFullName;
    private final Level lvl;
    private final Template tmlTimestamp;
    private final List<Template> tmls;

    /* Ctor 1*/
    public TmlEntry(Class<?> lgClass, Boolean useFullName, Level lvl, Template tmlTimestamp, List<Template> tmls) {
        this.lgClass = lgClass;
        this.useFullName = useFullName;
        this.lvl = lvl;
        this.tmlTimestamp = tmlTimestamp;
        this.tmls = tmls;
    }

    /* Ctor 2 */
    public TmlEntry(Class<?> lgClass, Boolean useFullName, Level lvl, Template tmlTimestamp, Template... tmls) {
        this(lgClass, useFullName, lvl, tmlTimestamp, Arrays.asList(tmls));
    }

    /* Ctor 3 */
    public TmlEntry(Class<?> lgClass, Boolean useFullName, Level lvl, Template tmlTimestamp, String msg) {
        this(lgClass, useFullName, lvl, tmlTimestamp, new TmlMsg(msg));
    }

    /* Ctor 4 */
    public TmlEntry(Class<?> lgClass, Level lvl, Template tmlTimestamp, List<Template> tmls) {
        this(lgClass, true, lvl, tmlTimestamp, tmls);
    }

    /* Ctor 5 */
    public TmlEntry(Class<?> lgClass, Level lvl, List<Template> tmls) {
        this(lgClass, true, lvl, new TmlTimestamp(), tmls);
    }

    /* Ctor 6 */
    public TmlEntry(Level lvl, List<Template> tmls) {
        this(TmlEntry.class, true, lvl, new TmlTimestamp(), tmls);
    }

    /* Ctor 7 */
    public TmlEntry(Level lvl, Template tmlTimestamp, List<Template> tmls) {
        this(TmlEntry.class, true, lvl, tmlTimestamp, tmls);
    }

    /* Ctor 8 */
    public TmlEntry(Class<?> lgClass, Level lvl, Template tmlTimestamp, Template... tmls) {
        this(lgClass, lvl, tmlTimestamp, Arrays.asList(tmls));
    }

    /* Ctor 9 */
    public TmlEntry(Class<?> lgClass, Level lvl, Template... tmls) {
        this(lgClass, lvl, new TmlTimestamp(), Arrays.asList(tmls));
    }

    /* Ctor 10 */
    public TmlEntry(Level lvl, Template tmlTimestamp, Template... tmls) {
        this(TmlEntry.class, lvl, tmlTimestamp, Arrays.asList(tmls));
    }

    /* Ctor 11 */
    public TmlEntry(Level lvl, Template... tmls) {
        this(TmlEntry.class, lvl, new TmlTimestamp(), Arrays.asList(tmls));
    }

    /* Ctor 12 */
    public TmlEntry(Class<?> lgClass, Level lvl, Template tmlTimestamp, String msg) {
        this(lgClass, lvl, tmlTimestamp, new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 13 */
    public TmlEntry(Class<?> lgClass, Level lvl, String msg) {
        this(lgClass, lvl, new TmlTimestamp(), new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /* Ctor 14 */
    public TmlEntry(Level lvl, Template tmlTimestamp, String msg) {
        this(TmlEntry.class, lvl, tmlTimestamp, msg);
    }

    /* Ctor 15 */
    public TmlEntry(Level lvl, String msg) {
        this(TmlEntry.class, lvl, new TmlTimestamp(), msg);
    }

    /* Ctor 16 */
    public TmlEntry(Class<?> lgClass, Level lvl, Template tmlTimestamp, String msg, List<Object> params) {
        this(lgClass, lvl, tmlTimestamp, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 17 */
    public TmlEntry(Class<?> lgClass, Level lvl, String msg, List<Object> params) {
        this(lgClass, lvl, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 18 */
    public TmlEntry(Level lvl, String msg, List<Object> params) {
        this(TmlEntry.class, lvl, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 19 */
    public TmlEntry(Class<?> lgClass, Level lvl, Template tmlTimestamp, String msg, Object... params) {
        this(lgClass, lvl, tmlTimestamp, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 20 */
    public TmlEntry(Class<?> lgClass, Level lvl, String msg, Object... params) {
        this(lgClass, lvl, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 21 */
    public TmlEntry(Level lvl, Template tmlTimestamp, String msg, Object... params) {
        this(TmlEntry.class, lvl, tmlTimestamp, new Template[] { new TmlMsg(msg, params) });
    }

    /* Ctor 22 */
    public TmlEntry(Level lvl, String msg, Object... params) {
        this(TmlEntry.class, lvl, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    @Override
    public String create() {
        StringBuilder sb = new StringBuilder(new TmlLevel(lvl).create())
                .append(" ")
                .append(tmlTimestamp.create())
                .append(" ")
                .append(new TmlClass(lgClass, useFullName).create());
        tmls.forEach(tml -> sb.append(" ").append(tml.create()));
        return sb.toString();
    }

    @Override
    public Level level() {
        return lvl;
    }
}
