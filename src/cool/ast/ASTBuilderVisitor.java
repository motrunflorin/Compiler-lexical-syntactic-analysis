package cool.ast;

import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;

import java.util.ArrayList;
import java.util.List;

public class ASTBuilderVisitor extends CoolParserBaseVisitor<ASTNode> {


    public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        ProgramNode programNode = new ProgramNode();
        for (CoolParser.ClassDefContext classCtx : ctx.classDef()) {
            programNode.children.add(visitClassDef(classCtx));
        }

        return programNode;
    }


    public ASTNode visitClassDef(CoolParser.ClassDefContext ctx) {
        String className = ctx.TYPE(0).getText();
        String parentName = ctx.TYPE().size() > 1 ? ctx.TYPE(1).getText() : null;

        ClassNode classNode = new ClassNode(className, parentName);

        for (CoolParser.FeatureContext featureCtx : ctx.feature()) {
            classNode.features.add((FeatureNode) visitFeature(featureCtx));
        }

        return classNode;
    }


    public ASTNode visitFeature(CoolParser.FeatureContext ctx) {
        String name = ctx.ID().getText();
        String type = ctx.TYPE().getText();

        if (ctx.LPAREN() != null) {
            return visitMethod(ctx, name, type);
        } else if (ctx.expr() != null) {
            return visitAttribute(ctx, name, type);
        }

        return new AttributeNode(name, type, null, false);
    }

    public ASTNode visitMethod(CoolParser.FeatureContext ctx, String name, String type) {
        List<FormalNode> formals = new ArrayList<>();

        if (ctx.formal() != null) {
            for (CoolParser.FormalContext formalCtx : ctx.formal()) {
                formals.add((FormalNode) visitFormal(formalCtx));
            }
        }
        ExprNode body = ctx.expr() != null ? (ExprNode) visit(ctx.expr()) : null;

        return new MethodNode(name, formals, type, body);
    }

    public ASTNode visitAttribute(CoolParser.FeatureContext ctx, String name, String type) {
        ExprNode initializer = null;
        if (ctx.expr() != null) {
            initializer = (ExprNode) visit(ctx.expr());
        }

        return new AttributeNode(name, type, initializer, false);
    }



    public ASTNode visitFormal(CoolParser.FormalContext ctx) {
        String name = ctx.ID().getText();
        String type = ctx.TYPE().getText();

        return new FormalNode(name, type);
    }


    public ASTNode visitPlusMinus(CoolParser.PlusMinusContext ctx) {
        String operator = ctx.PLUS() != null ? "+" : "-";
        ExprNode left = (ExprNode) visit(ctx.expr(0));
        ExprNode right = (ExprNode) visit(ctx.expr(1));

        return new BinaryOpNode(operator, left, right);
    }


    public ASTNode visitMultDiv(CoolParser.MultDivContext ctx) {
        String operator = ctx.MULT() != null ? "*" : "/";
        ExprNode left = (ExprNode) visit(ctx.expr(0));
        ExprNode right = (ExprNode) visit(ctx.expr(1));

        return new BinaryOpNode(operator, left, right);
    }


    public ASTNode visitRelational(CoolParser.RelationalContext ctx) {
        String operator;

        if (ctx.LE() != null) {
            operator = "<=";
        } else if (ctx.LT() != null) {
            operator = "<";
        } else if (ctx.EQUAL() != null) {
            operator = "=";
        } else {
            throw new UnsupportedOperationException("Unknown relational operator");
        }

        ExprNode left = (ExprNode) visit(ctx.expr(0));
        ExprNode right = (ExprNode) visit(ctx.expr(1));

        return new BinaryOpNode(operator, left, right);
    }


    public ASTNode visitId(CoolParser.IdContext ctx) {
        return new VarNode(ctx.getText());
    }


    public ASTNode visitInt(CoolParser.IntContext ctx) {
        return new IntNode(Integer.parseInt(ctx.getText()));
    }


    public ASTNode visitBool(CoolParser.BoolContext ctx) {
        return new BoolNode(Boolean.parseBoolean(ctx.getText()));
    }


    public ASTNode visitString(CoolParser.StringContext ctx) {
        return new StringNode(ctx.getText());
    }


    public ASTNode visitParen(CoolParser.ParenContext ctx) {
        return visit(ctx.expr());
    }


    public ASTNode visitComplement(CoolParser.ComplementContext ctx) {
        return new UnaryOpNode("~", (ExprNode) visit(ctx.expr()));
    }


    public ASTNode visitNot(CoolParser.NotContext ctx) {
        ExprNode operand = (ExprNode) visit(ctx.expr());

        return new NotNode(operand);
    }


    public ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
        ExprNode operand = (ExprNode) visit(ctx.expr());

        return new IsVoidNode(operand);
    }


    public ASTNode visitNew(CoolParser.NewContext ctx) {
        String className = ctx.TYPE().getText();
        VarNode varNode = new VarNode(className);

        return new NewNode(varNode);
    }


    public ASTNode visitAssign(CoolParser.AssignContext ctx) {
        ExprNode left = new VarNode(ctx.ID().getText());
        ExprNode right = (ExprNode) visit(ctx.expr());

        return new AssignmentNode(left, right);
    }



    public ASTNode visitIf(CoolParser.IfContext ctx) {
        ExprNode condition = (ExprNode) visit(ctx.expr(0));
        ExprNode thenBranch = (ExprNode) visit(ctx.expr(1));
        ExprNode elseBranch = (ExprNode) visit(ctx.expr(2));

        return new IfNode(condition, thenBranch, elseBranch);
    }


    public ASTNode visitWhile(CoolParser.WhileContext ctx) {
        ExprNode condition = (ExprNode) visit(ctx.expr(0));
        ExprNode body = (ExprNode) visit(ctx.expr(1));

        return new WhileNode(condition, body);
    }


    public ASTNode visitLet(CoolParser.LetContext ctx) {
        List<AttributeNode> locals = new ArrayList<>();
        for (CoolParser.LocalContext localContext : ctx.local()) {
            String varName = localContext.ID().getText();
            String varType = localContext.TYPE().getText();

            ExprNode initExpr = null;
            if (localContext.expr() != null) {
                initExpr = (ExprNode) visit(localContext.expr());
            }

            AttributeNode attribute = new AttributeNode(varName, varType, initExpr, true);
            locals.add(attribute);
        }

        ExprNode body = (ExprNode) visit(ctx.expr());
        return new LetNode(locals, body);
    }


    public ASTNode visitCase(CoolParser.CaseContext ctx) {
        ExprNode caseValue = (ExprNode) visit(ctx.expr(0));
        List<CaseBranchNode> branches = new ArrayList<>();

        for (int i = 0; i < ctx.ID().size(); i++) {
            String varName = ctx.ID(i).getText();
            String varType = ctx.TYPE(i).getText();
            ExprNode branchExpr = (ExprNode) visit(ctx.expr(i + 1));

            branches.add(new CaseBranchNode(varName, varType, branchExpr));
        }

        return new CaseNode(caseValue, branches);
    }



    public ASTNode visitBlock(CoolParser.BlockContext ctx) {
        List<ExprNode> expressions = new ArrayList<>();

        for (CoolParser.ExprContext exprCtx : ctx.expr()) {
            ExprNode expr = (ExprNode) visit(exprCtx);
            expressions.add(expr);
        }

        return new BlockNode(expressions);
    }


    public ASTNode visitExplicitCall(CoolParser.ExplicitCallContext ctx) {
        ExprNode prefix = (ExprNode) visit(ctx.expr(0));

        String methodName = ctx.ID().getText();

        var atType = ctx.TYPE() != null ? ctx.TYPE().getSymbol() : null;

        List<ExprNode> arguments = new ArrayList<>();
        if (ctx.args != null) {
            for (CoolParser.ExprContext exprCtx : ctx.args) {
                arguments.add((ExprNode) visit(exprCtx));
            }
        }

        return new ExplicitCallNode(prefix, methodName, atType, arguments);
    }


    public ASTNode visitImplicitCall(CoolParser.ImplicitCallContext ctx) {
        String methodName = ctx.ID().getText();

        List<ExprNode> arguments = new ArrayList<>();
        if (ctx.args != null) {
            for (CoolParser.ExprContext exprCtx : ctx.args) {
                arguments.add((ExprNode) visit(exprCtx));
            }
        }

        return new ImplicitCallNode(methodName, arguments);
    }

}

