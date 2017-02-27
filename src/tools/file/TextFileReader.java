package tools.file;

import com.rong360.im.common.CheckArgs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by chao.cheng on 2016/4/23.
 */
public class TextFileReader {

    private String filePath;

    public TextFileReader(String filePath) {
        this.filePath = filePath;
    }

    public String findLine(String keyword, int up, int down) {
        if (CheckArgs.Strings.isEmpty(keyword)) return null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String line;
            StringBuilder builder = new StringBuilder();
            LinkedList<String> before = new LinkedList<String>();
            boolean isFound = false;
            int after = 0;
            while ((line = reader.readLine()) != null) {
                if (up > 0) {
                    before.add(line);
                    if (before.size() > up + 1) {
                        before.removeFirst();
                    }
                }

                if (isFound && after < down) {
                    builder.append(line).append("\r\n");
                    after++;
                    continue;
                }
                if (isFound) {
                    builder.append("------------").append("\r\n");
                    isFound = false;
                }
                if (line.contains(keyword)) {
                    for (int i = 0; i < before.size() - 1; i++) {
                        builder.append(before.get(i)).append("\r\n");
                    }
                    builder.append(line).append("\r\n");
                    isFound = true;
                }
            }
            return builder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                //ignore
            }
        }
    }

    public String findLine(String keyword) {
        return findLine(keyword, 0, 0);
    }

}
