package com.hpit;

import com.hpit.entity.Teacher;
import com.hpit.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Test;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
public class TestHibernate {
  Session session;
  Transaction transaction;
    {
        session= HibernateSessionFactory.getCurrentSession();//得到当前的session
        transaction = session.beginTransaction();//打开事务
    }

    @Test
    public void testSession(){
        System.out.println(session.toString());
    }

    @Test
    public void testSave(){ //插入
        Teacher teacher = new Teacher("2","郑亚杰",25,"黄冈");
        Serializable serializable=session.save(teacher);
        System.out.println(serializable); //打印的是序列化对象的序列号
    }

    @Test //增加
    public void testSave1(){
        Teacher teacher = new Teacher("6","杨过",50,"襄阳");
        Serializable serializable=session.save(teacher);
        System.out.println(serializable); //打印的是序列化对象的序列号
    }

    @Test
    public void testSaveOrUpdate(){ //先查有没有这条数据，如果没有就增加，有就不增加
        Teacher teacher = new Teacher("7","李世民",150,"长安");
        session.saveOrUpdate(teacher);
    }

    @Test //修改
    public void testUpdate(){
        Teacher teacher = (Teacher)session.get(Teacher.class,"5");//get(）返回Object
        teacher.setTaddress("海南省");
        session.update(teacher);
    }

    @Test //删除
    public void testDelete(){
        Teacher teacher = (Teacher)session.get(Teacher.class,"6");
        session.delete(teacher);
    }

    @Test //查询
    public void testGet(){
        Teacher teacher = (Teacher)session.get(Teacher.class,"5");
        System.out.println(teacher.toString());
        //第一次从缓存中查找，没有找到，去数据库中查找
        Teacher teacher2= (Teacher)session.get(Teacher.class,"5");
        System.out.println(teacher2.toString());
        //第二次直接从缓存中查找
    }

    @Test //查询
    public void testLoad(){
        Teacher teacher = (Teacher)session.load(Teacher.class,"5");
        System.out.println(teacher);
        //session.clear();//清除缓存中的所有数据
        session.evict(teacher);//清除缓存中的teacher这个数据(单个对象移除缓存)
        Teacher teacher2= (Teacher)session.load(Teacher.class,"5");
        System.out.println(teacher2.toString());
    }

    @Test
    public void testGetAndLoad(){
        Teacher teacher = (Teacher)session.get(Teacher.class,"5");
        System.out.println(teacher.toString());
        //先去缓存中查找id=5的这条数据，没有找到，就去数据库中加载这条数据到缓存中
        Teacher teacher2= (Teacher)session.load(Teacher.class,"5");
        System.out.println(teacher2);
        //直接去缓存中，提取id=5的这条数据记录
    }

    @Test
    public void testGetAndLoad2(){
        Teacher teacher = (Teacher)session.get(Teacher.class,"5");
        System.out.println(teacher.toString());
        //先去缓存中查找id=5的这条数据，没有找到，就去数据库中加载这条数据到缓存中
        Teacher teacher2= (Teacher)session.load(Teacher.class,"4");
        System.out.println(teacher2);
        //先去缓存中查找id=4的这条数据，没有找到，就去数据库中加载这条数据到缓存中
    }

    @After
    public void closeSession(){
        transaction.commit(); //提交事务
        HibernateSessionFactory.closeSession();//关闭session
    }
}
