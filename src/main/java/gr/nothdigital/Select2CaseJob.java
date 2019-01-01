package gr.nothdigital;

import gr.northdigital.orasqlworker.command.Command;

import java.sql.Connection;
import java.sql.ResultSetMetaData;

@SuppressWarnings("Duplicates")
public class Select2CaseJob {
  public static void run(Connection connection, String sql, String caseClassName) throws Exception {
    Command oraCommand = new Command(connection, sql + " WHERE ROWNUM = 1");

    final String[] seperator = {""};

    System.out.print("case class " + caseClassName + "(");

    oraCommand.processResultSet(resultSet -> {
      ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

      int columnCount = resultSetMetaData.getColumnCount();

      for (int i = 1; i <= columnCount; i++) {
        String COLUMN_NAME = resultSetMetaData.getColumnName(i);
        String DATA_TYPE = resultSetMetaData.getColumnTypeName(i);

        if (DATA_TYPE.equals("NUMBER")) {
          System.out.print(seperator[0] + COLUMN_NAME + ": Double");
          seperator[0] = ", ";
        } else if (DATA_TYPE.equals("VARCHAR2")) {
          System.out.print(seperator[0] + COLUMN_NAME + ": String");
          seperator[0] = ", ";
        }
      }
    });

    System.out.print(")");

    oraCommand.close();
  }
}
