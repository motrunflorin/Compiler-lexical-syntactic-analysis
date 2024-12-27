package cool.ast;

import org.antlr.v4.runtime.Token;

import java.util.List;

public abstract class ExprNode extends  ASTNode {
    public abstract void print(StringBuilder sb, String indent);

}

class IntNode extends ExprNode {
    final int value;

    public IntNode(int value) {
        this.value = value;
    }

    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append(value).append("\n");
    }

}

class StringNode extends ExprNode {
    final String value;

    public StringNode(String value) {
        this.value = value;
    }

    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append(value).append("\n");
    }
}

class BoolNode extends ExprNode {
    final boolean value;

    public BoolNode(boolean value) {
        this.value = value;
    }


    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append(value).append("\n");
    }
}

class VarNode extends ExprNode {
    final String varName;

    public VarNode(String varName) {
        this.varName = varName;
    }


    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append(varName).append("\n");
    }

}

class BinaryOpNode extends ExprNode {
    final String operator;
    final ExprNode left;
    final ExprNode right;

    public BinaryOpNode(String operator, ExprNode left, ExprNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public void print(StringBuilder sb, String indent) {
        if (left != null && right != null) {
            sb.append(indent).append(operator).append("\n");
            left.print(sb, indent + "  ");
            right.print(sb, indent + "  ");
        }
    }
}

class UnaryOpNode extends ExprNode {
    final String operator;
    final ExprNode operand;

    public UnaryOpNode(String operator, ExprNode operand) {
        this.operator = operator;
        this.operand = operand;
    }


    public void print(StringBuilder sb, String indent) {
        if (operand != null && operator != null) {
            sb.append(indent).append(operator).append("\n");
            operand.print(sb, indent + "  ");
        }
    }

}

class AssignmentNode extends ExprNode {
    final ExprNode left;
    final ExprNode right;

    public AssignmentNode(ExprNode left, ExprNode right) {
        this.left = left;
        this.right = right;
    }

    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append("<-\n");
        left.print(sb, indent + "  ");
        right.print(sb, indent + "  ");
    }

}

class IsVoidNode extends ExprNode {
    final ExprNode operand;

    public IsVoidNode(ExprNode operand) {
        this.operand = operand;
    }

    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append("isvoid\n");
        operand.print(sb, indent + "  ");
    }
}

class NewNode extends ExprNode {
    final VarNode operand;

    public NewNode(VarNode operand) {
        this.operand = operand;
    }

    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append("new\n");
        operand.print(sb, indent + "  ");
    }
}


class NotNode extends ExprNode {
    final ExprNode operand;

    public NotNode(ExprNode operand) {
        this.operand = operand;
    }


    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append("not\n");
        operand.print(sb, indent + "  ");
    }

}

class IfNode extends ExprNode {
    final ExprNode condition;
    final ExprNode thenBranch;
    final ExprNode elseBranch;

    public IfNode(ExprNode condition, ExprNode thenBranch, ExprNode elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }


    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append("if\n");
        if (thenBranch != null) {
            condition.print(sb, indent + "  ");
            thenBranch.print(sb, indent + "  ");
            elseBranch.print(sb, indent + "  ");
        }
    }
}

class WhileNode extends ExprNode {
    final ExprNode condition;
    final ExprNode body;

    public WhileNode(ExprNode condition, ExprNode body) {
        this.condition = condition;
        this.body = body;
    }


    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append("while\n");
        condition.print(sb, indent + "  ");
        body.print(sb, indent + "  ");
    }
}

class LetNode extends ExprNode {
    final List<AttributeNode> locals;
    final ExprNode body;

    public LetNode(List<AttributeNode> locals, ExprNode body) {
        this.locals = locals;
        this.body = body;
    }


    public void print(StringBuilder sb, String indent) {
        if (locals  != null && body != null)  {
            sb.append(indent).append("let\n");
            for (AttributeNode local : locals) {
                sb.append(indent).append("  local");
                local.print(sb, indent + "  ");
            }
            body.print(sb, indent + "  ");
        }
    }
}

class CaseNode extends ExprNode {
    final ExprNode caseValue;
    final List<CaseBranchNode> branches;

    public CaseNode(ExprNode caseValue, List<CaseBranchNode> branches) {
        this.caseValue = caseValue;
        this.branches = branches;
    }


    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append("case\n");
        caseValue.print(sb, indent + "  ");
        for (CaseBranchNode branch : branches) {
            branch.print(sb, indent + "  ");
        }
    }
}

class BlockNode extends ExprNode {
    final List<ExprNode> actions;

    public BlockNode(List<ExprNode> actions) {
        this.actions = actions;
    }


    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append("block\n");
        for (ExprNode action : actions) {
            if (action != null) {
                action.print(sb, indent + "  ");
            }
        }
    }
}

class ExplicitCallNode extends ExprNode {
    final ExprNode prefix;
    final String methodName;
    final Token atType;
    final List<ExprNode> arguments;


    public ExplicitCallNode(ExprNode prefix, String methodName, Token atType, List<ExprNode> arguments) {
        this.prefix = prefix;
        this.methodName = methodName;
        this.atType = atType;
        this.arguments = arguments;
    }

    public void print(StringBuilder sb, String indent) {
            sb.append(indent).append(".\n");
            prefix.print(sb, indent + "  ");

            if (atType != null) {
                sb.append(indent).append("  ").append(atType.getText()).append("\n");
            }

            sb.append(indent).append("  ").append(methodName).append("\n");

            for (ExprNode arg : arguments) {
                arg.print(sb, indent + "  ");
            }
    }
}

class ImplicitCallNode extends ExprNode {
    final String methodName;
    final List<ExprNode> arguments;

    public ImplicitCallNode(String methodName, List<ExprNode> arguments) {
        this.methodName = methodName;
        this.arguments = arguments;
    }



    public void print(StringBuilder sb, String indent) {
        sb.append(indent).append("implicit dispatch\n").append("  ");
        sb.append(indent).append(methodName).append("\n");
        for (ExprNode arg : arguments) {
            arg.print(sb, indent + "  ");
        }
    }
}
