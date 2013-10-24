package controllers;

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
   * Returns the home page. 
   * @return The resulting home page. 
   */
  public static Result index() {
    return ok(Index.render());
  }
  
  /**
   * Returns the login page. 
   * @return The resulting login page. 
   */
  public static Result login() {
    Form<LoginFormData> formData = Form.form(LoginFormData.class);
    return ok(Login.render(formData));
  }

  /**
   * Process a login form submission.
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
      flash("error", "Email or password not valid.");
      return badRequest(Login.render(formData));
    }
    else {
      session().clear();
      session("email", formData.get().email);
      return redirect(routes.Application.index());
    }
  }
  
  /**
   * Returns the profile page if the user is logged in.
   * @return The Page1.
   */
  @Security.Authenticated(Secured.class)
  public static Result profile() {
    return ok(Profile.render("Welcome to Protected."));
  }
}
