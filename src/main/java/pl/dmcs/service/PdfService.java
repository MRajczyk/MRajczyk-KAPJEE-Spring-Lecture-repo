package pl.dmcs.service;

import jakarta.servlet.http.HttpServletResponse;
import pl.dmcs.domain.AppUser;

public interface PdfService {
    public void generatePdf(AppUser appUser, HttpServletResponse response);
}




