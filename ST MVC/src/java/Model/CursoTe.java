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
 * @author Francisco
 */
@Entity
@Table(name = "curso_te")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoTe.findAll", query = "SELECT c FROM CursoTe c"),
    @NamedQuery(name = "CursoTe.findByIdCurso", query = "SELECT c FROM CursoTe c WHERE c.idCurso = :idCurso"),
    @NamedQuery(name = "CursoTe.findByNombre", query = "SELECT c FROM CursoTe c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CursoTe.findBySeccion", query = "SELECT c FROM CursoTe c WHERE c.seccion = :seccion")})
public class CursoTe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso")
    private Integer idCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seccion")
    private int seccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoTe")
    private Collection<NotaCurso> notaCursoCollection;

    public CursoTe() {
    }

    public CursoTe(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public CursoTe(Integer idCurso, String nombre, int seccion) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.seccion = seccion;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }

    @XmlTransient
    public Collection<NotaCurso> getNotaCursoCollection() {
        return notaCursoCollection;
    }

    public void setNotaCursoCollection(Collection<NotaCurso> notaCursoCollection) {
        this.notaCursoCollection = notaCursoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoTe)) {
            return false;
        }
        CursoTe other = (CursoTe) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.CursoTe[ idCurso=" + idCurso + " ]";
    }
    
}
