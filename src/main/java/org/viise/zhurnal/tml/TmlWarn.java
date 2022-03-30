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

/**
 * Warn template.
 * Level of template is {@link Level#WARN}.
 */
public final class TmlWarn implements TemplateLeveled {

    /**
     * Classname of template.
     */
    private final Class<?> lgClass;

    /**
     * Use fully name of {@link #lgClass} or not.
     */
    private final Boolean useFullName;

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
     * @param tmlTimestamp Timestamp of template.
     * @param tmls The list of templates.
     */
    public TmlWarn(Class<?> lgClass, Boolean useFullName, Template tmlTimestamp, List<Template> tmls) {
        this.lgClass = lgClass;
        this.useFullName = useFullName;
        this.tmlTimestamp = tmlTimestamp;
        this.tmls = tmls;
    }

    /**
     * Ctor 2.
     * @param lgClass Classname of template.
     * @param useFullName Use fully name of {@link #lgClass} or not.
     * @param tmlTimestamp Timestamp of template.
     * @param tmls The array of templates.
     */
    public TmlWarn(Class<?> lgClass, Boolean useFullName, Template tmlTimestamp, Template... tmls) {
        this(lgClass, useFullName, tmlTimestamp, Arrays.asList(tmls));
    }

    /**
     * Ctor 3.
     * @param lgClass Classname of template.
     * @param useFullName Use fully name of {@link #lgClass} or not.
     * @param tmlTimestamp Timestamp of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     *
     * @see TmlMsg
     */
    public TmlWarn(Class<?> lgClass, Boolean useFullName, Template tmlTimestamp, String msg) {
        this(lgClass, useFullName, tmlTimestamp, new TmlMsg(msg));
    }

    /**
     * Ctor 4. {@link #tmlTimestamp} is now.
     * @param lgClass Classname of template.
     * @param useFullName Use fully name of {@link #lgClass} or not.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     *
     * @see TmlMsg
     */
    public TmlWarn(Class<?> lgClass, Boolean useFullName, String msg) {
        this(lgClass, useFullName, new TmlTimestamp(), new TmlMsg(msg));
    }

    /**
     * Ctor 5. {@link #tmlTimestamp} is now. {@link #lgClass} is {@code TmlInfo.class}.
     * @param useFullName Use fully name of {@link #lgClass} or not.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     *
     * @see TmlMsg
     */
    public TmlWarn(Boolean useFullName, String msg) {
        this(TmlWarn.class, useFullName, new TmlTimestamp(), new TmlMsg(msg));
    }

    /**
     * Ctor 6. {@link #tmlTimestamp} is now.
     * @param lgClass Classname of template.
     * @param useFullName Use fully name of {@link #lgClass} or not.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlWarn(Class<?> lgClass, Boolean useFullName, String msg, List<Object> params) {
        this(lgClass, useFullName, new TmlTimestamp(), new TmlMsg(msg, params));
    }

    /**
     * Ctor 7. {@link #tmlTimestamp} is now.
     * @param lgClass Classname of template.
     * @param useFullName Use fully name of {@link #lgClass} or not.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlWarn(Class<?> lgClass, Boolean useFullName, String msg, Object... params) {
        this(lgClass, useFullName, new TmlTimestamp(), new TmlMsg(msg, params));
    }

    /**
     * Ctor 8. {@link #tmlTimestamp} is now. {@link #lgClass} is {@code TmlInfo.class}.
     * @param useFullName Use fully name of {@link #lgClass} or not.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlWarn(Boolean useFullName, String msg, List<Object> params) {
        this(TmlWarn.class, useFullName, new TmlTimestamp(), new TmlMsg(msg, params));
    }

    /**
     * Ctor 9. {@link #tmlTimestamp} is now. {@link #lgClass} is {@code TmlInfo.class}.
     * @param useFullName Use fully name of {@link #lgClass} or not.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlWarn(Boolean useFullName, String msg, Object... params) {
        this(TmlWarn.class, useFullName, new TmlTimestamp(), new TmlMsg(msg, params));
    }

    /**
     * Ctor 10. {@link #useFullName} is {@code true}.
     * @param lgClass Classname of template.
     * @param tmlTimestamp Timestamp of template.
     * @param tmls The list of templates.
     */
    public TmlWarn(Class<?> lgClass, Template tmlTimestamp, List<Template> tmls) {
        this(lgClass, true, tmlTimestamp, tmls);
    }

    /**
     * Ctor 11. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now. {@link #lgClass} is
     * {@code TmlInfo.class}.
     * @param lgClass Classname of template.
     * @param tmls The list of templates.
     */
    public TmlWarn(Class<?> lgClass, List<Template> tmls) {
        this(lgClass, true, new TmlTimestamp(), tmls);
    }

    /**
     * Ctor 12. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now. {@link #lgClass} is
     * {@code TmlInfo.class}.
     * @param tmls The list of templates.
     */
    public TmlWarn(List<Template> tmls) {
        this(TmlWarn.class, true, new TmlTimestamp(), tmls);
    }

    /**
     * Ctor 13. {@link #useFullName} is {@code true}. {@link #lgClass} is {@code TmlInfo.class}.
     * @param tmlTimestamp Timestamp of template.
     * @param tmls The list of templates.
     */
    public TmlWarn(Template tmlTimestamp, List<Template> tmls) {
        this(TmlWarn.class, true, tmlTimestamp, tmls);
    }

