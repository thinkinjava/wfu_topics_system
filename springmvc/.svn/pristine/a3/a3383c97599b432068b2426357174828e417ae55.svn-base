package com.tepusoft.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role implements Serializable{

	private static final long serialVersionUID = -8915899840197374108L;
	private String roleId;
	private String roleName;
	private String theorder;
	private String description;
	private String createTime;
	private String modifyTime;
	private Set<UsersRoles> usersRoles= new HashSet<>(0);
	private Set<RolesMenus> rolesMenus = new HashSet<>(0);
	
	@Id
	@Column(name = "roleId", nullable = false, length = 36)
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="roles")
	public Set<UsersRoles> getUsersRoles() {
		return usersRoles;
	}
	public void setUsersRoles(Set<UsersRoles> usersRoles) {
		this.usersRoles = usersRoles;
	}
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="roles")
	public Set<RolesMenus> getRolesMenus() {
		return rolesMenus;
	}
	public void setRolesMenus(Set<RolesMenus> rolesMenus) {
		this.rolesMenus = rolesMenus;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTheorder() {
		return theorder;
	}
	public void setTheorder(String theorder) {
		this.theorder = theorder;
	}


	

	
	
}
