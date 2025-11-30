package multithreadSingleton;

public class Test {

    public void temp(){
        RedisCache redisCache = RedisCache.getInstance();
        System.out.println(redisCache);
    }
}
