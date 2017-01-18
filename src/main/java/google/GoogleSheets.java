package google;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleSheets extends ActionSupport {

    private List<List<Object>> columnData = new ArrayList<>();
    private String fileId;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String fetchMailIds() throws IOException {
        // Build a new authorized API client service.
        Sheets service = GoogleUtils.getSheetsService();

        // Prints the names and majors of students in a sample spreadsheet:
        // https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
        String spreadsheetId = fileId;
        System.out.println("spreadsheetId-------------------->" + spreadsheetId);
        String range = "A1:ZZZ";
        String majorDimension = "COLUMNS";
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .setMajorDimension(majorDimension)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.size() == 0) {
            System.out.println("No data found.");
        } else {
            for (int i = 0; i < values.size(); i++) {
                List<Object> column = values.get(i);
                if (column.get(0).toString().toLowerCase().contains("email")) {
                    columnData.add(column);
                }
            }
            return SUCCESS;
        }
        return ERROR;
    }
}
