Spring框架学习
=================
Spring七种传播行为
-----------------
### 什么是事务的传播行为？
事务传播行为（propagation behavior）指的就是当一个事务方法被另一个事务方法调用时，这个事务方法应该如何运行[^有问题上知乎]。

|序号|传播行为[^PROPAGATION]|描述|
|----|----|----|
|1|PROPAGATION_REQUIRED|如果当前没有事务，就创建一个新事务，如果当前存在事务，<br>就加入该事务，这是最常见的选择，也是Spring默认的事务传播行为。|
|2|PROPAGATION_SUPPORTS|支持当前事务，如果当前存在事务，就加入该事务，<br>如果当前不存在事务，就以非事务执行。|
|3|PROPAGATION_MANDATORY|支持当前事务，如果当前存在事务，就加入该事务，<br>如果当前不存在事务，就抛出异常。|
|4|PROPAGATION_REQUIRES_NEW|创建新事务，无论当前存不存在事务，都创建新事务。|
|5|PROPAGATION_NOT_SUPPORTED|以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。|
|6|PROPAGATION_NEVER|以非事务方式执行，如果当前存在事务，则抛出异常。|
|7|PROPAGATION_NESTED|如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，<br>则按REQUIRED属性执行。其实这7中我也没看懂，不过不急，咱们接下来直接看效果。|






[^有问题上知乎]:https://zhuanlan.zhihu.com/p/91779567
[^PROPAGATION]:美[prɑːpəˈɡeɪʃn]pu ruang pu










