@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello Hexlet!</title>
</head>
<body>
<main>
    <h1>Привет, Хекслет!</h1>
    @if(page.getName() != null)
        Добро пожаловать, ${String.valueOf(page.getName())}.
        Чтобы разлогиниться, удалите куку JSESSIONID из браузера
        <div>
            <form action="${NamedRoutes.logoutPath()}" method="post">
                <button type="submit">Выйти</button>
            </form>
        </div>
    @else
        <div>
            <form action="${NamedRoutes.buildSessionPath()}" method="get">
                <button type="submit">Войти</button>
            </form>
        </div>
    @endif
</main>
</body>
</html>