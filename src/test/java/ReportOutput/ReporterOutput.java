package ReportOutput;

import org.testng.Reporter;

public class ReporterOutput {

    public static String ReporterLog(String log) {
        System.out.println(log);
        Reporter.log(log);
        return log;
    }
}
