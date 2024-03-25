package agent.test;

public class XxxService {

    static {
        System.out.println("XxxService load");
    }

    static YyyService yyyService = new YyyService();

    public Integer sum(int a, int b) {
        return a + b;
    }

}
