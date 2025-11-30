package singleton;

public class Client {

    public static void main(String[] args) {
        RedisCache redisCache = RedisCache.getInstance();
        System.out.println(redisCache);
        Test test = new Test();
        test.temp();
    }
}
