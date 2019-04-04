/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.reactor.cobar.mysql;

/**
 * @author sunqinwen
 * @version \: QuitPacket.java,v 0.1 2019-04-04 12:05
 */
public class QuitPacket extends MySQLPacket {

    public static final byte[] QUIT = new byte[]{1, 0, 0, 0, 1};

    @Override
    public int calcPacketSize() {
        return 1;
    }

    @Override
    protected String getPacketInfo() {
        return "MySQL Quit Packet";
    }

}
