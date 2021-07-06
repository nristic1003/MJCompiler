// generated with ast extension for cup
// version 0.8
// 29/5/2021 22:43:56


package rs.ac.bg.etf.pp1.ast;

public class SingleV extends VarDecl {

    private Type Type;
    private ListOfVars ListOfVars;

    public SingleV (Type Type, ListOfVars ListOfVars) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ListOfVars=ListOfVars;
        if(ListOfVars!=null) ListOfVars.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ListOfVars getListOfVars() {
        return ListOfVars;
    }

    public void setListOfVars(ListOfVars ListOfVars) {
        this.ListOfVars=ListOfVars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ListOfVars!=null) ListOfVars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ListOfVars!=null) ListOfVars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ListOfVars!=null) ListOfVars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleV(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ListOfVars!=null)
            buffer.append(ListOfVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleV]");
        return buffer.toString();
    }
}
