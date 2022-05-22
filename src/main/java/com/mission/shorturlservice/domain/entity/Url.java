package com.mission.shorturlservice.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "url")
public class Url {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer index;

	@Column(name = "original", nullable = false, unique = true)
	private String original;

	@Column(name = "shorts")
	private String shorts;

	@Column(name = "count")
	@ColumnDefault("1")
	private int count;

	@Builder
	public Url(String original) {
		this.original = original;
	}

	public void increaseCount() {
		this.count += 1;
	}

	public void setShorts(String shorts) {
		this.shorts = shorts;
	}

}
