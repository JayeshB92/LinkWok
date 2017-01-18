package google;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleSheets extends ActionSupport {

    private static List<List<Object>> columnData = new ArrayList<>();
    private List<String> columnHead = new ArrayList<>();
    public List<String> selectedEmailIds = new ArrayList<>();
    private String fileId;
    private boolean flag = true;
    private String columns;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public List<String> getColumnHead() {
        return columnHead;
    }

    public void setColumnHead(List<String> columnHead) {
        this.columnHead = columnHead;
    }

    public String getMailIds() throws IOException {
        // Build a new authorized API client service.
        Sheets service = GoogleUtils.getSheetsService();

        // Prints the names and majors of students in a sample spreadsheet:
        // https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
        String spreadsheetId = fileId;
        String range = "A1:ZZZ";
        String majorDimension = "COLUMNS";
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .setMajorDimension(majorDimension)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.size() == 0) {
            System.out.println("No data found.");
            GoogleDrive.msg = "No Data Found.";
            return NONE;
        } else {
            for (int i = 0; i < values.size(); i++) {
                List<Object> column = values.get(i);
                if (column.get(0).toString().toLowerCase().contains("email")) {
                    flag = false;
                    columnData.add(column);
                }
            }
            for (List column : columnData) {
                columnHead.add((String) column.get(0));
            }
            if (flag) {
                GoogleDrive.msg = "No Email Column Found. Please Select Other File.";
                return NONE;
            } else {
                return SUCCESS;
            }
        }
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public List<String> getSelectedEmailIds() {
        return selectedEmailIds;
    }

    public void setSelectedEmailIds(List<String> selectedEmailIds) {
        this.selectedEmailIds = selectedEmailIds;
    }

    public String getEmailFromColumns() {
        if (columnData.size() == 0) {
            return NONE;
        }
        for (List column : columnData) {
            if (columns.contains((String) column.get(0))) {
                column.remove(0);
                selectedEmailIds.addAll(column);
            }
        }
        return SUCCESS;
    }
}
