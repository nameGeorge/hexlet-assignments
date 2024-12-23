@import exercise.dto.users.UsersPage
@param UsersPage page

<html>
<head>
    <title>Хекслет</title>
</head>
<body>
<h1>${page.getUsers().size()}</h1>
@if(page.getUsers().isEmpty())
    <p>Пока не добавлено ни одного пользователя</p>
@else
    @for(var user : page.getUsers())
        <div>
            <h2><a href="/users/${user.getId()}">${user.getFirstName()}</a></h2>
            <p>${user.getFirstName()}</p>
            <p>${user.getLastName()}</p>
        </div>
    @endfor
@endif
</body>
</html>