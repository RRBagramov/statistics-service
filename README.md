# Инструкция по сборке, запуску и проверке.
### Что вам потребуется
* JDK 8 и выше
* Maven 3.0+
* pgAdmin - PostgreSQL
### Загрузка проекта
* Загрузите и распакуйте архив с кодом этого проекта, либо кнонируйте из репозитория с помощью Git: git clone https://github.com/RobertBag/statistics-service.git
### Создание базы данных
1. Откройте pgAdmin
2. В верхней панели выберете пункт Правка->Новый объект->Новая база данных
3. Впишите в имя "statistics_db" и нажмите "ок"
4. Выберете в списке баз данных, находящимся в браузере объектов, созданную бд
5. На панели инструментов выберете "Выполнить пользовательские SQL-скрипты"
6. Скопируйте скрипты из файлов "data_model_main_create.sql" и "statistics_db_public_visits_inserts.sql", находящихся в statistics-app-bl\src\main\resources\db, и выполните нажатием кнопки f5
### Конфигурация проекта
* В директории statistics-service\src\main\resources откройте файл "application.properties" текстовым редактором и в строках "spring.datasource.username" и "spring.datasource.password" напишите корректные данные 
### Сборка и запуск
1. Откройте командную строку 
2. Перейдите в главную директорию проекта командой cd, указав путь
3. И выполните команду mvn package && java -jar target/statistics-app-bl-1.0.jar
### Проверка
Проверку производить по описанию [API](https://github.com/RobertBag/statistics-service/wiki/API).
Тестовые данные с 2018-03-08 по 2018-03-09
