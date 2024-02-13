package controller;

import database.HibernateUtil;
import model.Equipos;
import model.Ligas;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;

public class DAOLigas {
    private static SessionFactory sessionFactory;

    public DAOLigas(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    //Método que Crea las ligas
    public void insertarLiga(Ligas liga){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(liga);
        session.getTransaction().commit();
        session.close();
    }

    //Método que Actualiza las ligas
    public void actualizarLiga(Ligas ligaActualizada){
        if (ligaActualizada == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Liga no encontrada"
            );
            return;
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(ligaActualizada);
        session.getTransaction().commit();
        JOptionPane.showMessageDialog(
                null,
                "Liga " + ligaActualizada.getNombre_liga() + " actualizada"
        );
        session.close();
    }

    //Método que Elimina las Ligas
    public void eliminarLiga(Ligas ligaEliminada){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        if(ligaEliminada != null){
            JOptionPane.showMessageDialog(
                    null,
                    "Liga " + ligaEliminada.getNombre_liga() + " eliminada"
            );
            session.remove(ligaEliminada);
        }else JOptionPane.showMessageDialog(
                null,
                "Liga no encontrada"
        );
        session.getTransaction().commit();
        session.close();
    }

    //Método que asigna equipos a las ligas
    public void asignarEquiposALiga(Ligas liga, List<Equipos> equipos) {
        if (liga == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Liga no encontrada"
            );
            return;
        }

        if (equipos.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "No se han proporcionado equipos para asignar a la liga"
            );
            return;
        }

        liga.setEquipos(new HashSet<>(equipos));

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(liga);
        session.getTransaction().commit();
        JOptionPane.showMessageDialog(
                null,
                "Equipos asignados a la liga: " + liga.getNombre_liga()
        );
        session.close();
    }

    //Método que obtiene todas las ligas
    public static List<Ligas> obtenerTodasLasLigas() {
        Session session = sessionFactory.openSession();
        List<Ligas> ligas = session.createQuery("FROM Ligas", Ligas.class).getResultList();
        session.close();
        return ligas;
    }

}
