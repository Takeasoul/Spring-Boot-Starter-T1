# Spring Boot HTTP Logging Starter

**Spring Boot HTTP Logging Starter** — это модуль для автоматического логирования всех входящих и исходящих HTTP-запросов и ответов в вашем Spring Boot приложении. Логирование включает метод запроса, URL, заголовки, параметры, код ответа, а также время обработки запроса.

## Функциональность

Этот стартер автоматически добавляет фильтр, который логирует HTTP-запросы и ответы, прошедшие через ваше Spring Boot приложение. Он поддерживает различные уровни логирования и позволяет настраивать формат вывода логов (в текстовом или JSON формате).

Основные возможности:
- Логирование метода запроса (GET, POST и др.)
- Логирование URL и параметров запроса
- Логирование заголовков запроса и ответа
- Логирование статуса HTTP-ответа
- Логирование времени обработки запроса
- Поддержка форматов вывода логов (json или text)
- Возможность изменения уровня логирования (DEBUG, INFO, WARNING, ERROR)

## Сборка и запуск

### 1. Для начала клонируйте репозиторий с GitHub:

```bash
git clone https://github.com/Takeasoul/Spring-Boot-Starter-T1.git
```

### 2. Перейдите в корневую директорию проекта и выполните команду:

```bash
mvn clean install
```

### 3. После успешной сборки, перейдите в папку AppForSpringBootStarter:

```bash
cd AppForSpringBootStarter
```

### 4. Пропишите необходимые конфигурации в application.yml или application.properties.
#### Пример конфигурации в application.yml:

```yml
api-logging:
  http:
    level: DEBUG   # Уровень логирования: DEBUG, INFO, WARNING, ERROR
    format: json   # Формат логов: json или text
```

#### Пример конфигурации в application.properties:
```properties
api-logging.http.level=DEBUG
api-logging.http.format=json
```

### 4. Запустите приложение с использованием Gradle:

```bash
./gradlew bootRun
```


## Использование
После добавления зависимости и настройки конфигурации, стартер автоматически начнет логировать все HTTP-запросы и ответы. В логах будут отображаться следующие данные:

- Метод запроса (GET, POST и т.д.)
- URI запроса
- Заголовки запроса
- Параметры запроса
- Код ответа
- Заголовки ответа
- Время обработки запроса

#### Пример логов в JSON формате:

```json
{
  "method": "GET",
  "uri": "/api/v1/users",
  "headers": {
    "Authorization": "Bearer token123",
    "Content-Type": "application/json"
  },
  "params": {
    "page": ["1"],
    "size": ["10"]
  },
  "status": 200,
  "duration": "123ms"
} 
```
#### Пример логов в TEXT формате:
```text
Incoming Request: {method=GET, uri=/api/v1/users, headers={Authorization=Bearer token123, Content-Type=application/json}, params={page=[1], size=[10]}}
Outgoing Response: {status=200, headers={Content-Type=application/json}, duration=123ms}
```
## Конфигурация

| Параметр                    | Описание                                          | Пример   |
|-----------------------------|---------------------------------------------------|----------|
| `api-logging.http.level`     | Уровень логирования (`DEBUG`, `INFO`, `WARNING`, `ERROR`) | `DEBUG`  |
| `api-logging.http.format`    | Формат логов (`json` или `text`)                  | `json`   |

### Описание параметров

- **`api-logging.http.level`** — Уровень логирования. Возможные значения:
    - `DEBUG`: Логирование на уровне отладки.
    - `INFO`: Логирование общей информации.
    - `WARNING`: Логирование предупреждений.
    - `ERROR`: Логирование ошибок.

- **`api-logging.http.format`** — Формат логов. Возможные значения:
    - `json`: Логи будут выводиться в формате JSON.
    - `text`: Логи будут выводиться в виде обычного текста.

## Тестирование

Проект включает unit-тесты для проверки корректности работы логирования HTTP-запросов и ответов. Тесты покрывают следующие сценарии:

- Логирование запроса и ответа в текстовом формате.
- Логирование запроса и ответа в формате JSON.
- Проверка корректности обработки запросов с различными HTTP-методами (GET, POST и др.).
- Тестирование обработки и логирования ошибок (коды ответа `4xx` или `5xx`).

## Запуск тестов

Для запуска тестов используйте команду:

```bash
mvn test
```