package rs.ac.bg.etf.pp1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import rs.ac.bg.etf.pp1.ast.ArrayDesignator;
import rs.ac.bg.etf.pp1.ast.AssignDesign;
import rs.ac.bg.etf.pp1.ast.BoolFactor;
import rs.ac.bg.etf.pp1.ast.CharFactor;
import rs.ac.bg.etf.pp1.ast.DesignFactor;
import rs.ac.bg.etf.pp1.ast.DesignatorMM;
import rs.ac.bg.etf.pp1.ast.DesignatorPP;
import rs.ac.bg.etf.pp1.ast.DessignatorArr;
import rs.ac.bg.etf.pp1.ast.DivOperation;
import rs.ac.bg.etf.pp1.ast.ElseKeyword;
import rs.ac.bg.etf.pp1.ast.ElseStatement;
import rs.ac.bg.etf.pp1.ast.FixMatched;
import rs.ac.bg.etf.pp1.ast.FixUnmatched;
import rs.ac.bg.etf.pp1.ast.GEQRelOp;
import rs.ac.bg.etf.pp1.ast.GreaterRelOp;
import rs.ac.bg.etf.pp1.ast.LEQRelOp;
import rs.ac.bg.etf.pp1.ast.LowerRelOp;
import rs.ac.bg.etf.pp1.ast.MatchedStatementElseFix;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodName;
import rs.ac.bg.etf.pp1.ast.MinusExpr;
import rs.ac.bg.etf.pp1.ast.MinusOperator;
import rs.ac.bg.etf.pp1.ast.ModOperation;
import rs.ac.bg.etf.pp1.ast.MulOperation;
import rs.ac.bg.etf.pp1.ast.MultipleFactors;
import rs.ac.bg.etf.pp1.ast.MultipleOperators;
import rs.ac.bg.etf.pp1.ast.MultipleTerms;
import rs.ac.bg.etf.pp1.ast.NewFactor;
import rs.ac.bg.etf.pp1.ast.NotSameRelOp;
import rs.ac.bg.etf.pp1.ast.NumFactor;
import rs.ac.bg.etf.pp1.ast.OneCondFact;
import rs.ac.bg.etf.pp1.ast.OneTerm;
import rs.ac.bg.etf.pp1.ast.PlusOperator;
import rs.ac.bg.etf.pp1.ast.PrintStatementNoArg;
import rs.ac.bg.etf.pp1.ast.PrintStatementWArgs;
import rs.ac.bg.etf.pp1.ast.ReadStatement;
import rs.ac.bg.etf.pp1.ast.Relop;
import rs.ac.bg.etf.pp1.ast.RparenIf;
import rs.ac.bg.etf.pp1.ast.SameRelOp;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	Deque<Integer> operatorAdd;
	Deque<Integer> operatorMul;
	int operation;
	int level = 0;
	
	public class Fix
	{
		int adrThen =0;
		int adrElse = 0;
	}
	
	public HashMap<Integer, Fix> mapa = new HashMap<>();
	
	
	public CodeGenerator() {
		operatorAdd = new ArrayDeque<>();
		operatorMul = new ArrayDeque<>();
	}

	public int getMainPc() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void visit(PrintStatementNoArg printStmt)
	{
		if(printStmt.getExpr().struct.equals(Tab.intType))
		{
			Code.loadConst(5);
			Code.put(Code.print);
		}else
		{
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	
	public void visit(PrintStatementWArgs printStmt)
	{
		int args = printStmt.getN2();
		Code.loadConst(args);
		if(printStmt.getExpr().struct.equals(Tab.intType))
		{	
			Code.put(Code.print);
		}else
		{
			Code.put(Code.bprint);
		}
	}
	
	public void visit(NumFactor numFactor)
	{
		Obj num = Tab.insert(Obj.Con, "$", numFactor.struct);
		num.setLevel(0);
		num.setAdr(numFactor.getN1());
		Code.load(num);
	}
	
	public void visit(CharFactor charFactor)
	{
		Obj ch = Tab.insert(Obj.Con, "$", charFactor.struct);
		ch.setLevel(0);
		ch.setAdr(charFactor.getC1());
		Code.load(ch);
	}
	
	public void visit(OneTerm oneTerm)
	{
		if(oneTerm.getOptionMinus() instanceof MinusExpr)
		{
			Code.put(Code.neg);
		}
	}
	
	
	
	
	public void visit(BoolFactor boolFactor)
	{
		Obj b = Tab.insert(Obj.Con, "$", boolFactor.struct);
		b.setLevel(0);
		int x = boolFactor.getB1() ? 1 : 0;
		b.setAdr(x);
		Code.load(b);
	}
	
	public void visit(MethodName mname)
	{
		if("main".equalsIgnoreCase(mname.getMethodName()))
		{
			mainPc = Code.pc;
		}
		
		mname.obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(0);
		Code.put(mname.obj.getLocalSymbols().size());
	}
	
	public void visit(MethodDecl methodDecl)
	{
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(AssignDesign dessignAssign)
	{
		Code.store(dessignAssign.getDesignator().obj);
	}
	
	public void visit(DesignFactor dessignFactor)
	{
		Code.load(dessignFactor.getDesignator().obj);
	}
	
	public void visit(ReadStatement readStmt)
	{
		if(readStmt.getDesignator().obj.getType().equals(Tab.intType))
		{
			Code.put(Code.read);
		}else
		{
			Code.put(Code.bread);
		}
		Code.store(readStmt.getDesignator().obj);
	
	}

	
	public void visit(DessignatorArr arrDesgin)
	{
		Code.load(arrDesgin.getDesignator().obj); //proveri
	}
	
	public void visit(NewFactor newFactor)
	{
		if(newFactor.struct.getKind()==Struct.Array)
		{
			Code.put(Code.newarray);
			if(newFactor.getType().struct == Tab.intType)
			{
				Code.put(0);
			}else
			{
				Code.put(1);
			}
		}
	}
	
	public void visit(OneCondFact condFact)
	{
		Code.put(Code.const_1);

	
	}
	
//	public void visit (Test t)
//	{
//		int num = t.getN2();
//		Code.load(t.getDesignator().obj); //niz
//		Code.loadConst(num);          // num
//		Code.put(Code.aload);        //niz[num]
//		Code.load(t.getDesignator().obj);  //niz
//		Code.put(Code.dup);					//niz niz
//		Code.put(Code.arraylength);        //niz[num] niz arrle
//		Code.put(Code.const_1);
//		Code.put(Code.sub);            
//		Code.put(Code.aload);    //niz[num] niz[arrl - 1]
//		Code.put(Code.add);
//		Code.put(Code.const_1);
//		Code.put(Code.print);            //
//		
//	}
	

	public void visit(MultipleOperators ml)  { 
			 operation =getRelOP(ml.getRelop()); 
		 }
			

	
	public void visit(FixMatched fx)
	{
		Code.putFalseJump(operation, 0);
		int adr = Code.pc  -2;
		Fix f= new Fix();
		f.adrThen = adr;
		mapa.put(level, f);
		level++;
		
		//++
	}
	
	public void visit(FixUnmatched fu)
	{
		level--;
		Fix f = mapa.get(level);
		Code.fixup(f.adrThen);
	}
	
	public void visit(ElseKeyword e)
	{
		Code.putJump(0);
		int adr2 = Code.pc - 2;
		Fix f = mapa.get(level-1);
		f.adrElse = adr2;
		Code.fixup(f.adrThen);
		mapa.put(level - 1, f);
	}
	
	public void visit(MatchedStatementElseFix m)
	{
		level--;
		Fix f = mapa.get(level);
		Code.fixup(f.adrElse);
	}
	

	
	
	public int getRelOP(Relop relop) {
		if (relop instanceof SameRelOp) 		  { return Code.eq; }
		else if (relop instanceof NotSameRelOp) { return Code.ne; }
		else if (relop instanceof GreaterRelOp) 	  {	return Code.gt; }
		else if (relop instanceof GEQRelOp)   { return Code.ge; }
		else if (relop instanceof LowerRelOp) 	  { return Code.lt; }
		else if (relop instanceof LEQRelOp)   { return Code.le;	}
		return Code.eq;
	}
	
	public void visit(DesignatorPP des)
	{
		if(des.getDesignator().obj.getKind() ==Obj.Elem)
		{
			Code.put(Code.dup2);
		}
		
		Code.load(des.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(des.getDesignator().obj);
	}
	
	public void visit(DesignatorMM des)
	{
		if(des.getDesignator().obj.getKind() ==Obj.Elem)
		{
			Code.put(Code.dup2);
		}
		Code.load(des.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(des.getDesignator().obj);
	}
	
	
	
	public void visit(MultipleFactors mulFactors)
	{
		Code.put(operatorMul.pollLast());
	}
	
	public void visit(MultipleTerms addFactors)
	{
		Code.put(operatorAdd.pollLast());
	}
	
	public void visit(MulOperation mul)
	{
		operatorMul.addLast(Code.mul);
	}
	public void visit(ModOperation mod)
	{
		operatorMul.addLast(Code.rem);
	}
	public void visit(DivOperation div)
	{
		operatorMul.addLast(Code.div);
	}
	public void visit(PlusOperator plus)
	{
		operatorAdd.addLast(Code.add);
	}
	public void visit(MinusOperator minus)
	{
		operatorAdd.addLast(Code.sub);
	}
	
	
	
}
