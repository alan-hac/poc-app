package br.com.falconer.component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.com.falconer.dto.Identification;
import br.com.falconer.entity.User;
import br.com.falconer.repository.UserRepository;

@Component
public class IdentificationComponent {

	@Autowired
	private ChatComponent chatComponent;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private UserRepository userRepository;
	
	public User getUser(String email) throws UnsupportedEncodingException {
		return this.recoverUserFromCookie(email);
	}

	public User setUser(Identification identification) throws UnsupportedEncodingException {
		User user = userRepository.findById(identification.getEmail())
				.orElse(new User(identification.getEmail(), identification.getName(), new Date(), new Date()));
		user.setLastLogin(new Date());
		response.addCookie(this.getCookie(user));
		chatComponent.initChat(user);
		return userRepository.save(user);
	}
	
	private User recoverUserFromCookie(String email) throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(email))
			return new User();
		User user = userRepository.findById( URLDecoder.decode(email, "UTF-8")).orElse(new User());
		chatComponent.recuperaChat(user);
		return user;
	}
	
	private Cookie getCookie(User user) throws UnsupportedEncodingException {
		Cookie cookie = new Cookie("myappuserid", URLEncoder.encode(user.getEmail(), "UTF-8"));
		cookie.setHttpOnly(true);
		cookie.setMaxAge(-1);
		cookie.setPath("/");
		return cookie;
	}
}
