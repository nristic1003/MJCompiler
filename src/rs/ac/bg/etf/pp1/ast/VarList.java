// generated with ast extension for cup
// version 0.8
// 29/5/2021 22:43:56


package rs.ac.bg.etf.pp1.ast;

public class VarList extends ListOfVars {

    private ListOfVars ListOfVars;
    private SingleVar SingleVar;

    public VarList (ListOfVars ListOfVars, SingleVar SingleVar) {
        this.ListOfVars=ListOfVars;
        if(ListOfVars!=null) ListOfVars.setParent(this);
        this.SingleVar=SingleVar;
        if(SingleVar!=null) SingleVar.setParent(this);
    }

    public ListOfVars getListOfVars() {
        return ListOfVars;
    }

    public void setListOfVars(ListOfVars ListOfVars) {
        this.ListOfVars=ListOfVars;
    }

    public SingleVar getSingleVar() {
        return SingleVar;
    }

    public void setSingleVar(SingleVar SingleVar) {
        this.SingleVar=SingleVar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ListOfVars!=null) ListOfVars.accept(visitor);
        if(SingleVar!=null) SingleVar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ListOfVars!=null) ListOfVars.traverseTopDown(visitor);
        if(SingleVar!=null) SingleVar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ListOfVars!=null) ListOfVars.traverseBottomUp(visitor);
        if(SingleVar!=null) SingleVar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarList(\n");

        if(ListOfVars!=null)
            buffer.append(ListOfVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleVar!=null)
            buffer.append(SingleVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarList]");
        return buffer.toString();
    }
}
