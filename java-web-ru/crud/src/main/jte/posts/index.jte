@import exercise.dto.posts.PostsPage

@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(content = @`
    <div>

        @for( var post : page.getPosts())
            <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>

        @endfor

        <nav aria-label="Page navigation example">

            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="?page=${page.getPageNumber() ==1 ? 1 : page.getPageNumber() - 1}">Previous</a></li>

                <li class="page-item"><a class="page-link" href="?page=${page.getPageNumber() == 1 ? 2 : page.getPageNumber() + 1}">Next</a></li>
            </ul>
        </nav>

    </div>
`)