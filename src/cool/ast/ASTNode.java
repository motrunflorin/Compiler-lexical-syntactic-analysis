package cool.ast;

import java.util.ArrayList;
import java.util.List;

public abstract class ASTNode {
    public abstract void print(StringBuilder sb, String indent);

}

class ProgramNode extends ASTNode {
    public List<ASTNode> children = new ArrayList<>();


    public void print(StringBuilder builder, String indent) {
        builder.append(indent).append("program\n");
        for (ASTNode child : children) {
            child.print(builder, indent + "  ");
        }
    }
}
class ClassNode extends ASTNode {
    public String className;
    public String parentName;
    public List<FeatureNode> features = new ArrayList<>();

    public ClassNode(String className, String parentName) {
        this.className = className;
        this.parentName = parentName;
    }


    public void print(StringBuilder builder, String indent) {
        builder.append(indent).append("class\n");
        builder.append(indent).append("  ").append(className).append("\n");

        if (parentName != null) {
            builder.append(indent).append("  ").append(parentName).append("\n");
        }

        for (FeatureNode feature : features) {
            feature.print(builder, indent + "  ");
        }

    }
}

class FormalNode extends ASTNode {
    final String name;
    final String type;

    public FormalNode(String name, String type) {
        this.name = name;
        this.type = type;
    }


    public void print(StringBuilder builder, String indent) {
        builder.append(indent).append("formal\n");
        builder.append(indent).append("  ").append(name).append("\n");
        builder.append(indent).append("  ").append(type).append("\n");
    }
}

abstract class FeatureNode extends ASTNode {
    public String name;
    public String type;

    public FeatureNode(String name, String type) {
        this.name = name;
        this.type = type;
    }
}

class MethodNode extends FeatureNode {
    final List<FormalNode> formals;
    final ExprNode body;

    public MethodNode(String name, List<FormalNode> formals, String returnType, ExprNode body) {
        super(name, returnType);
        this.formals = formals;
        this.body = body;
    }


    public void print(StringBuilder builder, String indent) {
        builder.append(indent).append("method").append("\n");
        builder.append(indent).append("  ").append(name).append("\n");

        for (FormalNode formal : formals) {
            formal.print(builder, indent + "  ");
        }

        builder.append(indent).append("  ").append(type).append("\n");

        if (body != null) {
            body.print(builder, indent + "  ");
        }

    }
}

class AttributeNode extends FeatureNode {
    final ExprNode initializer;
    final boolean isLet;

    public AttributeNode(String name, String type, ExprNode initializer, boolean isLet) {
        super(name, type);
        this.initializer = initializer;
        this.isLet = isLet;
    }


    public void print(StringBuilder sb, String indent) {
        if (!isLet) {
            sb.append(indent).append("attribute");
        }
        sb.append("\n");
        sb.append(indent).append("  ").append(name).append("\n");
        sb.append(indent).append("  ").append(type).append("\n");

        if (initializer != null) {
            initializer.print(sb, indent + "  ");
        }

    }
}

class CaseBranchNode extends ASTNode {
    final String varName;
    final String type;
    final ExprNode branchExpr;

    public CaseBranchNode(String varName, String type, ExprNode branchExpr) {
        this.varName = varName;
        this.type = type;
        this.branchExpr = branchExpr;
    }

    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append("case branch\n");

        sb.append(indent).append("  ").append(varName).append("\n");
        sb.append(indent).append("  ").append(type).append("\n");

        if (branchExpr != null) {
            branchExpr.print(sb, indent + "  ");
        }
    }
}
