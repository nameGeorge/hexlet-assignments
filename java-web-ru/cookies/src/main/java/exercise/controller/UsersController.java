package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) throws Exception {
        var firstName = ctx.formParam("firstName");
        System.out.println("create firstName: " + firstName);
        var lastName = ctx.formParam("lastName");
        System.out.println("create lastName: " + lastName);
        var email = ctx.formParam("email");
        System.out.println("create email: " + email);
        var password = ctx.formParam("password");
        System.out.println("create password: " + password);
        var token = Security.generateToken();

        var user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);

        System.out.println("User saved: " + user);

        ctx.cookie("token", token);
        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }

    public static void show(Context ctx) throws Exception {
        var token = ctx.cookie("token");
        var id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User not found"));

        System.out.println("User found: " + user);

        if (StringUtils.equals(token, user.getToken())) {
            var page = new UserPage(user);

            ctx.render("users/show.jte", model("page", page));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
