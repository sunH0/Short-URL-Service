package com.mission.ShortURLService.entity;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "url")
public class Url {

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName = "url_seq", initialValue = 100000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
	private BigInteger id;
	@Column(name = "origin")
	private String originUrl;
	@Column(name = "shorten")
	private String shortenUrl;

	public Url(String originUrl) {
		this.originUrl = originUrl;
	}

	public void setShortenUrl(String shortenUrl) {
		this.shortenUrl = shortenUrl;
	}
}
