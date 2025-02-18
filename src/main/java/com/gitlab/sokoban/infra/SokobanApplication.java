package com.gitlab.sokoban.infra;

import com.gitlab.sokoban.domain.livingdoc.Feature;
import com.gitlab.sokoban.domain.livingdoc.Stub;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
		basePackages = "com.gitlab.sokoban",
		includeFilters = @ComponentScan.Filter(type=FilterType.ANNOTATION, classes={Feature.class, Stub.class})
)

@SpringBootApplication
public class SokobanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SokobanApplication.class, args);
	}

}
