package com.mission.shorturlservice.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.mission.shorturlservice.domain.entity.Url;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class UrlRepositoryTest {

	@Autowired
	private UrlRepository urlRepository;

	private Url url;

	@BeforeEach
	void init() {
		url = Url.builder()
				 .originalURL("https://www.naver.com/")
				 .build();
	}

	@DisplayName("Short URL로 Url 데이터를 찾을 수 있다.")
	@Test
	public void testFindByShorts() throws Exception {
		// given
		String shortUrl = "https://www.apiA.com";
		url.setShorts(shortUrl);
		Url save = urlRepository.save(url);

		String original = url.getOriginalURL();

		// when
		Optional<Url> actual = urlRepository.findByShorts(shortUrl);
		// then
		assertThat(actual).isPresent();
		assertThat(actual.get().getOriginalURL()).isEqualTo(original);
	}

	@DisplayName("Original Url로 Url 데이터를 찾을 수 있다")
	@Test
	public void testFindByOriginal() throws Exception {
		// given
		urlRepository.save(url);
		String originalUrl = url.getOriginalURL();

		// when
		Optional<Url> actual = urlRepository.findByOriginalURL(originalUrl);
		// then
		assertThat(actual).isPresent();
		assertThat(actual.get().getOriginalURL()).isEqualTo(originalUrl);
	}




}
