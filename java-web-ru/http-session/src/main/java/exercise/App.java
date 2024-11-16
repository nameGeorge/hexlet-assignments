package exercise;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_PER_PAGE = 5;

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", App::handleGetUsers);
        // END

        return app;
    }

    private static void handleGetUsers(Context ctx) {
        int page = ctx.queryParam("page") != null ? Integer.parseInt(ctx.queryParam("page")) : DEFAULT_PAGE;
        int perPage = ctx.queryParam("per") != null ? Integer.parseInt(ctx.queryParam("per")) : DEFAULT_PER_PAGE;

        int fromIndex = (page - 1) * perPage;
        int toIndex = Math.min(fromIndex + perPage, USERS.size());

        if (fromIndex >= USERS.size()) {
            ctx.json(List.of());
        } else {
            List<Map<String, String>> usersPage = USERS.subList(fromIndex, toIndex);
            ctx.json(usersPage);
        }
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}}