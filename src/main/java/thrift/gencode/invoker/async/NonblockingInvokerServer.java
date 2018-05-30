/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package thrift.gencode.invoker.async;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

import thrift.gencode.service.HelloService;
import thrift.gencode.service.HelloServiceImpl;

/**
 * 异步非阻塞I/O服务调用示例。
 *
 * @author shinnlove.jinsheng
 * @version $Id: NonblockingInvokerServer.java, v 0.1 2018-05-30 下午10:32 shinnlove.jinsheng Exp $$
 */
public class NonblockingInvokerServer {

    private final static int port = 8091;

    /**
     * 创建并开启一个非阻塞的thrift服务器。
     * 
     * @throws TTransportException
     */
    public void startServer() throws TTransportException {

        // 创建processor (服务代理)
        TProcessor tProcessor = new HelloService.Processor<HelloService.Iface>(
            new HelloServiceImpl());

        // 创建transport数据传输方式，非阻塞需要使用这种方式传输 (非阻塞独有的Transport类型)
        TFramedTransport.Factory transport = new TFramedTransport.Factory();

        // 创建protocol数据传输协议 (协议都是有的)
        TCompactProtocol.Factory protocol = new TCompactProtocol.Factory();

        // 创建non-blocking的transport (非阻塞的服务端套接字)
        TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);

        // 服务器参数
        TNonblockingServer.Args args = new TNonblockingServer.Args(serverTransport);
        args.processor(tProcessor);
        args.transportFactory(transport);
        args.protocolFactory(protocol);

        // 创建非阻塞服务器
        TServer server = new TNonblockingServer(args);
        // 开启服务
        server.serve();

    }

    public static void main(String[] args) throws TTransportException {
        NonblockingInvokerServer server = new NonblockingInvokerServer();
        server.startServer();
    }

}