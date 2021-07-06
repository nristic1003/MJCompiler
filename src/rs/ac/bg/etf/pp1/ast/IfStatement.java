// generated with ast extension for cup
// version 0.8
// 29/5/2021 22:43:56


package rs.ac.bg.etf.pp1.ast;

public class IfStatement extends Statement {

    private CondIf CondIf;
    private FixMatched FixMatched;
    private Statement Statement;
    private ElseKeyword ElseKeyword;
    private Statement Statement1;
    private MatchedStatementElseFix MatchedStatementElseFix;

    public IfStatement (CondIf CondIf, FixMatched FixMatched, Statement Statement, ElseKeyword ElseKeyword, Statement Statement1, MatchedStatementElseFix MatchedStatementElseFix) {
        this.CondIf=CondIf;
        if(CondIf!=null) CondIf.setParent(this);
        this.FixMatched=FixMatched;
        if(FixMatched!=null) FixMatched.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ElseKeyword=ElseKeyword;
        if(ElseKeyword!=null) ElseKeyword.setParent(this);
        this.Statement1=Statement1;
        if(Statement1!=null) Statement1.setParent(this);
        this.MatchedStatementElseFix=MatchedStatementElseFix;
        if(MatchedStatementElseFix!=null) MatchedStatementElseFix.setParent(this);
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

    public ElseKeyword getElseKeyword() {
        return ElseKeyword;
    }

    public void setElseKeyword(ElseKeyword ElseKeyword) {
        this.ElseKeyword=ElseKeyword;
    }

    public Statement getStatement1() {
        return Statement1;
    }

    public void setStatement1(Statement Statement1) {
        this.Statement1=Statement1;
    }

    public MatchedStatementElseFix getMatchedStatementElseFix() {
        return MatchedStatementElseFix;
    }

    public void setMatchedStatementElseFix(MatchedStatementElseFix MatchedStatementElseFix) {
        this.MatchedStatementElseFix=MatchedStatementElseFix;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondIf!=null) CondIf.accept(visitor);
        if(FixMatched!=null) FixMatched.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ElseKeyword!=null) ElseKeyword.accept(visitor);
        if(Statement1!=null) Statement1.accept(visitor);
        if(MatchedStatementElseFix!=null) MatchedStatementElseFix.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondIf!=null) CondIf.traverseTopDown(visitor);
        if(FixMatched!=null) FixMatched.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ElseKeyword!=null) ElseKeyword.traverseTopDown(visitor);
        if(Statement1!=null) Statement1.traverseTopDown(visitor);
        if(MatchedStatementElseFix!=null) MatchedStatementElseFix.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondIf!=null) CondIf.traverseBottomUp(visitor);
        if(FixMatched!=null) FixMatched.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ElseKeyword!=null) ElseKeyword.traverseBottomUp(visitor);
        if(Statement1!=null) Statement1.traverseBottomUp(visitor);
        if(MatchedStatementElseFix!=null) MatchedStatementElseFix.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStatement(\n");

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

        if(ElseKeyword!=null)
            buffer.append(ElseKeyword.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement1!=null)
            buffer.append(Statement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatchedStatementElseFix!=null)
            buffer.append(MatchedStatementElseFix.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStatement]");
        return buffer.toString();
    }
}
