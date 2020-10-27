package com.gft.estudosmvc.utils;

import com.gft.estudosmvc.model.Usuario;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextSetter {

  public static void setContext(SecurityContext context, Usuario usuario) {
    Authentication authentication = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha());

    context.setAuthentication(authentication);

    SecurityContextHolder.setContext(context);
  }
}
