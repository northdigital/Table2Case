package gr.nothdigital;

import gr.northdigital.orasqlworker.SqlWorker;
import gr.northdigital.orasqlworker.utils.JdbcConBuilder;

public class Main {

  public static void main(String[] args) throws Exception {
    System.out.println("begin");

    JdbcConBuilder jdbcConBuilder;
    SqlWorker sqlWorker;

    jdbcConBuilder = new JdbcConBuilder(args[0], args[1], args[2], args[3]);
    sqlWorker = new SqlWorker(jdbcConBuilder);

    sqlWorker.run(false, false, connection -> {
      //TestJob.run(connection);
      //Table2CaseJob.run(connection, args[4], args[5]);
      Select2CaseJob.run(connection, "SELECT ID, TITLE FROM CASINOCRM.ACTION", "ActionSelect");
    });

    System.out.println();
    System.out.println("end");
  }
}

