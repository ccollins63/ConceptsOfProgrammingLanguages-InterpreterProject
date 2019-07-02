public class Token
{
    private int rowNumber;
    private int columnNumber;
    private String lexeme;
    private TokenType tokenType;

    public Token (int rowNumber, int columnNumber, String lexeme, TokenType tokenType)
    {
        if (rowNumber <= 0)
        {
            throw new IllegalArgumentException("Invalid row number argument");
        }
        if (columnNumber <= 0)
        {
            throw new IllegalArgumentException("Invalid column number argument");
        }
        if (lexeme == null || lexeme.length() == 0)
        {
            throw new IllegalArgumentException("Invalid lexeme argument");
        }
        if (tokenType == null)
        {
            //throw new IllegalArgumentException("Invalid TokenType argument");
        }
        this.rowNumber      = rowNumber;
        this.columnNumber   = columnNumber;
        this.lexeme         = lexeme;
        this.tokenType      = tokenType;
    }

    public int getRowNumber()
    {
        return rowNumber;
    }

    public int getColumnNumber()
    {
        return columnNumber;
    }

    public String getLexeme()
    {
        return lexeme;
    }

    public TokenType getTokenType()
    {
        return tokenType;
    }

    @Override
    public String toString()
    {
        return String.format( "%2s %-8s %-20s %-8s", getRowNumber(), getColumnNumber(), getLexeme(), getTokenType());
    }
}
