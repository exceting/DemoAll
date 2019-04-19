package demo.simple.context;

/**
 * Create by 18073 on 2019/4/18.
 */
public class ContextHolder {

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
