package lure.lang.globals.web;

import static lure.lang.globals.Web.wrapRequestObject;
import static lure.lang.globals.Web.wrapResponseObject;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class SetPort extends lure.lang.Function {
  public Object call(Object port) {
    Spark.setPort((Integer)port);
    return port;
  }
}
