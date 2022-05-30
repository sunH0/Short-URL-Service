package com.mission.ShortURLService.repository;

import com.mission.ShortURLService.entity.ShortenUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenerRepository extends JpaRepository<ShortenUrl, Integer> {

	Optional<ShortenUrl> findByOriginUrl(String urlStr);
}
