# HibernateOneToManyToOne
# 注解联级关联在多表操作中十分重要，如在User中插多一个用户同时关联几个Orders，如下：
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Set<Order> orders = new HashSet<>();
# 若未声明以上的联级关系的话 会报如下错误
Exception in thread "main" org.hibernate.TransientObjectException: object references an unsaved transient instance - save the transient instance before flushing: online.shixun.model.Order
	at org.hibernate.engine.internal.ForeignKeys.getEntityIdentifierIfNotUnsaved(ForeignKeys.java:249)
	at org.hibernate.type.EntityType.getIdentifier(EntityType.java:510)
	at org.hibernate.type.ManyToOneType.nullSafeSet(ManyToOneType.java:165)
	at org.hibernate.persister.collection.AbstractCollectionPersister.writeElement(AbstractCollectionPersister.java:863)
	at org.hibernate.persister.collection.AbstractCollectionPersister.recreate(AbstractCollectionPersister.java:1253)
	at org.hibernate.persister.collection.OneToManyPersister.recreate(OneToManyPersister.java:183)
	at org.hibernate.action.internal.CollectionRecreateAction.execute(CollectionRecreateAction.java:58)
	at org.hibernate.engine.spi.ActionQueue.execute(ActionQueue.java:377)
	at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:369)
	at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:292)
	at org.hibernate.event.internal.AbstractFlushingEventListener.performExecutions(AbstractFlushingEventListener.java:339)
	at org.hibernate.event.internal.DefaultFlushEventListener.onFlush(DefaultFlushEventListener.java:52)
	at org.hibernate.internal.SessionImpl.flush(SessionImpl.java:1234)
	at org.hibernate.internal.SessionImpl.managedFlush(SessionImpl.java:404)
	at org.hibernate.engine.transaction.internal.jdbc.JdbcTransaction.beforeTransactionCommit(JdbcTransaction.java:101)
	at org.hibernate.engine.transaction.spi.AbstractTransactionImpl.commit(AbstractTransactionImpl.java:175)
	at online.shixun.test.HibernateOneToManyToOneTest.destroy(HibernateOneToManyToOneTest.java:34)
	at online.shixun.test.HibernateOneToManyToOneTest.main(HibernateOneToManyToOneTest.java:79)
