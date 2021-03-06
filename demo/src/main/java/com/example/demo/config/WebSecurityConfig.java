// package com.example.demo.config;

// import com.example.demo.security.JwtAuthenticationFilter;

// import org.apache.catalina.filters.CorsFilter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;

// import lombok.extern.slf4j.Slf4j;

// @EnableWebSecurity
// @Slf4j
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//     @Autowired
//     private JwtAuthenticationFilter jwtAuthenticationFilter;

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.cors()
//         .and()
//         .csrf().disable()
//         .httpBasic().disable()
//         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//         .and()
//         .authorizeRequests().antMatchers("/", "/auth/**").permitAll()
//         .anyRequest().authenticated();
        
//         http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);

//     }
// }

package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http ???????????? ??????
		http.cors() // WebMvcConfig?????? ?????? ?????????????????? ?????? cors ??????.
				.and()
				.csrf()// csrf??? ?????? ???????????? ???????????? disable
						.disable()
				.httpBasic()// token??? ??????????????? basic ?????? disable
						.disable()
				.sessionManagement()  // session ????????? ????????? ??????
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests() // /??? /auth/** ????????? ?????? ????????? ???.
						.antMatchers("/", "/auth/**").permitAll()
				.anyRequest() // /??? /auth/**????????? ?????? ????????? ?????? ?????????.
						.authenticated();

		// filter ??????.
		// ??? ??????????????????
		// CorsFilter ????????? ??????
		// jwtAuthenticationFilter ????????????.
		http.addFilterAfter(
						jwtAuthenticationFilter,
						CorsFilter.class
		);
	}
}
