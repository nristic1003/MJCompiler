// generated with ast extension for cup
// version 0.8
// 29/5/2021 22:43:56


package rs.ac.bg.etf.pp1.ast;

public class ArrayDesignator extends Designator {

    private DessignatorArr DessignatorArr;
    private Expr Expr;

    public ArrayDesignator (DessignatorArr DessignatorArr, Expr Expr) {
        this.DessignatorArr=DessignatorArr;
        if(DessignatorArr!=null) DessignatorArr.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DessignatorArr getDessignatorArr() {
        return DessignatorArr;
    }

    public void setDessignatorArr(DessignatorArr DessignatorArr) {
        this.DessignatorArr=DessignatorArr;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DessignatorArr!=null) DessignatorArr.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DessignatorArr!=null) DessignatorArr.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DessignatorArr!=null) DessignatorArr.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ArrayDesignator(\n");

        if(DessignatorArr!=null)
            buffer.append(DessignatorArr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ArrayDesignator]");
        return buffer.toString();
    }
}
