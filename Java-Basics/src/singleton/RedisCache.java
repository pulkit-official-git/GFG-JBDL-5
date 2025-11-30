package singleton;

public class RedisCache {

    private String url;

    private String username;

    private String password;

    private Long port;

    private static RedisCache instance = new RedisCache("url", "username", "password", 6379L);

    private RedisCache(String url, String username, String password, Long port) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public static RedisCache getInstance() {
//        if (instance == null) {
//            instance = new RedisCache("url", "username", "password", 6379L);
//        }
        return instance;
    }
}
