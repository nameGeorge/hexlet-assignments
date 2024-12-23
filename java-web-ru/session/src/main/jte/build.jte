@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page

<!-- BEGIN -->
@template.layout.page(
content = @`
    <main>
        <div>
            @if(page.getError() != null)
                <div>
                    <p>${page.getError()}</p>
                </div>
            @endif
            <form action="${NamedRoutes.loginPath()}" method="post">
                <input type="text" placeholder="name" name="name" />
                <input type="password" placeholder="password" name="password" />
                <input type="submit" value="Войти" />
            </form>
        </div>
    </main>
`
)