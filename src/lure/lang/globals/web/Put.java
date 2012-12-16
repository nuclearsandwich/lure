package lure.lang.globals.web;

import static lure.lang.globals.Web.wrapRequestObject;
import static lure.lang.globals.Web.wrapResponseObject;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class Put extends lure.lang.Function {
  public Object call(Object routeName, final Object responseHandler) {
    Spark.put(new Route((String)routeName) {
      public Object handle(Request req, Response resp) {
        try {
          return ((lure.lang.Function)responseHandler).call(
            wrapRequestObject(req),
            wrapResponseObject(resp));
        } catch (lure.lang.ArityException e) {
          e.printStackTrace(); return null;
        }
      }
    });
    return routeName;
  }
}
