package exercise;

import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {

        // BEGIN
        var app = Javalin.create(javalinConfig -> {
            javalinConfig.bundledPlugins.enableDevLogging();
        });
        List<String> phones = Data.getPhones();
        List<String> domains = Data.getDomains();

        app.get("/phones", context -> context.json(phones));
        app.get("/domains", context -> context.json(domains));
        return app;
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
