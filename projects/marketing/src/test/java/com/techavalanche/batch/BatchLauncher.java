package com.techavalanche.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchLauncher {

	public static Job job;
	public static JobLauncher jobLauncher;
	public static JobRepository jobRepository;

	public static void main(String args[]) {
		try {
			AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("simpleJob.xml");
			
			JobParametersBuilder builder = new JobParametersBuilder();
			builder.addString("Date", "12/02/2011");
			jobLauncher.run(job, builder.toJobParameters());

			JobExecution jobExecution = jobRepository.getLastJobExecution(job.getName(), builder.toJobParameters());

			System.out.println(jobExecution.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	public void setJobRepository(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	public void setJob(Job job) {
		this.job = job;
	}
}