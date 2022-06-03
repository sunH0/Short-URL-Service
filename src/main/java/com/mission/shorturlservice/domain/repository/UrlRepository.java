package com.mission.shorturlservice.domain.repository;

import com.mission.shorturlservice.domain.entity.Url;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UrlRepository extends JpaRepository<Url, Integer> {

	Optional<Url> findByShorts(String shorts);

	Optional<Url> findByOriginalURL(String original);

	List<Url> findByModifiedAtLessThan(LocalDateTime limitedTime);

	void deleteById(Integer id);

}
