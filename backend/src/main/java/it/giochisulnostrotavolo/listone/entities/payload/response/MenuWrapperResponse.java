// package it.giochisulnostrotavolo.listone.entities.payload.response;

// import java.io.Serializable;
// import java.util.Set;
// import java.util.TreeSet;

// public class MenuWrapperResponse implements Serializable {

// 	/**
// 	 * 
// 	 */
// 	private static final long serialVersionUID = 6094350048466155087L;

// 	private String description;
// 	private Set<Menu> menuList = new TreeSet<Menu>();
// 	private boolean active;
	
// 	public MenuWrapperResponse() {

// 	}

// 	public MenuWrapperResponse(String description, String linkTo, Set<Menu> menuList) {
// 		this.setDescription(description);
// 		this.setMenuList(menuList);
// 	}

	
// 	public String getDescription() {
// 		return description;
// 	}

// 	public void setDescription(String description) {
// 		this.description = description;
// 	}

// 	public Set<Menu> getMenuList() {
// 		return menuList;
// 	}

// 	public void setMenuList(Set<Menu> menuList) {
// 		this.menuList = menuList;
// 	}

// 	public boolean isActive() {
// 		return active;
// 	}

// 	public void setActive(boolean active) {
// 		this.active = active;
// 	}

// }
