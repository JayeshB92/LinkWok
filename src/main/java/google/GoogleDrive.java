package google;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleDrive extends ActionSupport {

    private List<File> files;
    private Map<String,String> fileNames = new HashMap<>();
    public static String msg = null;

    public Map<String, String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(Map<String, String> fileNames) {
        this.fileNames = fileNames;
    }
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String getSpreadsheets() throws IOException {
        Drive service = GoogleUtils.getDriveService();
        FileList result = service.files().list()
                .setQ("mimeType='application/vnd.google-apps.spreadsheet' or mimeType='application/vnd.ms-excel' or mimeType='application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'")
                .setFields("nextPageToken, files(id, name)")
                .execute();
        files = result.getFiles();
        if (files == null || files.size() == 0) {
            System.out.println("No files found.");
        } else {
            for (File file : files) {
                fileNames.put(file.getId(), file.getName());
            }
            System.out.println("Successfully Loaded Files");
        }
        return SUCCESS;
    }
}
