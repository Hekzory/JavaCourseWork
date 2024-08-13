**Платная поликлиника | Курсовая Java, 2 курс 4 сем.** 
[![CodeQL](https://github.com/Hekzory/JavaCourseWork/actions/workflows/codeql.yml/badge.svg?branch=master)](https://github.com/Hekzory/JavaCourseWork/actions/workflows/codeql.yml) [![Java CI with Gradle](https://github.com/Hekzory/JavaCourseWork/actions/workflows/gradle.yml/badge.svg)](https://github.com/Hekzory/JavaCourseWork/actions/workflows/gradle.yml)
==================================================================

**Краткое описание проекта:**
-------------------

Проект представляет собой веб-сервис для произвольной платной поликлиники. 
Есть основной простой функционал - ведение мед. карты(сохранение данных о пациенте) и запись на приём
Репозиторий архивирован - курсовая сдана

**Tech Stack**
------------------

* **Backend**: Spring Boot, PostgreSQL
* **Frontend**: Thymeleaf, TailwindCSS, FeatherIcons

**Запуск**
------------------

Для запуска воспользуйтесь Docker Compose. Приложение запустится на localhost:8080.

```
docker-compose up
```

**Примечание**: На данный момент(11.05.24) можно запустить проект напрямую с помощью gradle bootRun, предварительно включив СУБД, но работа этого способа не поддерживается. 
В конце концов, Docker действительно удобен.

**Скриншоты**
--------------

![image](https://github.com/Hekzory/JavaCourseWork/assets/36638457/20c39abb-1af4-4383-9900-e731733ef4ea)
![image](https://github.com/Hekzory/JavaCourseWork/assets/36638457/af9fc00e-19fc-489e-b9fa-f08d7f70be84)
