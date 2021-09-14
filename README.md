## Модули

- **app** - Главный модуль, содержащий Application класс и в котором происходит настройка Dagger.
- **buildSrc** - Специфичный модуль со списком зависимостей ([документация](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources)).
- **modules/core-di** - Интерфейсы для компонентов Dagger.
- **modules/core-entity** - Entity из Clean Architecture.
- **modules/core-feature** - Общий код для фич модулей (в основном работа с MVVM, Dagger). Общие Views при этом идут в **modules/core-uikit**.
- **modules/core-network** - Сетевой слой (Retrofit).
- **modules/core-persistence** - Код, взаимодействующий с постоянной памятью (базы данных, SharedPreferences).
- **modules/core-repositories** - Repositories из Clean Architecture.
- **module/core-testutils** - Утилиты для тестирования.
- **modules/core-uikit** - Общие UI элементы для всех модулей.
- **modules/core-utils** - Утилиты для удобной работы с Kotlin.
- **modules/features/main** - Корневой фрагмент, который живет на протяжении жизни Activity.

Также присутствуют модули с постфиксом `-impl` - про них расписано в секции "Разрешение цикличных зависимостей".

## Архитектура

В основе архитектуры используется **Clean Architecture**. 

Для слоя представления используется подход **MVVM** (Model-View-ViewModel).

Вместо **UseCase** используется **Interactor** (объединение UseCase).

С целью упрощения архитектуры, разрешены обращения непосредственно в **Repository**, минуя **Interactor**. В случае необходимости **Interactor** создается внутри фичи, наиболее близко подходящей по значению.

### Модели

Имеются свои модели для разных слоев:

* доменный слой (core-entity): **Xxx**;
* сетевой слой (core-network): **XxxResponse** / **XxxRequest**;
* локальное хранилище (core-persistence): **XxxEntity**.

В feature-модулях используются модели из доменного слоя.

## Разрешение цикличных зависимостей

**Проблема:** модуль **A** хочет использовать у себя классы из модуля **B**, а модуль **B** - классы из модуля **A**. Если написать в *build.gradle* файле одного модуля `implementation {второй модуль}`, а у второго модуля наоборот, то будет цикличная зависимость и проект не сможет скомпилироваться.

**Решение:** Вводятся модули с постфиксом `-impl`. Если модуль **X** имеет "брата" с таким постфиксом, то это означает, что модуль разделен на абстрацию и реализацию. Все модули, кроме модуля **app**, имеют право подключать в качестве зависимости только модуль **X** (модуль абстракции). В модуле **X** зачастую будут лежать интерфейсы, которые имеют реализацию в **X-impl** модуле. Модуль **app** имеет доступ ко всем модулям, ибо он содержит логику по "связыванию" абстрактных модулей и модулей реализации.

## Навигация

Приложение выполнено с подходом **Single Activity**. 

Для навигации не используются сторонние библиотеки (вроде Cicerone или Navigation Architecture Component).

Все фрагменты организуются в дерево, где у каждого фрагмента есть ровно один "родительский" фрагмент (вроде фрагмента **Main** - у него непосредственным "родителем" является Activity). Каждый фрагмент знает только про себя и непосредственные "дочерние" фрагменты. 

Если фрагменту надо сообщить что-то "внуку", то он сообщает это "сыну", а уже "сын" вызывает метод своего "сына". 

Если фрагменту **A** надо сообщить что-то "отцу" (фрагмент **B**), то во фрагменте **A** создается интерфейс **Listener**, где перечисляются методы callback'а (например, `onAuthSuccess`). Фрагмент **B** должен реализовать данный интерфейс **A.Listener**. В свое время фрагмент **A** должен в `onAttach` подключиться к фрагменту B через методы `requireListener` или `getListener`. Если фрагмент **A** является диалогом, то фрагмент **B** при создании фрагмента **A** дополнительно указать у последнего target фрагмент (себя).

## Detekt

В приложении настроен статический анализатор кода (линтер) - **Detekt**. При выполнении `git push` автоматически запустится Gradle задача по выявлению ошибок в code style. Если будет найдена хотя бы одна ошибка, то она будет отображена на экране, а сам `git push` будет отменен.

Чтобы вручную запустить detekt, надо выполнить команду: `./gradlew detekt`.

Чтобы не запускать detekt при пуше, необходимо использовать `git push --no-verify`.

Чтобы убрать проверку detekt в конкретном месте кода, надо написать `@Suppress("{имя ошибки}")`, например `@Suppress("MagicNumber")`.