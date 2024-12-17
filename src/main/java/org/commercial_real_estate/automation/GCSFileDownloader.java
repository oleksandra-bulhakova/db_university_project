package org.commercial_real_estate.automation;


import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GCSFileDownloader {
    private static final String BUCKET_NAME = "report_bucket_9";
    private static final String FILE_NAME = "deals_report.pdf";

    public static void downloadFile() throws IOException {
        Storage storage = StorageOptions.getDefaultInstance().getService();

        Blob blob = storage.get(BUCKET_NAME, FILE_NAME);

        OutputStream outputStream = new FileOutputStream("C:/Users/o.bulhakova/IdeaProjects/db_university_project/report/weekly.pdf");
        blob.downloadTo(outputStream);
        outputStream.close();
    }
}
