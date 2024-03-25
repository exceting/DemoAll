package demo.distributed.zookeeper;

import org.apache.zookeeper.*;

import java.nio.charset.StandardCharsets;

/**
 * demo目标，利用zk原生客户端，实现一个资源的注册与发现
 */
public class ZookeeperDemo {

    public static void main(String[] args) throws Exception {

        ZooKeeper zk = new ZooKeeper("127.0.0.1:2188", 30000, null);
        Watcher watcher = new AppGatewayWatcher(zk);
        zk.register(watcher);

        System.out.println("OK!");

        // 创建一个目录节点
        /**
         * CreateMode枚举值:
         *       PERSISTENT (持久化节点：不会随着client session关闭而消失)
         *       PERSISTENT_SEQUENTIAL（带顺序的持久化节点）
         *       EPHEMERAL (临时节点：随着client session关闭而消失)
         *       EPHEMERAL_SEQUENTIAL  (带顺序的临时节点)
         *
         * 节点访问权限由List<ACL>确定，但有几个便捷的静态属性以供快速选择：
         *       Ids.CREATOR_ALL_ACL：创建该节点的客户端有读写权限
         *       Ids.OPEN_ACL_UNSAFE：所有客户端都有读写权限
         *       Ids.READ_ACL_UNSAFE：所有客户端都有只读权限
         */
        zk.delete("/ogv.ogv.app-gateway", -1);

        // 节点注册
        zk.create("/ogv.ogv.app-gateway", "127.0.0.1:8081,127.0.0.2:8081,127.0.0.3:8081".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 取出目录数据
        System.out.println("变更前：" + new String(zk.getData("/ogv.ogv.app-gateway", true, null), StandardCharsets.UTF_8));

        // 节点变更
        zk.setData("/ogv.ogv.app-gateway", "127.0.0.1:8081,127.0.0.2:8081,127.0.0.3:8081,127.0.0.4:8081".getBytes(), -1);
        System.out.println("变更后：" + new String(zk.getData("/ogv.ogv.app-gateway", true, null), StandardCharsets.UTF_8));
        //Thread.sleep(15000);
        zk.close();
        System.out.println("OK");
    }

    static class AppGatewayWatcher implements Watcher {

        private final ZooKeeper zooKeeper;

        public AppGatewayWatcher(ZooKeeper zooKeeper) {
            this.zooKeeper = zooKeeper;
        }

        @Override
        public void process(WatchedEvent event) {
            System.out.println(event.toString());
           // zooKeeper.register(this);
        }
    }

}
