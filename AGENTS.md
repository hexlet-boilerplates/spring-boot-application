# AGENTS.md

Приложение на Spring Boot и Gradle. Код лежит в `src/main`, тесты в `src/test`.

## Команды

| Команда | Что делает |
| --- | --- |
| `make setup` | Обновляет Gradle wrapper и собирает проект |
| `make install` | Собирает дистрибутив и ставит git-хуки |
| `make app` | Запускает приложение с профилем `dev` |
| `make reload-classes` | Пересобирает классы при правках, вместе с `make app` даёт перезагрузку |
| `make build` | Собирает проект с нуля |
| `make test` | Запускает тесты |
| `make lint` | Проверяет форматирование через Spotless и компилирует код и тесты |
| `make lint-fix` | Исправляет форматирование |

## Проверки

`make lint` также проверяет Markdown (`rumdl`), опечатки (`typos`) и workflow GitHub Actions (`jactionlint`). `make install` ставит git-хуки hk, перед коммитом они запускают `make lint`, перед push `make test`.

Перед завершением задачи запусти `make lint` и `make test`, обе команды должны проходить.

## Окружение

Инструменты и их версии задаёт `mise.toml`, ставит их `mise install`.
