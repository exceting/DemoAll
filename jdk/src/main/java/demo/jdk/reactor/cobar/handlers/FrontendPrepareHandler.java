/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.reactor.cobar.handlers;

/**
 * @author sunqinwen
 * @version \: FrontendPrepareHandler.java,v 0.1 2019-04-04 11:43
 */
public interface FrontendPrepareHandler {

    void prepare(String sql);

    void execute(byte[] data);

    void close();

}
