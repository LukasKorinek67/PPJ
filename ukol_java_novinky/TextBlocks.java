public class TextBlocks {

    /* Text Blocks - nová syntaxe pro víceřádkové řetězce.
    Usnadňuje práci s řetězci obsahujícími více řádků a snižuje nutnost
    používat escape sekvence.
    Příklad: */

    public static void main(String[] args) {

        String tableName = "employees";
        int minAge = 25;
        int maxAge = 35;
        String name = "John";
        String sqlQuery = generateSqlQuery(tableName, minAge, maxAge, name);
        System.out.println(sqlQuery);

    }
    public static String generateSqlQuery(String tableName, int minAge, int maxAge, String name) {
        return """
           SELECT *
           FROM """ + tableName + """
           WHERE age >= """ + minAge + """
           AND age <= """ + maxAge + """
           AND name LIKE """ + name + """
           """;
    }

    public static String getHtml() {
        String html = """
              <html>
                  <head>
                      <title>Hello, world!</title>
                  </head>
                  <body>
                      <h1>Hello, world!</h1>
                      <p>Welcome to my website.</p>
                  </body>
              </html>
              """;
        return html;
    }

}
