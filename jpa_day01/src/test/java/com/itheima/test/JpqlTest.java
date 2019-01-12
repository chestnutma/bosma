package com.itheima.test;

import com.itheima.domain.Customer;
import com.itheima.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpqlTest {
    //查询全部
    @Test
    public void testFindAll(){
        //1.获取entityManager对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.查询全部
        String jpql = "from Customer";
        //创建Query查询对象，query对象才是执行jqpl的对象
        Query query = entityManager.createQuery(jpql);
        //发送查询，并封装结果集
        List resultList = query.getResultList();
        for (Object o : resultList) {
            System.out.println(o);
        }
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 排序查询： 倒序查询全部客户（根据id倒序）
     *      sql：SELECT * FROM cst_customer ORDER BY cust_id DESC
     *      jpql：from Customer order by custId desc
     *
     * 进行jpql查询
     *      1.创建query查询对象
     *      2.对参数进行赋值
     *      3.查询，并得到返回结果
     */
    @Test
    public void testOrders(){
        //1.获取entityManager对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.查询全部
        String jpql = "from Customer order by custId desc";
        //创建Query查询对象，query对象才是执行jqpl的对象
        Query query = entityManager.createQuery(jpql);
        //发送查询，并封装结果集
        List resultList = query.getResultList();
        for (Object o : resultList) {
            System.out.println(o);
        }
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 使用jpql查询，统计客户的总数
     *      sql：SELECT COUNT(cust_id) FROM cst_customer
     *      jpql：select count(custId) from Customer
     */
    @Test
    public void testCount() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        //开启事务 提交事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //3.查询全部
        //i.根据jpql语句创建Query查询对象
        String jpql = "select count(custId) from Customer";
        Query query = entityManager.createQuery(jpql);
        Object result = query.getSingleResult();
        System.out.println(result);
        transaction.commit();
        entityManager.close();
    }

    /**
     * 分页查询
     *      sql：select * from cst_customer limit 0,2
     *      jqpl : from Customer
     */
    @Test
    public void testPaged() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpql = "from Customer";
        Query query = entityManager.createQuery(jpql);
        query.setFirstResult(0);
        query.setMaxResults(1);
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        transaction.commit();
        entityManager.close();
    }

    /**
     * 条件查询
     *     案例：查询客户名称以‘传智播客’开头的客户
     *          sql：SELECT * FROM cst_customer WHERE cust_name LIKE  ?
     *          jpql : from Customer where custName like ?
     */
    @Test
    public void testCondition() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpql = "from Customer where custName like ?";
        Query query = entityManager.createQuery(jpql);
        //第一个参数：占位符的索引位置（从1开始），第二个参数：取值
        query.setParameter(1,"迪丽%");
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }
        transaction.commit();
        entityManager.close();
    }


    //jpa在查询时，在没有重写构造函数的时候是不能查询部分字段的，要查询实体中的部分字段，可以这样：添加一个要查询字段的构造函数。
    @Test
    public void testPart(){
        //1.获取entityManager对象
        EntityManager entityManager = JpaUtils.getEntityManager();
        //2.开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //3.查询部分字段
        String jpql = "select new Customer(custName,custAddress) from Customer";
        //创建Query查询对象，query对象才是执行jqpl的对象
        Query query = entityManager.createQuery(jpql);
        //发送查询，并封装结果集
        List resultList = query.getResultList();
        for (Object o : resultList) {
            System.out.println(o);
        }
        //4.提交事务
        transaction.commit();
        //5.释放资源
        entityManager.close();
    }

    @Test
    //数字包含9跳过
    public void test(){
        for (int i = 0; i <= 100; i++) {
            int ge = i%10;
            int shi = i/10%10;
            int bai = i/10/10%10;
            if (ge!=9 && shi!=9 && bai!=9){
                System.out.println(i);
            }
        }
    }
}
