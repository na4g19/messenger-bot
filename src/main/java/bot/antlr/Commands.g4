grammar Commands;

@parser::members {

      private boolean isInteger(Token t, int min, int max) {

        int n = 0;
        try {
          n = Integer.parseInt(t.getText());
        } catch (Exception e) {
          return false;
        }
        return n >= min && n <= max;
      }

      private boolean isDouble(Token t) {

        try {
          double n = Double.parseDouble(t.getText());
        } catch (Exception e) {
          return false;
        }
        return true;
      }

      private boolean isName(Token t) {

        // fix this lmao
        List<String> names = new ArrayList<>();
        names.add("nedas");
        names.add("mykolas");
        String name = t.getText();
        return names.contains(name.toLowerCase());
      }
    }

/*
 * Lexer Rules
 */
 NAME_COMMAND    : N A M E                               ;
 SQUARE_COMMAND  : S Q U A R E                           ;

 TIMER_COMMAND   : T I M E R                             ;
 TIMER_START     : S T A R T                             ;
 TIMER_STOP      : S T O P                               ;

 HELP_COMMAND    : H E L P                               ;
 WEATHER_COMMAND : W E A T H E R                         ;
 MONEY_COMMAND   : M O N E Y                             ;

 EVOKE           : '!'                                   ;
 WORD            : [a-zA-Z\u0104-\u017D]+                ;
 NUMBER          : INTEGER
                 | FLOAT                                 ;
 INTEGER         : [0-9]+                                ;
 FLOAT           : INTEGER
                 | INTEGER PT INTEGER                    ;

 PT              : '.'                                   ;
 WHITESPACE      : (' ' | '\t') -> skip                  ;
 NEWLINE         : ('\r'? '\n' | '\r')+ -> skip          ;

 /*
  * Parser Rules
  */
  fragment A : [aA]; // match either an 'a' or 'A'
  fragment B : [bB];
  fragment C : [cC];
  fragment D : [dD];
  fragment E : [eE];
  fragment F : [fF];
  fragment G : [gG];
  fragment H : [hH];
  fragment I : [iI];
  fragment J : [jJ];
  fragment K : [kK];
  fragment L : [lL];
  fragment M : [mM];
  fragment N : [nN];
  fragment O : [oO];
  fragment P : [pP];
  fragment Q : [qQ];
  fragment R : [rR];
  fragment S : [sS];
  fragment T : [tT];
  fragment U : [uU];
  fragment V : [vV];
  fragment W : [wW];
  fragment X : [xX];
  fragment Y : [yY];
  fragment Z : [zZ];

  command        : EVOKE (nameCommand | squareCommand | timerCommand
                 | helpCommand | weatherCommand | moneyCommand) EOF              ;

  nameCommand    : NAME_COMMAND                                                  ;
  squareCommand  : SQUARE_COMMAND int32                                          ;
  timerCommand   : (TIMER_COMMAND TIMER_START)
                 | (TIMER_COMMAND TIMER_START int32)
                 | (TIMER_COMMAND TIMER_STOP)                                    ;
  helpCommand    : HELP_COMMAND                                                  ;
  weatherCommand : WEATHER_COMMAND WORD                                          ;
  moneyCommand   : MONEY_COMMAND
                 | (MONEY_COMMAND float_value)
                 | (MONEY_COMMAND name float_value)                              ;

  name           : WORD {isName($WORD)}?                                         ;
  int32          : NUMBER {isInteger($NUMBER, 0, 2147483647)}?                   ;
  float_value    : NUMBER {isDouble($NUMBER)}?                                   ;
