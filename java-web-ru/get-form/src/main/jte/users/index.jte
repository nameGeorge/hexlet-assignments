@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

<form action="/users" method="get" class="mb-3">
    <div class="form-group">
        <input type="search" class="form-control" name="term" value="${page.getTerm()}" placeholder="Search by first name">
    </div>
    <button type="submit" class="btn btn-primary mt-2">Search</button>
</form>
<ul class="list-group">
    @for (User user : page.getUsers())
        <li class="list-group-item">
            <strong>${user.getFirstName()} ${user.getLastName()}</strong><br>
            <a href="mailto:${user.getEmail()}">${user.getEmail()}</a>
        </li>

    @endfor
    @if (page.getUsers().isEmpty())
        <li class="list-group-item text-muted">No users found</li>
    @endif
</ul>