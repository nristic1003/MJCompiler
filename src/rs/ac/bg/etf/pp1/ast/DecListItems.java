// generated with ast extension for cup
// version 0.8
// 29/5/2021 22:43:56


package rs.ac.bg.etf.pp1.ast;

public class DecListItems extends DeclList {

    private DeclList DeclList;
    private DecItem DecItem;

    public DecListItems (DeclList DeclList, DecItem DecItem) {
        this.DeclList=DeclList;
        if(DeclList!=null) DeclList.setParent(this);
        this.DecItem=DecItem;
        if(DecItem!=null) DecItem.setParent(this);
    }

    public DeclList getDeclList() {
        return DeclList;
    }

    public void setDeclList(DeclList DeclList) {
        this.DeclList=DeclList;
    }

    public DecItem getDecItem() {
        return DecItem;
    }

    public void setDecItem(DecItem DecItem) {
        this.DecItem=DecItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DeclList!=null) DeclList.accept(visitor);
        if(DecItem!=null) DecItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DeclList!=null) DeclList.traverseTopDown(visitor);
        if(DecItem!=null) DecItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DeclList!=null) DeclList.traverseBottomUp(visitor);
        if(DecItem!=null) DecItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DecListItems(\n");

        if(DeclList!=null)
            buffer.append(DeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DecItem!=null)
            buffer.append(DecItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DecListItems]");
        return buffer.toString();
    }
}
