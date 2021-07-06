// generated with ast extension for cup
// version 0.8
// 29/5/2021 22:43:56


package rs.ac.bg.etf.pp1.ast;

public class SingleVar implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String varName;
    private OptionSquare OptionSquare;

    public SingleVar (String varName, OptionSquare OptionSquare) {
        this.varName=varName;
        this.OptionSquare=OptionSquare;
        if(OptionSquare!=null) OptionSquare.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public OptionSquare getOptionSquare() {
        return OptionSquare;
    }

    public void setOptionSquare(OptionSquare OptionSquare) {
        this.OptionSquare=OptionSquare;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionSquare!=null) OptionSquare.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionSquare!=null) OptionSquare.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionSquare!=null) OptionSquare.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleVar(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(OptionSquare!=null)
            buffer.append(OptionSquare.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleVar]");
        return buffer.toString();
    }
}
