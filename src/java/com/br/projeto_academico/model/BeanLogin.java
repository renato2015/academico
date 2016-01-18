/*
 * BeanLogin.java
 *
 * Created on 06/01/2016, 13:49:21
 */

package com.br.projeto_academico.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tekna <informatica@teknamed.com.br>
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "BeanLogin.findAll", query = "SELECT b FROM BeanLogin b"),
    @NamedQuery(name = "BeanLogin.findByIdLogin", query = "SELECT b FROM BeanLogin b WHERE b.idLogin = :idLogin"),
    @NamedQuery(name = "BeanLogin.findBySenha", query = "SELECT b FROM BeanLogin b WHERE b.senha = :senha"),
    @NamedQuery(name = "BeanLogin.findByStatus", query = "SELECT b FROM BeanLogin b WHERE b.status = :status")
})
public class BeanLogin implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_login")
    private Integer idLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "id_aluno", referencedColumnName = "id_aluno")
    @ManyToOne(optional = false)
    private BeanAluno idAluno;

    public BeanLogin ()
    {
    }

    public BeanLogin (Integer idLogin)
    {
        this.idLogin = idLogin;
    }

    public BeanLogin (Integer idLogin, String senha, String status)
    {
        this.idLogin = idLogin;
        this.senha = senha;
        this.status = status;
    }

    public Integer getIdLogin ()
    {
        return idLogin;
    }

    public void setIdLogin (Integer idLogin)
    {
        this.idLogin = idLogin;
    }

    public String getSenha ()
    {
        return senha;
    }

    public void setSenha (String senha)
    {
        this.senha = senha;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public BeanAluno getIdAluno ()
    {
        return idAluno;
    }

    public void setIdAluno (BeanAluno idAluno)
    {
        this.idAluno = idAluno;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (idLogin != null ? idLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeanLogin))
        {
            return false;
        }
        BeanLogin other = (BeanLogin) object;
        if ((this.idLogin == null && other.idLogin != null) || (this.idLogin != null && !this.idLogin.equals(other.idLogin)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString ()
    {
        return "com.br.projeto_academico.model.BeanLogin[ idLogin=" + idLogin + " ]";
    }

}
