package sg.edu.iss.etoe.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		LOGGER.info("FILTER: Logging Request  {} : {}", request.getMethod(), request.getRequestURI());

		// Call next filter in the filter chain
		chain.doFilter(request, response);

		LOGGER.info("Logging Response :{}", response.getContentType());
	}
}
