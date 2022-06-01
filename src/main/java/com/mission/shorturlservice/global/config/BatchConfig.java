package com.mission.shorturlservice.global.config;

import com.mission.shorturlservice.domain.Service.UrlService;
import com.mission.shorturlservice.domain.entity.Url;
import com.mission.shorturlservice.domain.repository.UrlRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

	public final JobBuilderFactory jobBuilderFactory;
	public final StepBuilderFactory stepBuilderFactory;
	private final UrlRepository urlRepository;

	@Bean
	public Job job() {
		Job job = jobBuilderFactory.get("job")
			.start(step())
			.build();

		return job;
	}

	@Bean
	public Step step() {
		return stepBuilderFactory.get("step")
			.tasklet((contribution, chunkContext) -> {
				log.info("Step!");
				LocalDateTime limitedTime = LocalDateTime.now().minusDays(7);
				List<Url> limitedUrls = urlRepository.findByModifiedAtLessThan(limitedTime);

				if(limitedUrls.size()>0 && limitedUrls != null) {
					for(Url url : limitedUrls){
						urlRepository.deleteById(url.getId());
					}
				}
				return RepeatStatus.FINISHED;
			})
			.build();
	}

}
