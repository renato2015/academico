/*
 * Dao.java
 *
 * Created on 05/01/2016, 14:48:04
 */

package com.br.projeto_academico.sql;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Renato Borges Cardoso
 * @version 1.00 05/01/2016
 * @param <T>
 */
public class Dao<T> {
    private static Dao instance;

    public static Dao getInstance(){
        if(instance == null){
            instance = new Dao();
        }
        return instance;
    }


    /**
     * Metodo generico para salvar dados
     * @param t
     * @param session
     */
    public void salva (T t, Session session){
        session.save(t);
    }

    public void excluir (T t , Session session){
        session.delete(t);
    }

    public void alterar (T t, Session session){
        session.update(t);
    }

    @SuppressWarnings("unchecked")
    public List<T> lista (Class classe, Session session){
        return session.createQuery("FROM " + classe.getName()).list();
    }


}
