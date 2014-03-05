package com.tepusoft.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="menus")
public class Menu implements Serializable{

	private static final long serialVersionUID = 3237590467600644477L;
	private String menuId;
	private String menuName;
	private String menuPid;
	private String url;
	private String iconCls;
	private String type;
	private String description;
	private String num;
	private Set<RolesMenus> rolesMenus = new HashSet<>(0);
	
	@Id
	public String getMenuId() {
		return menuId;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuPid() {
		return menuPid;
	}
	public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="menus")
	public Set<RolesMenus> getRolesMenus() {
		return rolesMenus;
	}
	public void setRolesMenus(Set<RolesMenus> rolesMenus) {
		this.rolesMenus = rolesMenus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}

	
	
}
