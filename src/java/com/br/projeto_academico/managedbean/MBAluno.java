package com.br.projeto_academico.managedbean;

import com.br.projeto_academico.model.BeanAluno;
import com.br.projeto_academico.model.BeanLogin;
import com.br.projeto_academico.sql.Dao;
import com.br.projeto_academico.util.Criptografia;
import com.br.projeto_academico.util.HibernateUtil;
import com.br.projeto_academico.util.Mensagem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Tekna <informatica@teknamed.com.br>
 */
@ManagedBean(name = "aluno")
public class MBAluno
{
    private BeanAluno bAluno = new BeanAluno();
    private BeanLogin bLogin = new BeanLogin();

    private Session session;
    private Transaction tx;

    public MBAluno (){
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }



    @SuppressWarnings("unchecked")
    public void salvar(){
        try{
            Dao.getInstance().salva(bAluno, session);
            bLogin.setIdAluno(bAluno);
            bLogin.setStatus(bAluno.getSituacao().toString());
            bLogin.setSenha(Criptografia.sha256(bLogin.getSenha()));
            Dao.getInstance().salva(bLogin, session);
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
            Dao.getInstance().excluir(bAluno, session);
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
            Dao.getInstance().alterar(bAluno, session);
        }catch (Exception e){
            tx.rollback();
            Mensagem.getInstance().erro("Erro ao alterar registro!" + e.getMessage());
        }finally{
            tx.commit();
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<BeanAluno> listAll(){
        List<BeanAluno> lista = new ArrayList<>();
        try{
            lista = Dao.getInstance().lista(BeanAluno.class, session);
        }catch (Exception e){
            tx.rollback();
            Mensagem.getInstance().erro("Erro ao consultar registro!" + e.getMessage());
        }finally{
            tx.commit();
            session.close();
        }
        return lista;
    }

    public void relatorioAlunos(){
        try
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.responseComplete();
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext();

            Map parametro = new HashMap();
//          parametro.put("id", '1');
            //C:\Home\ProjetoAcademico\src\java\com\br\projeto_academico\relatorios
            JasperPrint print = JasperFillManager.fillReport(servletContext.getRealPath("relatorios/alunos.jasper"), parametro );
            JasperViewer view = new JasperViewer(print);
            view.viewReport(print);
        }
        catch (Exception e){
            Mensagem.getInstance().erro("Erro ao imprimir relatorio" + e.getMessage());
        }
    }

    public BeanAluno getbAluno ()
    {
        return bAluno;
    }

    public void setbAluno (BeanAluno bAluno)
    {
        this.bAluno = bAluno;
    }

    public BeanLogin getbLogin ()
    {
        return bLogin;
    }

    public void setbLogin (BeanLogin bLogin)
    {
        this.bLogin = bLogin;
    }


}
