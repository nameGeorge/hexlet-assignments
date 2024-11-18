@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <h2>Users List</h2>
    <a href="/users/build" class="btn btn-primary mb-3">New User</a>
    <ul class="list-group">
        @for(var user : page.getUsers())
            <li class="list-group-item">
                <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
            </li>
        @endfor
    </ul>
`
)
