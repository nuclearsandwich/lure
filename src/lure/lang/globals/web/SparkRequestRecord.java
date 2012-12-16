package lure.lang.globals.web;

import lure.lang.Record;
import lure.lang.Function;
import spark.Request;

public class SparkRequestRecord extends Record {
  private Request request;

  public SparkRequestRecord(Request req) {
    request = req;
    /* Defined fields */

    /* body();   request body sent by the client */
    _fieldSet("body", new Function() {
      public Object call() {
        return request.body();
      }
    });

    /* contentLength();    length of request body */
    _fieldSet("contentLength", new Function() {
      public Object call() {
        return request.contentLength();
      }
    });

    /* contentType();   content type of request.body */
    _fieldSet("contentType", new Function() {
      public Object call() {
        return request.contentType();
      }
    });

    /* headers();            // the HTTP header list */
    _fieldSet("headers", new Function() {
      public Object call() {
        return request.headers();
      }

    /* headers("BAR");       // value of BAR header */
      public Object call(Object arg) {
        return request.headers((String)arg);
      }
    });

    /* attributes();         // the attributes list */
    _fieldSet("attributes", new Function() {
      public Object call() {
        return request.attributes();
      }
    });

    /* attribute("foo");     // value of foo attribute */
    _fieldSet("attribute", new Function() {
      public Object call(Object arg) {
        return request.attribute((String)arg);
      }

    /* attribute("A", "V");  // sets value of attribute A to V */
      public Object call(Object arg1, Object arg2) {
        request.attribute((String)arg1, (String)arg2);
        return arg2;
      }
    });

    /* host();               // "example.com" */
    _fieldSet("host", new Function() {
      public Object call() {
        return request.host();
      }
    });

    /* ip();                 // client IP address */
    _fieldSet("ip", new Function() {
      public Object call() {
        return request.ip();
      }
    });

    /* pathInfo();           // the path info */
    _fieldSet("pathInfo", new Function() {
      public Object call() {
        return request.pathInfo();
      }
    });

    /* port();               // the server port */
    _fieldSet("port", new Function() {
      public Object call() {
        return request.port();
      }
    });

    /* queryParams("FOO");   // value of FOO query param */
    _fieldSet("queryParams", new Function() {
      public Object call(Object arg1) {
        return request.queryParams((String)arg1);
      }

    /* queryParams();        // the query param list */
      public Object call() {
        return request.queryParams();
      }
    });

    /* raw();                // raw request handed in by Jetty */
    _fieldSet("raw", new Function() {
      public Object call() {
        return request.raw();
      }
    });

    /* requestMethod();      // The HTTP method (GET, ..etc) */
    _fieldSet("requestMethod", new Function() {
      public Object call() {
        return request.requestMethod();
      }
    });

    /* scheme();             // "http" */
    _fieldSet("scheme", new Function() {
      public Object call() {
        return request.scheme();
      }
    });

    /* url();                // "http://example.com/foo" */
    _fieldSet("url", new Function() {
      public Object call() {
        return request.url();
      }
    });

    /* userAgent();          // user agent */
    _fieldSet("userAgent", new Function() {
      public Object call() {
        return request.userAgent();
      }
    });
  }
}
