/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package thrift.gencode.invoker;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import thrift.gencode.service.HelloService;
import thrift.gencode.service.HelloServiceImpl;
import thrift.gencode.service.User;

/**
 * 简单调用。
 *
 * @author shinnlove.jinsheng
 * @version $Id: SimpleInvoker.java, v 0.1 2018-05-30 下午9:31 shinnlove.jinsheng Exp $$
 */
public class SimpleInvoker {

    private final static String SERVER_IP = "127.0.0.1";

    private final static int    port      = 8091;

    private final static int    timeOut   = 1000;

    /**
     * 启动服务，使用的是thrift的`TSimpleServer`，对外提供服务是`serve()`方法。
     *
     * @throws TTransportException
     */
    public void startServer() throws TTransportException {
        // 创建transport (这是阻塞通信的)
        TServerSocket serverTransport = new TServerSocket(port);

        // 创建protocol
        TBinaryProtocol.Factory protocol = new TBinaryProtocol.Factory();

        // 创建processor
        TProcessor tProcessor = new HelloService.Processor<HelloService.Iface>(
            new HelloServiceImpl());

        // 定义服务器参数
        TServer.Args args = new TServer.Args(serverTransport);
        args.protocolFactory(protocol);
        args.processor(tProcessor);

        // 将`processor`、`transport`、`protocol`这三个参数设置到服务器server中
        TServer server = new TSimpleServer(args);
        // 开启服务
        server.serve();
    }

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
        SimpleInvoker simpleInvoker = new SimpleInvoker();
        simpleInvoker.startServer();
        simpleInvoker.startClient();
    }

}