/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package thrift.gencode.invoker.async;

import java.util.concurrent.CountDownLatch;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

import thrift.gencode.service.HelloService;

/**
 * 异步回调结果类。
 *
 * @author shinnlove.jinsheng
 * @version $Id: AsyncInvokerCallback.java, v 0.1 2018-05-30 下午11:00 shinnlove.jinsheng Exp $$
 */
public class AsyncInvokerCallback implements
                                 AsyncMethodCallback<HelloService.AsyncClient.sayHello_call> {

    private CountDownLatch latch;

    public AsyncInvokerCallback(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onComplete(HelloService.AsyncClient.sayHello_call response) {
        try {
            System.out.println("AsyncInvokerCallback response: " + response.getResult());
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

    @Override
    public void onError(Exception exception) {
        latch.countDown();
    }

}