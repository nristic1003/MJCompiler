// generated with ast extension for cup
// version 0.8
// 29/5/2021 22:43:56


package rs.ac.bg.etf.pp1.ast;

public class IfWithoutElse extends Statement {

    private CondIf CondIf;
    private FixMatched FixMatched;
    private Statement Statement;
    private FixUnmatched FixUnmatched;

    public IfWithoutElse (CondIf CondIf, FixMatched FixMatched, Statement Statement, FixUnmatched FixUnmatched) {
        this.CondIf=CondIf;
        if(CondIf!=null) CondIf.setParent(this);
        this.FixMatched=FixMatched;
        if(FixMatched!=null) FixMatched.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.FixUnmatched=FixUnmatched;
        if(FixUnmatched!=null) FixUnmatched.setParent(this);
    }

    public CondIf getCondIf() {
        return CondIf;
    }

    public void setCondIf(CondIf CondIf) {
        this.CondIf=CondIf;
    }

    public FixMatched getFixMatched() {
        return FixMatched;
    }

    public void setFixMatched(FixMatched FixMatched) {
        this.FixMatched=FixMatched;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public FixUnmatched getFixUnmatched() {
        return FixUnmatched;
    }

    public void setFixUnmatched(FixUnmatched FixUnmatched) {
        this.FixUnmatched=FixUnmatched;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondIf!=null) CondIf.accept(visitor);
        if(FixMatched!=null) FixMatched.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(FixUnmatched!=null) FixUnmatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondIf!=null) CondIf.traverseTopDown(visitor);
        if(FixMatched!=null) FixMatched.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(FixUnmatched!=null) FixUnmatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondIf!=null) CondIf.traverseBottomUp(visitor);
        if(FixMatched!=null) FixMatched.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(FixUnmatched!=null) FixUnmatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfWithoutElse(\n");

        if(CondIf!=null)
            buffer.append(CondIf.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FixMatched!=null)
            buffer.append(FixMatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FixUnmatched!=null)
            buffer.append(FixUnmatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfWithoutElse]");
        return buffer.toString();
    }
}
