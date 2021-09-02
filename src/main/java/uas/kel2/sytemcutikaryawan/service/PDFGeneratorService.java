package uas.kel2.sytemcutikaryawan.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import uas.kel2.sytemcutikaryawan.models.DetailPengajuanCuti;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response, DetailPengajuanCuti detail) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Pengajuan Cuti", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        String text = "tanggal pengajuan \n" +
                "Perihal : " +detail.getPengajuanCuti().getKeterangan()+"\n\n"+
                "Kepada \nHRD Perusahaan \nDitempat \nDengan Hormat \nYang Bertanda Tangan Di Bawah ini \n\n"+
                "Nama : "+detail.getPengajuanCuti().getEmployee().getNamaLengkap() +"\n"+
                "NIP  : "+detail.getPengajuanCuti().getEmployee().getNip() +"\n"+
                "Divisi : "+detail.getPengajuanCuti().getEmployee().getDivisi() +"";
        Paragraph paragraph2 = new Paragraph(""+text+"", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph2);
        document.close();

    }
}
