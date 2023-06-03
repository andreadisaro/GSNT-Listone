// package it.giochisulnostrotavolo.listone.entities.payload.response;

// import java.io.Serializable;
// import java.sql.Timestamp;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;
// import javax.persistence.SequenceGenerator;
// import javax.persistence.Table;
// import javax.persistence.Transient;

// import org.apache.commons.collections4.map.HashedMap;

// @Entity
// @Table(name = "MENU")
// public class Menu implements Serializable {

// 	private static final long serialVersionUID = 5568525094206591331L;

// 	@Id
// 	@SequenceGenerator(name = "MENU_ID_SEQ", sequenceName = "MENU_ID_SEQ", allocationSize = 1, initialValue = 1)
// 	@GeneratedValue(generator = "MENU_ID_SEQ")
// 	@Column
// 	private Long id;

// 	@Column
// 	private String name;

// 	@Column
// 	private String path;

// 	@Column
// 	private Long parentMenu;

// 	@Column(name = "profile")
// 	private String profile;

// 	@Column(name = "insert_user")
// 	private String insertUser;

// 	@Column(name = "insert_date")
// 	private Timestamp insertDate;

// 	@Column(name = "update_user")
// 	private String updateUser;

// 	@Column(name = "update_date")
// 	private Timestamp updateDate;

// 	@Column(name = "profile_menu")
// 	private String profileMenu;

// 	@Column
// 	private Integer ordinal;

// 	@Transient
// 	private List<Menu> submenuList = new ArrayList<>();

// 	@Transient
// 	private Map<Long, Boolean> selectedProfile = new HashedMap<>();

// 	@Transient
// 	private Integer submenuLength = 0;

// 	public String getName() {
// 		return name;
// 	}

// 	public void setName(String name) {
// 		this.name = name;
// 	}

// 	public String getPath() {
// 		return path;
// 	}

// 	public void setPath(String path) {
// 		this.path = path;
// 	}

// 	public List<Menu> getSubmenuList() {
// 		return submenuList;
// 	}

// 	public void setSubmenuList(List<Menu> submenuList) {
// 		this.submenuList = submenuList;
// 	}

// 	public void addSubmenuList(Menu submenu) {
// 		this.submenuList.add(submenu);
// 	}

// 	public Long getId() {
// 		return id;
// 	}

// 	public void setId(Long id) {
// 		this.id = id;
// 	}

// 	public Long getParentMenu() {
// 		return parentMenu;
// 	}

// 	public void setParentMenu(Long parentMenu) {
// 		this.parentMenu = parentMenu;
// 	}

// 	public String getProfile() {
// 		return profile;
// 	}

// 	public void setProfile(String profile) {
// 		this.profile = profile;
// 	}

// 	public void setProfileMenu(String profileMenu) {
// 		this.profileMenu = profileMenu;
// 	}

// 	public String getInsertUser() {
// 		return insertUser;
// 	}

// 	public void setInsertUser(String insertUser) {
// 		this.insertUser = insertUser;
// 	}

// 	public Timestamp getInsertDate() {
// 		return insertDate;
// 	}

// 	public void setInsertDate(Timestamp insertDate) {
// 		this.insertDate = insertDate;
// 	}

// 	public String getUpdateUser() {
// 		return updateUser;
// 	}

// 	public void setUpdateUser(String updateUser) {
// 		this.updateUser = updateUser;
// 	}

// 	public Timestamp getUpdateDate() {
// 		return updateDate;
// 	}

// 	public void setUpdateDate(Timestamp updateDate) {
// 		this.updateDate = updateDate;
// 	}

// 	public String getProfileMenu() {
// 		return profileMenu;
// 	}

// 	public Integer getSubmenuLength() {
// 		return submenuLength;
// 	}

// 	public void setSubmenuLength(Integer submenuLength) {
// 		this.submenuLength = submenuLength;
// 	}

// 	public Integer getOrdinal() {
// 		return ordinal;
// 	}

// 	public void setOrdinal(Integer ordinal) {
// 		this.ordinal = ordinal;
// 	}

// 	public Map<Long, Boolean> getSelectedProfile() {
// 		return selectedProfile;
// 	}

// 	public void setSelectedProfile(Map<Long, Boolean> selectedProfile) {
// 		this.selectedProfile = selectedProfile;
// 	}

// }
