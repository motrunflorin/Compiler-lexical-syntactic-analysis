parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}

program
    :  (classDef SEMICOLON)+ EOF
    ;

classDef: CLASS TYPE (INHERITS TYPE)? LBRACE (feature SEMICOLON)* RBRACE
        ;

formal
    : ID COLON TYPE
    ;

feature
    : ID COLON TYPE (ASSIGN expr)?
    | ID LPAREN (formal (COMMA formal)*)? RPAREN COLON TYPE LBRACE expr RBRACE
    ;

local
    : ID COLON TYPE (ASSIGN expr)?
    ;

expr
    : expr (AT TYPE)? DOT ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN            # explicitCall
    | ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN                                # implicitCall
    | IF expr THEN expr ELSE expr FI                                                    # if
    | WHILE expr LOOP expr POOL                                                         # while
    | LBRACE (expr SEMICOLON)+ RBRACE                                                   # block
    | LET local (COMMA local)* IN expr                                                  # let
    | CASE expr OF (ID COLON TYPE CASE_BRANCH expr SEMICOLON)+ ESAC                     # case
    | COMPLEMENT expr                                                                   # complement
    | ISVOID expr                                                                       # isvoid
    | NEW TYPE                                                                          # new
    | MINUS expr                                                                        # unaryMinus
    | expr (MULT | DIV) expr                                                            # multDiv
    | expr (PLUS | MINUS) expr                                                          # plusMinus
    | expr (LT | LE | EQUAL) expr                                                       # relational
    | NOT expr                                                                          # not
    | ID ASSIGN expr                                                                    # assign
    | LPAREN expr RPAREN                                                                # paren
    | ID                                                                                # id
    | INT                                                                               # int
    | BOOL                                                                              # bool
    | STRING                                                                            # string
    ;
