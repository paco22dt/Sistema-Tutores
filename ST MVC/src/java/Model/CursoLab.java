/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "curso_lab")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoLab.findAll", query = "SELECT c FROM CursoLab c"),
    @NamedQuery(name = "CursoLab.findBySeccion", query = "SELECT c FROM CursoLab c WHERE c.seccion = :seccion")})
public class CursoLab implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "seccion")
    private Integer seccion;
    @JoinColumn(name = "CURSO_TE_id_curso", referencedColumnName = "id_curso_tutor")
    @ManyToOne(optional = false)
    private CursoTutor cURSOTEidcurso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cURSOLABseccion")
    private Collection<CursoTutor> cursoTutorCollection;

    public CursoLab() {
    }

    public CursoLab(Integer seccion) {
        this.seccion = seccion;
    }

    public Integer getSeccion() {
        return seccion;
    }

    public void setSeccion(Integer seccion) {
        this.seccion = seccion;
    }

    public CursoTutor getCURSOTEidcurso() {
        return cURSOTEidcurso;
    }

    public void setCURSOTEidcurso(CursoTutor cURSOTEidcurso) {
        this.cURSOTEidcurso = cURSOTEidcurso;
    }

    @XmlTransient
    public Collection<CursoTutor> getCursoTutorCollection() {
        return cursoTutorCollection;
    }

    public void setCursoTutorCollection(Collection<CursoTutor> cursoTutorCollection) {
        this.cursoTutorCollection = cursoTutorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seccion != null ? seccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoLab)) {
            return false;
        }
        CursoLab other = (CursoLab) object;
        if ((this.seccion == null && other.seccion != null) || (this.seccion != null && !this.seccion.equals(other.seccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.CursoLab[ seccion=" + seccion + " ]";
    }
    
}
