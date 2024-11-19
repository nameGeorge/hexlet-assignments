@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <a href="/articles">Все статьи</a>
            <h1>Создать новую статью</h1>

            @if(page.getErrors() != null)
                <ul>
                    @for(var validator : page.getErrors().values())
                        @for(var error : validator)
                            <li>${error.getMessage()}</li>
                        @endfor
                    @endfor
                </ul>
            @endif

            <form action="/articles" method="post">
                <div>
                    <label>
                        Название
                        <input type="text" name="title" value="${page.getTitle()}" />
                    </label>
                </div>
                <div>
                    <label>
                        Содержимое
                        <textarea name="content">${page.getContent()}</textarea>
                    </label>
                </div>
                <input type="submit" value="Создать статью" />
            </form>
        </main>
    </div>
`
)