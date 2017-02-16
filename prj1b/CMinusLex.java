/* The following code was generated by JFlex 1.6.1 */

/* User code */
import java.io.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>CMinus.flex</tt>
 */
class CMinusLex implements Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int COMMENT = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\6\1\5\1\12\1\13\1\4\22\0\1\6\1\37\2\0"+
    "\1\1\3\0\1\42\1\43\1\10\1\32\1\41\1\33\1\0\1\11"+
    "\1\2\11\3\1\0\1\40\1\34\1\35\1\36\2\0\32\1\1\44"+
    "\1\0\1\45\1\0\1\1\1\0\3\1\1\21\1\25\1\26\1\1"+
    "\1\23\1\14\2\1\1\24\1\1\1\15\1\20\2\1\1\30\1\27"+
    "\1\16\1\31\1\17\1\22\3\1\1\46\1\0\1\47\7\0\1\12"+
    "\34\0\4\1\4\0\1\1\12\0\1\1\4\0\1\1\5\0\27\1"+
    "\1\0\37\1\1\0\u01ca\1\4\0\14\1\16\0\5\1\7\0\1\1"+
    "\1\0\1\1\201\0\5\1\1\0\2\1\2\0\4\1\10\0\1\1"+
    "\1\0\3\1\1\0\1\1\1\0\24\1\1\0\123\1\1\0\213\1"+
    "\10\0\236\1\11\0\46\1\2\0\1\1\7\0\47\1\7\0\1\1"+
    "\100\0\33\1\5\0\3\1\30\0\1\1\24\0\53\1\25\0\12\7"+
    "\4\0\2\1\1\0\143\1\1\0\1\1\17\0\2\1\7\0\2\1"+
    "\12\7\3\1\2\0\1\1\20\0\1\1\1\0\36\1\35\0\131\1"+
    "\13\0\1\1\16\0\12\7\41\1\11\0\2\1\4\0\1\1\5\0"+
    "\26\1\4\0\1\1\11\0\1\1\3\0\1\1\27\0\31\1\107\0"+
    "\1\1\1\0\13\1\127\0\66\1\3\0\1\1\22\0\1\1\7\0"+
    "\12\1\4\0\12\7\1\0\7\1\1\0\7\1\5\0\10\1\2\0"+
    "\2\1\2\0\26\1\1\0\7\1\1\0\1\1\3\0\4\1\3\0"+
    "\1\1\20\0\1\1\15\0\2\1\1\0\3\1\4\0\12\7\4\1"+
    "\7\0\1\1\11\0\6\1\4\0\2\1\2\0\26\1\1\0\7\1"+
    "\1\0\2\1\1\0\2\1\1\0\2\1\37\0\4\1\1\0\1\1"+
    "\7\0\12\7\2\0\3\1\20\0\11\1\1\0\3\1\1\0\26\1"+
    "\1\0\7\1\1\0\2\1\1\0\5\1\3\0\1\1\22\0\1\1"+
    "\17\0\2\1\4\0\12\7\1\0\1\1\23\0\10\1\2\0\2\1"+
    "\2\0\26\1\1\0\7\1\1\0\2\1\1\0\5\1\3\0\1\1"+
    "\36\0\2\1\1\0\3\1\4\0\12\7\1\0\1\1\21\0\1\1"+
    "\1\0\6\1\3\0\3\1\1\0\4\1\3\0\2\1\1\0\1\1"+
    "\1\0\2\1\3\0\2\1\3\0\3\1\3\0\14\1\26\0\1\1"+
    "\25\0\12\7\11\0\1\1\13\0\10\1\1\0\3\1\1\0\27\1"+
    "\1\0\12\1\1\0\5\1\3\0\1\1\32\0\2\1\6\0\2\1"+
    "\4\0\12\7\25\0\10\1\1\0\3\1\1\0\27\1\1\0\12\1"+
    "\1\0\5\1\3\0\1\1\40\0\1\1\1\0\2\1\4\0\12\7"+
    "\1\0\2\1\22\0\10\1\1\0\3\1\1\0\51\1\2\0\1\1"+
    "\20\0\1\1\21\0\2\1\4\0\12\7\12\0\6\1\5\0\22\1"+
    "\3\0\30\1\1\0\11\1\1\0\1\1\2\0\7\1\37\0\12\7"+
    "\21\0\60\1\1\0\2\1\13\0\10\1\11\0\12\7\47\0\2\1"+
    "\1\0\1\1\2\0\2\1\1\0\1\1\2\0\1\1\6\0\4\1"+
    "\1\0\7\1\1\0\3\1\1\0\1\1\1\0\1\1\2\0\2\1"+
    "\1\0\4\1\1\0\2\1\11\0\1\1\2\0\5\1\1\0\1\1"+
    "\11\0\12\7\2\0\4\1\40\0\1\1\37\0\12\7\26\0\10\1"+
    "\1\0\44\1\33\0\5\1\163\0\53\1\24\0\1\1\12\7\6\0"+
    "\6\1\4\0\4\1\3\0\1\1\3\0\2\1\7\0\3\1\4\0"+
    "\15\1\14\0\1\1\1\0\12\7\6\0\46\1\1\0\1\1\5\0"+
    "\1\1\2\0\53\1\1\0\u014d\1\1\0\4\1\2\0\7\1\1\0"+
    "\1\1\1\0\4\1\2\0\51\1\1\0\4\1\2\0\41\1\1\0"+
    "\4\1\2\0\7\1\1\0\1\1\1\0\4\1\2\0\17\1\1\0"+
    "\71\1\1\0\4\1\2\0\103\1\45\0\20\1\20\0\125\1\14\0"+
    "\u026c\1\2\0\21\1\1\0\32\1\5\0\113\1\3\0\3\1\17\0"+
    "\15\1\1\0\4\1\16\0\22\1\16\0\22\1\16\0\15\1\1\0"+
    "\3\1\17\0\64\1\43\0\1\1\3\0\2\1\3\0\12\7\46\0"+
    "\12\7\6\0\130\1\10\0\51\1\1\0\1\1\5\0\106\1\12\0"+
    "\35\1\51\0\12\7\36\1\2\0\5\1\13\0\54\1\25\0\7\1"+
    "\10\0\12\7\46\0\27\1\11\0\65\1\53\0\12\7\6\0\12\7"+
    "\15\0\1\1\135\0\57\1\21\0\7\1\4\0\12\7\51\0\36\1"+
    "\15\0\2\1\12\7\54\1\32\0\44\1\34\0\12\7\3\0\3\1"+
    "\12\7\44\1\153\0\4\1\1\0\4\1\3\0\2\1\11\0\300\1"+
    "\100\0\u0116\1\2\0\6\1\2\0\46\1\2\0\6\1\2\0\10\1"+
    "\1\0\1\1\1\0\1\1\1\0\1\1\1\0\37\1\2\0\65\1"+
    "\1\0\7\1\1\0\1\1\3\0\3\1\1\0\7\1\3\0\4\1"+
    "\2\0\6\1\4\0\15\1\5\0\3\1\1\0\7\1\53\0\1\12"+
    "\1\12\25\0\2\1\23\0\1\1\34\0\1\1\15\0\1\1\20\0"+
    "\15\1\3\0\33\1\107\0\1\1\4\0\1\1\2\0\12\1\1\0"+
    "\1\1\3\0\5\1\6\0\1\1\1\0\1\1\1\0\1\1\1\0"+
    "\4\1\1\0\13\1\2\0\4\1\5\0\5\1\4\0\1\1\21\0"+
    "\51\1\u0a77\0\57\1\1\0\57\1\1\0\205\1\6\0\4\1\3\0"+
    "\2\1\14\0\46\1\1\0\1\1\5\0\1\1\2\0\70\1\7\0"+
    "\1\1\20\0\27\1\11\0\7\1\1\0\7\1\1\0\7\1\1\0"+
    "\7\1\1\0\7\1\1\0\7\1\1\0\7\1\1\0\7\1\120\0"+
    "\1\1\u01d5\0\3\1\31\0\11\1\7\0\5\1\2\0\5\1\4\0"+
    "\126\1\6\0\3\1\1\0\132\1\1\0\4\1\5\0\51\1\3\0"+
    "\136\1\21\0\33\1\65\0\20\1\u0200\0\u19b6\1\112\0\u51cd\1\63\0"+
    "\u048d\1\103\0\56\1\2\0\u010d\1\3\0\20\1\12\7\2\1\24\0"+
    "\57\1\20\0\31\1\10\0\120\1\47\0\11\1\2\0\147\1\2\0"+
    "\4\1\1\0\4\1\14\0\13\1\115\0\12\1\1\0\3\1\1\0"+
    "\4\1\1\0\27\1\25\0\1\1\7\0\64\1\16\0\62\1\34\0"+
    "\12\7\30\0\6\1\3\0\1\1\4\0\12\7\34\1\12\0\27\1"+
    "\31\0\35\1\7\0\57\1\34\0\1\1\12\7\26\0\12\7\6\0"+
    "\51\1\27\0\3\1\1\0\10\1\4\0\12\7\6\0\27\1\3\0"+
    "\1\1\5\0\60\1\1\0\1\1\3\0\2\1\2\0\5\1\2\0"+
    "\1\1\1\0\1\1\30\0\3\1\2\0\13\1\7\0\3\1\14\0"+
    "\6\1\2\0\6\1\2\0\6\1\11\0\7\1\1\0\7\1\221\0"+
    "\43\1\15\0\12\7\6\0\u2ba4\1\14\0\27\1\4\0\61\1\u2104\0"+
    "\u016e\1\2\0\152\1\46\0\7\1\14\0\5\1\5\0\1\1\1\0"+
    "\12\1\1\0\15\1\1\0\5\1\1\0\1\1\1\0\2\1\1\0"+
    "\2\1\1\0\154\1\41\0\u016b\1\22\0\100\1\2\0\66\1\50\0"+
    "\15\1\66\0\2\1\30\0\3\1\31\0\1\1\6\0\5\1\1\0"+
    "\207\1\7\0\1\1\13\0\12\7\7\0\32\1\4\0\1\1\1\0"+
    "\32\1\13\0\131\1\3\0\6\1\2\0\6\1\2\0\6\1\2\0"+
    "\3\1\3\0\2\1\3\0\2\1\31\0\14\1\1\0\32\1\1\0"+
    "\23\1\1\0\2\1\1\0\17\1\2\0\16\1\42\0\173\1\105\0"+
    "\65\1\u010b\0\35\1\3\0\61\1\57\0\37\1\21\0\33\1\65\0"+
    "\36\1\2\0\44\1\4\0\10\1\1\0\5\1\52\0\236\1\2\0"+
    "\12\7\u0356\0\6\1\2\0\1\1\1\0\54\1\1\0\2\1\3\0"+
    "\1\1\2\0\27\1\252\0\26\1\12\0\32\1\106\0\70\1\6\0"+
    "\2\1\100\0\1\1\17\0\4\1\1\0\3\1\1\0\33\1\54\0"+
    "\35\1\203\0\66\1\12\0\26\1\12\0\23\1\215\0\111\1\u03ba\0"+
    "\65\1\56\0\12\7\23\0\55\1\40\0\31\1\7\0\12\7\11\0"+
    "\44\1\17\0\12\7\103\0\60\1\16\0\4\1\13\0\12\7\u0116\0"+
    "\12\7\u01d6\0\12\7\u0176\0\12\7\46\0\53\1\25\0\12\7\u0216\0"+
    "\12\7\u0716\0\u036f\1\221\0\143\1\u0b9d\0\u042f\1\u33d1\0\u0239\1\47\0"+
    "\12\7\346\0\12\7\u03a6\0\105\1\13\0\1\1\102\0\15\1\u4060\0"+
    "\2\1\u23fe\0\125\1\1\0\107\1\1\0\2\1\2\0\1\1\2\0"+
    "\2\1\2\0\4\1\1\0\14\1\1\0\1\1\1\0\7\1\1\0"+
    "\101\1\1\0\4\1\2\0\10\1\1\0\7\1\1\0\34\1\1\0"+
    "\4\1\1\0\5\1\1\0\1\1\3\0\7\1\1\0\u0154\1\2\0"+
    "\31\1\1\0\31\1\1\0\37\1\1\0\31\1\1\0\37\1\1\0"+
    "\31\1\1\0\37\1\1\0\31\1\1\0\37\1\1\0\31\1\1\0"+
    "\10\1\2\0\62\7\u1600\0\4\1\1\0\33\1\1\0\2\1\1\0"+
    "\1\1\2\0\1\1\1\0\12\1\1\0\4\1\1\0\1\1\1\0"+
    "\1\1\6\0\1\1\4\0\1\1\1\0\1\1\1\0\1\1\1\0"+
    "\3\1\1\0\2\1\1\0\1\1\2\0\1\1\1\0\1\1\1\0"+
    "\1\1\1\0\1\1\1\0\1\1\1\0\2\1\1\0\1\1\2\0"+
    "\4\1\1\0\7\1\1\0\4\1\1\0\4\1\1\0\1\1\1\0"+
    "\12\1\1\0\21\1\5\0\3\1\1\0\5\1\1\0\21\1\u1144\0"+
    "\ua6d7\1\51\0\u1035\1\13\0\336\1\u3fe2\0\u021e\1\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\u05f0\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\2\3\2\4\1\1\1\5\1\6"+
    "\5\2\1\7\1\10\1\11\1\12\1\13\1\1\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\4"+
    "\2\24\1\0\1\25\1\2\1\26\4\2\1\27\1\30"+
    "\1\31\1\32\1\33\1\34\4\2\1\35\1\2\1\36"+
    "\1\2\1\37\1\2\1\40";

  private static int [] zzUnpackAction() {
    int [] result = new int[58];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\50\0\120\0\170\0\240\0\310\0\360\0\120"+
    "\0\240\0\120\0\u0118\0\u0140\0\u0168\0\u0190\0\u01b8\0\u01e0"+
    "\0\120\0\120\0\u0208\0\u0230\0\u0258\0\u0280\0\120\0\120"+
    "\0\120\0\120\0\120\0\120\0\120\0\120\0\u02a8\0\u02d0"+
    "\0\u02f8\0\240\0\120\0\u0320\0\170\0\u0348\0\u0370\0\u0398"+
    "\0\u03c0\0\120\0\120\0\120\0\120\0\120\0\170\0\u03e8"+
    "\0\u0410\0\u0438\0\u0460\0\170\0\u0488\0\170\0\u04b0\0\170"+
    "\0\u04d8\0\170";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[58];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\6\1\7\2\10\1\11\1\12"+
    "\1\13\1\0\1\10\1\14\2\4\1\15\2\4\1\16"+
    "\2\4\1\17\2\4\1\20\1\4\1\21\1\22\1\23"+
    "\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33"+
    "\1\34\1\35\1\36\4\10\1\0\3\10\1\37\1\10"+
    "\2\0\34\10\51\0\1\4\2\40\3\0\1\40\4\0"+
    "\16\4\17\0\1\41\2\42\3\0\1\42\4\0\16\41"+
    "\17\0\1\41\2\6\3\0\1\42\4\0\16\41\23\0"+
    "\1\10\52\0\1\43\40\0\1\4\2\40\3\0\1\40"+
    "\4\0\1\4\1\44\10\4\1\45\3\4\17\0\1\4"+
    "\2\40\3\0\1\40\4\0\4\4\1\46\11\4\17\0"+
    "\1\4\2\40\3\0\1\40\4\0\7\4\1\47\6\4"+
    "\17\0\1\4\2\40\3\0\1\40\4\0\10\4\1\50"+
    "\5\4\17\0\1\4\2\40\3\0\1\40\4\0\11\4"+
    "\1\51\4\4\53\0\1\52\47\0\1\53\47\0\1\54"+
    "\47\0\1\55\23\0\1\56\40\0\2\40\3\0\1\40"+
    "\41\0\1\41\12\0\16\41\17\0\1\4\2\40\3\0"+
    "\1\40\4\0\2\4\1\57\13\4\17\0\1\4\2\40"+
    "\3\0\1\40\4\0\1\60\15\4\17\0\1\4\2\40"+
    "\3\0\1\40\4\0\1\61\15\4\17\0\1\4\2\40"+
    "\3\0\1\40\4\0\13\4\1\62\2\4\17\0\1\4"+
    "\2\40\3\0\1\40\4\0\2\4\1\63\13\4\17\0"+
    "\1\4\2\40\3\0\1\40\4\0\5\4\1\64\10\4"+
    "\17\0\1\4\2\40\3\0\1\40\4\0\10\4\1\65"+
    "\5\4\17\0\1\4\2\40\3\0\1\40\4\0\11\4"+
    "\1\66\4\4\17\0\1\4\2\40\3\0\1\40\4\0"+
    "\15\4\1\67\17\0\1\4\2\40\3\0\1\40\4\0"+
    "\11\4\1\70\4\4\17\0\1\4\2\40\3\0\1\40"+
    "\4\0\14\4\1\71\1\4\17\0\1\4\2\40\3\0"+
    "\1\40\4\0\1\4\1\72\14\4\16\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1280];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\4\1\1\11\1\1\1\11\6\1\2\11"+
    "\4\1\10\11\3\1\1\0\1\11\6\1\5\11\14\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[58];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    
	public CMinusLex(BufferedReader file){
		this.zzReader = file;
		try{
            nextToken = scanToken();
        } catch (IOException e){
            nextToken = new Token(Token.TokenType.ERROR);
        }
	}
    
    


    private Token nextToken;

    public Token getNextToken() {
        Token returnToken = nextToken;

        if (nextToken.getType() != Token.TokenType.EOF) {
            try {
                nextToken = scanToken();
            } catch (IOException ioe) {
                nextToken = new Token(Token.TokenType.ERROR);
            }
        }

        return returnToken;
    }

    public Token viewNextToken() {
        return nextToken;
    }


	public static void main(String[] args) throws IOException{
		/* Test the program here */		
        if(args.length != 1){
            System.out.println("USAGE: java CMinusLex input_file");
            System.exit(1);
        }
		File f = new File(args[0]);
		
		BufferedReader r = new BufferedReader(new FileReader(f));
		
		CMinusLex s = new CMinusLex(r);
		
		System.out.println("Testing File (" + args[0] + ")");
		Token t = s.getNextToken();
		while(t.getType() != Token.TokenType.EOF && t.getType() != Token.TokenType.ERROR){
			System.out.println(t.toString());
			t = s.getNextToken();
		}
        System.out.println(t.toString());
	}


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  CMinusLex(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2344) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Token scanToken() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            switch (zzLexicalState) {
            case COMMENT: {
              return new Token(Token.TokenType.ERROR);
            }
            case 59: break;
            default:
              {
                return new Token(Token.TokenType.EOF  );
              }
        }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return new Token(Token.TokenType.ERROR);
            }
          case 33: break;
          case 2: 
            { return new Token(Token.TokenType.ID, yytext());
            }
          case 34: break;
          case 3: 
            { return new Token(Token.TokenType.NUM, Integer.parseInt(yytext()));
            }
          case 35: break;
          case 4: 
            { 
            }
          case 36: break;
          case 5: 
            { return new Token(Token.TokenType.MULT      );
            }
          case 37: break;
          case 6: 
            { return new Token(Token.TokenType.DIV       );
            }
          case 38: break;
          case 7: 
            { return new Token(Token.TokenType.ADD       );
            }
          case 39: break;
          case 8: 
            { return new Token(Token.TokenType.SUB       );
            }
          case 40: break;
          case 9: 
            { return new Token(Token.TokenType.LT        );
            }
          case 41: break;
          case 10: 
            { return new Token(Token.TokenType.ASSIGN    );
            }
          case 42: break;
          case 11: 
            { return new Token(Token.TokenType.GT        );
            }
          case 43: break;
          case 12: 
            { return new Token(Token.TokenType.SEMICOLON );
            }
          case 44: break;
          case 13: 
            { return new Token(Token.TokenType.COMMA     );
            }
          case 45: break;
          case 14: 
            { return new Token(Token.TokenType.OPEN_PAREN    );
            }
          case 46: break;
          case 15: 
            { return new Token(Token.TokenType.CLOSE_PAREN   );
            }
          case 47: break;
          case 16: 
            { return new Token(Token.TokenType.OPEN_BRACKET  );
            }
          case 48: break;
          case 17: 
            { return new Token(Token.TokenType.CLOSE_BRACKET );
            }
          case 49: break;
          case 18: 
            { return new Token(Token.TokenType.OPEN_BRACE    );
            }
          case 50: break;
          case 19: 
            { return new Token(Token.TokenType.CLOSE_BRACE   );
            }
          case 51: break;
          case 20: 
            { return new Token(Token.TokenType.ERROR, "illegal identifier");
            }
          case 52: break;
          case 21: 
            { yybegin(COMMENT);
            }
          case 53: break;
          case 22: 
            { return new Token(Token.TokenType.IF);
            }
          case 54: break;
          case 23: 
            { return new Token(Token.TokenType.LTE       );
            }
          case 55: break;
          case 24: 
            { return new Token(Token.TokenType.EQUAL     );
            }
          case 56: break;
          case 25: 
            { return new Token(Token.TokenType.GTE       );
            }
          case 57: break;
          case 26: 
            { return new Token(Token.TokenType.NOT_EQUAL );
            }
          case 58: break;
          case 27: 
            { yybegin(YYINITIAL); return new Token(Token.TokenType.COMMENT, yytext());
            }
          case 59: break;
          case 28: 
            { return new Token(Token.TokenType.INT);
            }
          case 60: break;
          case 29: 
            { return new Token(Token.TokenType.VOID);
            }
          case 61: break;
          case 30: 
            { return new Token(Token.TokenType.ELSE);
            }
          case 62: break;
          case 31: 
            { return new Token(Token.TokenType.WHILE);
            }
          case 63: break;
          case 32: 
            { return new Token(Token.TokenType.RETURN);
            }
          case 64: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
