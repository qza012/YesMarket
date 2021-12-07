package com.study.yesmarket.common.interceptor;

import com.study.yesmarket.member.exception.UnAuthorizationExceptioon;
import com.study.yesmarket.member.service.LoginService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/***
 * HandlerMethod : @RequestMapping이 붙은 메소드의 정보를 추상화한 객체
 *                 디스패처 서블릿은 애플리케이션이 실행될 때, 모든 컨트롤러 빈의 메소드를 살펴서 매핑 후보가 되는 메소드들을 추출한다.
 *                 추출된 것들은 HandlerMethod 형태로 저장해둔다.
 */

public class AuthHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Controller의 method인지 확인
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ForLoginMember forLoginMember = handlerMethod.getMethodAnnotation(ForLoginMember.class);

            if (forLoginMember == null) {
                return true;
            }

            HttpSession httpSession = request.getSession();
            if (httpSession.getAttribute(LoginService.LOGIN_ID) == null) {
                throw new UnAuthorizationExceptioon();
            }
        }

        return true;
    }
}
