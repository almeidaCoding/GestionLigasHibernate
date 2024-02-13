package controller;

import database.HibernateUtil;
import model.Partido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;

public class DAOPartido {
    private final SessionFactory sessionFactory;

    public DAOPartido(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void insertarPartido(Partido partido){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(partido);
        session.getTransaction().commit();
        session.close();
    }

    public void asignarResultados(int idPartido, int golesEquipoLocal, int golesEquipoVisitante) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Partido partido = session.get(Partido.class, idPartido);
        if (partido != null) {
            partido.setGoles_equipo_local(golesEquipoLocal);
            partido.setGoles_equipo_visitante(golesEquipoVisitante);
            session.getTransaction().commit();
        }
        session.close();
    }

    public void actualizarPartido(Partido partidoActualizado){
        if (partidoActualizado == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Partido no encontrado"
            );
            return;
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(partidoActualizado);
        session.getTransaction().commit();
        JOptionPane.showMessageDialog(
                null,
                "Partido actualizado con éxito"
        );
        session.close();
    }

    public List<Partido> consultarCalendarioPartidos() {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        String hql = "FROM Partido";
        Query<Partido> query = session.createQuery(hql, Partido.class);
        List<Partido> partidos = query.getResultList();
        session.getTransaction().commit();

        session.close();
        return partidos;
    }

    public List<Partido> consultarPartidosConResultados() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "FROM Partido WHERE goles_equipo_local IS NOT NULL AND goles_equipo_visitante IS NOT NULL";
            Query<Partido> query = session.createQuery(hql, Partido.class);
            List<Partido> partidos = query.getResultList();
            session.getTransaction().commit();
            return partidos;
        }
    }
}
