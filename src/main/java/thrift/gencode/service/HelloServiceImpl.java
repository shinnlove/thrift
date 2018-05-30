/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package thrift.gencode.service;

import org.apache.thrift.TException;

/**
 * 基础服务接口的实现。
 *
 * @author shinnlove.jinsheng
 * @version $Id: HelloServiceImpl.java, v 0.1 2018-05-30 下午9:26 shinnlove.jinsheng Exp $$
 */
public class HelloServiceImpl implements HelloService.Iface {

    @Override
    public String sayHello(User user) throws TException {
        return "Hello " + user.getName() +", your email is "+ user.getEmail();
    }

}