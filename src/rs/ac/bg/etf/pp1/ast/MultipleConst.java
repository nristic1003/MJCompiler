// generated with ast extension for cup
// version 0.8
// 29/5/2021 22:43:56


package rs.ac.bg.etf.pp1.ast;

public class MultipleConst extends ListConst {

    private ListConst ListConst;
    private SingleConst SingleConst;

    public MultipleConst (ListConst ListConst, SingleConst SingleConst) {
        this.ListConst=ListConst;
        if(ListConst!=null) ListConst.setParent(this);
        this.SingleConst=SingleConst;
        if(SingleConst!=null) SingleConst.setParent(this);
    }

    public ListConst getListConst() {
        return ListConst;
    }

    public void setListConst(ListConst ListConst) {
        this.ListConst=ListConst;
    }

    public SingleConst getSingleConst() {
        return SingleConst;
    }

    public void setSingleConst(SingleConst SingleConst) {
        this.SingleConst=SingleConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ListConst!=null) ListConst.accept(visitor);
        if(SingleConst!=null) SingleConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ListConst!=null) ListConst.traverseTopDown(visitor);
        if(SingleConst!=null) SingleConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ListConst!=null) ListConst.traverseBottomUp(visitor);
        if(SingleConst!=null) SingleConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleConst(\n");

        if(ListConst!=null)
            buffer.append(ListConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleConst!=null)
            buffer.append(SingleConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleConst]");
        return buffer.toString();
    }
}
