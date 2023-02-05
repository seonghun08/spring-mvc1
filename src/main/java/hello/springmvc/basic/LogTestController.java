package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
/**
 * @Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다.
 * @RestController 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다.
 * 따라서 실행 결과로 ok 메세지를 받을 수 있다.
 */
@RestController
public class LogTestController {

    /**
     * 로그 사용시 장점
     * 쓰레드 정보, 클래스 이름 같은 부가 정보를 함께 볼 수 있고, 출력 모양을 조정할 수 있다.
     * 로그 레벨에 따라 개발 서버에서는 모든 로그를 출력하고, 운영서버에서는 출력하지 않는 등 로그를 상황에 맞게 조절할 수 있다.
     * 시스템 아웃 콘솔에만 출력하는 것이 아니라, 파일이나 네트워크 등, 로그를 별도의 위치에 남길 수 있다.
     * 특히 파일로 남길 때는 일별, 특정 용량에 따라 로그를 분할하는 것도 가능하다.
     * 성능도 일반 System.out 보다 좋다. (내부 버퍼링, 멀티 쓰레드 등등) 그래서 실무에서는 꼭 로그를 사용해야 한다.
     */

    // @Slf4j를 사용하면 자동으로 세팅
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "spring";

        /**
         * LEVEL: TRACE > DEBUG > INFO > WARN > ERROR
         * 개발 서버는 debug 출력
         * 운영 서버는 info 출력
         */
        log.trace(" trace log={}", name);
        log.debug(" debug log={}", name);
        log.info(" trace log={}", name);
        log.warn(" warn log={}", name);
        log.error(" error log={}", name);

        return "ok";
    }
}
