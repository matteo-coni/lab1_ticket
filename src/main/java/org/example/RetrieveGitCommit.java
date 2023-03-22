package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class RetrieveGitCommit {
    /*  You need change path name of your git project and the keyword
     *  for search ID's commits in the Main method of this class.
     */

    private static String OS = null;

    public static String getOsType()
    {
        // check type of OS in use
        if(OS == null) { OS = System.getProperty("os.name"); }
        return OS;
    }

    public static void retrieveIdThroughKeyWordLOCAL(String keyword, String pathName, Integer switchSO){
        /* Retrieve commit id from log of our local repository "lab1_ticket"
         *
         * switchOS variable assumptions:
         *      0 - Windows
         *      1 - Linux based
         */

        ProcessBuilder processBuilder = new ProcessBuilder().directory(new File(pathName));


        if(switchSO == 1){
            //command for Linux System
            processBuilder.command("cmd.exe", "/c", "git log --grep=\"" + keyword + "\" --pretty=format:\"%h\"");
        }

        else{
            //command for Windows
            processBuilder.command("bash", "-c", "git log --grep=\"" + keyword + "\" --pretty=format:\"%h\"");
        }

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            System.out.println("\n\nShowing IDs:\n");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }





    public static void main(String[] args){
        String path="C:\\Users\\Marco\\GitHub\\lab1_ticket";
        String keyword="roma";

        // Retrieve commit from Windows shell
        if(getOsType().startsWith("Windows")){
            retrieveIdThroughKeyWordLOCAL(keyword, path, 0);
        }

        // Retrieve commit from Linux based OS shell
        else{
            retrieveIdThroughKeyWordLOCAL(keyword, path, 1);
        }

    }



}
