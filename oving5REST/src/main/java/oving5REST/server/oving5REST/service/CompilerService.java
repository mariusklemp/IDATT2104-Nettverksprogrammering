package oving5REST.server.oving5REST.service;

import java.io.*;

import org.springframework.stereotype.Service;
import oving5REST.server.oving5REST.model.SourceCode;

import java.io.BufferedWriter;

@Service
public class CompilerService {

    public String doCompile(SourceCode sourceCode) throws IOException {

        System.out.println("doCompile() called");
        File file = new File("/Users/mariusklemppetersen/Documents/NTNU/Semester4/Nettverksprog/NettverksprogOvinger/oving5REST/compile/main.cpp");
        StringBuilder output = new StringBuilder();
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.append(sourceCode.getSourceCode());
            sourceCode.setOutput("Output fra server!!");

            System.out.println("Builder docker");
            Process process = Runtime.getRuntime().exec("docker build --tag java-docker .");
            System.out.println("Kjører docker");
            Process process2 = Runtime.getRuntime().exec("docker run java-docker");
//            process.waitFor();
//            System.out.println("Finished compiling");
//            Process proc = Runtime.getRuntime().exec("java " + process);
//            System.out.println("Finished running");

            System.out.println("Lager string");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process2.getInputStream()));

            String s = null;
            while((s = stdInput.readLine()) != null){
                output.append(s).append("\n");
            }

            int exitValue = process2.waitFor();
            if (exitValue == 0) {
                System.out.println("Success");
                System.out.println(output);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return output.toString();
    }
}
