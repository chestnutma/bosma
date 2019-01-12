package com.itheima.test;

import com.itheima.domain.Customer;
import com.itheima.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    @Test
    public void testSave(){
        //1.加载配置文件创建工厂（实体管理器工厂）对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过实体管理器工厂获取实体管理器
        EntityManager entityManager = factory.createEntityManager();
        //获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //4.完成增删改查操作：保存一个客户到数据库中
        Customer customer = new Customer();
        customer.setCustName("迪丽热巴");
        customer.setCustAddress("武汉");
        //保存操作
        entityManager.persist(customer);
        //5.提交事务
        transaction.commit();
        //6.释放资源
        entityManager.close();
        factory.close();
    }

    @Test
    public void testUtils(){
//        //1.加载配置文件创建工厂（实体管理器工厂）对象
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
//        //2.通过实体管理器工厂获取实体管理器
//        EntityManager entityManager = factory.createEntityManager();
        EntityManager entityManager = JpaUtils.getEntityManager();
        //获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //4.完成增删改查操作：保存一个客户到数据库中
        Customer customer = new Customer();
        customer.setCustName("迪丽热巴1");
        customer.setCustAddress("武汉");
        //保存操作
        entityManager.persist(customer);
        //5.提交事务
        transaction.commit();
        //6.释放资源
        entityManager.close();
        //factory.close();
    }

    /**
     * 根据id查询客户
     *  使用find方法查询：
     *      1.查询的对象就是当前客户对象本身
     *      2.在调用find方法的时候，就会发送sql语句查询数据库
     *  立即加载
     */
    @Test
    public void testFind(){
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //4.完成增删改查操作：
        Customer customer = entityManager.find(Customer.class, 2L);
        System.out.println(customer);
        //5.提交事务
        transaction.commit();
        //6.释放资源
        entityManager.close();
        //factory.close();
    }

    /**
     * 根据id查询客户
     *      getReference方法
     *          1.获取的对象是一个动态代理对象
     *          2.调用getReference方法不会立即发送sql语句查询数据库
     *              * 当调用查询结果对象的时候，才会发送查询的sql语句：什么时候用，什么时候发送sql语句查询数据库
     *
     * 延迟加载（懒加载）
     *      * 得到的是一个动态代理对象
     *      * 什么时候用，什么使用才会查询
     */
    @Test
    public void testReference(){
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //4.完成增删改查操作：
        Customer customer = entityManager.getReference(Customer.class, 2L);
        System.out.println(customer);
        //5.提交事务
        transaction.commit();
        //6.释放资源
        entityManager.close();
        //factory.close();
    }

    //删除客户的案例
    @Test
    public void testRemove(){
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //4.完成增删改查操作：
        Customer customer = entityManager.find(Customer.class, 2L);
        entityManager.remove(customer);
        //5.提交事务
        transaction.commit();
        //6.释放资源
        entityManager.close();
    }

    //更新客户的操作
    @Test
    public void testUpdate(){
        //1.通过工具类获取entityManager
        EntityManager entityManager = JpaUtils.getEntityManager();
        //获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //4.完成增删改查操作：
        Customer customer = entityManager.find(Customer.class, 1L);
        customer.setCustLevel("1");
        entityManager.merge(customer);
        //5.提交事务
        transaction.commit();
        //6.释放资源
        entityManager.close();
    }


}
