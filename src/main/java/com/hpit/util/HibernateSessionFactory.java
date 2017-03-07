package com.hpit.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
public class HibernateSessionFactory {
    private static Configuration configuration;
    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;
    private static ThreadLocal<?> threadLocal= new ThreadLocal<Object>();

    static {   //静态加载session工厂
        configuration = new Configuration().configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static Session getCurrentSession(){ //得到当前session
        Session session = (Session)threadLocal.get();
        if(session==null){
            session = sessionFactory.openSession();
        }
        return session;
    }

    public static  void closeSession(){  //关闭session
        Session session =(Session)threadLocal.get();
        if(session!=null){
          sessionFactory.close();
        }
    }
}
