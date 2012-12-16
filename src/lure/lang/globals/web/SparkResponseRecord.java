package lure.lang.globals.web;

import lure.lang.Record;
import lure.lang.Function;
import spark.Request;
import spark.Response;

public class SparkResponseRecord extends Record {
  private Response response;

  public SparkResponseRecord(Response resp) {
    response = resp;

    /* Defined fields */

    /* response.body("Hello");         sets content to Hello */
    _fieldSet("body", new Function() {
      public Object call(Object arg) {
        response.body((String)arg);
        return arg;
      }
    });

    /* response.header("FOO", "bar");  sets header FOO with value bar */
    _fieldSet("header", new Function() {
      public Object call(Object arg1, Object arg2) {
        response.header((String)arg1, (String)arg2);
        return arg2;
      }
    });

    /* response.raw();                 raw response handed in by Jetty */
    _fieldSet("raw", new Function() {
      public Object call() {
        return response.raw();
      }
    });

    /* response.redirect("/example");  browser redirect to /example */
    _fieldSet("redirect", new Function() {
      public Object call(Object arg) {
        response.redirect((String)arg);
        return arg;
      }
    });

    /* response.status(401);           set status code to 401 */
    _fieldSet("status", new Function() {
      public Object call(Object arg) {
        response.status((Integer)arg);
        return arg;
      }
    });

    /* response.type("text/xml");      set content type to text/xml */
    _fieldSet("type", new Function() {
      public Object call(Object arg) {
        response.type((String)arg);
        return arg;
      }
    });
  }
}
