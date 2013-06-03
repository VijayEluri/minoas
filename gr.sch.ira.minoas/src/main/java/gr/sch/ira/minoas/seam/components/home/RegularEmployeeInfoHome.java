package gr.sch.ira.minoas.seam.components.home;

import gr.sch.ira.minoas.model.employee.RegularEmployeeInfo;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * @version $Id$
 */
@Name("regularEmployeeInfoHome")
@Scope(ScopeType.CONVERSATION)
@AutoCreate
public class RegularEmployeeInfoHome extends MinoasEntityHome<RegularEmployeeInfo> {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected RegularEmployeeInfo createInstance() {
		RegularEmployeeInfo new_instance = new RegularEmployeeInfo();
		return new_instance;
	}

	/**
	 * @see org.jboss.seam.framework.Home#getInstance()
	 */
	@Override
	@Factory(value = "regularEmployeeInfo", scope = ScopeType.PAGE)
	public RegularEmployeeInfo getInstance() {
		return (RegularEmployeeInfo) super.getInstance();

	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.home.MinoasEntityHome#persist()
	 */
	@Override
	@Transactional
	public String persist() {
		return super.persist();
	}

	/**
	 * @see gr.sch.ira.minoas.seam.components.home.MinoasEntityHome#update()
	 */
	@Override
	@Transactional
	public String update() {
		return super.update();
	}

	@Transactional
	public boolean wire() {
		return true;
	}
	
	@Transactional
    public String revert() {
        info("principal #0 is reverting updates to regular employee info  #1", getPrincipalName(), getInstance());
        getEntityManager().refresh(getInstance());
        return "reverted";
    }
}
