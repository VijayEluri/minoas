/**
 * 
 */
package gr.sch.ira.minoas.seam.components.suggesters;

import gr.sch.ira.minoas.core.CoreUtils;
import gr.sch.ira.minoas.model.core.Unit;
import gr.sch.ira.minoas.model.employement.Disposal;
import gr.sch.ira.minoas.model.employement.DisposalTargetType;
import gr.sch.ira.minoas.seam.components.BaseDatabaseAwareSeamComponent;

import java.util.Collection;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;

/**
 * @author slavikos
 *
 */
@Name("disposalAwareUnitSuggester")
@Scope(ScopeType.PAGE)
public class DisposalAwareUnitSuggester extends BaseDatabaseAwareSeamComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@In(required = false)
	private Disposal disposal;

	@SuppressWarnings("unchecked")
	@Transactional
	public Collection<Unit> suggest(Object secondmemt_search_pattern) {
		DisposalTargetType type = disposal != null ? type = disposal.getTargetType() : null;
		if (type != null) {
			return getEntityManager()
					.createQuery(
							"SELECT u.unit FROM DisposalUnit u WHERE (u.unit.title) LIKE LOWER(:search_pattern) AND u.type=:type ORDER BY u.unit.title")
					.setParameter("search_pattern",
							CoreUtils.getSearchPattern(String.valueOf(secondmemt_search_pattern))).setParameter("type",
							type).getResultList();

		} else {
			return getEntityManager()
					.createQuery(
							"SELECT u.unit FROM DisposalUnit u WHERE (u.unit.title) LIKE LOWER(:search_pattern) ORDER BY u.unit.title")
					.setParameter("search_pattern",
							CoreUtils.getSearchPattern(String.valueOf(secondmemt_search_pattern))).getResultList();
		}
	}

}
