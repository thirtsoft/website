package com.website.security;

import com.website.security.jwt.JwtAuthEntryPoint;
import com.website.security.jwt.JwtAuthTokenFilter;
import com.website.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //        registry.addMapping("/**").allowedOrigins("http://localhost:4200");
                // registry.addMapping("/**").allowedOrigins("http://localhost:5000");
                    registry.addMapping("/**").allowedOrigins("https://medic-website.herokuapp.com");

            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/**/auth/signUp").permitAll()
                .antMatchers("/**/auth/signIn").permitAll()

                .antMatchers("/**/demandes/create").permitAll()
                .antMatchers("/**/demandes/createDemandeWithFile").permitAll()
                .antMatchers("/**/demandes/createDemandeWithFileInPath").permitAll()
                .antMatchers("/**/demandes/countNumberTotalOfDemande").permitAll()
                .antMatchers("/**/demandes/countNumberOfDemandeInDay").permitAll()
                .antMatchers("/**/demandes/countNumberOfDemandeInMonth").permitAll()
                .antMatchers("/**/demandes/countNumberOfDemandeByStatusPending").permitAll()
                .antMatchers("/**/demandes/countNumberOfDemandeByStatusRefused").permitAll()
                .antMatchers("/**/demandes/countNumberOfDemandeByStatusValidated").permitAll()
                .antMatchers("/**/demandes/findById/{id}").permitAll()
                .antMatchers("/**/demandes/all").permitAll()
                .antMatchers("/**/demandes/allDemandesOrderDesc").permitAll()
                .antMatchers("/**/demandes/allPendingDemandesOrderByIdDesc").permitAll()
                .antMatchers("/**/demandes/allRefusedDemandesOrderByIdDesc").permitAll()
                .antMatchers("/**/demandes/allValidatedDemandesOrderByIdDesc").permitAll()
                .antMatchers("/**/demandes/numberTotalOfDemandeByMonth").permitAll()
                .antMatchers("/**/demandes/numberTotalOfDemandeByYear").permitAll()
                .antMatchers("/**/demandes/updateStatusOfDemande/{id}").permitAll()
                .antMatchers("/**/demandes/updatePriceAndNumberOfDayOfDemandeByID/{id}").permitAll()
                .antMatchers("/**/demandes/delete/{id}").permitAll()
                .antMatchers("/**/demandes/uploadDemandeFile/{id}").permitAll()
                .antMatchers("/**/demandes/downloadDemandeFile/*").permitAll()
                .antMatchers("/**/demandes/downloadDemandeFileFromPath/*").permitAll()
                .antMatchers("/**/demandes/sumOfDemandeByMonth").permitAll()


                .antMatchers("/**/services/create").permitAll()
                .antMatchers("/**/services/update/{id}").permitAll()
                .antMatchers("/**/services/countNumberTotalOfServices").permitAll()
                .antMatchers("/**/services/findById/{id}").permitAll()

                .antMatchers("/**/services/all").permitAll()
                .antMatchers("/**/services/allServicesOrderDesc").permitAll()
                .antMatchers("/**/services/delete/{id}").permitAll()

                .antMatchers("/**/utilisateurs/all").permitAll()
                .antMatchers("/**/utilisateurs/findById/{idUtilisateur}").permitAll()
                .antMatchers("/**/utilisateurs/update/{idUser}").permitAll()
                .antMatchers("/**/utilisateurs/avatar/{id}").permitAll()
                .antMatchers("/**/utilisateurs/uploadUserPhoto/{id}").permitAll()
                .antMatchers("/**/utilisateurs/*").permitAll()
                .antMatchers("/**/utilisateurs/updateCustomerProfileByUsername").permitAll()
                .antMatchers("/**/utilisateurs/delete/{idUtilisateur}").permitAll()

                .antMatchers("/**/emails/sendEmailToManager").permitAll()
                .antMatchers("/**/emails/sendEmailToCustomer").permitAll()
                .antMatchers("/**/emails/findById/{id}").permitAll()
                .antMatchers("/**/emails/allEmailsOrderDesc").permitAll()
                .antMatchers("/**/emails/all").permitAll()
                .antMatchers("/**/emails/countNumberOfMailInMonth").permitAll()
                .antMatchers("/**/emails/delete/{id}").permitAll()



                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}
