/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "nota_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotaCurso.findAll", query = "SELECT n FROM NotaCurso n"),
    @NamedQuery(name = "NotaCurso.findByNotaCurso", query = "SELECT n FROM NotaCurso n WHERE n.notaCurso = :notaCurso"),
    @NamedQuery(name = "NotaCurso.findByCURSOTEidcurso", query = "SELECT n FROM NotaCurso n WHERE n.notaCursoPK.cURSOTEidcurso = :cURSOTEidcurso"),
    @NamedQuery(name = "NotaCurso.findByTUTORcarnet", query = "SELECT n FROM NotaCurso n WHERE n.notaCursoPK.tUTORcarnet = :tUTORcarnet")})
public class NotaCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotaCursoPK notaCursoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota_curso")
    private int notaCurso;
    @JoinColumn(name = "TUTOR_carnet", referencedColumnName = "carnet", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tutor tutor;
    @JoinColumn(name = "CURSO_TE_id_curso", referencedColumnName = "id_curso", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CursoTe cursoTe;

    public NotaCurso() {
    }

    public NotaCurso(NotaCursoPK notaCursoPK) {
        this.notaCursoPK = notaCursoPK;
    }

    public NotaCurso(NotaCursoPK notaCursoPK, int notaCurso) {
        this.notaCursoPK = notaCursoPK;
        this.notaCurso = notaCurso;
    }

    public NotaCurso(int cURSOTEidcurso, int tUTORcarnet) {
        this.notaCursoPK = new NotaCursoPK(cURSOTEidcurso, tUTORcarnet);
    }

    public NotaCursoPK getNotaCursoPK() {
        return notaCursoPK;
    }

    public void setNotaCursoPK(NotaCursoPK notaCursoPK) {
        this.notaCursoPK = notaCursoPK;
    }

    public int getNotaCurso() {
        return notaCurso;
    }

    public void setNotaCurso(int notaCurso) {
        this.notaCurso = notaCurso;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public CursoTe getCursoTe() {
        return cursoTe;
    }

    public void setCursoTe(CursoTe cursoTe) {
        this.cursoTe = cursoTe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notaCursoPK != null ? notaCursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotaCurso)) {
            return false;
        }
        NotaCurso other = (NotaCurso) object;
        if ((this.notaCursoPK == null && other.notaCursoPK != null) || (this.notaCursoPK != null && !this.notaCursoPK.equals(other.notaCursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.NotaCurso[ notaCursoPK=" + notaCursoPK + " ]";
    }
    
}
