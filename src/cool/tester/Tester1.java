package cool.tester;

import java.io.*;
import java.util.*;

import cool.compiler.Compiler;

public class Tester1 {
    // java -cp "bin;antlr-4.8-complete.jar;%CLASSPATH%" coolc.tester.Tester1
    public static void main(String[] args) throws IOException {
        final String TEST_DIR_NAME = "tests/tema1";
        var testDir = new File(TEST_DIR_NAME);
        
        var filenameFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".cl");
            }
        };
        
        var oldOut = System.out;
        var oldErr = System.err;
        
        var total = 0;

        var files = testDir.listFiles(filenameFilter);
        Arrays.sort(files);
        for (var file : files) {
            var inPath = file.getPath();
            var outPath = inPath.replace(".cl", ".out");
            var newOut = new PrintStream(outPath, "UTF-8");
            System.setOut(newOut);
            System.setErr(newOut);
            Compiler.main(new String[] { inPath });
            
            oldOut.printf("%-30s -> ", file.getName());
            var result = compare(outPath, inPath.replace(".cl", ".ref"));
            if (result == 0) {
                oldOut.println("OK");
                total += 5;
            }
            else
                oldOut.println("Failed at line " + result);
        }
        
        oldOut.println("Total: " + total);
        
        System.setOut(oldOut);
        System.setErr(oldErr);
    }
    
    public static int compare(String fileName1, String fileName2)
            throws IOException {
        try (LineNumberReader reader1 = new LineNumberReader(new FileReader(fileName1));
             LineNumberReader reader2 = new LineNumberReader(new FileReader(fileName2));
        ) {
            String line1 = null, line2 = null;
            
            while ((line1 = reader1.readLine()) != null
                    & (line2 = reader2.readLine()) != null) {
                
                if (! line1.equals(line2))
                    return reader1.getLineNumber();
            }
            
            if (line1 != null || line2 != null)
                return reader1.getLineNumber() + 1;
            
            return 0;
        }
    }

}
