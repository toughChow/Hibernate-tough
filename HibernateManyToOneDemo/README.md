# HibernateManyToOneDemo
# OneToMany: 由一的一方来管理。
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Set<Order> orders = new HashSet<>();
# 用户来管理多个订单，在User类(1)中通过以上代码可在Order表(n)中添加一列'user_id'
######
# ManyToOne: 由多的一方来管理。
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "status_id")
	private Status status;
# 一种状态下有多个订单，在Order类(n)中通过以上代码可在Order表(n)中添加一列'status_id'
