package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.CompilerError.CompilerErrorType;
import rs.ac.bg.etf.pp1.ast.*;


import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;


public class SemanticAnalyzer extends VisitorAdaptor {
	
	
	int printCallCount = 0;
	int varDeclCount = 0;
	Obj currentMethod = null;
	Struct currentType = null;
	Struct currentMethodType =null;
	boolean returnFound = false;
	boolean errorDetected = false;
	boolean voidMethod = false;
	int nVars;
	
	private List<CompilerError> semErrors = new ArrayList<>();
	
	
	public SemanticAnalyzer()
	{
		BoolTab.init();
	}
	
	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
	
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
		{
			msg.append (" na liniji ").append(line);
			CompilerError c= new CompilerError(line, 	msg.append (" na liniji ").append(line).toString()  , CompilerErrorType.SEMANTIC_ERROR);
			semErrors.add(c);
		}
			
		log.error(msg.toString());
	}
	
	public List<CompilerError> getErrors()
	{
		return semErrors;
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public void visit(ProgName p)
	{
		p.obj = Tab.insert(Obj.Prog, p.getProgName(), Tab.noType);
		Tab.openScope();
	}
	
	public void visit(Program prog)
	{
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(prog.getProgName().obj);
		Tab.closeScope();
	}
	
	public void visit(Type type)
	{
		Obj t = Tab.find(type.getTypeName());
		if(t==Tab.noObj)
		{
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
			currentType = Tab.noType;
		}else
		{
			if(Obj.Type==t.getKind())
			{
				currentType =t.getType();
			}else
			{
				report_error("Ime: " + type.getTypeName() + " ne predstavlja tip ", null);
				currentType = Tab.noType;
			}
		}
		
	}
	
	@Override
	public void visit(SingleNumConst singleNumConst) {
		if(currentType.getKind()==Struct.Int)
		{
			Obj insertedConstant = Tab.insert(Obj.Con, singleNumConst.getConstName(), currentType);
			insertedConstant.setAdr(singleNumConst.getConstValue());
		}else
		{
			report_error("Ime: " + singleNumConst.getConstName() + " ne predstavlja konstantu ", null);
		}
	}
	
	@Override
	public void visit(SingleCharConst singleCharConst) {
		if(currentType.getKind()==Struct.Char)
		{
			Obj insertedConstant = Tab.insert(Obj.Con, singleCharConst.getCharName(), currentType);
			insertedConstant.setAdr(singleCharConst.getCharValue()); 
		}else
		{
			report_error("Ime: " + singleCharConst.getCharName() + " ne predstavlja konstantu ", null);
		}
	}
	
	@Override
	public void visit(SingleBoolConst singleBoolConst) {
		if(currentType.getKind()==Struct.Bool)
		{
			Obj insertedConstant = Tab.insert(Obj.Con, singleBoolConst.getBoolName(), currentType);
			int x=0;
			if(singleBoolConst.getBoolValue()) x=1;
			else x=0;
			insertedConstant.setAdr(x);
		}else
		{
			report_error("Ime: " + singleBoolConst.getBoolName() + " ne predstavlja konstantu ", null);
		}
	}
	
	@Override
	public void visit(SingleVar singleVar) {
		Obj t = Tab.find(singleVar.getVarName());
		if(t!=Tab.noObj)
		{
			report_error("Ime: " + singleVar.getVarName() + " je vec definisano ", null);
		}else
		{
			varDeclCount++;
			if(singleVar.getOptionSquare() instanceof NoArray)
			{
				singleVar.obj = Tab.insert(Obj.Var, singleVar.getVarName(), currentType);
				report_info("Varijabla " + singleVar.getVarName()+ " ubacena u tabelu simbola", singleVar);
			}else
			{
				singleVar.obj = Tab.insert(Obj.Var, singleVar.getVarName(), new Struct(Struct.Array , currentType));
				report_info("Varijabla niza " + singleVar.getVarName()+ " ubacena u tabelu simbola", singleVar);
			}
			
			
		}
	}
	
	
	
	
	public void visit(MethodName mname)
	{
		if(Tab.currentScope.findSymbol(mname.getMethodName())!=null)
		{
			report_error("Simbol: " + mname.getMethodName() + " iskoriscen ", null);
			return;
		}
		
		if(!mname.getMethodName().equals("main"))
		{
			report_error("Nema main ", null);
			return;
		}
		mname.obj = Tab.insert(Obj.Meth, mname.getMethodName() , Tab.noType);
		
		Tab.openScope();
	}
	
	public void visit(MethodDecl methodDecl)
	{
		Tab.chainLocalSymbols(methodDecl.getMethodName().obj);
		Tab.closeScope();
	}
	
	
	public void visit(NumFactor numFactor)
	{
		numFactor.struct = Tab.intType;
	}
	
	public void visit(BoolFactor boolFactor)
	{
		boolFactor.struct = BoolTab.boolType;
	}
	
	public void visit(CharFactor charFactor)
	{
		charFactor.struct = Tab.charType;
	}
	
	public void visit(SingleFactor singleFactor)
	{
		singleFactor.struct = singleFactor.getFactor().struct;
	}
	
	public void visit(OneTerm oneTerm)
	{
		if(oneTerm.getOptionMinus() instanceof MinusExpr)
		{
			if(oneTerm.getSingleTerm().struct != Tab.intType)
			{
				report_error("Mora da bude int type", null);
			}
		}
		
		oneTerm.struct = oneTerm.getSingleTerm().struct;
	}
	
	public void visit(SingleExpr singleExpr)
	{
		singleExpr.struct = singleExpr.getListOfTerms().struct;
	}
	
	public void visit(PrintStatementNoArg printStatement)
	{
		if(printStatement.getExpr().struct != Tab.intType &&
				printStatement.getExpr().struct != Tab.charType &&
						printStatement.getExpr().struct != BoolTab.boolType
				)
		{
			report_error("Designator mora biti tipa int, char, bool", printStatement);
		}else
		{
			printCallCount++;
		}
	}
	
	public void visit(PrintStatementWArgs printStatement)
	{
		if(printStatement.getExpr().struct != Tab.intType &&
				printStatement.getExpr().struct != Tab.charType &&
						printStatement.getExpr().struct != BoolTab.boolType
				)
		{
			report_error("Designator mora biti tipa int, char, bool", printStatement);
		}else
		{
			printCallCount++;
		}
	}
	
	
	public void visit(AssignDesign assignDesign)
	{
		int x = assignDesign.getDesignator().obj.getKind();
		report_info("vrednost je " + x , assignDesign);
		if(!(x== Obj.Var || x ==Obj.Elem ))
		{
			report_error("Designator  mora biti promenljiva ili niz", assignDesign);
		}
		
		if(!assignDesign.getExpr().struct.assignableTo(assignDesign.getDesignator().obj.getType()))
		{
			report_error("Expr mora biti kompatibilan sa designator", assignDesign);
		}
		
		}
	
	public void visit(SingleDesignator des)
	{
		Obj d = Tab.find(des.getName());
		if(d==Tab.noObj)
		{
			report_error("Greska kod designatora", des);
		}
		des.obj=d;
	}
	
	public void visit(ArrayDesignator des)
	{
		Obj de = Tab.find(des.getDessignatorArr().getDesignator().obj.getName());
		if(de==Tab.noObj)
		{
			report_error("Greska! Ime niza nije definisano", des);
		}
		
		/*
		 * if(des.getExpr().struct !=Tab.intType) {
		 * report_error("Expr mora biti tip int", des); }
		 */
		
		if(de.getType().getKind() !=Struct.Array)
		{
			report_error("Designator mora biti tipa array", des);
		}
		des.obj = new Obj(Obj.Elem, "ArrElem", de.getType().getElemType());
	}
	
	public void visit(DesignatorPP desPlusPlus)
	{
		int x = desPlusPlus.getDesignator().obj.getKind();
		if(!(x== Obj.Var || x ==Obj.Elem ))
		{
			report_error("Designator mora biti promenljiva ili niz", desPlusPlus);
		}
		
		if(desPlusPlus.getDesignator().obj.getType() != Tab.intType)
		{
			report_error("Designator mora biti tipa int", desPlusPlus);
		}
	}
	
	public void visit(DesignatorMM desMinusMinus)
	{
		int x = desMinusMinus.getDesignator().obj.getKind();
		if(!(x== Obj.Var || x ==Obj.Elem ))
		{
			report_error("Designator mora biti promenljiva ili niz", desMinusMinus);
		}
		
		if(desMinusMinus.getDesignator().obj.getType() != Tab.intType)
		{
			report_error("Designator mora biti tipa int", desMinusMinus);
		}
	}
	
	public void visit(ReadStatement readStmt)
	{
		int x = readStmt.getDesignator().obj.getKind();
		if(!(x== Obj.Var || x ==Obj.Elem ))
		{
			report_error("Designator mora biti promenljiva ili niz", readStmt);
		}
		
		if(readStmt.getDesignator().obj.getType() != Tab.intType &&
				readStmt.getDesignator().obj.getType() != Tab.charType &&
				readStmt.getDesignator().obj.getType() != BoolTab.boolType
				)
		{
			report_error("Designator mora biti tipa int, char, bool", readStmt);
		}
	}
	
	public void visit(MultipleFactors multipleFactors)
	{
		if(!multipleFactors.getFactor().struct.equals(multipleFactors.getSingleTerm().struct) || multipleFactors.getFactor().struct != Tab.intType )
		{
				report_error("Factor i term moraju biti tipa int", multipleFactors);
		}
		multipleFactors.struct = multipleFactors.getSingleTerm().struct;
	}
	
	public void visit(MultipleTerms multipleTerms)
	{
		Struct termStruct = multipleTerms.getListOfTerms().struct;
		Struct singleTermStruct = multipleTerms.getSingleTerm().struct;
		if(!termStruct.equals(singleTermStruct))
		{
			report_error("Expr i term moraju biti kompatibilni ", multipleTerms);
		}
		
		if(termStruct !=Tab.intType || singleTermStruct != Tab.intType)
		{
			report_error("Expr i term moraju biti int type ", multipleTerms);
		}
		multipleTerms.struct = multipleTerms.getSingleTerm().struct;
		
	}
	
	public void visit(NewFactor newFactor)
	{
		if(newFactor.getExpr().struct !=Tab.intType)
		{
			report_error("Expr mora biti tipa int ", newFactor);
		}
		newFactor.struct = new Struct(Struct.Array, currentType);
	}
	
	public void visit(ExprFactor exprFactor)
	{
		exprFactor.struct = exprFactor.getExpr().struct;
	}
	
	public void visit(DesignFactor designFactor)
	{
		designFactor.struct = designFactor.getDesignator().obj.getType();
	}
	
	public void visit(OneCondFact oneCondFact) {
		
		if(oneCondFact.getExpr().struct !=BoolTab.boolType)
		{
			report_error("Izraz u confact mora biti bool", oneCondFact);
			return;
		}
		oneCondFact.struct = oneCondFact.getExpr().struct;
	}
	

	
	public void visit(MultipleOperators mul)
	{
		Struct first = mul.getExpr().struct;
		Struct second = mul.getExpr().struct;
		
		if(!first.compatibleWith(second))
		{
			report_error("Tipovi u if nisu compatibilni", mul);
		}
		//mul.struct = BoolTab.boolType;
	}
//	
//	public void visit(IfStmt statementIf)
//	{
//		if(statementIf.getCondFact().struct !=BoolTab.boolType)
//		{
//			report_error("Expr mora biti tipa bool ", statementIf);
//		}
//	}
	
	public boolean passed()
	{
		return !errorDetected;
	}
	
}
