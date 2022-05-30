package com.mission.shorturlservice.domain.repository;

import com.mission.shorturlservice.domain.entity.Url;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Integer> {

	Optional<Url> findByShorts(String shorts);

	Optional<Url> findByOriginalURL(String original);

}
