/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package thrift.gencode.invoker;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import thrift.gencode.service.HelloService;
import thrift.gencode.service.User;

/**
 * 简单调用-客户端。
 *
 * @author shinnlove.jinsheng
 * @version $Id: SimpleInvokerClient.java, v 0.1 2018-05-30 下午9:31 shinnlove.jinsheng Exp $$
 */
public class SimpleInvokerClient {

    private final static String SERVER_IP = "127.0.0.1";

    private final static int    port      = 8091;

    private final static int    timeOut   = 1000;


    public void startClient() throws TException {
        // 创建 TTransport (其实是个客户端套接字)
        TTransport transport = new TSocket(SERVER_IP, port, timeOut);
        // 创建 TProtocol (使用二进制协议传输)
        TProtocol protocol = new TBinaryProtocol(transport);
        // 基于 TTransport和TProtocol 创建client (生成的服务类中有客户端，传入协议)
        HelloService.Client client = new HelloService.Client(protocol);
        // 打开套接字
        transport.open();

        // 创建数据
        User user = new User();
        user.setName("shinnlove");
        user.setEmail("306086640@qq.com");

        // 调用服务客户端，像调用本地服务一样
        String content = client.sayHello(user);
        System.out.println("content:" + content);
    }

    public static void main(String[] args) throws TException {
        SimpleInvokerClient simpleInvokerClient = new SimpleInvokerClient();
        simpleInvokerClient.startClient();
    }

}