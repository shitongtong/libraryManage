package com.stt.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by stt on 2017/2/11.
 */
@SpringBootApplication
@EnableBatchProcessing
public class SampleBatchApplication {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    protected Tasklet tasklet(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    public Job job(){
        return this.jobs.get("job").start(step1()).build();
    }

    @Bean
    public Step step1(){
        return this.steps.get("step1").tasklet(tasklet()).build();
    }

    public static void main(String[] args) {
        // System.exit is common for Batch applications since the exit code can be used to
        // drive a workflow
        System.exit(SpringApplication.exit(SpringApplication.run(SampleBatchApplication.class,args)));
    }

}
