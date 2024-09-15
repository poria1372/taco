package com.example.taco.security;

import com.example.taco.UserObj;
import com.example.taco.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Objects;

@Configuration
public class SecurityConfig {
@Autowired
    private  UserRepository repository;

    public SecurityConfig(UserRepository repository) {
        this.repository = repository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            UserObj user = userRepo.findByUsername(username);//هنگام لاگین کاربر موقعی که یوزر رو وارد میکنه ، وارد این متد میشه و میره از دیتابیس یوزر وارد شده رو لود کنه اگر وجود نداشته باشد اکسپشن میده
            if (Objects.nonNull(user)) {
                return user;
            }
            throw new UsernameNotFoundException("با اطلاعات وارد شده نام کاربری یافت نشد");
        };
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //این متد تفاوتش با بالایی اینه که مثلا میخوای تعیین کنی که کاربر چه روزهایی  از هفته به صفحه دسترسی داشته باشه
//        return http
//                .securityMatcher("/design", "/orders")
//                .access("hasRole('USER') && " +
//                        "T(java.util.Calendar).getInstance().get("+
//                        "T(java.util.Calendar).DAY_OF_WEEK) == " +
//                        "T(java.util.Calendar).TUESDAY")
//                .antMatchers("/", "/**").access("permitAll")
//                .and()
//                .build();
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {//جهت سطح دسترسی به صفحاتی که ساختیم
//        return http.authorizeRequests()
//                .antMatchers("/design", "/orders").hasRole("USER")//جهت دسترسی به این صفحات فقط کاربرانی که یوزر دارند
//                .antMatchers("/", "/**").permitAll()// این خط کد یعنی این که بقیه صفحات هرچی که هست نیاز به دسترسی ندارد
//                .and()
//                .build();
//    }
//    @Bean
//    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//        http
//                .securityMatcher("/","/**")
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().hasRole("USER")
//                )
//                .httpBasic(withDefaults());
//        return http.build();
//    }
//}


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {//جهت سطح دسترسی به صفحاتی که ساختیم
        return http.authorizeRequests()
//                .antMatchers("/design", "/orders").hasRole("USER")//جهت دسترسی به این صفحات فقط کاربرانی که یوزر دارند
                .antMatchers("/", "/**").permitAll()// این خط کد یعنی این که بقیه صفحات هرچی که هست نیاز به دسترسی ندارد
                .and()
                .build();
    }
}



//@Bean
//public UserDetailsService userDetailsService(UserRepository repository) {
////        UserObj user = new UserObj();
////        user.setUsername("hihihiih");
////        List<UserDetails> users = List.of(user);
////        return new InMemoryUserDetailsManager(users);
//    return new UserService(repository);

