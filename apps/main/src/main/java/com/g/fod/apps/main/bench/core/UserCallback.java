package com.g.fod.apps.main.bench.core;

public interface UserCallback {

  default <T> T exec(UserRepository userRepository) {return null;}
}
