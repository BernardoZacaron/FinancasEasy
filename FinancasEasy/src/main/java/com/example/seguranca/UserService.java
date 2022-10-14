/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.seguranca;

import com.example.dao.UsuarioDAO;
import com.example.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aluno
 */
@Service
public class UserService implements UserDetailsService{

    @Autowired
    UsuarioDAO uDao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = uDao.findByLogin(username);
        
        if (u == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        
        return new UserLogado(u);
    }
}
