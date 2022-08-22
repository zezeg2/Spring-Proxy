package hello.aop.internalcall.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InternalService {
    public void internal() {
        log.info("call internal");
    }
}
