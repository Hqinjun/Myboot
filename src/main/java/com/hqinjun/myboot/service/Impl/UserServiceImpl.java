package com.hqinjun.myboot.service.Impl;

import com.hqinjun.myboot.domain.Spermission;
import com.hqinjun.myboot.domain.Suser;
import com.hqinjun.myboot.dto.UserDTO;
import com.hqinjun.myboot.repository.SpermissionRepository;
import com.hqinjun.myboot.repository.SuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private SuserRepository suserRepository;

    @Autowired
    private SpermissionRepository spermissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = new UserDTO();

        Suser suser = suserRepository.findSuserByUsername(username);
        //断定
        if(suser!=null){ //有用户对象
            userDTO.setUsername(suser.getUsername());
            String password = passwordEncoder.encode(suser.getPassword());
            userDTO.setPassword(password);
            //根据用户对象中的用户编号，获取该用户所拥有的所有权限集合
            List<Spermission> permissions=spermissionRepository.getAdminPermission(suser.getId());
            //如果想让你的权限在UI层可以使用，必须进行包装，保证类型是GrantedAuthority
            //1.先准备一个和框架对接的容器
            List<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();
            //2.进行容器的替换
            for(Spermission permission:permissions){
                //每个permission对象都是一个权限对象
                if(permission != null && permission.getName()!= null){ //进行了双重校验，保证权限的准确性
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    //将单个grantedAuthority对象添加到集合中
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            userDTO.setAuthorities(grantedAuthorities);
            userDTO.setAccountNonExpired(true);
            userDTO.setAccountNonLocked(true);
            userDTO.setCredentialsNonExpired(true);
            userDTO.setEnabled(true);

            return userDTO;
        }else{
            throw new UsernameNotFoundException("admin:"+username+"do not exits");
        }

    }
}
