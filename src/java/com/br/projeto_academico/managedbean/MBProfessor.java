/*
 * MBProfessor.java
 *
 * Created on 06/01/2016, 13:46:38
 */

package com.br.projeto_academico.managedbean;

import com.br.projeto_academico.model.BeanProfessor;
import com.br.projeto_academico.sql.Dao;
import com.br.projeto_academico.util.HibernateUtil;
import com.br.projeto_academico.util.Mensagem;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Tekna <informatica@teknamed.com.br>
 */
@ManagedBean(name = "professor")
public class MBProfessor
{
     private BeanProfessor bProfessor = new BeanProfessor();


    private Session session;
    private Transaction tx;

    public MBProfessor (){
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }



    @SuppressWarnings("unchecked")
    public void salvar(){
        try{
            Dao.getInstance().salva(bProfessor, session);
        }catch (Exception e){
            tx.rollback();
            Mensagem.getInstance().erro("Erro ao salvar registro!" + e.getMessage());
        }finally{
            tx.commit();
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public void excluir(){
        try{
            Dao.getInstance().excluir(bProfessor, session);
        }catch (Exception e){
            tx.rollback();
            Mensagem.getInstance().erro("Erro ao excluir registro!" + e.getMessage());
        }finally{
            tx.commit();
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public void alterar(){
        try{
            Dao.getInstance().alterar(bProfessor, session);
        }catch (Exception e){
            tx.rollback();
            Mensagem.getInstance().erro("Erro ao alterar registro!" + e.getMessage());
        }finally{
            tx.commit();
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<BeanProfessor> listAll(){
        List<BeanProfessor> lista = new ArrayList<>();
        try{
            lista = Dao.getInstance().lista(BeanProfessor.class, session);
        }catch (Exception e){
            tx.rollback();
            Mensagem.getInstance().erro("Erro ao consultar registro!" + e.getMessage());
        }finally{
            tx.commit();
            session.close();
        }
        return lista;
    }

    public BeanProfessor getbProfessor ()
    {
        return bProfessor;
    }

    public void setbProfessor (BeanProfessor bProfessor)
    {
        this.bProfessor = bProfessor;
    }

    
}
