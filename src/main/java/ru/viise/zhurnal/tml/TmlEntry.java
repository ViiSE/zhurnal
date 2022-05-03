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

package ru.viise.zhurnal.tml;

import ru.viise.zhurnal.Level;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.TemplateLeveled;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Entry template.
 * Entry template is a basic leveled template implementation.
 */
public final class TmlEntry implements TemplateLeveled {

    /**
     * Classname of template.
     */
    private final Class<?> lgClass;

    /**
     * Use fully name of {@link #lgClass} or not.
     */
    private final Boolean useFullName;

    /**
     * {@link Level} of template.
     */
    private final Level lvl;

    /**
     * Timestamp of template.
     */
    private final Template tmlTimestamp;

    /**
     * The list of templates. These templates will print in the entry template in order in which they were specified.
     */
    private final List<Template> tmls;

    /**
     * Ctor 1.
     * @param lgClass Classname of template.
     * @param useFullName Use fully name of {@link #lgClass} or not.
     * @param lvl {@link Level} of template.
     * @param tmlTimestamp Timestamp of template.
     * @param tmls The list of templates.
     */
    public TmlEntry(Class<?> lgClass, Boolean useFullName, Level lvl, Template tmlTimestamp, List<Template> tmls) {
        this.lgClass = lgClass;
        this.useFullName = useFullName;
        this.lvl = lvl;
        this.tmlTimestamp = tmlTimestamp;
        this.tmls = tmls;
    }

    /**
     * Ctor 2.
     * @param lgClass Classname of template.
     * @param useFullName Use fully name of {@link #lgClass} or not.
     * @param lvl {@link Level} of template.
     * @param tmlTimestamp Timestamp of template.
     * @param tmls The array of templates.
     */
    public TmlEntry(Class<?> lgClass, Boolean useFullName, Level lvl, Template tmlTimestamp, Template... tmls) {
        this(lgClass, useFullName, lvl, tmlTimestamp, Arrays.asList(tmls));
    }

    /**
     * Ctor 3.
     * @param lgClass Classname of template.
     * @param useFullName Use fully name of {@link #lgClass} or not.
     * @param lvl {@link Level} of template.
     * @param tmlTimestamp Timestamp of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     *
     * @see TmlMsg
     */
    public TmlEntry(Class<?> lgClass, Boolean useFullName, Level lvl, Template tmlTimestamp, String msg) {
        this(lgClass, useFullName, lvl, tmlTimestamp, new TmlMsg(msg));
    }

    /**
     * Ctor 4. {@link #useFullName} is {@code true}.
     * @param lgClass Classname of template.
     * @param lvl {@link Level} of template.
     * @param tmlTimestamp Timestamp of template.
     * @param tmls The list of templates.
     */
    public TmlEntry(Class<?> lgClass, Level lvl, Template tmlTimestamp, List<Template> tmls) {
        this(lgClass, true, lvl, tmlTimestamp, tmls);
    }

    /**
     * Ctro 5. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now.
     * @param lgClass Classname of template.
     * @param lvl {@link Level} of template.
     * @param tmls The list of templates.
     */
    public TmlEntry(Class<?> lgClass, Level lvl, List<Template> tmls) {
        this(lgClass, true, lvl, new TmlTimestamp(), tmls);
    }

    /**
     * Ctor 6. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now. {@link #lgClass} is
     * {@code TmlEntry.class}.
     * @param lvl {@link Level} of template.
     * @param tmls The list of templates.
     */
    public TmlEntry(Level lvl, List<Template> tmls) {
        this(TmlEntry.class, true, lvl, new TmlTimestamp(), tmls);
    }

    /**
     * Ctor 7. {@link #useFullName} is {@code true}. {@link #lgClass} is {@code TmlEntry.class}.
     * @param lvl {@link Level} of template.
     * @param tmlTimestamp Timestamp of template.
     * @param tmls The list of templates.
     */
    public TmlEntry(Level lvl, Template tmlTimestamp, List<Template> tmls) {
        this(TmlEntry.class, true, lvl, tmlTimestamp, tmls);
    }

    /**
     * Ctor 8. {@link #useFullName} is {@code true}.
     * @param lgClass Classname of template.
     * @param lvl {@link Level} of template.
     * @param tmlTimestamp Timestamp of template.
     * @param tmls The array of templates.
     */
    public TmlEntry(Class<?> lgClass, Level lvl, Template tmlTimestamp, Template... tmls) {
        this(lgClass, lvl, tmlTimestamp, Arrays.asList(tmls));
    }

    /**
     * Ctor 9. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now.
     * @param lgClass Classname of template.
     * @param lvl {@link Level} of template.
     * @param tmls The array of templates.
     */
    public TmlEntry(Class<?> lgClass, Level lvl, Template... tmls) {
        this(lgClass, lvl, new TmlTimestamp(), Arrays.asList(tmls));
    }

