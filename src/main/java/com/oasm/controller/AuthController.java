package com.oasm.controller;

import com.oasm.domain.MenuVO;
import com.oasm.domain.RatingVO;
import com.oasm.domain.UserVO;
import com.oasm.domain.menuAuthManageVO;
import com.oasm.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService service;

    @GetMapping("/userGroup")
    public String userGroup (Model m) {
        UserVO vo = new UserVO();
        List<UserVO> users = service.selectUserRatings(vo.getUser_id(), vo.getRating_cd(), vo.getUser_nm());
        List<RatingVO> ratings = service.selectRatingsPaging();

        m.addAttribute("users", users);
        m.addAttribute("ratings", ratings);
        return "auth/userGroup";
    }

    @PostMapping("/userGroup")
    @ResponseBody
    public List<UserVO> userGroup (@RequestParam(value="id", required = false) String id,
                                          @RequestParam(value="code", required = false) String code,
                                          @RequestParam(value="name", required = false) String name) {
        if (code.equals("all")) code = null;
        log.info("id : " + id  + "/ code : " + code + "/ name : " + name);
        List<UserVO> users = service.selectUserRatings(id, code, name);
        log.info("users......" + users.size() + "/" + users.get(0));
        return service.selectUserRatings(id, code, name);
    }


    @Transactional
    @PostMapping("/userGroup/deleteRtn")
    @ResponseBody
    public int userGroupDeleteRtn (@RequestParam(value="rtnGroup[]")ArrayList<String> rtns){
        int result = 0;
        for (String rtn : rtns){
            service.deleteUserRatings(rtn);
            service.deleteRating(rtn);
            result++;
        }
        return result;
    }

    @Transactional
    @PostMapping("/userGroup/deleteUser")
    @ResponseBody
    public int userGroupDeleteUser (@RequestParam(value="userGroup[]")ArrayList<String> users){

        int result = 0;
        for (String user : users){
            String[] userCode = user.split("/");
            service.deleteUserRating(userCode[1], userCode[0]);
            result++;
        }
        
        return result;
    }

    @GetMapping("/userGroupPopup")
    public String userGroupPopup (Model m,
                                  @RequestParam(value="rating", required = false) String rating) {
        List<UserVO> users = service.selectUsersByRating(rating);
        RatingVO rtn = service.selectRating(rating);

        if (rtn != null && users != null) {
            m.addAttribute("users", users);
            m.addAttribute("rtn", rtn);
        }

        return "auth/userGroupPopup";
    }

    @Transactional
    @PostMapping("/userGroupPopup")
    @ResponseBody
    public int userGroupPopup (HttpServletResponse resp,
                                @RequestParam(value = "code", required = false) String code,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "users[]", required = false) ArrayList<String> users) {

        int result1 = 0;
        int result2 = 0;

        if (service.countRating(code) == 0) {
            result1 = service.insertRating(code, name, "admin");
        }

        if (users != null) {
            for (String user : users) {
                result2 = service.insertUserRating(code, user, "admin");
            }
        }


        if (result1 > 0) {
            return 1;
        } else if (result2 > 0) {
            return 2;
        } else if (code != null) {
            return 3;
        }
        return 0;

    }

    @GetMapping("/userGroupAddPopup")
    public String userGroupAddPopup (Model m){
        List<UserVO> users = service.selectUsers();

        m.addAttribute("users", users);
        return "auth/userGroupAddPopup";
    }

    @GetMapping("/authMenu")
    public String authMenu (Model m) {
        List<RatingVO> ratings = service.selectRatings();

        List<MenuVO> menus = service.selectFirstMenus();
        ArrayList<String> firstMenus = new ArrayList<>();
        for (MenuVO menu : menus) {
            firstMenus.add(menu.getMenu_cd());
        }

        Map<String, List<MenuVO>> menuMap = new HashMap<>();
        for (String menu : firstMenus) {
            menuMap.put(service.selectFirstMenu(menu).getMenu_nm(), service.selectSecondMenus(menu));
        }

        m.addAttribute("ratings", ratings);
        m.addAttribute("menuMap", menuMap);

        return "auth/authMenu";
    }

    @PostMapping("/authMenuVO")
    @ResponseBody
    public List<menuAuthManageVO> authMenuVO (){
        return service.authMenuSearch();
    }

    @PostMapping("/authMenu")
    @ResponseBody
    public int authMenu (String level, String code){
        int result = 0;
        int lvl = Integer.valueOf(level);

        return result;
    }

    @PostMapping("/authMenu/allSave")
    @ResponseBody
    public void authMenuAllSave (menuAuthManageVO menuAuthManageVO){
        service.authAllMenuSave(menuAuthManageVO);
    }

    @PostMapping("/authMenu/save")
    @ResponseBody
    public void authMenuSave (menuAuthManageVO menuAuthManageVO){
        service.authMenuSave(menuAuthManageVO);
    }

}
