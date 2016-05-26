/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Francisco
 */
@Embeddable
public class NotaCursoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CURSO_TE_id_curso")
    private int cURSOTEidcurso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TUTOR_carnet")
    private int tUTORcarnet;

    public NotaCursoPK() {
    }

    public NotaCursoPK(int cURSOTEidcurso, int tUTORcarnet) {
        this.cURSOTEidcurso = cURSOTEidcurso;
        this.tUTORcarnet = tUTORcarnet;
    }

    public int getCURSOTEidcurso() {
        return cURSOTEidcurso;
    }

    public void setCURSOTEidcurso(int cURSOTEidcurso) {
        this.cURSOTEidcurso = cURSOTEidcurso;
    }

    public int getTUTORcarnet() {
        return tUTORcarnet;
    }

    public void setTUTORcarnet(int tUTORcarnet) {
        this.tUTORcarnet = tUTORcarnet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cURSOTEidcurso;
        hash += (int) tUTORcarnet;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotaCursoPK)) {
            return false;
        }
        NotaCursoPK other = (NotaCursoPK) object;
        if (this.cURSOTEidcurso != other.cURSOTEidcurso) {
            return false;
        }
        if (this.tUTORcarnet != other.tUTORcarnet) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.NotaCursoPK[ cURSOTEidcurso=" + cURSOTEidcurso + ", tUTORcarnet=" + tUTORcarnet + " ]";
    }
    
}
