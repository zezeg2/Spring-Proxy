package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {
    @DisplayName("jdkProxy")
    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(false);

        //프록시를 인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService) factory.getProxy();

        //JDK 동적 프록시를 구현 클래스로 캐스팅 시도 실패 ClassCastException
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberServiceImpl castringMemberService = (MemberServiceImpl) memberServiceProxy;
        });

    }

    @DisplayName("CGLIBProxy")
    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.setProxyTargetClass(true);

        //프록시를 인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService) factory.getProxy();

        log.info("proxy class={}", memberServiceProxy.getClass());

        //JDK 동적 프록시를 구현 클래스로 캐스팅 시도 성공
        MemberServiceImpl castringMemberService = (MemberServiceImpl) memberServiceProxy;
    }
}
