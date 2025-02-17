package com.mission.shorturlservice.domain.entity;

import com.mission.shorturlservice.domain.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "url", indexes = @Index(name = "i_url", columnList = "count DESC, modified_at"))
public class Url extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "original", nullable = false, unique = true)
	@Lob
	private String originalURL;

	@Column(name = "shorts")
	private String shorts;

	@Column(name = "count", columnDefinition = "int default 0")
	private int count;

	@Builder
	public Url(String originalURL) {
		this.originalURL = originalURL;
	}

	public void increaseCount() {
		this.count += 1;
	}

	public void setShorts(String shorts) {
		this.shorts = shorts;
	}

}
