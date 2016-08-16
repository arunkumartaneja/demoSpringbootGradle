package demo.controller;

import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import demo.dao.UserDao;

@Controller
public class HelloController {

	private static final Logger logger = LoggerFactory
			.getLogger(HelloController.class);

	@Autowired
	UserDao userDao;

	@RequestMapping(value = { "/", "/home" })
	public String index(Model model, HttpServletRequest request) {
		// return "Greetings from Spring Boot!";

		// slf4j logging example
		logger.info("HelloController");
//		logger.error("Message logged at ERROR level");
//		logger.warn("Message logged at WARN level");
//		logger.info("Message logged at INFO level");
//		logger.debug("Message logged at DEBUG level");

		Date start = new Date();
		Random rand = new Random();
		int name = rand.nextInt(5) + 1;
		System.out.println(" ## name ## " + name);
		userDao.getUserData(String.valueOf(name));
		System.out.println("##### Total Time ### : "
				+ (new Date().getTime() - start.getTime()));
		return "home";
	}

	@RequestMapping("/generateError")
	public String generateError(Model model, HttpServletRequest request) {
		// return "Greetings from Spring Boot!";
		int i = 4 / 0;
		System.out.println(i);
		return "index";
	}

	@RequestMapping("/hello")
	public String hello(Model model, HttpServletRequest request) {
		return "hello";
	}

	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		return "login";
	}
	
}