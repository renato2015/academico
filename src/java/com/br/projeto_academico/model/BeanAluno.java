/*
 * BeanAluno.java
 *
 * Created on 06/01/2016, 13:49:21
 */

package com.br.projeto_academico.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tekna <informatica@teknamed.com.br>
 */
@Entity
@Table(name = "aluno")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "BeanAluno.findAll", query = "SELECT b FROM BeanAluno b"),
    @NamedQuery(name = "BeanAluno.findByIdAluno", query = "SELECT b FROM BeanAluno b WHERE b.idAluno = :idAluno"),
    @NamedQuery(name = "BeanAluno.findByNome", query = "SELECT b FROM BeanAluno b WHERE b.nome = :nome"),
    @NamedQuery(name = "BeanAluno.findByCpf", query = "SELECT b FROM BeanAluno b WHERE b.cpf = :cpf"),
    @NamedQuery(name = "BeanAluno.findBySituacao", query = "SELECT b FROM BeanAluno b WHERE b.situacao = :situacao")
})
public class BeanAluno implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_aluno")
    private Integer idAluno;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAluno")
    private List<BeanLogin> beanLoginList;
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "matricula")
    private String matricula;

    public BeanAluno ()
    {
    }

    public BeanAluno (Integer idAluno)
    {
        this.idAluno = idAluno;
    }

    public BeanAluno (Integer idAluno, String nome, String cpf, Character situacao)
    {
        this.idAluno = idAluno;
        this.nome = nome;
        this.cpf = cpf;
        this.situacao = situacao;
    }

    public Integer getIdAluno ()
    {
        return idAluno;
    }

    public void setIdAluno (Integer idAluno)
    {
        this.idAluno = idAluno;
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

    public String getMatricula ()
    {
        return matricula;
    }

    public void setMatricula (String matricula)
    {
        this.matricula = matricula;
    }


    
    @XmlTransient
    public List<BeanLogin> getBeanLoginList ()
    {
        return beanLoginList;
    }

    public void setBeanLoginList (List<BeanLogin> beanLoginList)
    {
        this.beanLoginList = beanLoginList;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (idAluno != null ? idAluno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeanAluno))
        {
            return false;
        }
        BeanAluno other = (BeanAluno) object;
        if ((this.idAluno == null && other.idAluno != null) || (this.idAluno != null && !this.idAluno.equals(other.idAluno)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString ()
    {
        return "com.br.projeto_academico.model.BeanAluno[ idAluno=" + idAluno + " ]";
    }

}
