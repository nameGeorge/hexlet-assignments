package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            int pageNum = ctx.queryParamAsClass("page", Integer.class)
                    .getOrDefault(1);
            int resultsNum = ctx.queryParamAsClass("per", Integer.class)
                    .getOrDefault(5);
            int end = pageNum * resultsNum;
            int begin = pageNum * resultsNum - resultsNum;
            ctx.json(USERS.subList(begin, end));
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}