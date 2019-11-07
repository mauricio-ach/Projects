package com.maave.maps2go.modelo;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDAO<T> {
    
    protected SessionFactory sessionFactory;

    public AbstractDAO() {
         this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    public SessionFactory getSessionFactory() {
        // Automatically generated method. Please do not modify this code.
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory session) {
        // Automatically generated method. Please do not modify this code.
        this.sessionFactory = session;
    }

    protected void agregar(T obj) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
    }

    protected void actualizar(T obj) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
    }

    protected void borrar(T obj) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(obj);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
    }

    protected T consultarInt(Class clazz, int id) {
        T obj =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            obj =(T)session.get(clazz, id);
            tx.commit();
            
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.close();
        
        }
        return obj;
    }
    
     protected T consultarString(Class clazz, String id) {
        T obj =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            obj =(T)session.get(clazz, id);
            tx.commit();
            
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.close();
        
        }
        return obj;
    }

    protected List<T> consultarTodos(Class clazz) {
        List<T> obj =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from "+clazz.getName();
            System.out.println(hql);
            Query query = session.createQuery(hql);
            obj = (List<T>)query.list();
            tx.commit();
            
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.close();
        
        }
        return obj;
    }

}
