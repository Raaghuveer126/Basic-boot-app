package com.framework.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name=Test.Columns.TABLE)
public class Test extends BaseEntity{
	public interface Columns{
		String TABLE="tests";
		String QUERY="CREATE TABLE tests (id INT AUTO_INCREMENT,test  NOT NULL,created DATETIME,updated DATETIME,creator INTEGER,updator INTEGER,PRIMARY KEY (id));";
		String TEST ="test";
	}

	@Column(name = Columns.TEST, nullable=false, unique=false)
	private Long test;
}