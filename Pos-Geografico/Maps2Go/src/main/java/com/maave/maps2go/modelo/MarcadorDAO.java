package com.maave.maps2go.modelo;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class MarcadorDAO extends AbstractDAO<Marcador>{
    
    public MarcadorDAO() {
        super();
    }
    
    @Override
    public void agregar(Marcador marcador) {
        super.agregar(marcador);
    }

    @Override
    public void actualizar(Marcador marcador) {
        super.actualizar(marcador);
    }

    @Override
    public void borrar(Marcador marcador) {
        super.borrar(marcador);
    }

    public Marcador consultar(int id) {
        return super.consultarInt(Marcador.class, id);
    }

    public List<Marcador> consultarTodos() {
        return super.consultarTodos(Marcador.class);
    }
    
    public String obtenerColor(String color){
    String colorMar = ""; 
    Session session = this.sessionFactory.openSession();
    Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Marcador where color = :color";
            Query query = session.createQuery(hql);
            query.setParameter("color", color);
            colorMar = (String)query.uniqueResult();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();

        }finally{
            session.close();
        }
        return colorMar;
    }
    
    public Marcador buscaMarcador(int id) {
        Marcador m = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Marcador where num_marcador = :num";
            Query query = session.createQuery(hql);
            query.setParameter("num", id);
            m = (Marcador)query.uniqueResult();
            tx.commit();
            
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();

        }finally{
            session.close();
        }
        return m;
    }

    public List<Marcador> marcadorPorTema (String tipo_tema){
        List<Marcador> obj =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Marcador where tipo_tema = :tipo_tema";
            Query query = session.createQuery(hql);
            query.setParameter("tipo_tema", tipo_tema);
            obj = (List<Marcador>)query.list();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return obj;
    }
    
    
}
