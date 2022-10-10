package kr.limc.limcblog.Config.Security;

public class SecurityConstants {
    public static final long JWT_ACCESS_TOKEN_VALIDITY = 10 * 60;
    public static final long JWT_REFRESH_TOKEN_VALIDITY = 24 * 60 * 60;
    public static final String ISSUER = "limcpf";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    
    private SecurityConstants() {
        throw new UnsupportedOperationException();
    }
    
}
