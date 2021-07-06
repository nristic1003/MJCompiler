// generated with ast extension for cup
// version 0.8
// 29/5/2021 22:43:56


package rs.ac.bg.etf.pp1.ast;

public class OneTerm extends ListOfTerms {

    private OptionMinus OptionMinus;
    private SingleTerm SingleTerm;

    public OneTerm (OptionMinus OptionMinus, SingleTerm SingleTerm) {
        this.OptionMinus=OptionMinus;
        if(OptionMinus!=null) OptionMinus.setParent(this);
        this.SingleTerm=SingleTerm;
        if(SingleTerm!=null) SingleTerm.setParent(this);
    }

    public OptionMinus getOptionMinus() {
        return OptionMinus;
    }

    public void setOptionMinus(OptionMinus OptionMinus) {
        this.OptionMinus=OptionMinus;
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
        if(OptionMinus!=null) OptionMinus.accept(visitor);
        if(SingleTerm!=null) SingleTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionMinus!=null) OptionMinus.traverseTopDown(visitor);
        if(SingleTerm!=null) SingleTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionMinus!=null) OptionMinus.traverseBottomUp(visitor);
        if(SingleTerm!=null) SingleTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneTerm(\n");

        if(OptionMinus!=null)
            buffer.append(OptionMinus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleTerm!=null)
            buffer.append(SingleTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneTerm]");
        return buffer.toString();
    }
}
