package net.webapp.user.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import net.webapp.user.Authority;
import net.webapp.user.User;
import net.webapp.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	@PostConstruct
	public void init() {
		LOG.info(getClass().getSimpleName() + ".start()");
	}

	@PreDestroy
	public void destroy() {
		LOG.info(getClass().getSimpleName() + ".destroy()");
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public String getUserById(@PathVariable Long userId, ModelMap model) {
		User user = service.getUserById(userId);
		List<Authority> authorities = service.getAuthoritiesForUser(userId);
		model.addAttribute("user", user);
		model.addAttribute("authorities", authorities);
		model.addAttribute("method", "POST");
		return "user";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, params = {"new"})
	public String getNewUser(ModelMap model) {
		model.addAttribute("user", new User());
		model.addAttribute("method", "PUT");
		return "user";
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public String createUser(User user) {
		service.saveUser(user);
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.POST)
	public String saveUser(User user) {
		service.saveUser(user);
		return "redirect:/users";
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long userId) {
		service.deleteUser(userId);
		return "redirect:/users";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getAllUsers(@RequestParam(required=false) String ps, @RequestParam(required=false) String pn, ModelMap model) {
		Page<User> users = service.getAllUsers(new PageRequest(pn == null ? 0 : Integer.valueOf(pn), ps == null ? 10 : Integer.valueOf(ps)));
		model.addAttribute("page", users);
		return "users";
	}

	// @ModelAttribute("allSecurityRoles")
	// public List<SecurityRole> populateSecurityRoles() {
	// return Arrays.asList(SecurityRole.ALL);
	// }

}
