package cn.wjw.code.review.sdk;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * @author weir
 * @description
 * @date 2025-03-18 09:30
 */
public class OpenAiCodeReview {
    public static void main(String[] args) throws Exception {
        System.out.println("测试执行");

        ProcessBuilder processBuilder = new ProcessBuilder("git", "diff", "HEAD~", "HEAD");
        processBuilder.directory(new File("."));
        Process process = processBuilder.start();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        StringBuilder diffCode = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            diffCode.append(line);
        }

        int exitCode = process.waitFor();

        System.out.println("Exited with code:" + exitCode);

        System.out.println("评审代码：" + diffCode.toString());
    }
}
