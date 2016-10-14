/**
 * Created by Elise on 2016-10-14.
 */

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    private String fileName;
    private String charset;
    private List<String> lines;


    public ReadFile(String fileName, String charset){
        this.fileName = fileName;
        this.lines = new ArrayList<>();
        this.charset = charset;

        // Check if file exists
        this.readFile();

    }

    /**
     *
     * @return boolean - true if the file exists, false if it does not
     */
    private Boolean readFile(){
        boolean fileFound = false;
        String line;
        try (
                InputStream fis = new FileInputStream(this.fileName);
                InputStreamReader isr = new InputStreamReader(fis, Charset.forName(this.charset));
                BufferedReader br = new BufferedReader(isr);
        ) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fileFound = true;
        }

        return fileFound;
    }

    /**
     * Returns the file content, each element represents a line
     * @return List<String> file content
     */
    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
