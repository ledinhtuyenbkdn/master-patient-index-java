package com.ledinhtuyenbkdn.masterpersonindex.config.security;

public final class SecurityConstants {

    private SecurityConstants() {
    }

    public static final String SECRET_KEY = "abc123dsfsadfsduifhids759y5yjfuykfyukgfu4598023458943758903475893253495fhiusof";

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "bearer ";

    public static final String LOGIN_URL = "/api/login";

    public static final Long TOKEN_DURATION = 7 * 24 * 60 * 60 * 1000L;
}
