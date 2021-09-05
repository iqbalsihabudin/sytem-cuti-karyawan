package uas.kel2.sytemcutikaryawan.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uas.kel2.sytemcutikaryawan.models.DetailPengajuanCuti;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExcelExporterService {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Iterable<DetailPengajuanCuti> listData;

    public ExcelExporterService(Iterable<DetailPengajuanCuti> list) {
        this.listData = list;
        workbook = new XSSFWorkbook();
//        System.out.println(listData.getClass());
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Pengajuan ID", style);
        createCell(row, 1, "tanggal", style);
        createCell(row, 2, "NIP", style);
        createCell(row, 3, "Nama Lengkap", style);
        createCell(row, 4, "Telepon", style);
        createCell(row, 5, "Alamat", style);
        createCell(row, 6, "Jenis Cuti", style);
        createCell(row, 7, "Lama Cuti", style);
        createCell(row, 8, "Status cuti", style);
        createCell(row, 9, "Hrd ID", style);
        createCell(row, 10, "Keterangan", style);


    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

        for (DetailPengajuanCuti data : listData) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, data.getDetailPengajuanCutiId(), style);
            createCell(row, columnCount++, data.getTglCuti(), cellStyle);
            createCell(row, columnCount++, data.getPengajuanCuti().getEmployee().getNip(), style);
            createCell(row, columnCount++, data.getPengajuanCuti().getEmployee().getNamaLengkap(), style);
            createCell(row, columnCount++, data.getPengajuanCuti().getNoTelp(), style);
            createCell(row, columnCount++, data.getPengajuanCuti().getAlamat(), style);
            createCell(row, columnCount++, data.getJenisCuti().getJenisCuti(), style);
            createCell(row, columnCount++, data.getPengajuanCuti().getLamaCuti(), style);
            createCell(row, columnCount++, data.getPengajuanCuti().getStatusCuti().getStatusCuti(), style);
            createCell(row, columnCount++, data.getPengajuanCuti().getHrdId(), style);
            createCell(row, columnCount++, data.getPengajuanCuti().getKeterangan(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
