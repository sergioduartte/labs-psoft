package psoft.labdao.filters;

import io.jsonwebtoken.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends GenericFilter {

    public final static int TOKEN_INDEX  = 7;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Token inexistente ou mal formatado!");
            return;

        }

        String token = header.substring(TOKEN_INDEX);

        try {
            Jwts.parser().setSigningKey("login jooj").parse(token).getBody();
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException | PrematureJwtException
            | UnsupportedJwtException | IllegalArgumentException e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        chain.doFilter(request,response);

    }

}



