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
@Table(name = "tutor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t"),
    @NamedQuery(name = "Tutor.findByCarnet", query = "SELECT t FROM Tutor t WHERE t.carnet = :carnet"),
    @NamedQuery(name = "Tutor.findByNombre", query = "SELECT t FROM Tutor t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tutor.findByApellido", query = "SELECT t FROM Tutor t WHERE t.apellido = :apellido"),
    @NamedQuery(name = "Tutor.findByCarrera", query = "SELECT t FROM Tutor t WHERE t.carrera = :carrera"),
    @NamedQuery(name = "Tutor.findByPorcentaje", query = "SELECT t FROM Tutor t WHERE t.porcentaje = :porcentaje"),
    @NamedQuery(name = "Tutor.findByTipoAyudaEconomica", query = "SELECT t FROM Tutor t WHERE t.tipoAyudaEconomica = :tipoAyudaEconomica"),
    @NamedQuery(name = "Tutor.findByCorreo", query = "SELECT t FROM Tutor t WHERE t.correo = :correo"),
    @NamedQuery(name = "Tutor.findByCicloActual", query = "SELECT t FROM Tutor t WHERE t.cicloActual = :cicloActual"),
    @NamedQuery(name = "Tutor.findByPromedio", query = "SELECT t FROM Tutor t WHERE t.promedio = :promedio"),
    @NamedQuery(name = "Tutor.findByTelefono", query = "SELECT t FROM Tutor t WHERE t.telefono = :telefono")})
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "carnet")
    private Integer carnet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "carrera")
    private int carrera;
    @Column(name = "porcentaje")
    private Integer porcentaje;
    @Size(max = 30)
    @Column(name = "tipo_ayuda_economica")
    private String tipoAyudaEconomica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ciclo_actual")
    private String cicloActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "promedio")
    private int promedio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefono")
    private int telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tUTORcarnet")
    private Collection<HistorialTutor> historialTutorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutor")
    private Collection<NotaCurso> notaCursoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tUTORcarnet")
    private Collection<CursoTutor> cursoTutorCollection;

    public Tutor() {
    }

    public Tutor(Integer carnet) {
        this.carnet = carnet;
    }

    public Tutor(Integer carnet, String nombre, String apellido, int carrera, String correo, String cicloActual, int promedio, int telefono) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.correo = correo;
        this.cicloActual = cicloActual;
        this.promedio = promedio;
        this.telefono = telefono;
    }

    public Integer getCarnet() {
        return carnet;
    }

    public void setCarnet(Integer carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCarrera() {
        return carrera;
    }

    public void setCarrera(int carrera) {
        this.carrera = carrera;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getTipoAyudaEconomica() {
        return tipoAyudaEconomica;
    }

    public void setTipoAyudaEconomica(String tipoAyudaEconomica) {
        this.tipoAyudaEconomica = tipoAyudaEconomica;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCicloActual() {
        return cicloActual;
    }

    public void setCicloActual(String cicloActual) {
        this.cicloActual = cicloActual;
    }

    public int getPromedio() {
        return promedio;
    }

    public void setPromedio(int promedio) {
        this.promedio = promedio;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public Collection<HistorialTutor> getHistorialTutorCollection() {
        return historialTutorCollection;
    }

    public void setHistorialTutorCollection(Collection<HistorialTutor> historialTutorCollection) {
        this.historialTutorCollection = historialTutorCollection;
    }

    @XmlTransient
    public Collection<NotaCurso> getNotaCursoCollection() {
        return notaCursoCollection;
    }

    public void setNotaCursoCollection(Collection<NotaCurso> notaCursoCollection) {
        this.notaCursoCollection = notaCursoCollection;
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
        hash += (carnet != null ? carnet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if ((this.carnet == null && other.carnet != null) || (this.carnet != null && !this.carnet.equals(other.carnet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Tutor[ carnet=" + carnet + " ]";
    }
    
}
