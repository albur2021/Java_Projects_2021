package com.buran.oneToMany.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="teams")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=2,max=100,message="Team name must be between 2-100 characters")
	private String name;
	
	@NotBlank(message="City may not be null")
	@Size(min=1,message="City must be at least 1 character")
	private String city;
	
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	
	//One to Many SetUp
	@OneToMany(mappedBy="team", fetch=FetchType.LAZY)//'LAZY' provides just team data,not players, unless we asked
	private List<Player>players;
	
	//empty constructor allows to create an object and feed data into DB 
	public Team() {
		
	}
	
	public Team(String name, String city) {
		this.name=name;
		this.city=city;
	}
	
		//Methods://///////////////////////
		@PrePersist
		protected void onCreate() {
			this.createdAt = new Date();
		}
		
		@PreUpdate
		protected void onUpdate() {
			this.updatedAt = new Date();
		}
		
		/// Getters and Setters//////////////
		
		
		
		
		
		public List<Player> getPlayers() {
			return players;
		}

		public void setPlayers(List<Player> players) {
			this.players = players;
		}

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

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
	
	
		
}
