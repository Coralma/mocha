/**
 * 
 */
package com.mocha.cooperate.page.event;

/**
 * @author Coral.Ma
 *
 */
public interface HomePageEvent {

	public class ChangeHeadMenuStyleEvent {
		private String selectedMenu;

		/**
		 * @return the selectedMenu
		 */
		public String getSelectedMenu() {
			return selectedMenu;
		}

		/**
		 * @param selectedMenu the selectedMenu to set
		 */
		public void setSelectedMenu(String selectedMenu) {
			this.selectedMenu = selectedMenu;
		}
	}
	
	public class ChangeShotCutEvent {
		private String action;

		/**
		 * @return the action
		 */
		public String getAction() {
			return action;
		}

		/**
		 * @param action the action to set
		 */
		public void setAction(String action) {
			this.action = action;
		}

		
	}
}
