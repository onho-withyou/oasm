package com.oasm.service;

import com.oasm.dao.AuthDAO;
import com.oasm.domain.UserList;
import com.oasm.domain.UserRole;
import com.oasm.domain.UserRtnManage;
import com.oasm.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    private AuthDAO authDAO;
    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {

        Optional<UserList> _siteUser = this.userRepository.findByUserId(user_id);
        List<UserRtnManage> Rating = authDAO.typeOfRating(user_id);
        //해당 이름의 사용자 db에서 못찾은 경우
        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
        }
        //해당 이름의 사용자 db에서 찾은 경우
        UserList userList = _siteUser.get();//해당 유저 엔티티를 Optional 객체에서 꺼냄
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userList.getUser_nm()));
        for (UserRtnManage i : Rating) {
            if (i.getRating_cd().equals("A01")) {  //관리자 권한 부여
                authorities.add(new SimpleGrantedAuthority(UserRole.A01.name()));
            }
            if (i.getRating_cd().equals("B01")) {    //조사자 권한 부여
                authorities.add(new SimpleGrantedAuthority(UserRole.B01.name()));
            }
            if (i.getRating_cd().equals("B02")) {    //일반사용자 권한 부여
                authorities.add(new SimpleGrantedAuthority(UserRole.B02.name()));
            }
            if (i.getRating_cd().equals("C01")) {    //정리 권한 부여
                authorities.add(new SimpleGrantedAuthority(UserRole.C01.name()));
            }
            if (i.getRating_cd().equals("middle")) {    //중간관리자 권한 부여
                authorities.add(new SimpleGrantedAuthority(UserRole.middle.name()));
            }
            if (i.getRating_cd().equals("TEST")) {    //테스터 권한 부여
                authorities.add(new SimpleGrantedAuthority(UserRole.TEST.name()));
            }
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String securePw = encoder.encode(userList.getUser_pw());
        return new User(userList.getUserId(), securePw, authorities); //UserList 객체 아님!!
    }

    public List<UserList> setUser() {
        List<UserList> _siteUser = this.userRepository.findAll();
        for (UserList i : _siteUser) {
            if (i.getClient_nm() == null) {
                i.setClient_nm("");
            }
            if (i.getRmk() == null) {
                i.setRmk("");
            }
        }
        return _siteUser;
    }

    public void create(UserList userList) {
        authDAO.typeOfInsert(
                userList.getUserId(),userList.getUser_pw(),userList.getUser_nm(),
                userList.getClient_nm(),userList.getRmk());
    }

    public void delete(UserList userList) {
        String[] userId = userList.getUserId().split(",");
        for (String i : userId) {
            authDAO.typeOfDelete(i);
        }
    }

    public void update(UserList userList) {
        authDAO.typeOfUpdate(
                userList.getUserId(),userList.getUser_nm(),
                userList.getClient_nm(),userList.getRmk());
    }

    public UserList read(UserList userList) {
        return authDAO.typeOfRead(userList.getUserId());
    }

}
