package demo.simple.context;

/**
 * Create by 18073 on 2019/4/18.
 */
public class ContextHolder {

    //这里仅用TL简单实现，如果项目里使用了线程池，那么这里的实现要变成TTL，并让TTL代理全局的线程池对象，也可以不用TTL，自己代理线程池对象，这里不再详述
    private static ThreadLocal<Context> contextThreadLocal = new ThreadLocal<>();

    private ContextHolder() {
    }

    public static void removeContext() {
        contextThreadLocal.remove();
    }

    public static Context getContext() {
        return contextThreadLocal.get();
    }

    public static void setContext(Context context) {
        if (context == null) {
            removeContext();
        }
        contextThreadLocal.set(context);
    }

}
