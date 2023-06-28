package com.beans;

public class UserDaoImpl {
    // 实例化一个HibernateTemplate对象，用于执行持久化操作
    private HibernateTemplate ht=null;
    // Hibernate持久化操作所需SessionFactory
    private SessionFactory sessionFactory=null;

    // 用于依赖注入的setter方法
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    // 初始化HibernateTemplate方法
    private HibernateTemplate getHibernateTemplate() {
        if(ht==null) {
            ht=new HibernateTemplate(sessionFactory);
        }
        return ht;
    }

    public User get(Integer id) {
        // 获取对应表中id为某个值的数据，id为主键索引
        return getHibernateTemplate().get(User.class,id);
    }

    public Integer save(User user) {
        return (Integer) getHibernateTemplate().save(user);
    }

    public List<User> findName(String name) {
        // 根据名称查找匹配的User
        return (List<User>) getHibernateTemplate().find("from User u where u.name=?",name);
    }
}
