package io.project.easycut.easy_cut.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling   // 스케줄링 활성화
@EnableJpaAuditing  // 생성일/수정일 자동 관리를 위해 필요
public class ApplicationConfig {


}
