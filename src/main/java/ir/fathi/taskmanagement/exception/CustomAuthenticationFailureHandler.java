package ir.fathi.taskmanagement.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        Map<String , Object> date=new HashMap<>();
        date.put("statusCode" , HttpStatus.UNAUTHORIZED.value());
        date.put("exception" , exception.getCause());
        date.put("message" , """
                you are not authenticated.
                please check your password and username.
                """);

        response.getOutputStream().println(objectMapper.writeValueAsString(date));
    }
}
