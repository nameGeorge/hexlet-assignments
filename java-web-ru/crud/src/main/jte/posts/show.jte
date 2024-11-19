@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
content = @`
    <h3>${page.getPost().getName()}</h3>
    <p>${page.getPost().getBody()}</p>
    <a href="${NamedRoutes.postsPath()}">Список постов</a>
`
)