/**
 * DDD: interfaces 用户界面层（表示层/接口层/协议层）  最顶层
 * 负责向用户显示信息和解释用户命令
 * 请求应用层以获取用户所需要展现的数据(比如获取首页的商品数据)
 * 发送命令给应用层要求其执行某个用户命令(实现某个业务逻辑,比如用户要进行转账)
 * 它的主要工作就是将一个用户请求委派给一个或多个Service进行处理,也就是我们常说的Controller。
 * <p>
 * create in 2021/12/20 10:38 AM
 *
 * @author shishaodong
 * @version 0.0.1
 */
package com.matrixboot.hub.manager.interfaces;