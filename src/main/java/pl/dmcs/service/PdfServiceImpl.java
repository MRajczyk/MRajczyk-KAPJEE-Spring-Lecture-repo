package pl.dmcs.service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import pl.dmcs.domain.AppUser;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfServiceImpl implements PdfService {

    public void generatePdf(AppUser appUser, HttpServletResponse response) {
        try {
            OutputStream o = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=" + appUser.getLogin() + ".pdf");
            Document pdf = new Document();
            PdfWriter.getInstance(pdf, o);
            pdf.open();
            pdf.add(new Paragraph("Pdf example - Spring Framework & iText library"));
            pdf.add(new Paragraph(Chunk.NEWLINE));
            PdfPTable table = new PdfPTable(2);
            table.addCell("FirsName");
            table.addCell(appUser.getFirstName());
            table.addCell("LastName");
            table.addCell(appUser.getLastName());
            table.addCell("PESEL");
            table.addCell(appUser.getPesel().getPESEL());
            table.addCell("Login");
            table.addCell(appUser.getLogin());
            table.addCell("Email");
            table.addCell(appUser.getEmail());
            table.addCell("Active");
            table.addCell(String.valueOf(appUser.isEnabled()));
            pdf.add(table);
            pdf.close();
            o.close();
        }catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}

