/*
 * BeanProfessor.java
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
@Table(name = "professor")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "BeanProfessor.findAll", query = "SELECT b FROM BeanProfessor b"),
    @NamedQuery(name = "BeanProfessor.findByIdProfessor", query = "SELECT b FROM BeanProfessor b WHERE b.idProfessor = :idProfessor"),
    @NamedQuery(name = "BeanProfessor.findByNome", query = "SELECT b FROM BeanProfessor b WHERE b.nome = :nome"),
    @NamedQuery(name = "BeanProfessor.findByCpf", query = "SELECT b FROM BeanProfessor b WHERE b.cpf = :cpf"),
    @NamedQuery(name = "BeanProfessor.findBySituacao", query = "SELECT b FROM BeanProfessor b WHERE b.situacao = :situacao")
})
public class BeanProfessor implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_professor")
    private Integer idProfessor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "cpf")
    private String cpf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "situacao")
    private Character situacao;

    public BeanProfessor ()
    {
    }

    public BeanProfessor (Integer idProfessor)
    {
        this.idProfessor = idProfessor;
    }

    public BeanProfessor (Integer idProfessor, String nome, String cpf, Character situacao)
    {
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.cpf = cpf;
        this.situacao = situacao;
    }

    public Integer getIdProfessor ()
    {
        return idProfessor;
    }

    public void setIdProfessor (Integer idProfessor)
    {
        this.idProfessor = idProfessor;
    }

    public String getNome ()
    {
        return nome;
    }

    public void setNome (String nome)
    {
        this.nome = nome;
    }

    public String getCpf ()
    {
        return cpf;
    }

    public void setCpf (String cpf)
    {
        this.cpf = cpf;
    }

    public Character getSituacao ()
    {
        return situacao;
    }

    public void setSituacao (Character situacao)
    {
        this.situacao = situacao;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (idProfessor != null ? idProfessor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeanProfessor))
        {
            return false;
        }
        BeanProfessor other = (BeanProfessor) object;
        if ((this.idProfessor == null && other.idProfessor != null) || (this.idProfessor != null && !this.idProfessor.equals(other.idProfessor)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString ()
    {
        return "com.br.projeto_academico.model.BeanProfessor[ idProfessor=" + idProfessor + " ]";
    }

}
