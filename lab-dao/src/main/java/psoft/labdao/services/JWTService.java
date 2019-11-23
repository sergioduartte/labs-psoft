package psoft.labdao.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;
import psoft.labdao.entities.UserDao;
import psoft.labdao.filters.TokenFilter;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.Optional;

@Service
public class JWTService {

    private UserService userService;
    private final String TOKEN_KEY = "login jooj";

    public JWTService(UserService userService) {
        super();
        this.userService = userService;
    }

    public boolean hasUser(String authorizationHeader) throws ServletException {
        String sub = getOwnerToken(authorizationHeader);

        return userService.getUser(sub).isPresent();
    }

    public boolean userHavePermission(String authorizationHeader, String email) throws ServletException {
        String sub = getOwnerToken(authorizationHeader);
        Optional<UserDao> optUser = userService.getUser(sub);

        return optUser.isPresent() && optUser.get().getEmail().equals(email);
    }

    private String getOwnerToken(String authorizationHeader) throws ServletException {
        if( authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ServletException("Token inexistente ou mal formatado!");
        }

        String token = authorizationHeader.substring(TokenFilter.TOKEN_INDEX);

        String sub = null;

        try {
            sub = Jwts.parser().setSigningKey("login jooj").parseClaimsJws(token).getBody().getSubject();

        } catch (SignatureException e) {
            throw new ServletException("Token invalido ou expirado!!");
        }

        return sub;
    }

    public String generateToken(String email) {
        return Jwts.builder().setSubject(email)
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)).compact();
    }

}
