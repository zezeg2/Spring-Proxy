package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
public class AspectV5Order {

    @Aspect
    @Order(2)
    public static class LogAspect{
        @Around("hello.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
            log.info("[log] {} ", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class TxAspect{
        @Around("hello.aop.Pointcuts.orderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{
            try {
                //@Before
                log.info("[around][트랜잭션 시작] {}", joinPoint.getSignature()); Object result = joinPoint.proceed();
                //@AfterReturning
                log.info("[around][트랜잭션 커밋] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                //@AfterThrowing
                log.info("[around][트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;
            } finally {
                //@After
                log.info("[around][리소스 릴리즈] {}", joinPoint.getSignature()); }
        }
    }
}
