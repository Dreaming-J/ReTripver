package com.retripver.user.controller;

import com.retripver.global.util.JWTUtil;
import com.retripver.user.dto.FollowRequest;
import com.retripver.user.dto.StatusMapCountResponse;
import com.retripver.user.dto.StatusUserInfoResponse;
import com.retripver.user.dto.UserAchievementResponse;
import com.retripver.user.dto.UserInfoResponse;
import com.retripver.user.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // 유저 팔로우/언팔로우
    @PutMapping("/follow/{toId}")
    public ResponseEntity<?> follow(@PathVariable String toId,
                                    @RequestHeader(value = "Authorization") String authorization) {
        String fromId = jwtUtil.extractUserId(authorization, false);

        boolean isFollow = userService.follow(new FollowRequest(fromId, toId));

        return ResponseEntity.ok(isFollow);
    }

    // 상태창 유저 정보
    @GetMapping("/status/info")
    public ResponseEntity<?> statusUser(@RequestHeader(value = "Authorization") String authorization) {
        String id = jwtUtil.extractUserId(authorization, false);

        StatusUserInfoResponse statusInfo = userService.statusUserInfo(id);
        System.out.println(statusInfo.getUserInfo().getAchievementImg());

        return ResponseEntity.ok(statusInfo);
    }

    // 유저 아이디로 정보 가져오기
    @GetMapping("/status/info/{userId}")
    public ResponseEntity<?> statusUserByUserId(@PathVariable String userId) {
        StatusUserInfoResponse statusInfo = userService.statusUserInfo(userId);
        System.out.println(statusInfo.getQuestRate());

        System.out.println("호출!!@!!!" + userId);

        return ResponseEntity.ok(statusInfo);
    }

    // 상태창 지도
    @GetMapping("/status/map")
    public ResponseEntity<?> statusMap(@RequestHeader(value = "Authorization") String authorization) {
        String id = jwtUtil.extractUserId(authorization, false);

        List<StatusMapCountResponse> statusMapCountList = userService.statusMapCount(id);

        return ResponseEntity.ok(statusMapCountList);
    }

    // 유저 아이디로 상태창 지도
    @GetMapping("/status/map/{userId}")
    public ResponseEntity<?> statusMapByUserId(@PathVariable String userId) {
        List<StatusMapCountResponse> statusMapCountList = userService.statusMapCount(userId);

        return ResponseEntity.ok(statusMapCountList);
    }

    // 업적 확인
    @GetMapping("/achievement/list")
    public ResponseEntity<?> achievement(@RequestHeader(value = "Authorization") String authorization) {
        String id = jwtUtil.extractUserId(authorization, false);

        UserAchievementResponse userAchievement = userService.getUserAchievement(id);

        return ResponseEntity.ok(userAchievement);
    }

    // 전체 경험치에 대한 랭킹
    @GetMapping("/rank")
    public ResponseEntity<?> rankUserByExp() {
        System.out.println("!!");
        List<UserInfoResponse> userList = userService.getRankByExpUserList();

        return ResponseEntity.ok(userList);
    }

    // 해당 지역을 가장 많이 방문한 횟수
    @GetMapping("/rank/{sidoCode}")
    public ResponseEntity<?> rankUserBySidoCode(@PathVariable int sidoCode) {
        List<UserInfoResponse> userList = userService.getRankBySidoCodeUserList(sidoCode);

        return ResponseEntity.ok(userList);
    }

    // 유저 검색
    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchUser(@PathVariable String keyword) {
        List<UserInfoResponse> userList = userService.getSearchUserByKeyword(keyword);

        return ResponseEntity.ok(userList);
    }

    @GetMapping("/current-quest")
    public ResponseEntity<?> currentQuest(@RequestHeader(value = "Authorization") String authorization) {
        String id = jwtUtil.extractUserId(authorization, false);

        int planId = userService.currentQeust(id);

        System.out.println(planId);

        return ResponseEntity.ok(planId);
    }
}
