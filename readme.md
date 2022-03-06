# Задание 1. Библиотека
##### Автор: Витя Латышев
Задание является практикой и служит для освоения многослойной архитектурой, основных паттернов, работы с файлами и других особенностей Java.

![lib](https://sun1-17.userapi.com/1BYkR1ekQjibnfauWVCI7TVo1xr5yLbDZnmT-w/bi1BUwB6c8A.jpg "ожидание: Библиотека")
____
## Требования к проекту:

- #### Использовать возможности ООП:
    - Классы, наследование, полиморфизм, инкапсуляция.
    - Каждый класс должен иметь отражающее смысл название и информативный состав.
    - Наследование должно применяться только тогда, когда это имеет смысл.

- #### Оформление:
    - При кодировании должны быть использованы соглашения об оформлении кода java code convention.
    - Классы должны быть грамотно разложены по пакетам.
    - Консольное меню должно быть минимальным.

- #### Архитектура:
    -  Приложение должно быть в многослойной архитектуре (beans-классы сущностей, services-бизнес-логика, dao или repository-доступ к данным (сначала - эмуляция базы данных коллекцией, потом файлами), controller- диспетчер, распределяющий запросы пользователя)

- #### Иерархия :
    - Написать иерархию классов по одному из предложенных проектов:
        - Социальная сеть
        - Аукцион
        - Блог
        - Интернет-магазин
        - Библиотека
        - Таск менеджер
        - Поликлиника
        - Новостной портал
        - LMS(learning management system)
        - Университет
        - Бронирование билетов
        - Туристические туры
        - Аналог Uber
        - Больница
        - Любой другой реалистичный проект

    - Entity-классы не следует наполнять методами, выполняющими функциональные действия
      (методами бизнес-логики, такими как вычисление, поиск и т.д.).
    - Нарисовать схему как можно более полную, со всеми необходимыми полями и методами, с количеством сущностей, покрывающих предметную область.
    - Реализуем в коде около 10 классов-бинов (в зависимости от задачи) необходимых для вычисления, описанного в условии. В классе 3-5 полей и методов, необходимых для вычисления, описанного в условии.
    - Задача учебная, вы должны на ней просто прощупать руками принципы ООП и разные типы связей между объектами (а после и разные возможности java). Нет необходимости писать много похожего кода для этого.

- #### Требования и условия задачи:
    - Если необходимо, расширяем условие задачи, когда это требуется для реализации описанных требований к проекту. Лучше меньше да лучше. Сначала реализуем минимальный работающий функционал (MVP), потом, если есть время – наращиваем функционал.
    - Переопределить методы equals(), hashCode(), toString().
    - Добавить в классы проекта необходимые конструкторы
    - Добавить в классы проекта геттеры и сеттеры.
    - Использовать в классах проекта правильные модификаторы доступа.
    - Реализовать в проекте счѐтчик созданных объектов
    - Реализовать в проекте интерфейсы и абстрактные классы
    - Реализовать в проекте модуля custom exceptions и их корректную обработку
    - Посмотреть в исходном коде Java как реализованы коллекции (интерфейсы, абстрактные классы, - конкретные - классы). Перенять элементы архитектуры коллекций для своих проектов. Реализовать List, Set, Map в проекте
    - Реализовать Comparable и Comparator в проекте
    - Использовать в проекте классы даты и времени
    - Реализовать запись/чтение символьной и байтовой информации в файл в проекте
    - Реализовать сериализацию в проекте (иерархия, static, transient)
    - Реализовать перечисления в проекте
    - Реализовать параметризацию (generics) в проекте
    - Реализовать regexp в проекте
    - Реализовать функцию поиска по сущностям.
    - Делать валидацию всех данных
    - Добиться “защиты от дурака”, создать непадательное состояние проекта вне зависимости от любых вводимых данных - (корректных или не корректных)
    - Настроить IDE на GoogleStyle
    - Автоформатирование – необходимо во всех классах
    - Написать javadoc для классов из проекта
    - Сгенерировать javadoc в IDE для этих классов
    - Перед отправлением кода на проверку, устранять замечания утилит статического анализа кода (Code/Inspect code, Code/Analyze - code в Idea, SonarLint, FindBugs, PMD и др.)
    - Написать юнит-тесты с использованием Junit и Mockito
    - Проект должен быть размещен на github (gitlab)
    - В проекте необходимо использовать log4j для логирования, maven для билда
    - Реализовать логику логина в приложения под разными ролями (admin, user), с доступом к разному функционалу в - зависимости от роли
    - Программировать в интерфейсах
    - Избегать использовать статические методы (за исключением утилитных)
    - Изучить Appendix 1

____
    
# Литература:
- ### [Рефакторинг Улучшение существующего кода](https://docviewer.yandex.by/view/1492204582/?page=3&*=awHCY0%2F1WwDaMpm6LGkVqNwJtWh7InVybCI6Imh0dHBzOi8vZmt0cG0ucnUvZmlsZS82MS1mYXVsZXItcmVmYWN0b3JpbmctcmVmYWt0b3JpbmctdWx1Y3NlbmllLXN1c2hoZXN0dnV5dXNoaGVnby1rb2RhLnBkZiIsInRpdGxlIjoiNjEtZmF1bGVyLXJlZmFjdG9yaW5nLXJlZmFrdG9yaW5nLXVsdWNzZW5pZS1zdXNoaGVzdHZ1eXVzaGhlZ28ta29kYS5wZGYiLCJub2lmcmFtZSI6dHJ1ZSwidWlkIjoiMTQ5MjIwNDU4MiIsInRzIjoxNjQ2NDk4MDE0NTI5LCJ5dSI6IjE3MTkzMjMzNDE2MDU1NTI2OTAiLCJzZXJwUGFyYW1zIjoidG09MTY0NjQ5Nzg1NCZ0bGQ9YnkmbGFuZz1ydSZuYW1lPTYxLWZhdWxlci1yZWZhY3RvcmluZy1yZWZha3RvcmluZy11bHVjc2VuaWUtc3VzaGhlc3R2dXl1c2hoZWdvLWtvZGEucGRmJnRleHQ9JUQwJTlDJUQwJUIwJUQxJTgwJUQxJTgyJUQwJUI4JUQwJUJEJUQwJUIwKyVEMCVBNCVEMCVCMCVEMSU4MyVEMCVCQiVEMCVCNSVEMSU4MCVEMCVCMCslQzIlQUIlRDAlQTAlRDAlQjUlRDElODQlRDAlQjAlRDAlQkElRDElODIlRDAlQkUlRDElODAlRDAlQjglRDAlQkQlRDAlQjMlQzIlQkImdXJsPWh0dHBzJTNBLy9ma3RwbS5ydS9maWxlLzYxLWZhdWxlci1yZWZhY3RvcmluZy1yZWZha3RvcmluZy11bHVjc2VuaWUtc3VzaGhlc3R2dXl1c2hoZWdvLWtvZGEucGRmJmxyPTIxMDE1Jm1pbWU9cGRmJmwxMG49cnUmc2lnbj0yODQwMzcyOWY5MTdkZjI3MzhlYWU2ZjhkMmEyODU5YSZrZXlubz0wIn0%3D&lang=ru)
- ### [Паттерны объектно-оиентированного проектирования](https://vk.com/doc26879026_563356840?hash=645ddd8bf52f2e2144&dl=e734c134223f96bd8e)
- ### [Совершенный код. Практическое руководство по разработке ПО](https://docviewer.yandex.by/view/1492204582/?*=n9cLhVs7g%2B0tkuUgVyc8LoPYiC57InVybCI6Imh0dHBzOi8vZmt0cG0ucnUvZmlsZS84NC1zb3ZlcnNlbm55aS1rb2QucGRmIiwidGl0bGUiOiI4NC1zb3ZlcnNlbm55aS1rb2QucGRmIiwibm9pZnJhbWUiOnRydWUsInVpZCI6IjE0OTIyMDQ1ODIiLCJ0cyI6MTY0NjUwMDI2MjA0OCwieXUiOiIxNzE5MzIzMzQxNjA1NTUyNjkwIiwic2VycFBhcmFtcyI6InRtPTE2NDY1MDAyNTkmdGxkPWJ5Jmxhbmc9cnUmbmFtZT04NC1zb3ZlcnNlbm55aS1rb2QucGRmJnRleHQ9JUQwJUExJUQwJUJFJUQwJUIyJUQwJUI1JUQxJTgwJUQxJTg4JUQwJUI1JUQwJUJEJUQwJUJEJUQxJThCJUQwJUI5KyVEMCVCQSVEMCVCRSVEMCVCNC4rJUQwJTlGJUQxJTgwJUQwJUIwJUQwJUJBJUQxJTgyJUQwJUI4JUQxJTg3JUQwJUI1JUQxJTgxJUQwJUJBJUQwJUJFJUQwJUI1KyVEMSU4MCVEMSU4MyVEMCVCQSVEMCVCRSVEMCVCMiVEMCVCRSVEMCVCNCVEMSU4MSVEMSU4MiVEMCVCMiVEMCVCRSslRDAlQkYlRDAlQkUrJUQxJTgwJUQwJUIwJUQwJUI3JUQxJTgwJUQwJUIwJUQwJUIxJUQwJUJFJUQxJTgyJUQwJUJBJUQwJUI1KyVEMCU5RiVEMCU5RSZ1cmw9aHR0cHMlM0EvL2ZrdHBtLnJ1L2ZpbGUvODQtc292ZXJzZW5ueWkta29kLnBkZiZscj0yMTAxNSZtaW1lPXBkZiZsMTBuPXJ1JnNpZ249NjgzOTAwZTQ1MTEwNGZiMjgzNGFkZjRkNmE5MmEwYTAma2V5bm89MCJ9&amp;lang=ru)