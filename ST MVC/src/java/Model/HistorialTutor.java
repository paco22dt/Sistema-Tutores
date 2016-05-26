/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
 * @author Francisco
 */
@Entity
@Table(name = "historial_tutor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialTutor.findAll", query = "SELECT h FROM HistorialTutor h"),
    @NamedQuery(name = "HistorialTutor.findByIdHistorialTutor", query = "SELECT h FROM HistorialTutor h WHERE h.idHistorialTutor = :idHistorialTutor"),
    @NamedQuery(name = "HistorialTutor.findByCurso", query = "SELECT h FROM HistorialTutor h WHERE h.curso = :curso"),
    @NamedQuery(name = "HistorialTutor.findBySeccion", query = "SELECT h FROM HistorialTutor h WHERE h.seccion = :seccion"),
    @NamedQuery(name = "HistorialTutor.findByNombreDocente", query = "SELECT h FROM HistorialTutor h WHERE h.nombreDocente = :nombreDocente"),
    @NamedQuery(name = "HistorialTutor.findByApellidoDocente", query = "SELECT h FROM HistorialTutor h WHERE h.apellidoDocente = :apellidoDocente"),
    @NamedQuery(name = "HistorialTutor.findByNotaEvaluacion", query = "SELECT h FROM HistorialTutor h WHERE h.notaEvaluacion = :notaEvaluacion")})
public class HistorialTutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial_tutor")
    private Integer idHistorialTutor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "curso")
    private String curso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seccion")
    private int seccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_docente")
    private String nombreDocente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "apellido_docente")
    private String apellidoDocente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota_evaluacion")
    private int notaEvaluacion;
    @JoinColumn(name = "TUTOR_carnet", referencedColumnName = "carnet")
    @ManyToOne(optional = false)
    private Tutor tUTORcarnet;

    public HistorialTutor() {
    }

    public HistorialTutor(Integer idHistorialTutor) {
        this.idHistorialTutor = idHistorialTutor;
    }

    public HistorialTutor(Integer idHistorialTutor, String curso, int seccion, String nombreDocente, String apellidoDocente, int notaEvaluacion) {
        this.idHistorialTutor = idHistorialTutor;
        this.curso = curso;
        this.seccion = seccion;
        this.nombreDocente = nombreDocente;
        this.apellidoDocente = apellidoDocente;
        this.notaEvaluacion = notaEvaluacion;
    }

    public Integer getIdHistorialTutor() {
        return idHistorialTutor;
    }

    public void setIdHistorialTutor(Integer idHistorialTutor) {
        this.idHistorialTutor = idHistorialTutor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getApellidoDocente() {
        return apellidoDocente;
    }

    public void setApellidoDocente(String apellidoDocente) {
        this.apellidoDocente = apellidoDocente;
    }

    public int getNotaEvaluacion() {
        return notaEvaluacion;
    }

    public void setNotaEvaluacion(int notaEvaluacion) {
        this.notaEvaluacion = notaEvaluacion;
    }

    public Tutor getTUTORcarnet() {
        return tUTORcarnet;
    }

    public void setTUTORcarnet(Tutor tUTORcarnet) {
        this.tUTORcarnet = tUTORcarnet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorialTutor != null ? idHistorialTutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialTutor)) {
            return false;
        }
        HistorialTutor other = (HistorialTutor) object;
        if ((this.idHistorialTutor == null && other.idHistorialTutor != null) || (this.idHistorialTutor != null && !this.idHistorialTutor.equals(other.idHistorialTutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.HistorialTutor[ idHistorialTutor=" + idHistorialTutor + " ]";
    }
    
}
