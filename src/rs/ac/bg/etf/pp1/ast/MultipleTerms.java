// generated with ast extension for cup
// version 0.8
// 29/5/2021 22:43:56


package rs.ac.bg.etf.pp1.ast;

public class MultipleTerms extends ListOfTerms {

    private ListOfTerms ListOfTerms;
    private Addop Addop;
    private SingleTerm SingleTerm;

    public MultipleTerms (ListOfTerms ListOfTerms, Addop Addop, SingleTerm SingleTerm) {
        this.ListOfTerms=ListOfTerms;
        if(ListOfTerms!=null) ListOfTerms.setParent(this);
        this.Addop=Addop;
        if(Addop!=null) Addop.setParent(this);
        this.SingleTerm=SingleTerm;
        if(SingleTerm!=null) SingleTerm.setParent(this);
    }

    public ListOfTerms getListOfTerms() {
        return ListOfTerms;
    }

    public void setListOfTerms(ListOfTerms ListOfTerms) {
        this.ListOfTerms=ListOfTerms;
    }

    public Addop getAddop() {
        return Addop;
    }

    public void setAddop(Addop Addop) {
        this.Addop=Addop;
    }

    public SingleTerm getSingleTerm() {
        return SingleTerm;
    }

    public void setSingleTerm(SingleTerm SingleTerm) {
        this.SingleTerm=SingleTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ListOfTerms!=null) ListOfTerms.accept(visitor);
        if(Addop!=null) Addop.accept(visitor);
        if(SingleTerm!=null) SingleTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ListOfTerms!=null) ListOfTerms.traverseTopDown(visitor);
        if(Addop!=null) Addop.traverseTopDown(visitor);
        if(SingleTerm!=null) SingleTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ListOfTerms!=null) ListOfTerms.traverseBottomUp(visitor);
        if(Addop!=null) Addop.traverseBottomUp(visitor);
        if(SingleTerm!=null) SingleTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleTerms(\n");

        if(ListOfTerms!=null)
            buffer.append(ListOfTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Addop!=null)
            buffer.append(Addop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleTerm!=null)
            buffer.append(SingleTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleTerms]");
        return buffer.toString();
    }
}
