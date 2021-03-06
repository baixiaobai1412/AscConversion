package ord.AscConversion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SystemOperate {
    public static void CallSystem(String execute) {
        StringBuilder CommandResult = new StringBuilder();
        try {
            Runtime run = Runtime.getRuntime();
            Process process = run.exec(execute);

            InputStream InputStream = process.getInputStream();
            InputStreamReader InputStreamReader = new InputStreamReader(InputStream);
            BufferedReader BufferedReader = new BufferedReader(InputStreamReader);

            String inputLine;
            while ((inputLine = BufferedReader.readLine()) != null) {
                System.out.println(inputLine);
                CommandResult.append(inputLine);
            }
            BufferedReader.close();
//
        } catch (IOException a) {
            System.out.println("执行失败");
        }
    }
}
