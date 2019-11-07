package com.maave.maps2go.modelo;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class ComentarioDAO extends AbstractDAO<Comentario>{
    
    public ComentarioDAO() {
        super();
    }
    
    @Override
    public void agregar(Comentario comentario) {
        super.agregar(comentario);
    }

    @Override
    public void actualizar(Comentario comentario) {
        super.actualizar(comentario);
    }

    @Override
    public void borrar(Comentario comentario) {
        super.borrar(comentario);
    }

    public Comentario consultar(int id) {
        return super.consultarInt(Comentario.class, id);
    }

    public List<Comentario> consultarTodos() {
        return super.consultarTodos(Comentario.class);
    }    
  
    public List<Comentario> consultarOrden() {
        List<Comentario> obj = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Comentario c order by c.likes desc";
            Query query = session.createQuery(hql);
            obj = (List<Comentario>) query.list();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();

        }
        return obj;
    }
    
    public Comentario buscaId(int id){
        Comentario obj = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Comentario where num_comentario = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            obj = (Comentario) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();

        }
        return obj;
    }

    public boolean esPropio(int id, int user){
        Comentario obj = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Comentario where num_comentario = :id and id_usuario = :user";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("user", user);
            obj = (Comentario) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();

        }
        if(obj == null)
            return false;
        else
            return true;
    }

}