    /**
     * Ctor 10. {@link #useFullName} is {@code true}. {@link #lgClass} is {@code TmlEntry.class}.
     * @param lvl {@link Level} of template.
     * @param tmlTimestamp Timestamp of template.
     * @param tmls The array of templates.
     */
    public TmlEntry(Level lvl, Template tmlTimestamp, Template... tmls) {
        this(TmlEntry.class, lvl, tmlTimestamp, Arrays.asList(tmls));
    }

    /**
     * Ctor 11. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now. {@link #lgClass} is
     * {@code TmlEntry.class}.
     * @param lvl {@link Level} of template.
     * @param tmls The array of templates.
     */
    public TmlEntry(Level lvl, Template... tmls) {
        this(TmlEntry.class, lvl, new TmlTimestamp(), Arrays.asList(tmls));
    }

    /**
     * Ctor 12. {@link #useFullName} is {@code true}.
     * @param lgClass Classname of template.
     * @param lvl {@link Level} of template.
     * @param tmlTimestamp Timestamp of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     *
     * @see TmlMsg
     */
    public TmlEntry(Class<?> lgClass, Level lvl, Template tmlTimestamp, String msg) {
        this(lgClass, lvl, tmlTimestamp, new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /**
     * Ctor 13. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now.
     * @param lgClass Classname of template.
     * @param lvl {@link Level} of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     *
     * @see TmlMsg
     */
    public TmlEntry(Class<?> lgClass, Level lvl, String msg) {
        this(lgClass, lvl, new TmlTimestamp(), new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /**
     * Ctor 14. {@link #useFullName} is {@code true}. {@link #lgClass} is {@code TmlEntry.class}.
     * @param lvl {@link Level} of template.
     * @param tmlTimestamp Timestamp of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     *
     * @see TmlMsg
     */
    public TmlEntry(Level lvl, Template tmlTimestamp, String msg) {
        this(TmlEntry.class, lvl, tmlTimestamp, msg);
    }

    /**
     * Ctor 15. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now. {@link #lgClass} is
     * {@code TmlEntry.class}.
     * @param lvl {@link Level} of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     *
     * @see TmlMsg
     */
    public TmlEntry(Level lvl, String msg) {
        this(TmlEntry.class, lvl, new TmlTimestamp(), msg);
    }

    /**
     * Ctor 16. {@link #useFullName} is {@code true}.
     * @param lgClass Classname of template.
     * @param lvl {@link Level} of template.
     * @param tmlTimestamp Timestamp of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlEntry(Class<?> lgClass, Level lvl, Template tmlTimestamp, String msg, List<Object> params) {
        this(lgClass, lvl, tmlTimestamp, new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Ctor 17. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now.
     * @param lgClass Classname of template.
     * @param lvl {@link Level} of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlEntry(Class<?> lgClass, Level lvl, String msg, List<Object> params) {
        this(lgClass, lvl, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Ctor 18. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now. {@link #lgClass} is
     * {@code TmlEntry.class}.
     * @param lvl {@link Level} of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlEntry(Level lvl, String msg, List<Object> params) {
        this(TmlEntry.class, lvl, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Ctor 19. {@link #useFullName} is {@code true}.
     * @param lgClass Classname of template.
     * @param lvl {@link Level} of template.
     * @param tmlTimestamp Timestamp of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlEntry(Class<?> lgClass, Level lvl, Template tmlTimestamp, String msg, Object... params) {
        this(lgClass, lvl, tmlTimestamp, new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Ctor 20. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now.
     * @param lgClass Classname of template.
     * @param lvl {@link Level} of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlEntry(Class<?> lgClass, Level lvl, String msg, Object... params) {
        this(lgClass, lvl, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Ctor 21. {@link #useFullName} is {@code true}. {@link #lgClass} is {@code TmlEntry.class}.
     * @param lvl {@link Level} of template.
     * @param tmlTimestamp Timestamp of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlEntry(Level lvl, Template tmlTimestamp, String msg, Object... params) {
        this(TmlEntry.class, lvl, tmlTimestamp, new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Ctor 22. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now. {@link #lgClass} is
     * {@code TmlEntry.class}.
     * @param lvl {@link Level} of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlEntry(Level lvl, String msg, Object... params) {
        this(TmlEntry.class, lvl, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Creating entry template.
     * Examples:
     * <pre> {@code
     * String tml1 = new TmlEntry(Level.INFO, "Hello, {}!", "log").create();
     * // This code creating template:
     * // [LEVEL INFO] [TIMESTAMP 2022-03-31T02:09:59.441] [CLASS ru.viise.zhurnal.tml.TmlEntry] [MESSAGE Hello, log!]
     *
     * String tml2 = new TmlEntry(Level.INFO, new Template[] { new TmlMsg("Hello, log!") }).create();
     * } </pre>
     *
     * @return template as a String.
     */
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

    /**
     * Level of template.
     *
     * @return {@link #lvl}.
     */
    @Override
    public Level level() {
        return lvl;
    }
}
