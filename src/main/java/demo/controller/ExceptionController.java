package demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionController implements ErrorController {

	private static final String PATH = "/error";
	private boolean debug = true;

	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping(value = PATH)
	public ModelAndView error(HttpServletRequest request, HttpServletResponse response) {
		// Appropriate HTTP response code (e.g. 404 or 500) is automatically set
		// by Spring.
		// Here we just define response body.

		ModelAndView error = new ModelAndView("error");
		error.addObject("errorJson", new ErrorJson(response.getStatus(),
				getErrorAttributes(request, debug)));

		return error;

	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request,
			boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(
				request);
		return errorAttributes.getErrorAttributes(requestAttributes,
				includeStackTrace);
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

	public class ErrorJson {

		public Integer status;
		public String error;
		public String message;
		public String timeStamp;
		public String trace;

		public ErrorJson(int status, Map<String, Object> errorAttributes) {
			this.status = status;
			this.error = (String) errorAttributes.get("error");
			this.message = (String) errorAttributes.get("message");
			this.timeStamp = errorAttributes.get("timestamp").toString();
			this.trace = (String) errorAttributes.get("trace");
		}

	}

}
