@import exercise.dto.posts.PostsPage

@param PostsPage page

@template.layout.page(
page = page,
content = @`
    <main>
        <div>
            @if(!page.getPosts().isEmpty())
                @for(var post : page.getPosts())
                    <div>
                        <h2>${post.getName()}</h2>
                        <p>${post.getBody()}</p>
                    </div>
                @endfor
            @endif
        </div>
    </main>
`
)