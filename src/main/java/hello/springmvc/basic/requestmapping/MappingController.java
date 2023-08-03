package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    /**
     * - url 중복 가능: value = {"/hello-basic", "/hello-go"}
     *   # springBoot 3.0 이후 "/hello-basic" , "/hello-basic/"는 서로 다른 URL 요청을 사용해야 한다.
     *     기존 버전은 마지막 부분에 "/"가 제거 했지만 3.0 부터는 "/"가 유지 된다.
     * - method를 따로 지정하지 않으면 모든 HTTP 메서드 허용 (GET, HEAD, POST, PUT, PATCH, DELETE)
     */
    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic() {
        log.info("hello basic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mapping GetV1");
        return "ok";
    }

    /**
     * - @RequestMapping HTTP 메서드 별 축약 애노테이션
     *   GetMapping, PostMapping, PutMapping, DeleteMapping, PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * - PathVariable
     *   변수명이 같으면 생략 가능
     *   @PathVariable("userId") String userId → @PathVariable userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable String userId) {
        log.info("mappingPath userId = {}", userId);
        return "ok";
    }

    /**
     * - PathVariable 다중 사용
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId = {}, orderId = {}", userId, orderId);
        return "ok";
    }

    /**
     * - 파라미터로 추가 매핑
     *   파라미터에 설정 값이 있어야 처리 된다.
     *   params = "mode"
     *   params = "!mode"
     *   params = "mode=debug"
     *   params = "mode!=debug" (! = )
     *   params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * - 특정 헤더로 추가 매핑
     *   헤더에 설정 값이 있어야 처리가 된다.
     *   headers = "mode",
     *   headers = "!mode"
     *   headers = "mode=debug"
     *   headers = "mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /*
     * - Content-Type, Accept 헤더의 차이점
     *  "Content-Type"은 클라이언트가 서버로 보내는 데이터의 형식을 지정하고,
     *  "Accept"는 클라이언트가 서버로부터 받고자 하는 데이터의 형식을 요청하는 데 사용된다.
     */

    /**
     * - Content-Type 헤더 기반 추가 매핑 Media Type
     *   설정 값으로 요청이 와야 처리 된다.
     *   consumes = "application/json"
     *   consumes = "!application/json"
     *   consumes = "application/*"
     *   consumes = "*\/*"
     *   MediaType.APPLICATION_JSON_VALUE ≒ "application/json"
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * - Accept 헤더 기반 Media Type
     *   produces = "text/html"
     *   produces = "!text/html"
     *   produces = "text/*"
     *   produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
