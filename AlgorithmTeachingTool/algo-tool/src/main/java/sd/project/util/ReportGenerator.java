package sd.project.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import dal.model.User;
import sd.project.dto.InformationDTO;
import sd.project.dto.UserDTO;
import sd.project.services.InformationService;
import sd.project.services.UserService;

public class ReportGenerator {

	private static UserService userService = new UserService();
	private static InformationService infoService = new InformationService();
	public static void createUserReport() {
		
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		 
		try {
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		 
		contentStream.setFont(PDType1Font.COURIER, 12);
		List<UserDTO> users = userService.getAllUsersIncreasingRating();
		int i = 0;
		contentStream.beginText();
		contentStream.newLineAtOffset(100, 700);
		for(UserDTO user : users) {
			i++;
			contentStream.showText(Integer.toString(i) + ". " + user.getName() + "   " + user.getRating());
			contentStream.newLineAtOffset(0, -15);
		}
		contentStream.endText();
		contentStream.close();
		document.save("UserReport.pdf");
		document.close();
		}catch(Exception e) {
			
			
		}
	}
	
public static void createInfoReport() {
		
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
		 
		try {
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		 
		contentStream.setFont(PDType1Font.COURIER, 12);
		List<InformationDTO> infos = infoService.getAllInformationRatingIncreasing();
		int i = 0;
		contentStream.beginText();
		contentStream.newLineAtOffset(100, 700);
		for(InformationDTO info : infos) {
			i++;
			contentStream.showText(Integer.toString(i) + ". " + info.getAlgorithmName() + ", " + info.getLanguageName() + ", " + info.getUserName() + ": " + info.getRating());
			contentStream.newLineAtOffset(0, -15);
		}
		contentStream.endText();
		contentStream.close();
		document.save("InfoReport.pdf");
		document.close();
		}catch(Exception e) {
			
			
		}
	}
}
