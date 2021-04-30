import com.aspose.words.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Bullets {
    public static void main(String[] args) throws Exception {
        Document doc = new Document();

        FontSettings fs = FontSettings.getDefaultInstance();
        FolderFontSource folderSource = new FolderFontSource("./files/fonts", true);
        FontSourceBase[] fontSourceBase = new FontSourceBase[]{folderSource};
        for (PhysicalFontInfo info : folderSource.getAvailableFonts()) {
            System.out.println("Found font " + info.getFullFontName());
        }
        fs.setFontsSources(fontSourceBase);
        fs.getSubstitutionSettings().getDefaultFontSubstitution().setDefaultFontName("Liberation Sans");
        doc.setWarningCallback(warningInfo ->
            System.out.println(warningInfo.getDescription())
        );

        DocumentBuilder builder = new DocumentBuilder(doc);
        Path inputFile = Paths.get("./files/test.html");
        String data = Files.lines(inputFile).collect(Collectors.joining("\n"));

        builder.insertHtml(data);
        doc.save("./files/test.pdf");
    }
}
