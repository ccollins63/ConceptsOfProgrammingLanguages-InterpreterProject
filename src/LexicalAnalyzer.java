import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LexicalAnalyzer
{
    private static List <Token> tokens;


    public LexicalAnalyzer (String fileName)
    {
        assert(fileName != null);
        tokens = new ArrayList<Token>();
        try
        {
            Scanner scan = new Scanner(new File(fileName));
            int lineNumber = 1;
            while (scan.hasNext( ))
            {
                String line = scan.nextLine( );
                line = line.trim().replaceAll("\\s+", " ");
                processLine(line, lineNumber);
                lineNumber++;
            }
            scan.close( );
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private void processLine(String line, int lineNumber)
    {
        assert(line != null && lineNumber >= 1);
        int index = 0;
        while (index < line.length())
        {
            String lexeme = getLexeme(line, lineNumber, index);
            TokenType tokenType = getTokenType(lexeme, lineNumber, index);
            tokens.add(new Token(lineNumber, index + 1, lexeme, tokenType));
            index = index + lexeme.length() + 1;
        }
    }

    private TokenType getTokenType(String lexeme, int lineNumber, int columnNumber)
    {
        assert(lexeme != null && lineNumber >= 1 && columnNumber >= 1);
        TokenType tokenType = null;

        if (allDigits(lexeme))
        {
            tokenType = TokenType.DIGIT_TOK;
        }
        else if (lexeme.equalsIgnoreCase("FUNCTION"))
        {
            tokenType = TokenType.FUNCTION_TOK;
        }
        else if (lexeme.equalsIgnoreCase("VAR_INPUT"))
        {
            tokenType = TokenType.VAR_INPUT_TOK;
        }
        else if (lexeme.equalsIgnoreCase("END_VAR"))
        {
            tokenType = TokenType.END_VAR_TOK;
        }
        else if (lexeme.equalsIgnoreCase("BEGIN"))
        {
            tokenType = TokenType.BEGIN_TOK;
        }
        else if (lexeme.equalsIgnoreCase("INT"))
        {
            tokenType = TokenType.INT_TOK;
        }
        else if (lexeme.equalsIgnoreCase("IF"))
        {
            tokenType = TokenType.IF_TOK;
        }
        else if (lexeme.equalsIgnoreCase("ELSE"))
        {
            tokenType = TokenType.ELSE_TOK;
        }
        else if (lexeme.equalsIgnoreCase("END_FUNCTION"))
        {
            tokenType = TokenType.END_FUNCTION_TOK;
        }
        else if (lexeme.equalsIgnoreCase("END_IF"))
        {
            tokenType = TokenType.END_IF_TOK;
        }
        else if (lexeme.equalsIgnoreCase(":"))
        {
            tokenType = TokenType.COLON_TOK;
        }else if (lexeme.equalsIgnoreCase(":="))
        {
            tokenType = TokenType.DEFINE_TOK;
        }
        else if (lexeme.equalsIgnoreCase("*"))
        {
            tokenType = TokenType.MULTIPLICATION_TOK;
        }
        else if (lexeme.equalsIgnoreCase(";"))
        {
            tokenType = TokenType.END_EXPRESSION_TOK;
        }
        else if (lexeme.equalsIgnoreCase("<="))
        {
            tokenType = TokenType.LT_EQUAL_TOK;
        }
        else if (lexeme.equalsIgnoreCase("THEN"))
        {
            tokenType = TokenType.THEN_TOK;
        }
        else if (allLetters(lexeme))
        {
            tokenType = TokenType.VARIABLE_TOK;
        }


        return tokenType;
    }

    private boolean allDigits(String s)
    {
        assert(s != null);
        int i = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i)))
        {
            i++;
        }
        return i == s.length();
    }

    private boolean allLetters(String s)
    {
        assert(s != null);
        int i = 0;
        while (i < s.length() && Character.isLetter(s.charAt(i)))
        {
            i++;
        }
        return i == s.length();
    }

    private String getLexeme(String line, int lineNumber, int index)
    {
        assert(line != null && lineNumber >= 1 && index >= 0);
        int i = index;

        while (i < line.length() && !Character.isWhitespace(line.charAt(i)))
        {
            i++;
        }

        return line.substring(index, i);
    }

    public List<Token> getTokens()
    {
        return tokens;
    }
}
