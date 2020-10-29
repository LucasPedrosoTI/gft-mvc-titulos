package com.gft.estudosmvc.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

import com.gft.estudosmvc.model.Usuario;
import com.gft.estudosmvc.repository.Usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  @Lazy
  AuthenticationManager authenticationManager;

  @Autowired
  private Usuarios usuarios;

  public Long getUsuarioIdByEmail(String email) {
    return usuarios.findByEmail(email).getId();
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Usuario usuario = Optional.ofNullable(usuarios.findByEmail(email))
        .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));

    List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
    List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");

    return new User(usuario.getEmail(), usuario.getSenha(), usuario.isAdmin() ? authorityListAdmin : authorityListUser);
  }

  public Usuario cadastrarUsuario(Usuario usuario, HttpServletRequest request) throws LoginException {

    Usuario user = usuarios.findByEmail(usuario.getEmail());

    if (user != null)
      throw new DuplicateKeyException("E-mail já existente");

    Usuario novoUsuario = usuarios.save(new Usuario(null, usuario.getEmail(),
        passwordEncoder.encode(usuario.getSenha()), usuario.getNome(), usuario.isAdmin(), null));

    try {
      request.login(usuario.getEmail(), usuario.getSenha());
      return novoUsuario;
    } catch (Exception e) {
      throw new LoginException(e.getMessage());
    }
  }

}
