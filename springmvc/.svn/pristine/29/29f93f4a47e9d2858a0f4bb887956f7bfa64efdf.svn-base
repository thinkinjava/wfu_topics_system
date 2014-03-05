package com.tepusoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="roles_menus")
public class RolesMenus implements Serializable{

	private static final long serialVersionUID = 1888057972722194507L;
	private String id;
	private Role roles;
	private Menu menus;
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="roleId")
	public Role getRoles() {
		return roles;
	}
	public void setRoles(Role roles) {
		this.roles = roles;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="menuId")
	public Menu getMenus() {
		return menus;
	}
	public void setMenus(Menu menus) {
		this.menus = menus;
	}
	
	
}
