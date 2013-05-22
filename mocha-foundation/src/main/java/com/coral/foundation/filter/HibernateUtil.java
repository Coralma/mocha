package com.coral.foundation.filter;
import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.proxy.HibernateProxyHelper;

//import com.ebao.appframework.webapp.MyNavigableApplication;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Class<?> resolveHibernateProxy (Object obj) {
        return HibernateProxyHelper.getClassWithoutInitializingProxy(obj);
    }
    
//    public static Serializable getIdentifier(Object obj) {
//        if (obj == null)
//            return null;
//        
//        Session session = ((MyNavigableApplication)MyNavigableApplication.getCurrent()).getSession();
//        ClassMetadata classMetadata = session.getSessionFactory()
//                .getClassMetadata(HibernateUtil.resolveHibernateProxy(obj));
//        Serializable identifier = classMetadata.getIdentifier(
//                obj, (SessionImplementor)session);
//        return identifier;
//    }
    
}