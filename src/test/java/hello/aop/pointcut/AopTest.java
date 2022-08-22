package hello.aop.pointcut;


import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import hello.aop.order.aop.AspectV1;
import hello.aop.order.aop.AspectV3;
import hello.aop.order.aop.AspectV5Order;
import hello.aop.order.aop.AspectV6Advice;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
//@Import(AspectV1.class)
//@Import(AspectV2.class)
//@Import(AspectV3.class)
//@Import(AspectV4Pointcut.class)
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class})
@Import(AspectV6Advice.class)
public class AopTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @DisplayName("1.aopInfo")
    @Test
    void test_1() {
        log.info("isAopProxy, orderService = {}", AopUtils.isAopProxy(orderRepository));
        log.info("isAopProxy, orderService = {}", AopUtils.isAopProxy(orderService));
    }

    @DisplayName("2.success")
    @Test
    void test_2() {
        orderService.orderItem("itemA");
    }

    @DisplayName("exception")
    @Test
    void exception() {
        Assertions.assertThatThrownBy(()-> orderService.orderItem("ex")).isInstanceOf(IllegalStateException.class);
    }
}
