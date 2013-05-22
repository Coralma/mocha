/**
 * 
 */
package com.mocha.cooperate.widget.listener;

/**
 * @author Coral.Ma
 *
 */
public interface BreadcrumbListener {

	public void stepClick(BreadcrumbEvent event);
	
	public class BreadcrumbEvent {
		private String stepName;
		private Long stepID;
		/**
		 * @return the stepName
		 */
		public String getStepName() {
			return stepName;
		}
		/**
		 * @param stepName the stepName to set
		 */
		public void setStepName(String stepName) {
			this.stepName = stepName;
		}
		/**
		 * @return the stepID
		 */
		public Long getStepID() {
			return stepID;
		}
		/**
		 * @param stepID the stepID to set
		 */
		public void setStepID(Long stepID) {
			this.stepID = stepID;
		}
	}
}
