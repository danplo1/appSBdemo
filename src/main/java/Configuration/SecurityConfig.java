package Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * Created by Daniel_D'AGE on 02.10.2018.
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bcp; // klasa która koduje i rozkodowuje nasze hasła
    @Autowired
    private DataSource ds; // dostę do bazy danych
    @Value("${spring.queries.users-query}")
    private String usersQuery; // pobiera uzytkownika i hasło oraz czy uzytkownik jest aktywny,
    @Value(("{spring.queries.roles-query}"))
    private String rolesQuery;

    /**
     * pobiera nam role danego użytkownika, i na podstawie ról bedziemy udostępniac pewne
     * rzeczy,np tylko admin bedzie miał dostęp do katalogu admin a reszta dla użtkowników
     */

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(ds).passwordEncoder(bcp); // konfiguracja
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity /**w tej metodzie SpringSecurity dostarczamy  pewne zasady, które muszą być spełnione w przypadku
         wypełniania pewnych rzeczy, pilnuje jakie wywowłania są dla wszystkich, a jakiej dla zalogowanych*/
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/adduser").permitAll()
                .antMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")// dostep tylko dla admina
                .anyRequest().authenticated() // wymaganie autentykacji
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/").usernameParameter("email")
                .passwordParameter("password")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //przekierowanie na stronę logout
                .logoutSuccessUrl("/") // wylogowanie
                .and().exceptionHandling().accessDeniedPage("/denied"); // w przypadku braku uprawnień admina i próby zalogowania,
        // wyświetli sie komunikat
    }

    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                .antMatchers("/resources/**", "/statics/**", "/css/**", "/js/**", "/images/**", "/incl/**");

    }
}
