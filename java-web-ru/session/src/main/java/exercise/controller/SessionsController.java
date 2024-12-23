package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void mainPage(Context ctx) {
        var currentUser = ctx.sessionAttribute("currentUser");
        var page = new MainPage(currentUser);
        ctx.render("index.jte", model("page", page));
    }

    public static void loginPage(Context ctx) {
        var currentUser = ctx.sessionAttribute("currentUser");
        var page = new LoginPage(String.valueOf(currentUser), null);
        ctx.render("build.jte", model("page", page));
    }

    public static void login(Context ctx) {
        var username = ctx.formParam("name");
        var password = Security.encrypt(ctx.formParam("password"));

        if (UsersRepository.existsByName(username)) {
            var user = UsersRepository.findByName(username).get();
            var userPassword = user.getPassword();
            if (userPassword.equals(password)) {
                ctx.sessionAttribute("currentUser", user.getName());
                ctx.redirect(NamedRoutes.rootPath());
            } else {
                var page = new LoginPage(username, "Wrong username or password");
                ctx.render("build.jte", model("page", page));
            }
        } else {
            var page = new LoginPage(username, "Wrong username or password");
            ctx.render("build.jte", model("page", page));
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}