package br.com.falconer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = {@Index(name = "last_login", columnList="lastLogin", unique = false)})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@Column(length = 60)
	private String email;
	
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	
}
