package com.oasm.dao;

import com.oasm.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AuthDAO {

    int insertRating(String code, String name, String id);
    int insertUserRating(String rating, String user, String id);
    List<UserVO> selectUsers();
    List<UserVO> selectUserRatings(String id, String code, String name);
    List<UserVO> selectUsersByRating(String rating);
    RatingVO selectRating(String rating);
    List<RatingVO> selectRatings();
    List<RatingVO> selectRatingsPaging();
    MenuVO selectFirstMenu(String code);
    List<MenuVO> selectFirstMenus();
    List<MenuVO> selectSecondMenus(String code);
    int countRating(String code);
    int deleteRating(String rating);
    int deleteUserRating(String rating, String user);
    int deleteUserRatings(String rating);
    List<UserRtnManage> typeOfRating(String user_id);
    List<UserList> typeOfUsers();
    List<UserList> typeOfUsersSearchButton(String userId, String user_nm, String user_flag_yn);
    void typeOfInsert(String userId, String user_pw, String user_nm, String client_nm, String rmk);
    void typeOfDelete(String userId);
    void typeOfUpdate(String userId, String user_nm, String client_nm, String rmk);
    UserList typeOfRead(String userId);
    void authAllMenuSave(String menu_nm, String rating_cd);
    void authMenuSave(String menu_nm, String rating_cd);
    List<menuAuthManageVO> menuAuthSearch(String rating_cd);
    List<menuAuthManageVO> authMenuSearch();
    void authAllMenuNonSave(String menu_nm);
    void authMenuNonSave(String menu_nm);
}
