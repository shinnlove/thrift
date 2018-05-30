/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package thrift.gencode.invoker.async;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import thrift.gencode.service.HelloService;
import thrift.gencode.service.User;

/**
 * @author shinnlove.jinsheng
 * @version $Id: NonblockingInvokerClient.java, v 0.1 2018-05-30 下午10:55 shinnlove.jinsheng Exp $$
 */
public class NonblockingInvokerClient {

    private final static String SERVER_IP = "127.0.0.1";

    private final static int    port      = 8091;

    private final static int    timeOut   = 1000;

    public void startClient() throws IOException, InterruptedException, TException {
        // 协议
        TProtocolFactory tprotocol = new TCompactProtocol.Factory();
        // 客户端管理器
        TAsyncClientManager clientManager = new TAsyncClientManager();
        // 传输 (客户端的Socket)
        TNonblockingTransport transport = new TNonblockingSocket(SERVER_IP, port, timeOut);

        // 创建异步client
        HelloService.AsyncClient asyncClient = new HelloService.AsyncClient(tprotocol,
            clientManager, transport);

        // 客户端异步调用
        User user = new User();
        user.setName("shinnlove");
        user.setEmail("306086640@qq.com");

        CountDownLatch latch = new CountDownLatch(1);
        AsyncMethodCallback callback = new AsyncInvokerCallback(latch);
        asyncClient.sayHello(user, callback);
        latch.await(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException, TException, IOException {
        NonblockingInvokerClient client = new NonblockingInvokerClient();
        client.startClient();

        Thread.sleep(10000);
    }

}