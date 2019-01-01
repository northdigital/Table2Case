package gr.nothdigital;

import gr.northdigital.orasqlworker.command.Command;
import gr.northdigital.orasqlworker.param.VarcharInParam;

import java.sql.Connection;

@SuppressWarnings("Duplicates")
public class Table2CaseJob {
  public static void run(Connection connection, String tableName, String tableOwner) throws Exception {
    String sql = "SELECT COLUMN_NAME, DATA_TYPE, DATA_LENGTH FROM ALL_TAB_COLUMNS " +
                 "WHERE TABLE_NAME = ? AND OWNER = ?";

    Command oraCommand = new Command(connection, sql, new VarcharInParam(tableName), new VarcharInParam(tableOwner));

    final String[] seperator = {""};

    System.out.println("case class " + tableName + "(");

    oraCommand.processResultSet(resultSet -> {
      String COLUMN_NAME = resultSet.getString(1);
      String DATA_TYPE = resultSet.getString(2);
      long DATA_LENGTH = resultSet.getLong(3);

      if(DATA_TYPE.equals("NUMBER")) {
        System.out.print(seperator[0] + COLUMN_NAME + ": Double");
        seperator[0] = ", ";
      } else if(DATA_TYPE.equals("VARCHAR2")) {
        System.out.print(seperator[0] + COLUMN_NAME + ": String");
        seperator[0] = ", ";
      }

      //System.out.print("COLUMN_NAME=" + COLUMN_NAME + " DATA_TYPE=" + DATA_TYPE + " DATA_LENGTH=" + DATA_LENGTH);
    });

    System.out.print(")");
    System.out.println();

    oraCommand.close();
  }
}
