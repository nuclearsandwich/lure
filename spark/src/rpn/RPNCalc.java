package rpn;

import java.util.ArrayList;

import spark.*;
import static spark.Spark.*;

public class RPNCalc {
	private static String headerHtml = "<!DOCTYPE html>\n" +
		"<html>\n" +
		"  <head><title>Web RPN Calculator</title></head>\n" +
		"  <body>\n" +
		"    <h1>Web RPN Calculator</h1>\n";

	private static String formHtml = "    <form action=\"/rpn\" method=\"get\">\n" +
		"      <input type=\"text\" value=\"\" name=\"expr\">\n" +
		"      <input type=\"submit\">\n" +
		"    </form>\n";

	private static String footerHtml = "  </body>\n</html>\n";

	public static void Calc(String operation, ArrayList<Integer> arguments) {
		int answer = -1;
		if (operation.equals("+")) {
			answer = 0;
			for (Integer i : arguments) {
				answer += i;
			}
		} else if (operation.equals("-")) {
			answer = arguments.remove(0);
			for (Integer i : arguments) {
				answer -= i;
			}
		} else if (operation.equals("*")) {
			answer = 1;
			for (Integer i : arguments) {
				answer *= i;
			}
		} else if (operation.equals("/")) {
			answer = arguments.remove(0);
			for (Integer i : arguments) {
				answer /= i;
			}
		}
		arguments.clear();
		arguments.add(answer);
		return;
	}

	public static String Eval(String input) {
		ArrayList<String> values;
		ArrayList<Integer> arguments = new ArrayList();
		values = new ArrayList<String>();
		for (String s : input.split(" ")) {
			values.add(s);
		}
		while (values.size() != 0) {
			String top = values.remove(0);
			if (top.equals("+") || top.equals("-") ||
					top.equals("*") || top.equals("/")) {
				Calc(top, arguments);
			} else {
				arguments.add(Integer.parseInt(top));
			}
		}
		return arguments.get(0).toString();
	}


	public static void main(String[] args) {
		get(new Route("/calc") {
			public Object handle(Request req, Response resp) {
				resp.status(200);
				return headerHtml + formHtml + footerHtml;
			}
		});

		get(new Route("/rpn") {
			public Object handle(Request req, Response resp) {
				try {
					String result = Eval(req.queryParams("expr"));
					resp.status(200);
					return headerHtml + "<p>The result was " + result + "</p>\n" +
						formHtml + footerHtml;
				}
				catch (Exception e) {
					halt(500, "What the heck!?");
					return null;
				}
			}
		});
	}
}
