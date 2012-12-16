package lure.lang.globals;

import lure.lang.globals.web.*;
import spark.Request;
import spark.Response;

public class Web extends lure.lang.Record {
  public Web() {
    _fieldSet("get", new Get());
    _fieldSet("put", new Put());
    _fieldSet("post", new Post());
    _fieldSet("delete", new Delete());
    _fieldSet("setPort", new SetPort());
  }

  public static Object wrapRequestObject(Request req) {
    return new SparkRequestRecord(req);
  }

  public static Object wrapResponseObject(Response resp) {
    return new SparkResponseRecord(resp);
  }
}
