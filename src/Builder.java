import java.io.FileWriter;
import java.io.IOException;

class Report {
    private String header;
    private String content;
    private String footer;
    private ReportStyle style;
    private String styleTag;

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "Report{" +
                "header='" + header + '\'' +
                ", content='" + content + '\'' +
                ", footer='" + footer + '\'' +
                ", style=" + style +
                ", styleTag='" + styleTag + '\'' +
                '}';
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }


    public void setStyle(ReportStyle style) {
        this.style = style;
    }

    public void setStyleTag(String styleTag) {
        this.styleTag = styleTag;
    }

    public void export() {
        System.out.println("Header: " + header);
        System.out.println("Content: " + content);
        System.out.println("Footer: " + footer);
    }
}

interface IReportBuilder {
    void setHeader(String header);
    void setContent(String content);
    void setFooter(String footer);
    void addSection(String sectionName, String sectionContent);
    void setStyle(ReportStyle style);


    Report getReport();
}





class ReportStyle {
    private String backgroundColor;
    private String fontColor;
    private int fontSize;

    public ReportStyle(String backgroundColor, String fontColor, int fontSize) {
        this.backgroundColor = backgroundColor;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getFontColor() {
        return fontColor;
    }

    public int getFontSize() {
        return fontSize;
    }
}



class  PDFReportBuilder implements  IReportBuilder
{
    private Report report = new Report();
    private FileWriter writer;



    @Override
    public void setHeader(String header) {
        report.setHeader(header);
    }

    @Override
    public void setContent(String content) {
        report.setContent(content);
    }

    @Override
    public void setFooter(String footer) {
        report.setFooter(footer);
    }

    @Override
    public void addSection(String sectionName, String sectionContent) {

    }

    @Override
    public void setStyle(ReportStyle style) {
        report.setStyle(style);

    }

    @Override
    public Report getReport() {
        return report;
    }



    {
        try {
            writer = new FileWriter("Writer");
            writer.write(getReport().toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}




class TextReportBuilder implements IReportBuilder {
    private Report report = new Report();

    @Override
    public void setHeader(String header) {
        report.setHeader(header);
    }

    @Override
    public void setContent(String content) {
        report.setContent(content);
    }

    @Override
    public void setFooter(String footer) {
        report.setFooter(footer);
    }

    @Override
    public void addSection(String sectionName, String sectionContent) {

    }


    @Override
    public void setStyle(ReportStyle style) {
        report.setStyle(style);
    }

    @Override
    public Report getReport() {
        return report;
    }
}


class ReportDirector {
    private IReportBuilder builder;
    private ReportStyle style;
    public ReportDirector(IReportBuilder _reportBuilder,ReportStyle _style)
    {
        this.builder = _reportBuilder;
        this.style = _style;
    }

    public void constructReport() {
        builder.setStyle(style);
        builder.setHeader("Отчет за месяц");
        builder.setContent("Основное содержимое отчета.");
        builder.addSection("Продажи", "Детальная информация о продажах.");
        builder.setFooter("Подпись: Генеральный директор");
    }


}




public class Builder {
    public static void main(String[] args) {
        ReportStyle reportStyle = new ReportStyle("Черный","Синий",12);
        IReportBuilder pdfbuilder = new PDFReportBuilder();
        ReportDirector director  = new ReportDirector(pdfbuilder,reportStyle);
        director.constructReport();
        Report pdf = pdfbuilder.getReport();
        pdf.export();

        
        
    }
}

