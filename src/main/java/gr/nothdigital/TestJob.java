package gr.nothdigital;

import gr.northdigital.orasqlworker.command.Command;

import java.sql.Connection;

public class TestJob {
  public static void run(Connection connection) throws Exception {
    String sql = "SELECT ID, TITLE FROM CASINOCRM.ACTION";

    Command oraCommand = new Command(connection, sql);

    oraCommand.processResultSet(resultSet -> {
      long id = resultSet.getLong(1);
      String title = resultSet.getString(2);

      System.out.println("id=" + id + " title=" + title);
    });

    oraCommand.close();
  }
}
