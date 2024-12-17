package org.commercial_real_estate.automation;

import com.google.cloud.scheduler.v1.CloudSchedulerClient;
import com.google.cloud.scheduler.v1.*;
import com.google.cloud.scheduler.v1.Job;
import com.google.protobuf.ByteString;

public class GoogleSchedulerService {
    private String email;

    public void createWeeklyReportJob(String email) {
        try (CloudSchedulerClient client = CloudSchedulerClient.create()) {

            //String cronExpression = "0 6 * * 1";
            String cronExpression = "*/2 * * * *";

            String url = "https://abbd-109-86-224-61.ngrok-free.app/generate-report?email=" + email;
            String method = "POST";

            HttpTarget httpTarget = HttpTarget.newBuilder()
                    .setUri(url)
                    .setHttpMethod(HttpMethod.POST)
                    .putHeaders("Content-Type", "application/json")
                    .setBody(ByteString.copyFromUtf8("{" +
                            "\"email\":\"" + email + "\"" +
                            "}"))
                    .build();

            Job job = Job.newBuilder()
                    .setName(JobName.of("impactful-study-444921-h1", "us-central1", "weekly-report-job").toString())
                    .setSchedule(cronExpression)
                    .setHttpTarget(httpTarget)
                    .setTimeZone("UTC")
                    .build();

            client.createJob(CreateJobRequest.newBuilder().setParent("projects/impactful-study-444921-h1/locations/us-central1").setJob(job).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
