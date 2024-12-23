@import gg.jte.Content
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Javalin Site</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Home</a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/users">Users</a>
            </li>
        </ul>
    </div>
</header>
<main role="main" class="container">
    ${content}
</main>
<footer class="footer mt-auto py-3 bg-light">
    <div class="container">
        <span class="text-muted">Follow me on <a href="https://gitlab.com/yourprofile">GitLab</a></span>
    </div>
</footer>
</body>
</html>