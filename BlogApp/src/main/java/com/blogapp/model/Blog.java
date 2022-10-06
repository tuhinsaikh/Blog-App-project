package com.blogapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer blogId;
	private String title;
	@Column(columnDefinition = "TEXT")
	private String body;
	
	@UpdateTimestamp
	private LocalDateTime postTime;
	
//	@OneToMany(fetch = FetchType.LAZY)
//	List<Comment> comments = new ArrayList<>();
}
