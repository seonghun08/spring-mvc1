package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        log.info("httpEntity.getHeaders()={}", httpEntity.getHeaders());
        log.info("httpEntity.getBody()={}", httpEntity.getBody());
        return new HttpEntity<>("ok");
    }

//    @PostMapping("/request-body-string-v3")
//    public ResponseEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException {
//        log.info("httpEntity.getHeaders()={}", httpEntity.getHeaders());
//        log.info("httpEntity.getBody()={}", httpEntity.getBody());
//        return new ResponseEntity<>("ok", HttpStatus.CREATED);
//    }

    /**
     * - @RequestHeader
     *   String 타입으로 꺼낼 시 value를 따로 지정하지 않으면 hdr이라는 이름의 헤더의 첫 번째 값을 문자열로 가져온다.
     *   즉 null 값이 반환되며, String 타입은 전체 헤더 값이 아닌 첫번째 문자열 만 가져온다.
     *   만약 전체 헤더 값을 얻고 싶다면 MultiValueMap<String, String> 타입을 사용하면 된다.
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestHeader(value = "Content-Type", required = false) String hdr, @RequestBody String body) throws IOException {
        log.info("hdr={}", hdr);
        log.info("body={}", body);
        return "ok";
    }
}
