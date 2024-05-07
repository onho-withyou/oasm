package com.oasm.controller;

import com.oasm.dao.AuthDAO;
import com.oasm.domain.UserList;
import com.oasm.domain.menuAuthManageVO;
import com.oasm.service.AuthService;
import com.oasm.service.UserSecurityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    public final UserSecurityService userSecurityService;
    private final AuthService authService;
    public final AuthDAO authDAO;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:login";
    }
    @PostMapping("/_aside")
    @ResponseBody
    public List<menuAuthManageVO> asideAuth() {
        List<menuAuthManageVO> menuAuthManageVO = authService.menuAuthSearch();
        return menuAuthManageVO;
    }
    @GetMapping("/setUsers")
    public String setUser(Model model) {

        model.addAttribute("data", userSecurityService.setUser());

        return "auth/setUsers";
    }
    @PostMapping("/setUsers/searchButton")
    @ResponseBody
    public List<UserList> setUserSearchButton(UserList userList) {
         var a = authDAO.typeOfUsersSearchButton
                (userList.getUserId(), userList.getUser_nm(), userList.getUser_flag_yn());
        return a;
    }
    @GetMapping("/setUsersPopup")
    public String commonCodePopup () {
        return "auth/setUsersPopup";
    }
    @PostMapping("/setUsersPopup/insert")
    @ResponseBody
    public String commonCodePopupButton (UserList userList) {
        UserList userList2 = userSecurityService.read(userList);
        if (userList2 == null){
            userSecurityService.create(userList);
            return "성공";
        }
        return "실패";
    }
    @PostMapping("/setUsersPopup/delete")
    @ResponseBody
    public void commonCodePopupDelete (UserList userList) {
        userSecurityService.delete(userList);
    }
    @PostMapping("/setUsersPopup/update")
    @ResponseBody
    public void commonCodePopupUpdate (UserList userList) {
        userSecurityService.update(userList);
    }
    @PostMapping("/setUsersPopup/read")
    @ResponseBody
    public UserList commonCodePopupRead (UserList userList) {
        return userSecurityService.read(userList);
    }
}