    /**
     * Ctor 14. {@link #useFullName} is {@code true}.
     * @param lgClass Classname of template.
     * @param tmlTimestamp Timestamp of template.
     * @param tmls The array of templates.
     */
    public TmlWarn(Class<?> lgClass, Template tmlTimestamp, Template... tmls) {
        this(lgClass, tmlTimestamp, Arrays.asList(tmls));
    }

    /**
     * Ctor 15. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now.
     * @param lgClass Classname of template.
     * @param tmls The array of templates.
     */
    public TmlWarn(Class<?> lgClass, Template... tmls) {
        this(lgClass, new TmlTimestamp(), Arrays.asList(tmls));
    }

    /**
     * Ctor 16. {@link #useFullName} is {@code true}. {@link #lgClass} is {@code TmlInfo.class}.
     * @param tmlTimestamp Timestamp of template.
     * @param tmls The array of templates.
     */
    public TmlWarn(Template tmlTimestamp, Template... tmls) {
        this(TmlWarn.class, tmlTimestamp, Arrays.asList(tmls));
    }

    /**
     * Ctor 17. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now. {@link #lgClass} is
     * {@code TmlInfo.class}.
     * @param tmls The array of templates.
     */
    public TmlWarn(Template... tmls) {
        this(TmlWarn.class, new TmlTimestamp(), Arrays.asList(tmls));
    }

    /**
     * Ctor 18. {@link #useFullName} is {@code true}.
     * @param lgClass Classname of template.
     * @param tmlTimestamp Timestamp of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     *
     * @see TmlMsg
     */
    public TmlWarn(Class<?> lgClass, Template tmlTimestamp, String msg) {
        this(lgClass, tmlTimestamp, new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /**
     * Ctor 19. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now.
     * @param lgClass Classname of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     *
     * @see TmlMsg
     */
    public TmlWarn(Class<?> lgClass, String msg) {
        this(lgClass, new TmlTimestamp(), new Template[] { new TmlMsg(msg, new ArrayList<>()) });
    }

    /**
     * Ctor 20. {@link #useFullName} is {@code true}. {@link #lgClass} is {@code TmlInfo.class}.
     * @param tmlTimestamp Timestamp of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     *
     * @see TmlMsg
     */
    public TmlWarn(Template tmlTimestamp, String msg) {
        this(TmlWarn.class, tmlTimestamp, msg);
    }

    /**
     * Ctor 21. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now. {@link #lgClass} is
     * {@code TmlInfo.class}.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     *
     * @see TmlMsg
     */
    public TmlWarn(String msg) {
        this(TmlWarn.class, new TmlTimestamp(), msg);
    }

    /**
     * Ctor 22. {@link #useFullName} is {@code true}.
     * @param lgClass Classname of template.
     * @param tmlTimestamp Timestamp of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlWarn(Class<?> lgClass, Template tmlTimestamp, String msg, List<Object> params) {
        this(lgClass, tmlTimestamp, new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Ctor 23. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now.
     * @param lgClass Classname of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlWarn(Class<?> lgClass, String msg, List<Object> params) {
        this(lgClass, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Ctor 24. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now. {@link #lgClass} is
     * {@code TmlInfo.class}.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlWarn(String msg, List<Object> params) {
        this(TmlWarn.class, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Ctor 25. {@link #useFullName} is {@code true}.
     * @param lgClass Classname of template.
     * @param tmlTimestamp Timestamp of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlWarn(Class<?> lgClass, Template tmlTimestamp, String msg, Object... params) {
        this(lgClass, tmlTimestamp, new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Ctor 26. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now. {@link #lgClass} is
     * {@code TmlInfo.class}.
     * @param lgClass Classname of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlWarn(Class<?> lgClass, String msg, Object... params) {
        this(lgClass, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Ctor 27. {@link #useFullName} is {@code true}. {@link #lgClass} is {@code TmlInfo.class}.
     * @param tmlTimestamp Timestamp of template.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlWarn(Template tmlTimestamp, String msg, Object... params) {
        this(TmlWarn.class, tmlTimestamp, new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Ctor 28. {@link #useFullName} is {@code true}. {@link #tmlTimestamp} is now. {@link #lgClass} is
     * {@code TmlInfo.class}.
     * @param msg Message. Supports parameter substitution via {@code {}}.
     * @param params Params of message.
     *
     * @see TmlMsg
     */
    public TmlWarn(String msg, Object... params) {
        this(TmlWarn.class, new TmlTimestamp(), new Template[] { new TmlMsg(msg, params) });
    }

    /**
     * Creating warn template.
     * Examples:
     * <pre> {@code
     * String tml1 = new TmlWarn("Hello, {}!", "log").create();
     * // This code creating template:
     * // [LEVEL WARN] [TIMESTAMP 2022-03-31T02:09:59.441] [CLASS org.viise.zhurnal.tml.TmlEntry] [MESSAGE Hello, log!]
     *
     * String tml2 = new TmlWarn(new Template[] { new TmlMsg("Hello, log!") }).create();
     * String tml3 = new TmlWarn("Hello, log!");
     * } </pre>
     *
     * @return template as a String.
     */
    @Override
    public String create() {
        return new TmlEntry(lgClass, useFullName, Level.WARN, tmlTimestamp, tmls).create();
    }

    /**
     * Level of template.
     *
     * @return {@link Level#WARN}.
     */
    @Override
    public Level level() {
        return Level.WARN;
    }
}
