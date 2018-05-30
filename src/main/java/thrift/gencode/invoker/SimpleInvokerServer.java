/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package thrift.gencode.invoker;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import thrift.gencode.service.HelloService;
import thrift.gencode.service.HelloServiceImpl;

/**
 * 简单调用-服务端。
 *
 * @author shinnlove.jinsheng
 * @version $Id: SimpleInvokerServer.java, v 0.1 2018-05-30 下午10:16 shinnlove.jinsheng Exp $$
 */
public class SimpleInvokerServer {

    private final static int port = 8091;

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

    public static void main(String[] args) throws TTransportException {
        SimpleInvokerServer server = new SimpleInvokerServer();
        server.startServer();
    }

}