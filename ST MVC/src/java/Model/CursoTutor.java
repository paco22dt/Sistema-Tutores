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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "curso_tutor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoTutor.findAll", query = "SELECT c FROM CursoTutor c"),
    @NamedQuery(name = "CursoTutor.findByIdCursoTutor", query = "SELECT c FROM CursoTutor c WHERE c.idCursoTutor = :idCursoTutor"),
    @NamedQuery(name = "CursoTutor.findByTipoCurso", query = "SELECT c FROM CursoTutor c WHERE c.tipoCurso = :tipoCurso"),
    @NamedQuery(name = "CursoTutor.findByA\u00f1o", query = "SELECT c FROM CursoTutor c WHERE c.a\u00f1o = :a\u00f1o"),
    @NamedQuery(name = "CursoTutor.findByCiclo", query = "SELECT c FROM CursoTutor c WHERE c.ciclo = :ciclo")})
public class CursoTutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_curso_tutor")
    private Integer idCursoTutor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tipo_curso")
    private String tipoCurso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a\u00f1o")
    private int año;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ciclo")
    private int ciclo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cURSOTUTORidcursotutor")
    private Collection<Periodo> periodoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cURSOTEidcurso")
    private Collection<CursoLab> cursoLabCollection;
    @JoinColumn(name = "TUTOR_carnet", referencedColumnName = "carnet")
    @ManyToOne(optional = false)
    private Tutor tUTORcarnet;
    @JoinColumn(name = "DOCENTE_id_docente", referencedColumnName = "id_docente")
    @ManyToOne(optional = false)
    private Docente dOCENTEiddocente;
    @JoinColumn(name = "CURSO_LAB_seccion", referencedColumnName = "seccion")
    @ManyToOne(optional = false)
    private CursoLab cURSOLABseccion;

    public CursoTutor() {
    }

    public CursoTutor(Integer idCursoTutor) {
        this.idCursoTutor = idCursoTutor;
    }

    public CursoTutor(Integer idCursoTutor, String tipoCurso, int año, int ciclo) {
        this.idCursoTutor = idCursoTutor;
        this.tipoCurso = tipoCurso;
        this.año = año;
        this.ciclo = ciclo;
    }

    public Integer getIdCursoTutor() {
        return idCursoTutor;
    }

    public void setIdCursoTutor(Integer idCursoTutor) {
        this.idCursoTutor = idCursoTutor;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    @XmlTransient
    public Collection<Periodo> getPeriodoCollection() {
        return periodoCollection;
    }

    public void setPeriodoCollection(Collection<Periodo> periodoCollection) {
        this.periodoCollection = periodoCollection;
    }

    @XmlTransient
    public Collection<CursoLab> getCursoLabCollection() {
        return cursoLabCollection;
    }

    public void setCursoLabCollection(Collection<CursoLab> cursoLabCollection) {
        this.cursoLabCollection = cursoLabCollection;
    }

    public Tutor getTUTORcarnet() {
        return tUTORcarnet;
    }

    public void setTUTORcarnet(Tutor tUTORcarnet) {
        this.tUTORcarnet = tUTORcarnet;
    }

    public Docente getDOCENTEiddocente() {
        return dOCENTEiddocente;
    }

    public void setDOCENTEiddocente(Docente dOCENTEiddocente) {
        this.dOCENTEiddocente = dOCENTEiddocente;
    }

    public CursoLab getCURSOLABseccion() {
        return cURSOLABseccion;
    }

    public void setCURSOLABseccion(CursoLab cURSOLABseccion) {
        this.cURSOLABseccion = cURSOLABseccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursoTutor != null ? idCursoTutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoTutor)) {
            return false;
        }
        CursoTutor other = (CursoTutor) object;
        if ((this.idCursoTutor == null && other.idCursoTutor != null) || (this.idCursoTutor != null && !this.idCursoTutor.equals(other.idCursoTutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.CursoTutor[ idCursoTutor=" + idCursoTutor + " ]";
    }
    
}
