package controllers;

import models.UserInfo;
import models.UserInfoDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.Index;
import views.html.Profile;
import views.html.Login;
import views.formdata.LoginFormData;
import play.mvc.Security;

/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {

  /**
   * Provides the Index page to logged in users. 
   * @return The Index page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result index() {
    UserInfo userInfo = UserInfoDB.getUser(request().username());
    Boolean isLoggedIn = (userInfo != null);
    return ok(Index.render("Home", isLoggedIn, userInfo));
  }
  
  /**
   * Provides the Login page to all users. 
   * @return The Login page. 
   */
  public static Result login() {
    UserInfo userInfo = UserInfoDB.getUser(request().username());
    Boolean isLoggedIn = (userInfo != null);
    Form<LoginFormData> formData = Form.form(LoginFormData.class);
    return ok(Login.render("Login", isLoggedIn, userInfo, formData));
  }

  /**
   * Processes a login form submission from any user. 
   * First we bind the HTTP POST data to an instance of StudentFormData.
   * The binding process will invoke the StudentFormData.validate() method.
   * If errors are found, re-render the page, displaying the error data. 
   * If errors not found, render the page with the good data. 
   * @return The index page with the results of validation. 
   */
  public static Result postLogin() {

    // Get the submitted form data from the request object, and run validation.
    Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();

    if (formData.hasErrors()) {
      flash("error", "Login credentials not valid.");
      return badRequest(Login.render("Login", false, null, formData));
    }
    else {
      session().clear();
      session("email", formData.get().email);
      return redirect(routes.Application.index());
    }
  }
  
  /**
   * Logs the user out and returns them to the Login page. 
   * @return A redirect to the Login page. 
   */
  public static Result logout() {
    session().clear();
    flash("success", "You've been logged out");
    return redirect(routes.Application.login());
  }
  
  /**
   * Provides the Profile page to logged in users. 
   * @return The Profile page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result profile() {
    UserInfo userInfo = UserInfoDB.getUser(request().username());
    Boolean isLoggedIn = (userInfo != null);
    return ok(Profile.render("Profile", isLoggedIn, userInfo));
  }
}
