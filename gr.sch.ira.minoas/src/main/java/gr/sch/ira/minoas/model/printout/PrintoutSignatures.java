package gr.sch.ira.minoas.model.printout;

import gr.sch.ira.minoas.model.BaseIDModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PRINTOUT_SIGNATURES")

public class PrintoutSignatures extends BaseIDModel {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    
    @Basic
    @Column(name = "SIGNATURE_TITLE", nullable = false, length = 128)
    private String signatureTitle;
    
    @Basic
    @Column(name = "SIGNATURE_NAME", nullable = false, length = 128)
    private String signatureName;
    
    @Basic
    @Column(name = "SIGNATURE_SHORT_ALIAS", nullable = true, length = 32)
    private String signatureShortAlias;

    /**
     * @return the signatureTitle
     */
    public String getSignatureTitle() {
        return signatureTitle;
    }

    /**
     * @param signatureTitle the signatureTitle to set
     */
    public void setSignatureTitle(String signatureTitle) {
        this.signatureTitle = signatureTitle;
    }

    /**
     * @return the signatureName
     */
    public String getSignatureName() {
        return signatureName;
    }

    /**
     * @param signatureName the signatureName to set
     */
    public void setSignatureName(String signatureName) {
        this.signatureName = signatureName;
    }

    /**
     * @return the signatureShortAlias
     */
    public String getSignatureShortAlias() {
        return signatureShortAlias;
    }

    /**
     * @param signatureShortAlias the signatureShortAlias to set
     */
    public void setSignatureShortAlias(String signatureShortAlias) {
        this.signatureShortAlias = signatureShortAlias;
    }
}
