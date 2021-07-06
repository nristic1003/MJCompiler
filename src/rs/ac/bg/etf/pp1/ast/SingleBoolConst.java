// generated with ast extension for cup
// version 0.8
// 29/5/2021 22:43:56


package rs.ac.bg.etf.pp1.ast;

public class SingleBoolConst extends SingleConst {

    private String boolName;
    private Boolean boolValue;

    public SingleBoolConst (String boolName, Boolean boolValue) {
        this.boolName=boolName;
        this.boolValue=boolValue;
    }

    public String getBoolName() {
        return boolName;
    }

    public void setBoolName(String boolName) {
        this.boolName=boolName;
    }

    public Boolean getBoolValue() {
        return boolValue;
    }

    public void setBoolValue(Boolean boolValue) {
        this.boolValue=boolValue;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleBoolConst(\n");

        buffer.append(" "+tab+boolName);
        buffer.append("\n");

        buffer.append(" "+tab+boolValue);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleBoolConst]");
        return buffer.toString();
    }
}
