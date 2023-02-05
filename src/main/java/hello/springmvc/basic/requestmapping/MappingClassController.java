package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    /**
     * restAPI 간단한 예제
     * 회원 목록 조회: GET
     * 회원 등록: POST
     * 회원 조회: GET
     * 회원 수정: PATCH
     * 회원 삭제: DELETE
     */

    @GetMapping
    public String users() {
        return "get users";
    }

    @PostMapping
    public String addUsers() {
        return "post users";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable("userId") String userId) {
        return "get userId=" + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String userId) {
        return "update userId=" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        return "delete userId=" + userId;
    }
}
