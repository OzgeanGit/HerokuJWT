package login.microservice.JWT.Spring.Security.config.jwt;

import login.microservice.JWT.Spring.Security.config.CustomUserDetails;
import login.microservice.JWT.Spring.Security.config.CustomUserDetailsService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static org.springframework.util.StringUtils.hasText;

@Component
@Log
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       //logger.info("do filter..."); //adauga header in serverResponse
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        logger.info("do filter...valid = "+jwtProvider.validateToken(token));
        logger.info("do filter...token = "+token);
        if (token != null && jwtProvider.validateToken(token)) {
            logger.info("do filter...valid in if"+jwtProvider.validateToken(token));

            Integer userId = jwtProvider.getLoginFromToken(token);
            logger.info("userLogin = "+userId);
            CustomUserDetails customUserDetails = customUserDetailsService.loadUserById(userId);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            logger.info("auth = "+ auth.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if(((HttpServletResponse)servletResponse).getHeader("x-current-user")==null)
            ((HttpServletResponse)servletResponse).addHeader("x-current-user", userId.toString());
           // ((HttpServletResponse)servletResponse).addHeader("Authorization", "Bearer " + token);




        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }


}
