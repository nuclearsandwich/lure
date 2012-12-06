package sparktest;

import static spark.Spark.*;
import spark.*;

public class SparkTest {
	public static void main(String[] args) {
		get(new Route("/hello") {
			public Object handle(Request req, Response resp) {
				resp.status(200);
				return "Hello, " + req.queryParams("name") + "!";
			}
		});
	}
}
