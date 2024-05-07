package com.oasm.service;

import com.oasm.dao.AuthDAO;
import com.oasm.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthDAO dao;

    public int insertRating (String code, String name, String id) {
        return dao.insertRating(code, name, id);
    }

    public int insertUserRating (String rating, String user, String id){
        return dao.insertUserRating(rating, user, id);
    }

    public List<UserVO> selectUsers () {
        return dao.selectUsers();
    }

    public List<UserVO> selectUserRatings (String id, String code, String name) {
        return dao.selectUserRatings(id, code, name);
    }

    public List<UserVO> selectUsersByRating (String rating) {
        return dao.selectUsersByRating(rating);
    }

    public RatingVO selectRating (String rating) {
        return dao.selectRating(rating);
    }

    public List<RatingVO> selectRatings() {
        return dao.selectRatings();
    }
    public List<RatingVO> selectRatingsPaging() {return dao.selectRatingsPaging();}
    public MenuVO selectFirstMenu(String code) {return dao.selectFirstMenu(code);}
    public List<MenuVO> selectFirstMenus() {return dao.selectFirstMenus();}
    public List<MenuVO> selectSecondMenus(String code) {return dao.selectSecondMenus(code);}
    public int countRating(String code) {return dao.countRating(code);}

    public int deleteRating (String rating){
        return dao.deleteRating(rating);
    }

    public int deleteUserRating (String rating, String user){
        return dao.deleteUserRating(rating, user);
    }

    public int deleteUserRatings (String rating){
        return dao.deleteUserRatings(rating);
    }
    public List<UserList> typeOfUsers(){
        return dao.typeOfUsers();
    }
    public void authAllMenuSave(menuAuthManageVO menuAuthManageVO) {
        String[] menu_nm = menuAuthManageVO.getMenu_nm().split(",");
        dao.authAllMenuNonSave(menu_nm[0]);
        if(menuAuthManageVO.getRating_cd() != null) {
            String[] rating_cd = menuAuthManageVO.getRating_cd().split(",");
            for (String i : rating_cd) {
                dao.authAllMenuSave(menu_nm[0], i);
            }
        }
    }
    public void authMenuSave(menuAuthManageVO menuAuthManageVO) {
        String[] menu_nm = menuAuthManageVO.getMenu_nm().split(",");
        dao.authMenuNonSave(menu_nm[0]);
        if(menuAuthManageVO.getRating_cd() != null) {
            String[] rating_cd = menuAuthManageVO.getRating_cd().split(",");
            for (String i : rating_cd) {
                dao.authMenuSave(menu_nm[0], i);
            }
        }
    }
    public List<menuAuthManageVO> menuAuthSearch() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<menuAuthManageVO> menuAuthManageVO = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            List<menuAuthManageVO> resultList = dao.menuAuthSearch(authority.getAuthority());
            menuAuthManageVO.addAll(resultList);
        }
        return menuAuthManageVO;
    }
    public List<menuAuthManageVO> authMenuSearch() {
        return dao.authMenuSearch();
    }
}
