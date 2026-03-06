package zmkg;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class PythonCaller {
    public static void main(String[] args) {
        try {
            // Python 脚本路径
            String pythonScriptPath = "/Users/coatardbul/PycharmProjects/pythonProject3/ai/test33.py";

            // 调用参数
            String dateStr = "2024-12-12";
            String code = "600255";

            // 构建命令
            ProcessBuilder processBuilder = new ProcessBuilder("python3", pythonScriptPath, dateStr, code);
            processBuilder.redirectErrorStream(true); // 合并错误流和标准输出流

            // 启动进程
            Process process = processBuilder.start();

            // 读取 Python 脚本输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 等待进程结束
            int exitCode = process.waitFor();
            System.out.println("Exit Code: " + exitCode);

            // 打印输出结果
            System.out.println("Python Script Output:");
            System.out.println(output.toString());
            // 解析 JSON 输出（如果需要）
            // 使用 Jackson 或 Gson 解析 output.toString()
            // e.g., ObjectMapper mapper = new ObjectMapper();
            // Map<String, Object> result = mapper.readValue(output.toString(), Map.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
