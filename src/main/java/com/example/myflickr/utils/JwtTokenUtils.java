/**
 * 工具类 调用静态方法
 */
package com.example.myflickr.utils;

import cn.hutool.core.util.StrUtil;
import com.example.myflickr.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.myflickr.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenUtils {
    private static UserService staticUserService;
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtils.class);
    @Resource
    private UserService userService;

    @PostConstruct // spring容器初始化时执行
    public void setUserService() {
        staticUserService = userService;
    }

    /**
     * 生成token
     */
    public static String genToken(String adminId, String sign) {
        return JWT.create().withAudience(adminId) // 将 user id 保存到 token 里面作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期
                .sign(Algorithm.HMAC256(sign));  //password 作为 token 的密钥
    }

    /**
     * 获取当前登录的用户信息
     */
    public static User getCurrentUser() {
        String token = null;

        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)){
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)){
                log.error("获取当前登录的token失败 token: {}", token);
                return null;
            }

            // 解析token 获取用户的id
            String uId = JWT.decode(token).getAudience().get(0);
            return staticUserService.selectById(Integer.valueOf(uId));  // 这里是查数据库?
        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败, token: {}",token, e);
            return null;
        }
    }
}
