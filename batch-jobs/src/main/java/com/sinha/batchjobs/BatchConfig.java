package com.sinha.batchjobs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    Logger logger = LoggerFactory.getLogger(BatchConfig.class);


    @Bean
    public FlatFileItemReader<City> reader() {

        logger.debug("Inside reader...");

        return new FlatFileItemReaderBuilder<City>()
                .name("cityProcessor")
                .resource(new ClassPathResource("cities.csv"))
                .delimited()
                .names(new String[]{"id", "cityName"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{
                        setTargetType(City.class);
                }})
                .build();
    }


    @Bean
    public CityProcessor processor() {
        return new CityProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<City> jdbcWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<City>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO cities (id, city_name) VALUES (:id, :cityName)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importJob(JobRepository jobRepository, JobExecutionListener listener, Step step) {

        logger.debug("Inside import job...");

        return new JobBuilder("importJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();

    }

    @Bean
    public Step stepEach(JobRepository jobRepository, PlatformTransactionManager transactionManager, JdbcBatchItemWriter<City> writer) {

        logger.debug("Inside step...");

        return new StepBuilder("stepEach", jobRepository)
                .<City, City>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }




}
