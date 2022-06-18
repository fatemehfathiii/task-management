package ir.fathi.taskmanagement.config.security;

import ir.fathi.taskmanagement.model.User;
import ir.fathi.taskmanagement.repository.UserRepository;
import ir.fathi.taskmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

//    private final UserService repository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final var header=request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header.isEmpty() || header.isBlank()|| header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        var tokenArray=header.split(" ");

        if (tokenArray.length < 2){
            filterChain.doFilter(request,response);
            return;
        }

        var token=tokenArray[1].trim();

        if (!jwtTokenUtil.validate(token)) {
            filterChain.doFilter(request,response);
            return;
        }

//        UserDetails userDetails=repository
//                .findUserByUsernameAndDeletedFalse(jwtTokenUtil.getUsername(token))
//                .orElse(null);


        UserDetails userDetails=new User();
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails==null ? List.of() : userDetails.getAuthorities()
        );

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
    }
}
