# zhurnal
Микро объектно-ориентированная библиотека для логирования.

## Подключение

#### С использованием Maven
```xml
<dependency>
    <groupId>ru.viise.zhurnal</groupId>
    <artifactId>zhurnal</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### С использованием Gradle
```groovy
implementation 'ru.viise.zhurnal:zhurnal:1.0.0'
```

## Для чего нужна эта библиотека?
В мире Java существуют основные библиотеки логирования: [logback](https://logback.qos.ch), [log4j](https://logging.apache.org/log4j/2.x/), [slf4j](https://www.slf4j.org). 
Принцип действия у всех одинаковый. Для того, чтобы начать логировать, необходимо в классе определить объект класса лога.
Вот пример лога `slf4j`:

```java
private static final Logger log = LoggerFactory.getLogger(MyClass.java);
```

Библиотека [lombok]() упрощает написание, достаточно определить аннотацию `@Slf4j` над логируемым классом:
```java
@Slf4j
public class MyClass {
    
}
```

При помощи объекта `log` можно осуществлять логирование:
```java
@Slf4j
public class MyClass {
    
    public void hello() {
        log.info("Hello!");
    }
}
```

Для настройки библиотеки необходимо определять в classpath файлы настроек в xml формате. В них можно настроить, 
например, вывод логов в файл, отображение логов и т.д. 

Данный подход мотивирует использовать логирование внутри бизнес логики, что не есть хорошо, т.к. это довольно сильно 
засоряет код и не подходит для принципа ООП.

Библиотека `zhurnal` наоборот же, поддерживает принципы ООП, легковесная, и проста в использовании.

## Преимущества
- Легковесная;
- Простота использования: из коробки доступны паттерны для логов уровня `INFO`, `WARN`, `ERROR`, `TRACE`, `DEBUG`, 
                          файлов настроек нет, достаточно определить объект и использовать;
- Строгий шаблон логов: из-за строгости шаблона логов и возможности их конкатенации логи, сделанные посредством 
                        библиотеки `zhurnal`, достаточно легко парсить, например, используя `regex` или `grok`;
- Поддержка ELK логов из коробки;
- Поддержка принципов ООП: для использования не нужно определять статичный объект, для логирования достаточно применить
                           паттерн декоратор для логируемого класса.

## Функции
- Логи можно выводить в стандартный поток вывода, передавать через TCP или UDP, или в файл. Также есть поддержка вывода 
  логов в несколько различных источников;
- Ограничение вывода логов по уровням;
- Поддержка передачи объектов в сообщение лога в виде паттерна `{}` (Как в `Slf4j`);
- Поддержка [ELK логов](#elk-логи).

## Основные интерфейсы
Интерфейс `Log` необходим для вывода логов. В зависимости от реализации логи могут выводится в стандартный поток вывода,
через TCP или UDP, или в файл. Интерфейс в методе `print` принимает в качестве параметра объект интерфейса
`Template`. Данный интерфейс создает конечный лог, который будет выводиться в источник. 

## Примеры
Определим объект класса `LgConsole`, который будет выводить лог в стандартный поток вывода:
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
Для вывода лога уровня `INFO` передадим в метод `print` объект класса `TmlInfo`:
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
В итоге получим такой лог:

`[LEVEL INFO] [TIMESTAMP 2022-04-22T23:31:01.581] [CLASS ru.viise.zhurnal.tml.TmlInfo] [MESSAGE Hello, log!]`

Для определения логов уровня `WARN`, `ERROR`, `TRACE`, `DEBUG` необходимо использовать `TmlWarn`, `TmlError`, 
`TmlTrace`, `TmlDebug` соответственно:
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
Для ограничения логов по уровню необходимо использовать `LgLimited`:
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
В этом случае лог уровня `WARN` не будет выведен в стандартном потоке выводе.

Можно определить свой `Template`. Например:
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
В итоге получим:

`[MESSAGE Hello! Your name is Vitalya]`

Стандартные шаблоны интерфейса `TemplateLeveled` поддерживают конкатенацию других шаблонов:

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
Получим такой лог:

`[LEVEL INFO] [TIMESTAMP 2022-04-23T00:32:56.382] [CLASS ru.viise.zhurnal.tml.ExampleClass] [MESSAGE Hello, world!] [DURATION <VALUE:200> <UNIT:MILLISECONDS>] [SQL <ERROR_CODE:0> <SQL_STATE:OK> <MESSAGE:Select: done!>]`

## ELK логи
`zhurnal` поддерживает ELK логи. Необходимые шаблоны содержатся в пакете `ru.viise.zhurnal.tml.elk`.

Для определения стандартного ELK лога необходимо использовать `TmlElkStd`:

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
В итоге получится такой лог:
```json
{
  "level": "INFO",
  "thread_name": "main",
  "logger_name": "ru.viise.zhurnal.tml.TmlInfo",
  "message": "Hello, world!",
  "timestamp": "2022-04-23T00:00:20.069"
}
```
Можно также, например, добавить в лог информацию об `HTTP`:
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
В итоге получится такой лог:
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
Можно добавить ещё stack trace:
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
Итог:
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
