package controllers;

import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.Context;

/**
 * Implement authorization for this system.
 * 
 * @author Philip Johnson
 */
public class Secured extends Security.Authenticator {

  /**
   * Make sure the user is logged in.
   * @param ctx The context.
   * @return The email address of the logged in user.
   */
  @Override
  public String getUsername(Context ctx) {
    return ctx.session().get("email");
  }

  /**
   * Go to login page if unauthorized.
   * @param context The context.
   * @return The login page.
   */
  @Override
  public Result onUnauthorized(Context context) {
    return redirect(routes.Application.login()); 
  }
}