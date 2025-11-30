package multithreadSingleton;

public class RedisCache {

    private String url;

    private String username;

    private String password;

    private Long port;

    private static RedisCache instance;

    private RedisCache(String url, String username, String password, Long port) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public static RedisCache  getInstance() {

            if (instance == null) {
                synchronized (RedisCache.class) {
                    if (instance == null) {
                        instance = new RedisCache(null, null, null, null);
                    }
                }
            }
        return instance;

    }
}
