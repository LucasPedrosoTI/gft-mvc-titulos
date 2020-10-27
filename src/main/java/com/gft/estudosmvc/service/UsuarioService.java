package com.gft.estudosmvc.service;

import java.util.Optional;

import com.gft.estudosmvc.model.Usuario;
import com.gft.estudosmvc.repository.Usuarios;
import com.gft.estudosmvc.utils.SecurityContextSetter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  private SecurityContext context = SecurityContextHolder.createEmptyContext();

  @Autowired
  private Usuarios usuarios;

  public Usuario logar(Usuario usuario) throws Exception {
    try {

      Optional<Usuario> user = usuarios.findByEmail(usuario.getEmail());

      if (user.isEmpty())
        throw new Exception("Email ou senha inválido");

      if (!encoder.matches(usuario.getSenha(), user.get().getSenha()))
        throw new Exception("Email ou senha inválido");

      SecurityContextSetter.setContext(context, usuario);

      return user.get();
    } catch (Exception e) {
      throw new Exception("Email ou senha inválido");
    }

  }

  public Usuario cadastrarUsuario(Usuario usuario) {

    String hash = encoder.encode(usuario.getSenha());

    usuario.setSenha(hash);

    Usuario novoUsuario = usuarios.save(usuario);

    SecurityContextSetter.setContext(context, usuario);

    return novoUsuario;
  }

  public void logout() {
    // TODO: implement logout
  }

}
