package com.gft.estudosmvc.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

  public static void main(String[] args) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    System.out.println(bCryptPasswordEncoder.encode("123456"));

  }
}
