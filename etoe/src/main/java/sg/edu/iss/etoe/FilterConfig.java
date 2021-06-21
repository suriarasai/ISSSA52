package sg.edu.iss.etoe;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sg.edu.iss.etoe.filter.LogFilter;

@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<LogFilter> filterRegistrationBean() {
		FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
		LogFilter loggingFilter = new LogFilter();

		registrationBean.setFilter(loggingFilter);

		return registrationBean;
	}

}
