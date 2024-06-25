package com.univr.runnerz.user;


public record User(
        Integer id,
        String name,
        String password,
        String username,
        String email,
        Address address,
        String phone,
        String website,
        Company company
) {
}
