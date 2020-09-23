package ink.chevro.jwt;

import ink.chevro.entity.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  17:25 2020-07-08
 **/
public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String ISSUER = "Chevro";
    public static final String AUTH = "Authorities";
    public static final Long EXPIRATION = 43200L;
    public static final long EXPIRATION_REMEMBER = 604800L;
    public static final String SECRET = "SECRET_BLOG_JWT";

    private JwtTokenUtils() {}

    /**
     * 登录成功，生成Token
     *
     * @param jwtUser 用户安全实体
     * @return token
     */
    public static String createAccessToken(JwtUser jwtUser, boolean isRememberMe) {
        // 选择了记住我之后的过期时间为7天
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        return Jwts.builder()
                .setId(jwtUser.getId())
                .setSubject(jwtUser.getUsername())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer(ISSUER)
                // 自定义属性 放入用户拥有的权限
                .claim(AUTH, jwtUser.getAuthorities())
                // 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * token是否过期
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        try {
            return getTokenBody(token).getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token){
        return getTokenBody(token).getSubject();
    }

    /**
     * 获取用户角色
     *
     * @param token
     * @return
     */
    public static String getUserRole(String token){
        Claims tokenBody = getTokenBody(token);
        List list = (List) tokenBody.get(AUTH);
        // 只有一个角色
        LinkedHashMap auth = (LinkedHashMap)list.get(0);
        return (String) auth.get("authority");
    }


    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
