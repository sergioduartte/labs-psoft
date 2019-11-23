package psoft.labdao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import psoft.labdao.filters.TokenFilter;

@SpringBootApplication
public class LabDaoApplication {

	@Bean
	public FilterRegistrationBean<TokenFilter> filterJwt() {
		FilterRegistrationBean<TokenFilter> filterRB = new FilterRegistrationBean<TokenFilter>();
		filterRB.setFilter(new TokenFilter());
		filterRB.addUrlPatterns("/api/products","/auth/users");
		return filterRB;
	}


	public static void main(String[] args) {
		SpringApplication.run(LabDaoApplication.class, args);
	}

}
