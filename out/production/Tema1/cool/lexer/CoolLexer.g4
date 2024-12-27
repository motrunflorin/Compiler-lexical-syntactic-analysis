lexer grammar CoolLexer;

tokens { ERROR } 

@header{
    package cool.lexer;	
}

@members{
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
}

// Keywords
CLASS: 'class';
INHERITS: 'inherits';
AT: '@';
NEW: 'new';
OF: 'of';
NOT: 'not';
ISVOID: 'isvoid';
IN: 'in';
LET: 'let';
LOOP: 'loop';
POOL: 'pool';
WHILE: 'while';
CASE: 'case';
ESAC: 'esac';
IF : 'if';
THEN: 'then' ;
ELSE: 'else' ;
FI: 'fi' ;
FOR: 'for' ;
DO: 'do' ;

// Symbols
LBRACE: '{';
RBRACE: '}';
LPAREN: '(' ;
RPAREN: ')' ;
SEMICOLON: ';';
COLON: ':';
ASSIGN: '<-';
COMMA: ',';
COMPLEMENT: '~';
DOT: '.';
CASE_BRANCH: '=>';

// Operands
EQUAL : '=';
PLUS : '+';
MINUS : '-';
MULT : '*';
DIV : '/';
LT : '<';
LE : '<=';

// data types
TYPE: [A-Z] [a-zA-Z0-9_]*;

// boolean
BOOL: 'true'| 'false';

ID : (([a-zA-Z]| '_')([a-zA-Z] | '_' | [0-9])* | 'self' | 'SELF_TYPE');

// string
STRING: '"'
        ('\\"' | '\\' (' ')* ('\r'? '\n') | . )*?
        ('"' { processString(); } | ~'\\' (' ')* ('\r'? '\n') { raiseError("Unterminated string constant"); } | EOF { raiseError("EOF in string constant"); });

// int
INT : [0-9]+;

// comments
LINE_COMMENT: '--' .*? (('\r'? '\n') | EOF) -> skip;

UNMATCHED_BLOCK_COMMENT: ('*)' | BLOCK_COMMENT '*)') { raiseError("Unmatched *)"); };

BLOCK_COMMENT: '(*' (BLOCK_COMMENT | .)*? ('*)' { skip(); } | EOF { raiseError("EOF in comment"); });

WS
    :   [ \n\f\r\t]+ -> skip
    ;

INVALID_CHARACTER: . { raiseError("Invalid character: " + getText()); };
