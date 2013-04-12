package gr.sch.ira.minoas.seam.components.async;

import gr.sch.ira.minoas.model.employee.Employee;
import gr.sch.ira.minoas.model.employement.Disposal;
import gr.sch.ira.minoas.seam.components.BaseDatabaseAwareSeamComponent;

import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.TransactionPropagationType;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.async.Asynchronous;
import org.jboss.seam.annotations.async.Expiration;
import org.jboss.seam.annotations.async.FinalExpiration;
import org.jboss.seam.annotations.async.IntervalDuration;
import org.jboss.seam.async.QuartzTriggerHandle;

/**
 * Searches for disposals that must be activated.
 * 
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * @version $Id$
 */
@Name("disposalActivationProcessor")
@Scope(ScopeType.APPLICATION)
@AutoCreate
public class DisposalActivactionProcessor extends BaseDatabaseAwareSeamComponent {

    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    
	/**
	 * 
	 */
	public DisposalActivactionProcessor() {
	}

	
	
	@SuppressWarnings("unchecked")
    @Transactional(TransactionPropagationType.REQUIRED)
    public Collection<Disposal> getDisposalThatShouldBeActivated(EntityManager em, Date today) {
        return em.createQuery("SELECT s from Disposal s WHERE s.active IS FALSE  AND (s.autoCanceled IS FALSE OR s.autoCanceled IS NULL) AND (s.deleted IS FALSE OR s.deleted IS NULL) AND :onDate  BETWEEN s.established AND s.dueTo ORDER BY s.established").setParameter("onDate", today).getResultList();
    }
	
	@Asynchronous
	@Transactional(TransactionPropagationType.REQUIRED)
	public QuartzTriggerHandle scheduleDisposalActivation(@Expiration Date when, 
            @IntervalDuration Long interval,
            @FinalExpiration Date endDate) {
	    Date today = new Date();
	    info("will check for disposals that should be set current on #0", today);
	    Collection<Disposal> disposalsThatShouldBeActivated = getDisposalThatShouldBeActivated(getEntityManager(), today);
	    info("found totally #0 disposals that should be activated", disposalsThatShouldBeActivated.size());
	    for(Disposal disposal : disposalsThatShouldBeActivated) {
	        Employee employee = disposal.getEmployee();
	        info("setting disposal #0 as active for employee #1", disposal, employee);
	        //disposal.setActive(Boolean.TRUE);
	        
	        //employee.setLeave(newCurrentLeave);
	        //newCurrentLeave.setActive(Boolean.TRUE);
	    }
	    getEntityManager().flush();
	    return null;
	}
}
