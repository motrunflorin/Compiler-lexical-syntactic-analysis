package cool.ast;

public class ASTPrinter {
    public static void print(ASTNode root) {
        StringBuilder sb = new StringBuilder();
        root.print(sb, "");
        System.out.print(sb.toString().stripTrailing() + "\n");
    }
}
