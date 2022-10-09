package com.blogapp.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
   private Integer commentId;
	@Column(columnDefinition = "TEXT")
   private String commentBody;
   private String name;
   private LocalDateTime commentTime;
}
