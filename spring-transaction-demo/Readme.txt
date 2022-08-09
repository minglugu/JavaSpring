Spring事务和事务传播机制-13
https://www.baeldung.com/spring-transactional-propagation-isolation

Spring编程式事务(Programmatic transaction management)：手动加入
Spring声明式事务(Declarative transaction management)：自动加入。利用注解@Transactional
	1. @Test里面，加了@Transactional注解时，会进行回滚操作
	2.  在Spring声明式事务中，@Transactional，在成功处理事务时，会成功加入。但是如果失败时，会进行回滚操作。
https://docs.spring.io/spring-framework/docs/1.2.x/reference/transaction.html


MySQL的事务隔离级别有4种
1. Read uncommitted
2. Read committed
3. Repeatable read
4. Serializable
https://docs.microsoft.com/en-us/sql/t-sql/statements/set-transaction-isolation-level-transact-sql?view=sql-server-ver16

稀土掘金：juejin.cn/column/7035067383643373605

https://juejin.cn/post/7114494161155260429
面试突击61：事务隔离级别



