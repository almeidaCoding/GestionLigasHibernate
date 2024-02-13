package controller;

import database.HibernateUtil;
import model.Equipos;
import model.Ligas;
import model.Partido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;
import java.util.Queue;

public class DAOEquipos {
    private static SessionFactory sessionFactory;

    public DAOEquipos(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void insertarEquipo(Equipos equipo){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(equipo);
        session.getTransaction().commit();
        session.close();
    }

    public void actualizarEquipos(Equipos equiposActualizados){
        if (equiposActualizados == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Equipo no encontrado"
            );
            return;
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(equiposActualizados);
        session.getTransaction().commit();
        JOptionPane.showMessageDialog(
                null,
                "Equipo " + equiposActualizados.getNombre_equipo() + " actualizado"
        );
        session.close();
    }

    public void eliminarEquipos(int idEquipo1, int idEquipo2) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Equipos equipo1 = session.get(Equipos.class, idEquipo1);
        Equipos equipo2 = session.get(Equipos.class, idEquipo2);

        if (equipo1 != null && equipo2 != null) {
            session.remove(equipo1);
            session.remove(equipo2);
            JOptionPane.showMessageDialog(
                    null,
                    "Los equipos" + equipo1.getNombre_equipo() + " y" + equipo2.getNombre_equipo()
                    + " Se han eleimiado correctamente",
                    "Eliminación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Uno o ambos equipos no fueron encontrados",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        session.getTransaction().commit();
        session.close();
    }

    public static List<Equipos> obtenerEquipos (){
        Session session = sessionFactory.openSession();
        List<Equipos> equipos = null;
            session.beginTransaction();
            String hql = "FROM Equipos";
            Query<Equipos> query = session.createQuery(hql, Equipos.class);
            equipos = query.getResultList();
            session.getTransaction().commit();

            session.close();
        return equipos;
    }
}
