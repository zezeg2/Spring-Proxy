package hello.aop.proxyvs.code;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"})
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"})
//@SpringBootTest
@Import(ProxyDIAspect.class)
public class ProxyDITest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @DisplayName("go")
    @Test
    void go() {
        log.info("memberService class  = {}", memberService.getClass());
        log.info("memberServiceImpl class  = {}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }
}