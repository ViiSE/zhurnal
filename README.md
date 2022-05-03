[![Zhurnal-CI](https://github.com/ViiSE/zhurnal/actions/workflows/main.yml/badge.svg)](https://github.com/ViiSE/zhurnal/actions/workflows/main.yml)
[![codecov](https://codecov.io/gh/ViiSE/zhurnal/branch/main/graph/badge.svg?token=0RUR4S52NJ)](https://codecov.io/gh/ViiSE/zhurnal)

[![Stena-CI Deploy](https://github.com/ViiSE/zhurnal/actions/workflows/deploy.yml/badge.svg)](https://github.com/ViiSE/zhurnal/actions/workflows/deploy.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.viise.zhurnal/zhurnal.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.viise.zhurnal/zhurnal/)


# zhurnal
Micro object-oriented library for logging

## Get

#### Maven
```xml
<dependency>
    <groupId>ru.viise.zhurnal</groupId>
    <artifactId>zhurnal</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Gradle
```groovy
implementation 'ru.viise.zhurnal:zhurnal:1.0.0'
```

## Why
In the Java world, there are major logging libraries: [logback](https://logback.qos.ch), [log4j](https://logging.apache.org/log4j/2.x/), [slf4j](https://www.slf4j.org). 
The principle of operation is the same for everyone. In order to start logging, it is necessary to define an object of 
the log class in the destination class. Here is an example `slf4j` log:

```java
private static final Logger log = LoggerFactory.getLogger(MyClass.java);
```

The [lombok](https://projectlombok.org) library simplifies writing, just define the `@Slf4j` annotation on the logged class:
```java
@Slf4j
public class MyClass {
    
}
```

Using the `log` object, you can log:
```java
@Slf4j
public class MyClass {
    
    public void hello() {
        log.info("Hello!");
    }
}
```

To configure the library, it is necessary to define configuration files in xml format in the classpath. They can be
configured, for example, outputting logs to a file, displaying logs, etc.

This approach motivates to use logging inside the business logic, which is not good, because. it's pretty strong clogs
the code and is not suitable for the OOP principle.

The `zhurnal` library, on the other hand, supports OOP principles, is lightweight, and easy to use.

## Advantages
- Lightweight;
- Ease of use: patterns for `INFO`, `WARN`, `ERROR`, `TRACE`, `DEBUG` level logs are available out of the box, there are
  no configuration files, it is enough to define an object and use it;
- Strict log template: due to the severity of the log template and the possibility of their concatenation, logs made
  using the `zhurnal` library are quite easy to parse, for example, using `regex` or `grok`;
- Support for ELK logs out of the box;
- Support for OOP principles: for use, you do not need to define a static object, for logging it is enough to apply the
  decorator pattern for the class being logged.

## Functions
- Logs can be output to standard output, transmitted over TCP or UDP, or to a file. There is also support for outputting
  logs to several sources;
- Limiting the output of logs by levels;
- Support for passing objects to the log message as a pattern `{}` (As in `Slf4j`);
- Support for [ELK logs](#elk logs).

## Main interfaces
The `Log` interface is required to display logs. Depending on the implementation, logs can be output to standard output,
over TCP or UDP, or to a file. The interface in the `print` method takes a `Template` interface object as a parameter.
This interface creates a final log that will be output to the source.

## Examples
Let's define an object of the `LgConsole` class that will output the log to the standard output stream:
```java
import ru.viise.zhurnal.Log;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.lg.LgConsole;

public class ExampleClass {

    public void exampleMethod() {
        Log<Template> log = new LgConsole(); // (1)
    }
}
```
To display the `INFO` level log, pass an object of the `TmlInfo` class to the `print` method:
```java
import ru.viise.zhurnal.Log;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.lg.LgConsole;
import ru.viise.zhurnal.tml.TmlInfo;

public class ExampleClass {

    public void exampleMethod() {
        Log<Template> log = new LgConsole(); // (1)

        Template tml = new TmlInfo("Hello, {}!", "world"); // (2)
        log.print(tml);                                    // (2)
    }
}
```
As a result, we get the following log:

`[LEVEL INFO] [TIMESTAMP 2022-04-22T23:31:01.581] [CLASS ru.viise.zhurnal.tml.TmlInfo] [MESSAGE Hello, log!]`

To define `WARN`, `ERROR`, `TRACE`, `DEBUG` level logs, use `TmlWarn`, `TmlError`, `TmlTrace`, `TmlDebug` respectively:
```java
import ru.viise.zhurnal.Log;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.lg.LgConsole;
import ru.viise.zhurnal.tml.*;

public class ExampleClass {

    public void exampleMethod() {
        Log<Template> log = new LgConsole(); // (1)

        Template tmlWarn = new TmlWarn("Hello, {}!", "world");   // (3)
        Template tmlError = new TmlError("Hello, {}!", "world"); // (3)
        Template tmlTrace = new TmlTrace("Hello, {}!", "world"); // (3)
        Template tmlDebug = new TmlDebug("Hello, {}!", "world"); // (3)

        log.print(tmlWarn);                                      // (3)
        log.print(tmlError);                                     // (3)
        log.print(tmlTrace);                                     // (3)
        log.print(tmlDebug);                                     // (3)
    }
}
```
To limit logs by level, use `LgLimited`:
```java
import ru.viise.zhurnal.Level;
import ru.viise.zhurnal.Log;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.TemplateLeveled;
import ru.viise.zhurnal.lg.*;
import ru.viise.zhurnal.tml.*;

public class ExampleClass {

    public void exampleMethod() {
        Log<TemplateLeveled> log = new LgLimited(         // (4)
                new LgConsole(),
                Level.INFO, Level.ERROR
        );

        log.print(new TmlInfo("Hello, {}!", "world"));    // (4)
        log.print(new TmlWarn("Warning!"));               // (4)
        log.print(new TmlError("Error :("));              // (4)
    }
}
```
In this case, the `WARN` level log will not be printed to standard output.

You can define your `Template`. For example:
```java
import ru.viise.zhurnal.Level;
import ru.viise.zhurnal.Log;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.lg.*;
import ru.viise.zhurnal.tml.*;

public class ExampleClass {

    public class MyTml implements Template {
        
        private final String name;
        
        public MyTml(String name) {
            this.name = name;
        }
        
        @Override
        public String create() {
            return new TmlMsg("Hello! Your name is {}", name).create();
        }
        
        @Override
        public Level level() {
            return Level.INFO;
        }
    }
    
    public void exampleMethod() {
        Log<Template> log = new LgConsole();
        log.print(new MyTml("Vitalya"));       // (5)
    }
}
```
As a result, we get:

`[MESSAGE Hello! Your name is Vitalya]`

The standard `TemplateLeveled` interface templates support concatenation of other templates:

```java
import ru.viise.zhurnal.Log;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.lg.LgConsole;
import ru.viise.zhurnal.tml.*;

public class ExampleClass {

    public void exampleMethod() {
        Log<Template> log = new LgConsole();
        log.print(new TmlInfo(
                ExampleClass.class,
                new Template[]{
                        new TmlMsg("Hello, {}!", "world"),
                        new TmlDuration(200L, TimeUnit.MILLISECONDS),
                        new TmlSql(0, "OK", "Select: done!")
                }
        ));
    }
}
```
We get this log:

`[LEVEL INFO] [TIMESTAMP 2022-04-23T00:32:56.382] [CLASS ru.viise.zhurnal.tml.ExampleClass] [MESSAGE Hello, world!] [DURATION <VALUE:200> <UNIT:MILLISECONDS>] [SQL <ERROR_CODE:0> <SQL_STATE:OK> <MESSAGE:Select: done!>]`

## ELK logs
`zhurnal` support ELK logs. The required templates are contained in the `ru.viise.zhurnal.tml.elk` package.

To define a standard ELK log, use `TmlElkStd`:

```java
import ru.viise.zhurnal.Level;
import ru.viise.zhurnal.Log;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.lg.*;
import ru.viise.zhurnal.tml.*;
import ru.viise.zhurnal.tml.elk.TmlElkStd;

public class ExampleClass {

    public void exampleMethod() {
        Log<Template> log = new LgConsole();
        log.print(new TmlElkStd(new TmlInfo("Hello, {}!", "world")));  // (6)
    }
}
```
The end result is a log like this:
```json
{
  "level": "INFO",
  "thread_name": "main",
  "logger_name": "ru.viise.zhurnal.tml.TmlInfo",
  "message": "Hello, world!",
  "timestamp": "2022-04-23T00:00:20.069"
}
```
You can also, for example, add information about `HTTP` to the log:
```java
import ru.viise.zhurnal.HttpStatus;
import ru.viise.zhurnal.Level;
import ru.viise.zhurnal.Log;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.lg.*;
import ru.viise.zhurnal.tml.*;
import ru.viise.zhurnal.tml.elk.*;

public class ExampleClass {

    public void exampleMethod() {
        Log<Template> log = new LgConsole();
        Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "world"));

        log.print(new TmlElkHttp(        // (7)
                tmlElk,
                new TmlHttp(HttpMethod.GET, "/users/1", HttpStatus.OK))
        );
    }
}
```
The end result is a log like this:
```json
{
  "http_method": "GET",
  "http_endpoint": "/users/1",
  "level": "INFO",
  "thread_name": "main",
  "http_status": "200 OK",
  "logger_name": "ru.viise.zhurnal.tml.TmlInfo",
  "message": "Hello, world!",
  "timestamp": "2022-04-23T00:12:15.839"
}
```
You can add another stack trace:
```java
import ru.viise.zhurnal.HttpStatus;
import ru.viise.zhurnal.Level;
import ru.viise.zhurnal.Log;
import ru.viise.zhurnal.Template;
import ru.viise.zhurnal.lg.*;
import ru.viise.zhurnal.tml.*;
import ru.viise.zhurnal.tml.elk.*;

public class ExampleClass {

    public void exampleMethod() {
        Log<Template> log = new LgConsole();
        Template tmlElk = new TmlElkStd(new TmlInfo("Hello, {}!", "world"));

        Template tmlHttp = new TmlElkHttp(
                tmlElk,
                new TmlHttp(HttpMethod.GET, "/users/1", HttpStatus.INTERNAL_SERVER_ERROR)
        );
        
        log.print(new TmlElkThrowable(   // (8)
                tmlHttp,
                new TmlThrowable(new Throwable()))
        );
    }
}
```
Result:
```json
{
  "http_method": "GET",
  "http_endpoint": "/users/1",
  "level": "INFO",
  "thread_name": "main",
  "http_status": "500 Internal Server Error",
  "logger_name": "ru.viise.zhurnal.tml.TmlInfo",
  "stack_trace": "java.lang.Throwable\n\tat ru.viise.zhurnal.tml.ExampleClass.exampleMethod(ExampleClass.java:11)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)...",
  "message": "Hello, log!",
  "throwable_message": "null",
  "timestamp": "2022-04-23T00:15:23.657"
}
```
