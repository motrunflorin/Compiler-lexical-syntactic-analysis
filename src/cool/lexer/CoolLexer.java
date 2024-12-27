// Generated from /home/regelepirat/Compilatoare/teme-main-tema1-Tema1/tema1/Tema1/src/cool/lexer/CoolLexer.g4 by ANTLR 4.13.2

    package cool.lexer;	

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, CLASS=2, INHERITS=3, AT=4, NEW=5, OF=6, NOT=7, ISVOID=8, IN=9, 
		LET=10, LOOP=11, POOL=12, WHILE=13, CASE=14, ESAC=15, IF=16, THEN=17, 
		ELSE=18, FI=19, FOR=20, DO=21, LBRACE=22, RBRACE=23, LPAREN=24, RPAREN=25, 
		SEMICOLON=26, COLON=27, ASSIGN=28, COMMA=29, COMPLEMENT=30, DOT=31, CASE_BRANCH=32, 
		EQUAL=33, PLUS=34, MINUS=35, MULT=36, DIV=37, LT=38, LE=39, TYPE=40, BOOL=41, 
		ID=42, STRING=43, INT=44, LINE_COMMENT=45, UNMATCHED_BLOCK_COMMENT=46, 
		BLOCK_COMMENT=47, WS=48, INVALID_CHARACTER=49;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"CLASS", "INHERITS", "AT", "NEW", "OF", "NOT", "ISVOID", "IN", "LET", 
			"LOOP", "POOL", "WHILE", "CASE", "ESAC", "IF", "THEN", "ELSE", "FI", 
			"FOR", "DO", "LBRACE", "RBRACE", "LPAREN", "RPAREN", "SEMICOLON", "COLON", 
			"ASSIGN", "COMMA", "COMPLEMENT", "DOT", "CASE_BRANCH", "EQUAL", "PLUS", 
			"MINUS", "MULT", "DIV", "LT", "LE", "TYPE", "BOOL", "ID", "STRING", "INT", 
			"LINE_COMMENT", "UNMATCHED_BLOCK_COMMENT", "BLOCK_COMMENT", "WS", "INVALID_CHARACTER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'class'", "'inherits'", "'@'", "'new'", "'of'", "'not'", 
			"'isvoid'", "'in'", "'let'", "'loop'", "'pool'", "'while'", "'case'", 
			"'esac'", "'if'", "'then'", "'else'", "'fi'", "'for'", "'do'", "'{'", 
			"'}'", "'('", "')'", "';'", "':'", "'<-'", "','", "'~'", "'.'", "'=>'", 
			"'='", "'+'", "'-'", "'*'", "'/'", "'<'", "'<='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "CLASS", "INHERITS", "AT", "NEW", "OF", "NOT", "ISVOID", 
			"IN", "LET", "LOOP", "POOL", "WHILE", "CASE", "ESAC", "IF", "THEN", "ELSE", 
			"FI", "FOR", "DO", "LBRACE", "RBRACE", "LPAREN", "RPAREN", "SEMICOLON", 
			"COLON", "ASSIGN", "COMMA", "COMPLEMENT", "DOT", "CASE_BRANCH", "EQUAL", 
			"PLUS", "MINUS", "MULT", "DIV", "LT", "LE", "TYPE", "BOOL", "ID", "STRING", 
			"INT", "LINE_COMMENT", "UNMATCHED_BLOCK_COMMENT", "BLOCK_COMMENT", "WS", 
			"INVALID_CHARACTER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	    private void raiseError(String message) {
	        setText(message);
	        setType(ERROR);
	    }

	    private void processString() {
	        String parsedString = getText();

	        // Check for errors in the string
	        if (isTooLong(parsedString) || containsNullCharacter(parsedString)) {
	            return;
	        }

	        // Trim surrounding quotes and process special characters
	        String content = trimQuotes(parsedString);
	        content = replaceSpecialCharacters(content);
	        content = removeEscapeFromRemaining(content);

	        // Set the processed string as the token's text
	        setText(content);
	    }

	    private boolean isTooLong(String str) {
	        if (str.length() > 1024) {
	            raiseError("String constant too long");
	            return true;
	        }
	        return false;
	    }

	    private boolean containsNullCharacter(String str) {
	        if (str.contains("\u0000")) {
	            raiseError("String contains null character");
	            return true;
	        }
	        return false;
	    }

	    private String trimQuotes(String str) {
	        return str.substring(1, str.length() - 1); // Remove the first and last character
	    }

	    private String replaceSpecialCharacters(String str) {
	        StringBuilder result = new StringBuilder();
	        for (int i = 0; i < str.length(); i++) {
	            char c = str.charAt(i);
	            if (c == '\\' && i + 1 < str.length()) {
	                char next = str.charAt(i + 1);
	                switch (next) {
	                    case 'n': result.append('\n'); break;
	                    case 't': result.append('\t'); break;
	                    case 'b': result.append('\b'); break;
	                    case 'f': result.append('\f'); break;
	                    case 'r': result.append('\r'); break;
	                    default: result.append(c).append(next);
	                }
	                i++; // Skip the next character
	            } else {
	                result.append(c);
	            }
	        }
	        return result.toString();
	    }

	    private String removeEscapeFromRemaining(String str) {
	        return str.replaceAll("\\\\([\\w\\s])", "$1"); // Remove leading backslashes from escaped characters
	    }


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 41:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 44:
			UNMATCHED_BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 45:
			BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 47:
			INVALID_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 processString(); 
			break;
		case 1:
			 raiseError("Unterminated string constant"); 
			break;
		case 2:
			 raiseError("EOF in string constant"); 
			break;
		}
	}
	private void UNMATCHED_BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			 raiseError("Unmatched *)"); 
			break;
		}
	}
	private void BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			 skip(); 
			break;
		case 5:
			 raiseError("EOF in comment"); 
			break;
		}
	}
	private void INVALID_CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:
			 raiseError("Invalid character: " + getText()); 
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u00001\u0171\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d"+
		"\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f"+
		"\u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001$\u0001"+
		"$\u0001%\u0001%\u0001%\u0001&\u0001&\u0005&\u00e6\b&\n&\f&\u00e9\t&\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0003"+
		"\'\u00f4\b\'\u0001(\u0003(\u00f7\b(\u0001(\u0005(\u00fa\b(\n(\f(\u00fd"+
		"\t(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001"+
		"(\u0001(\u0001(\u0001(\u0003(\u010c\b(\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0005)\u0113\b)\n)\f)\u0116\t)\u0001)\u0003)\u0119\b)\u0001)\u0001)"+
		"\u0005)\u011d\b)\n)\f)\u0120\t)\u0001)\u0001)\u0001)\u0001)\u0005)\u0126"+
		"\b)\n)\f)\u0129\t)\u0001)\u0003)\u012c\b)\u0001)\u0001)\u0001)\u0001)"+
		"\u0001)\u0003)\u0133\b)\u0001*\u0004*\u0136\b*\u000b*\f*\u0137\u0001+"+
		"\u0001+\u0001+\u0001+\u0005+\u013e\b+\n+\f+\u0141\t+\u0001+\u0003+\u0144"+
		"\b+\u0001+\u0001+\u0003+\u0148\b+\u0001+\u0001+\u0001,\u0001,\u0001,\u0001"+
		",\u0001,\u0001,\u0003,\u0152\b,\u0001,\u0001,\u0001-\u0001-\u0001-\u0001"+
		"-\u0001-\u0005-\u015b\b-\n-\f-\u015e\t-\u0001-\u0001-\u0001-\u0001-\u0001"+
		"-\u0001-\u0003-\u0166\b-\u0001.\u0004.\u0169\b.\u000b.\f.\u016a\u0001"+
		".\u0001.\u0001/\u0001/\u0001/\u0003\u011e\u013f\u015c\u00000\u0001\u0002"+
		"\u0003\u0003\u0005\u0004\u0007\u0005\t\u0006\u000b\u0007\r\b\u000f\t\u0011"+
		"\n\u0013\u000b\u0015\f\u0017\r\u0019\u000e\u001b\u000f\u001d\u0010\u001f"+
		"\u0011!\u0012#\u0013%\u0014\'\u0015)\u0016+\u0017-\u0018/\u00191\u001a"+
		"3\u001b5\u001c7\u001d9\u001e;\u001f= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/"+
		"]0_1\u0001\u0000\u0006\u0001\u0000AZ\u0004\u000009AZ__az\u0003\u0000A"+
		"Z__az\u0001\u0000\\\\\u0001\u000009\u0003\u0000\t\n\f\r  \u0187\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000"+
		"\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000"+
		"\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000="+
		"\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000"+
		"\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000"+
		"\u0000G\u0001\u0000\u0000\u0000\u0000I\u0001\u0000\u0000\u0000\u0000K"+
		"\u0001\u0000\u0000\u0000\u0000M\u0001\u0000\u0000\u0000\u0000O\u0001\u0000"+
		"\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000\u0000S\u0001\u0000\u0000\u0000"+
		"\u0000U\u0001\u0000\u0000\u0000\u0000W\u0001\u0000\u0000\u0000\u0000Y"+
		"\u0001\u0000\u0000\u0000\u0000[\u0001\u0000\u0000\u0000\u0000]\u0001\u0000"+
		"\u0000\u0000\u0000_\u0001\u0000\u0000\u0000\u0001a\u0001\u0000\u0000\u0000"+
		"\u0003g\u0001\u0000\u0000\u0000\u0005p\u0001\u0000\u0000\u0000\u0007r"+
		"\u0001\u0000\u0000\u0000\tv\u0001\u0000\u0000\u0000\u000by\u0001\u0000"+
		"\u0000\u0000\r}\u0001\u0000\u0000\u0000\u000f\u0084\u0001\u0000\u0000"+
		"\u0000\u0011\u0087\u0001\u0000\u0000\u0000\u0013\u008b\u0001\u0000\u0000"+
		"\u0000\u0015\u0090\u0001\u0000\u0000\u0000\u0017\u0095\u0001\u0000\u0000"+
		"\u0000\u0019\u009b\u0001\u0000\u0000\u0000\u001b\u00a0\u0001\u0000\u0000"+
		"\u0000\u001d\u00a5\u0001\u0000\u0000\u0000\u001f\u00a8\u0001\u0000\u0000"+
		"\u0000!\u00ad\u0001\u0000\u0000\u0000#\u00b2\u0001\u0000\u0000\u0000%"+
		"\u00b5\u0001\u0000\u0000\u0000\'\u00b9\u0001\u0000\u0000\u0000)\u00bc"+
		"\u0001\u0000\u0000\u0000+\u00be\u0001\u0000\u0000\u0000-\u00c0\u0001\u0000"+
		"\u0000\u0000/\u00c2\u0001\u0000\u0000\u00001\u00c4\u0001\u0000\u0000\u0000"+
		"3\u00c6\u0001\u0000\u0000\u00005\u00c8\u0001\u0000\u0000\u00007\u00cb"+
		"\u0001\u0000\u0000\u00009\u00cd\u0001\u0000\u0000\u0000;\u00cf\u0001\u0000"+
		"\u0000\u0000=\u00d1\u0001\u0000\u0000\u0000?\u00d4\u0001\u0000\u0000\u0000"+
		"A\u00d6\u0001\u0000\u0000\u0000C\u00d8\u0001\u0000\u0000\u0000E\u00da"+
		"\u0001\u0000\u0000\u0000G\u00dc\u0001\u0000\u0000\u0000I\u00de\u0001\u0000"+
		"\u0000\u0000K\u00e0\u0001\u0000\u0000\u0000M\u00e3\u0001\u0000\u0000\u0000"+
		"O\u00f3\u0001\u0000\u0000\u0000Q\u010b\u0001\u0000\u0000\u0000S\u010d"+
		"\u0001\u0000\u0000\u0000U\u0135\u0001\u0000\u0000\u0000W\u0139\u0001\u0000"+
		"\u0000\u0000Y\u0151\u0001\u0000\u0000\u0000[\u0155\u0001\u0000\u0000\u0000"+
		"]\u0168\u0001\u0000\u0000\u0000_\u016e\u0001\u0000\u0000\u0000ab\u0005"+
		"c\u0000\u0000bc\u0005l\u0000\u0000cd\u0005a\u0000\u0000de\u0005s\u0000"+
		"\u0000ef\u0005s\u0000\u0000f\u0002\u0001\u0000\u0000\u0000gh\u0005i\u0000"+
		"\u0000hi\u0005n\u0000\u0000ij\u0005h\u0000\u0000jk\u0005e\u0000\u0000"+
		"kl\u0005r\u0000\u0000lm\u0005i\u0000\u0000mn\u0005t\u0000\u0000no\u0005"+
		"s\u0000\u0000o\u0004\u0001\u0000\u0000\u0000pq\u0005@\u0000\u0000q\u0006"+
		"\u0001\u0000\u0000\u0000rs\u0005n\u0000\u0000st\u0005e\u0000\u0000tu\u0005"+
		"w\u0000\u0000u\b\u0001\u0000\u0000\u0000vw\u0005o\u0000\u0000wx\u0005"+
		"f\u0000\u0000x\n\u0001\u0000\u0000\u0000yz\u0005n\u0000\u0000z{\u0005"+
		"o\u0000\u0000{|\u0005t\u0000\u0000|\f\u0001\u0000\u0000\u0000}~\u0005"+
		"i\u0000\u0000~\u007f\u0005s\u0000\u0000\u007f\u0080\u0005v\u0000\u0000"+
		"\u0080\u0081\u0005o\u0000\u0000\u0081\u0082\u0005i\u0000\u0000\u0082\u0083"+
		"\u0005d\u0000\u0000\u0083\u000e\u0001\u0000\u0000\u0000\u0084\u0085\u0005"+
		"i\u0000\u0000\u0085\u0086\u0005n\u0000\u0000\u0086\u0010\u0001\u0000\u0000"+
		"\u0000\u0087\u0088\u0005l\u0000\u0000\u0088\u0089\u0005e\u0000\u0000\u0089"+
		"\u008a\u0005t\u0000\u0000\u008a\u0012\u0001\u0000\u0000\u0000\u008b\u008c"+
		"\u0005l\u0000\u0000\u008c\u008d\u0005o\u0000\u0000\u008d\u008e\u0005o"+
		"\u0000\u0000\u008e\u008f\u0005p\u0000\u0000\u008f\u0014\u0001\u0000\u0000"+
		"\u0000\u0090\u0091\u0005p\u0000\u0000\u0091\u0092\u0005o\u0000\u0000\u0092"+
		"\u0093\u0005o\u0000\u0000\u0093\u0094\u0005l\u0000\u0000\u0094\u0016\u0001"+
		"\u0000\u0000\u0000\u0095\u0096\u0005w\u0000\u0000\u0096\u0097\u0005h\u0000"+
		"\u0000\u0097\u0098\u0005i\u0000\u0000\u0098\u0099\u0005l\u0000\u0000\u0099"+
		"\u009a\u0005e\u0000\u0000\u009a\u0018\u0001\u0000\u0000\u0000\u009b\u009c"+
		"\u0005c\u0000\u0000\u009c\u009d\u0005a\u0000\u0000\u009d\u009e\u0005s"+
		"\u0000\u0000\u009e\u009f\u0005e\u0000\u0000\u009f\u001a\u0001\u0000\u0000"+
		"\u0000\u00a0\u00a1\u0005e\u0000\u0000\u00a1\u00a2\u0005s\u0000\u0000\u00a2"+
		"\u00a3\u0005a\u0000\u0000\u00a3\u00a4\u0005c\u0000\u0000\u00a4\u001c\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a6\u0005i\u0000\u0000\u00a6\u00a7\u0005f\u0000"+
		"\u0000\u00a7\u001e\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005t\u0000\u0000"+
		"\u00a9\u00aa\u0005h\u0000\u0000\u00aa\u00ab\u0005e\u0000\u0000\u00ab\u00ac"+
		"\u0005n\u0000\u0000\u00ac \u0001\u0000\u0000\u0000\u00ad\u00ae\u0005e"+
		"\u0000\u0000\u00ae\u00af\u0005l\u0000\u0000\u00af\u00b0\u0005s\u0000\u0000"+
		"\u00b0\u00b1\u0005e\u0000\u0000\u00b1\"\u0001\u0000\u0000\u0000\u00b2"+
		"\u00b3\u0005f\u0000\u0000\u00b3\u00b4\u0005i\u0000\u0000\u00b4$\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b6\u0005f\u0000\u0000\u00b6\u00b7\u0005o\u0000"+
		"\u0000\u00b7\u00b8\u0005r\u0000\u0000\u00b8&\u0001\u0000\u0000\u0000\u00b9"+
		"\u00ba\u0005d\u0000\u0000\u00ba\u00bb\u0005o\u0000\u0000\u00bb(\u0001"+
		"\u0000\u0000\u0000\u00bc\u00bd\u0005{\u0000\u0000\u00bd*\u0001\u0000\u0000"+
		"\u0000\u00be\u00bf\u0005}\u0000\u0000\u00bf,\u0001\u0000\u0000\u0000\u00c0"+
		"\u00c1\u0005(\u0000\u0000\u00c1.\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005"+
		")\u0000\u0000\u00c30\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005;\u0000"+
		"\u0000\u00c52\u0001\u0000\u0000\u0000\u00c6\u00c7\u0005:\u0000\u0000\u00c7"+
		"4\u0001\u0000\u0000\u0000\u00c8\u00c9\u0005<\u0000\u0000\u00c9\u00ca\u0005"+
		"-\u0000\u0000\u00ca6\u0001\u0000\u0000\u0000\u00cb\u00cc\u0005,\u0000"+
		"\u0000\u00cc8\u0001\u0000\u0000\u0000\u00cd\u00ce\u0005~\u0000\u0000\u00ce"+
		":\u0001\u0000\u0000\u0000\u00cf\u00d0\u0005.\u0000\u0000\u00d0<\u0001"+
		"\u0000\u0000\u0000\u00d1\u00d2\u0005=\u0000\u0000\u00d2\u00d3\u0005>\u0000"+
		"\u0000\u00d3>\u0001\u0000\u0000\u0000\u00d4\u00d5\u0005=\u0000\u0000\u00d5"+
		"@\u0001\u0000\u0000\u0000\u00d6\u00d7\u0005+\u0000\u0000\u00d7B\u0001"+
		"\u0000\u0000\u0000\u00d8\u00d9\u0005-\u0000\u0000\u00d9D\u0001\u0000\u0000"+
		"\u0000\u00da\u00db\u0005*\u0000\u0000\u00dbF\u0001\u0000\u0000\u0000\u00dc"+
		"\u00dd\u0005/\u0000\u0000\u00ddH\u0001\u0000\u0000\u0000\u00de\u00df\u0005"+
		"<\u0000\u0000\u00dfJ\u0001\u0000\u0000\u0000\u00e0\u00e1\u0005<\u0000"+
		"\u0000\u00e1\u00e2\u0005=\u0000\u0000\u00e2L\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e7\u0007\u0000\u0000\u0000\u00e4\u00e6\u0007\u0001\u0000\u0000\u00e5"+
		"\u00e4\u0001\u0000\u0000\u0000\u00e6\u00e9\u0001\u0000\u0000\u0000\u00e7"+
		"\u00e5\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8"+
		"N\u0001\u0000\u0000\u0000\u00e9\u00e7\u0001\u0000\u0000\u0000\u00ea\u00eb"+
		"\u0005t\u0000\u0000\u00eb\u00ec\u0005r\u0000\u0000\u00ec\u00ed\u0005u"+
		"\u0000\u0000\u00ed\u00f4\u0005e\u0000\u0000\u00ee\u00ef\u0005f\u0000\u0000"+
		"\u00ef\u00f0\u0005a\u0000\u0000\u00f0\u00f1\u0005l\u0000\u0000\u00f1\u00f2"+
		"\u0005s\u0000\u0000\u00f2\u00f4\u0005e\u0000\u0000\u00f3\u00ea\u0001\u0000"+
		"\u0000\u0000\u00f3\u00ee\u0001\u0000\u0000\u0000\u00f4P\u0001\u0000\u0000"+
		"\u0000\u00f5\u00f7\u0007\u0002\u0000\u0000\u00f6\u00f5\u0001\u0000\u0000"+
		"\u0000\u00f7\u00fb\u0001\u0000\u0000\u0000\u00f8\u00fa\u0007\u0001\u0000"+
		"\u0000\u00f9\u00f8\u0001\u0000\u0000\u0000\u00fa\u00fd\u0001\u0000\u0000"+
		"\u0000\u00fb\u00f9\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000"+
		"\u0000\u00fc\u010c\u0001\u0000\u0000\u0000\u00fd\u00fb\u0001\u0000\u0000"+
		"\u0000\u00fe\u00ff\u0005s\u0000\u0000\u00ff\u0100\u0005e\u0000\u0000\u0100"+
		"\u0101\u0005l\u0000\u0000\u0101\u010c\u0005f\u0000\u0000\u0102\u0103\u0005"+
		"S\u0000\u0000\u0103\u0104\u0005E\u0000\u0000\u0104\u0105\u0005L\u0000"+
		"\u0000\u0105\u0106\u0005F\u0000\u0000\u0106\u0107\u0005_\u0000\u0000\u0107"+
		"\u0108\u0005T\u0000\u0000\u0108\u0109\u0005Y\u0000\u0000\u0109\u010a\u0005"+
		"P\u0000\u0000\u010a\u010c\u0005E\u0000\u0000\u010b\u00f6\u0001\u0000\u0000"+
		"\u0000\u010b\u00fe\u0001\u0000\u0000\u0000\u010b\u0102\u0001\u0000\u0000"+
		"\u0000\u010cR\u0001\u0000\u0000\u0000\u010d\u011e\u0005\"\u0000\u0000"+
		"\u010e\u010f\u0005\\\u0000\u0000\u010f\u011d\u0005\"\u0000\u0000\u0110"+
		"\u0114\u0005\\\u0000\u0000\u0111\u0113\u0005 \u0000\u0000\u0112\u0111"+
		"\u0001\u0000\u0000\u0000\u0113\u0116\u0001\u0000\u0000\u0000\u0114\u0112"+
		"\u0001\u0000\u0000\u0000\u0114\u0115\u0001\u0000\u0000\u0000\u0115\u0118"+
		"\u0001\u0000\u0000\u0000\u0116\u0114\u0001\u0000\u0000\u0000\u0117\u0119"+
		"\u0005\r\u0000\u0000\u0118\u0117\u0001\u0000\u0000\u0000\u0118\u0119\u0001"+
		"\u0000\u0000\u0000\u0119\u011a\u0001\u0000\u0000\u0000\u011a\u011d\u0005"+
		"\n\u0000\u0000\u011b\u011d\t\u0000\u0000\u0000\u011c\u010e\u0001\u0000"+
		"\u0000\u0000\u011c\u0110\u0001\u0000\u0000\u0000\u011c\u011b\u0001\u0000"+
		"\u0000\u0000\u011d\u0120\u0001\u0000\u0000\u0000\u011e\u011f\u0001\u0000"+
		"\u0000\u0000\u011e\u011c\u0001\u0000\u0000\u0000\u011f\u0132\u0001\u0000"+
		"\u0000\u0000\u0120\u011e\u0001\u0000\u0000\u0000\u0121\u0122\u0005\"\u0000"+
		"\u0000\u0122\u0133\u0006)\u0000\u0000\u0123\u0127\b\u0003\u0000\u0000"+
		"\u0124\u0126\u0005 \u0000\u0000\u0125\u0124\u0001\u0000\u0000\u0000\u0126"+
		"\u0129\u0001\u0000\u0000\u0000\u0127\u0125\u0001\u0000\u0000\u0000\u0127"+
		"\u0128\u0001\u0000\u0000\u0000\u0128\u012b\u0001\u0000\u0000\u0000\u0129"+
		"\u0127\u0001\u0000\u0000\u0000\u012a\u012c\u0005\r\u0000\u0000\u012b\u012a"+
		"\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000\u0000\u0000\u012c\u012d"+
		"\u0001\u0000\u0000\u0000\u012d\u012e\u0005\n\u0000\u0000\u012e\u012f\u0001"+
		"\u0000\u0000\u0000\u012f\u0133\u0006)\u0001\u0000\u0130\u0131\u0005\u0000"+
		"\u0000\u0001\u0131\u0133\u0006)\u0002\u0000\u0132\u0121\u0001\u0000\u0000"+
		"\u0000\u0132\u0123\u0001\u0000\u0000\u0000\u0132\u0130\u0001\u0000\u0000"+
		"\u0000\u0133T\u0001\u0000\u0000\u0000\u0134\u0136\u0007\u0004\u0000\u0000"+
		"\u0135\u0134\u0001\u0000\u0000\u0000\u0136\u0137\u0001\u0000\u0000\u0000"+
		"\u0137\u0135\u0001\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000\u0000"+
		"\u0138V\u0001\u0000\u0000\u0000\u0139\u013a\u0005-\u0000\u0000\u013a\u013b"+
		"\u0005-\u0000\u0000\u013b\u013f\u0001\u0000\u0000\u0000\u013c\u013e\t"+
		"\u0000\u0000\u0000\u013d\u013c\u0001\u0000\u0000\u0000\u013e\u0141\u0001"+
		"\u0000\u0000\u0000\u013f\u0140\u0001\u0000\u0000\u0000\u013f\u013d\u0001"+
		"\u0000\u0000\u0000\u0140\u0147\u0001\u0000\u0000\u0000\u0141\u013f\u0001"+
		"\u0000\u0000\u0000\u0142\u0144\u0005\r\u0000\u0000\u0143\u0142\u0001\u0000"+
		"\u0000\u0000\u0143\u0144\u0001\u0000\u0000\u0000\u0144\u0145\u0001\u0000"+
		"\u0000\u0000\u0145\u0148\u0005\n\u0000\u0000\u0146\u0148\u0005\u0000\u0000"+
		"\u0001\u0147\u0143\u0001\u0000\u0000\u0000\u0147\u0146\u0001\u0000\u0000"+
		"\u0000\u0148\u0149\u0001\u0000\u0000\u0000\u0149\u014a\u0006+\u0003\u0000"+
		"\u014aX\u0001\u0000\u0000\u0000\u014b\u014c\u0005*\u0000\u0000\u014c\u0152"+
		"\u0005)\u0000\u0000\u014d\u014e\u0003[-\u0000\u014e\u014f\u0005*\u0000"+
		"\u0000\u014f\u0150\u0005)\u0000\u0000\u0150\u0152\u0001\u0000\u0000\u0000"+
		"\u0151\u014b\u0001\u0000\u0000\u0000\u0151\u014d\u0001\u0000\u0000\u0000"+
		"\u0152\u0153\u0001\u0000\u0000\u0000\u0153\u0154\u0006,\u0004\u0000\u0154"+
		"Z\u0001\u0000\u0000\u0000\u0155\u0156\u0005(\u0000\u0000\u0156\u0157\u0005"+
		"*\u0000\u0000\u0157\u015c\u0001\u0000\u0000\u0000\u0158\u015b\u0003[-"+
		"\u0000\u0159\u015b\t\u0000\u0000\u0000\u015a\u0158\u0001\u0000\u0000\u0000"+
		"\u015a\u0159\u0001\u0000\u0000\u0000\u015b\u015e\u0001\u0000\u0000\u0000"+
		"\u015c\u015d\u0001\u0000\u0000\u0000\u015c\u015a\u0001\u0000\u0000\u0000"+
		"\u015d\u0165\u0001\u0000\u0000\u0000\u015e\u015c\u0001\u0000\u0000\u0000"+
		"\u015f\u0160\u0005*\u0000\u0000\u0160\u0161\u0005)\u0000\u0000\u0161\u0162"+
		"\u0001\u0000\u0000\u0000\u0162\u0166\u0006-\u0005\u0000\u0163\u0164\u0005"+
		"\u0000\u0000\u0001\u0164\u0166\u0006-\u0006\u0000\u0165\u015f\u0001\u0000"+
		"\u0000\u0000\u0165\u0163\u0001\u0000\u0000\u0000\u0166\\\u0001\u0000\u0000"+
		"\u0000\u0167\u0169\u0007\u0005\u0000\u0000\u0168\u0167\u0001\u0000\u0000"+
		"\u0000\u0169\u016a\u0001\u0000\u0000\u0000\u016a\u0168\u0001\u0000\u0000"+
		"\u0000\u016a\u016b\u0001\u0000\u0000\u0000\u016b\u016c\u0001\u0000\u0000"+
		"\u0000\u016c\u016d\u0006.\u0003\u0000\u016d^\u0001\u0000\u0000\u0000\u016e"+
		"\u016f\t\u0000\u0000\u0000\u016f\u0170\u0006/\u0007\u0000\u0170`\u0001"+
		"\u0000\u0000\u0000\u0017\u0000\u00e7\u00f3\u00f6\u00f9\u00fb\u010b\u0114"+
		"\u0118\u011c\u011e\u0127\u012b\u0132\u0137\u013f\u0143\u0147\u0151\u015a"+
		"\u015c\u0165\u016a\b\u0001)\u0000\u0001)\u0001\u0001)\u0002\u0006\u0000"+
		"\u0000\u0001,\u0003\u0001-\u0004\u0001-\u0005\u0001/\u0006";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}