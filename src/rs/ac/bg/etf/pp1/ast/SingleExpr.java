// generated with ast extension for cup
// version 0.8
// 29/5/2021 22:43:56


package rs.ac.bg.etf.pp1.ast;

public class SingleExpr extends Expr {

    private ListOfTerms ListOfTerms;

    public SingleExpr (ListOfTerms ListOfTerms) {
        this.ListOfTerms=ListOfTerms;
        if(ListOfTerms!=null) ListOfTerms.setParent(this);
    }

    public ListOfTerms getListOfTerms() {
        return ListOfTerms;
    }

    public void setListOfTerms(ListOfTerms ListOfTerms) {
        this.ListOfTerms=ListOfTerms;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ListOfTerms!=null) ListOfTerms.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ListOfTerms!=null) ListOfTerms.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ListOfTerms!=null) ListOfTerms.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleExpr(\n");

        if(ListOfTerms!=null)
            buffer.append(ListOfTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleExpr]");
        return buffer.toString();
    }
}
