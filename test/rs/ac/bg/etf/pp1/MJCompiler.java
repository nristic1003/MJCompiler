package rs.ac.bg.etf.pp1;


import java_cup.runtime.Symbol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.BoolTab;
import rs.ac.bg.etf.pp1.CodeGenerator;
import rs.ac.bg.etf.pp1.MJParser;
import rs.ac.bg.etf.pp1.SemanticAnalyzer;
import rs.ac.bg.etf.pp1.Yylex;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

public class MJCompiler implements Compiler {

	Reader br = null;
	Logger log;
	private List<CompilerError> errorList = new ArrayList<>();
	public List<CompilerError> compile(String sourceFilePath, String outputFilePath) {
	
		
	Logger log = Logger.getLogger(MJParserTest.class);
		
		Reader br = null;
		try {
			File sourceCode = new File(sourceFilePath);
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			errorList.addAll(lexer.getLexList());
			
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        
	        errorList.addAll(p.getErrors());
	        Program prog = (Program)(s.value); 
	        BoolTab.init();
			// ispis sintaksnog stabla
			log.info(prog.toString(""));
			log.info("===================================");

			// ispis prepoznatih programskih konstrukcija
			SemanticAnalyzer v = new SemanticAnalyzer();
			prog.traverseBottomUp(v); 
			 errorList.addAll(v.getErrors());
			
			log.info(" Print count calls = " + v.printCallCount);

			log.info(" Deklarisanih promenljivih ima = " + v.varDeclCount);
			
			log.info("Broj gresaka " + errorList.size());
			log.info("===================================");
			Tab.dump();
			
			log.info("Broj gresaka u lex " + lexer.getLexList().size());
			log.info("Broj gresaka u syn " + p.getErrors().size());
			log.info("Broj gresaka u sem " + v.getErrors().size());
			
			if(errorList.size()==0){
				File objFile = new File(outputFilePath);
				if(objFile.exists()) objFile.delete();
				
				CodeGenerator codeGenerator = new CodeGenerator();
				prog.traverseBottomUp(codeGenerator);
				Code.dataSize = v.nVars;
				Code.mainPc = codeGenerator.getMainPc();
				Code.write(new FileOutputStream(objFile));
				log.info("Parsiranje uspesno zavrseno!");
			}else{
				log.error("Parsiranje NIJE uspesno zavrseno!");
			}
		}catch (Exception e) {
			// TODO: handle exception
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}
		
		
		
		return errorList;
	}
	
	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args){
		
			String sourceFilePath = args[args.length - 2];
			String outputFilePath = args[args.length - 1];
			MJCompiler mc = new MJCompiler();
			mc.compile(sourceFilePath, outputFilePath);
		

	}
	


}
