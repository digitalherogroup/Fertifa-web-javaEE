package com.fertifa.app.converters;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.controllers.ShoppingCartController;
import com.fertifa.app.models.ShoppingCartFinal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet("/GetCVS")
public class GetCVS extends HttpServlet {

    private String Start;
    private String End;
    private int ServiceId;
    private int CompanyId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            getCVS(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            getCVS(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getCVS(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        getParameters(request);
        printCSV(Start, End, ServiceId, CompanyId, response, request);
    }


    private void getParameters(HttpServletRequest request) {
        Start = request.getParameter("start");
        End = request.getParameter("End");
        if (request.getParameter("category") != null && !request.getParameter("category").equals("")) {
            ServiceId = Integer.parseInt(request.getParameter("category"));
        }
        if (request.getParameter("company") != null && !request.getParameter("company").equals("")) {
            CompanyId = Integer.parseInt(request.getParameter("company"));
        }
    }

    /**
     * Data gates
     *
     * @return
     * @throws SQLException
     */
    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    private void printCSV(String start, String end, int ServiceId, int CompanyId, HttpServletResponse response, HttpServletRequest request) throws IOException, SQLException {

        response.setContentType("text/csv");
        String reportName = "GenerateCSV_Report_"
                + System.currentTimeMillis() + ".csv";
        response.setHeader("Content-disposition", "attachment; " +
                "filename=" + reportName);
        List<ShoppingCartFinal> roBuilder = new ArrayList<>();
        List<String> rows = new ArrayList<>();
        rows.add("id");
        rows.add(",");
        rows.add("Service Name");
        rows.add(",");
        rows.add("First Name");
        rows.add(",");
        rows.add("Last Name");
        rows.add(",");
        rows.add("Total Price");
        rows.add(",");
        rows.add("Order_id");
        rows.add(",");
       /* rows.add("Order Dates");
        rows.add(",");*/
        rows.add("Stripe Order Id");
        rows.add(",");
        rows.add("\n");

        ShoppingCartController shoppingCartController = new ShoppingCartController();
        roBuilder = shoppingCartController.GetAllByDates(end, start, ServiceId, CompanyId);
        for (int i = 0; i < roBuilder.size(); i++) {
            String id = String.valueOf(roBuilder.get(i).getId());
            System.out.println(id);
            String ServiceName = roBuilder.get(i).getServiceName();
            System.out.println(ServiceName);
            String cardholderfirstname = roBuilder.get(i).getCardholderfirstname();
            System.out.println(cardholderfirstname);
            String cardholderlastname = roBuilder.get(i).getCardholderlastname();
            System.out.println(cardholderlastname);
            String total_price = String.valueOf(roBuilder.get(i).getTotal_price());
            System.out.println(total_price);
            String Order_id = String.valueOf(roBuilder.get(i).getOrder_id());
            System.out.println(Order_id);
          /*  String order_dates = roBuilder.get(i).getOrder_dates();
            System.out.println(order_dates);*/
            String stripe_order_id = roBuilder.get(i).getStripe_user_id();
            System.out.println(stripe_order_id);
            rows.add(id);
            rows.add(",");
            rows.add(ServiceName);
            rows.add(",");
            rows.add(cardholderfirstname);
            rows.add(",");
            rows.add(cardholderlastname);
            rows.add(",");
            rows.add(total_price);
            rows.add(",");
            rows.add(Order_id);
            rows.add(",");
           /* rows.add(order_dates);
            rows.add(",");*/
            rows.add(stripe_order_id);
            rows.add(",");
            rows.add("\n");
        }

        Iterator<String> iter = rows.iterator();
        while (iter.hasNext()) {
            String outputString = iter.next();
            response.getOutputStream().print(outputString);
        }

        response.getOutputStream().flush();

        /*String uploadedFileLocation = System.getProperty("user.dir").concat("\\src\\main\\webapp\\upload\\sales.csv");
        PrintWriter printWriter = new PrintWriter(uploadedFileLocation);*//*
        File f = new File("sales.csv");
        FileOutputStream fout = new FileOutputStream(f);
        FileWriter fw = new FileWriter(f);
        System.out.println (f.getAbsolutePath());
        PrintWriter printWriter = new PrintWriter(fw);
        StringBuilder sb = new StringBuilder();
        com.fertifa.app.Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        ShoppingCartFinal shoppingCart = null;
        System.out.println("File location on server::"+f.getAbsolutePath());
        ServletContext ctx = getServletContext();
        InputStream fis = new FileInputStream(f);
        String mimeType = ctx.getMimeType(f.getAbsolutePath());
        response.setContentType(mimeType != null? mimeType:"application/octet-stream");
        response.setContentLength((int) f.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + f + "\"");
        try {
            connection = ConnectToData();

            String sql = "SELECT * FROM `shoppingcartfinal` WHERE `status`<> 9000";

            if (CompanyId > 0) {
                sql += " and `company_id`=" + CompanyId;

            }

            if (ServiceId > 0) {
                sql += " and `service_id`=" + ServiceId;

            }

            if (start != "") {
                sql += " and `order_dates` >=" + "\"" +  end + "\"";
            }

            if (end != "") {
                sql += " AND `order_dates`<= " + "\"" +start + "\"";
            }
            statement = connection.createStatement();
            set = statement.executeQuery(sql);

            while(set.next()){
                int id = set.getInt("id");
                System.out.println(id);
                printWriter.print(id);// first row first column
                printWriter.print(",");
                printWriter.print(set.getString("serviceName"));// first row second column
                printWriter.print(",");
                printWriter.println(set.getString("cardholderfirstname"));// first row third column
                printWriter.print(",");
               *//* sb.append(set.getInt("id"));
                sb.append(",");
                sb.append(set.getString("serviceName"));
                sb.append(",");
                sb.append(set.getString("cardholderfirstname"));
                sb.append(",");
                sb.append(set.getString("cardholderlastname"));
                sb.append(",");
                sb.append(set.getString("order_dates"));
                sb.append(",");
                sb.append(set.getString("total_price"));
                sb.append(",");
                sb.append(set.getString("stripe_order_id"));
                sb.append("\r\n");*//*
            }

            //Close the File Writer
            System.out.println (f.getAbsolutePath());
            String directiopn = f.getAbsolutePath() ;
            printWriter.flush();
            printWriter.close();
            fw.close();




            response.sendRedirect(directiopn);*/
       /* } catch (SQLException | IOException e) {
            e.printStackTrace();
        }*/
    }
}
