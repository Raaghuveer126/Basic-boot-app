package com.framework.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	public interface Columns {
		String ID = "id";
		String CREATED = "created_at";
		String UPDATED = "updated_at";
		String CREATOR = "created_by";
		String UPDATOR = "updated_by";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Columns.ID, nullable=false)
	private Long id;
	@Column(name = Columns.CREATED, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable=false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = Columns.UPDATED, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	@Column(name = Columns.CREATOR, length=80)
	@CreatedBy
	private String createdBy;
	@Column(name = Columns.UPDATOR, length=80)
	@LastModifiedBy
	private String updatedBy;
}
