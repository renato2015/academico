/*
 * MBLogin.java
 *
 * Created on 06/01/2016, 13:46:47
 */

package com.br.projeto_academico.managedbean;

import com.br.projeto_academico.model.BeanAluno;
import com.br.projeto_academico.model.BeanLogin;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tekna <informatica@teknamed.com.br>
 */
@ManagedBean(name = "login")
@SessionScoped
public class MBLogin
{
    private final BeanLogin bLogin = new BeanLogin();
    private final BeanAluno bAluno = new BeanAluno();

    // Metodo getter
    public BeanLogin getbLogin ()
    {
        return bLogin;
    }

    public BeanAluno getbAluno ()
    {
        return bAluno;
    }


    /**
     * Metodo para logar e gravar dados na sessão
     * @return pagina de bem vindo
     */
    public String logar(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("usuario", bAluno.getNome());
        return "bem vindo.xhtml?faces-redirect=true";
    }

    /**
     * Metodo para sair do sistema e destruir a sessão do usuário.
     * @return pagina de index
     */
    public String deslogar(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.removeAttribute("usuario");
        session.invalidate();
        return "index.xhtml?faces-redirect=true";
    }

}
