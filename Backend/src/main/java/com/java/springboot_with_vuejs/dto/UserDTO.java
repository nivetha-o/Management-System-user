// UserDTO.java
package com.java.springboot_with_vuejs.dto;

import java.time.LocalDateTime;
import java.util.Set;


public class UserDTO {
	
	
    private Long id;
    private String name;
    private String email;
    private String phNo;
    private Set<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhNo() {
		return phNo;
	}
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
	public Set<String> getTags() {
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public UserDTO(String name, String email, String phNo, Set<String> tags, LocalDateTime createdAt,
			LocalDateTime updatedAt, boolean deleted) {
		super();
		this.name = name;
		this.email = email;
		this.phNo = phNo;
		this.tags = tags;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deleted = deleted;
	}
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

    // Getters and setters
    // Omitted for brevity
}
