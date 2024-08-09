import java.sql.*;
import java.sql.SQLException;

public class order_item
{
   public static void main(String args[])
   {
       String driver="com.mysql.jdbc.Driver";
       String url="jdbc:mysql://localhost:3306/ShoppingWebsiteBackEndDB?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
       String username="root";
       String password="zrz0328";
       Connection conn=null;
       Statement stmt1=null;
       Statement stmt2=null;
       Statement stmt3=null;
       Statement stmt4=null;
       ResultSet rs1=null;   //orderNo
       ResultSet rs2=null;   //storeNo
       ResultSet rs3=null;   //productNo
       ResultSet rs4=null;   //price
       try
       {
           Class.forName(driver);
           conn = DriverManager.getConnection(url, username, password);
           stmt1 = conn.createStatement();
           stmt2 = conn.createStatement();
           stmt3 = conn.createStatement();
           stmt4 = conn.createStatement();
           String sql1="select orderNo from order";
           String sql2="select storeNo from onSale";
           String sql3="select productNo from onSale";
           String sql4="select price from onSale";
           String orderno[]=new String[110000];
           String storeno[]=new String[11000];
           String productno[]=new String[11000];
           float pce[]=new float[11000];
           rs1=stmt1.executeQuery(sql1);
           rs2=stmt2.executeQuery(sql2);
           rs3=stmt3.executeQuery(sql3);
           rs4=stmt4.executeQuery(sql4);
           int i=1;
           while(rs1.next())
          {
               orderno[i]=rs1.getString("orderNo");
                i++;
          }
          int j=1;
          while(rs2.next())
         {
               storeno[j]=rs2.getString("storeNo");
                j++;
         }
         int k=1;
         while(rs3.next())
         {
               productno[k]=rs3.getString("productNo");
               k++;
          }
         int z=1;
         while(rs4.next())
         {
            pce[z]=rs4.getFloat("price");
           }
        for(int loop=1;loop<=100000;loop++)
       {
             for(int lloop=1;lloop<=2;lloop++)
             {  
                     int n=(int)(Math.random()*20);
                     int prand=(int)(Math.random()*9990)+1;
                     float p=n*pce[prand];
                     String sql="insert into order_item(orderNo,storeNo,productNo,number_of_product,itemMonney) VALUES(?,?,?,?,?)";
                     PreparedStatement ps = conn.prepareStatement(sql);
                     ps.setString(1,orderno[loop]);
                     ps.setString(2,storeno[prand]);
                     ps.setString(3,productno[prand]);
                     ps.setInt(4,n);
                     ps.setFloat(5,p);
                     ps.executeUpdate();
             }
       }
}
           catch(Exception e)
          {
                  e.printStackTrace();
          } 
          finally 
          {
                if (rs1 != null)
                { //关闭结果集对象
	try 
               {
	          rs1.close();
               } 
               catch (SQLException e) 
              {
	    e.printStackTrace();
	}
	}			
               if (stmt1!= null) 
               { // 关闭数据库操作对象
	try {
	       stmt1.close();
	} 
               catch (SQLException e) 
               {
	        e.printStackTrace();
	}
	}
                 if (rs2 != null)
		     { //关闭结果集对象
						try {
							rs2.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (stmt2!= null) { // 关闭数据库操作对象
						try {
							stmt2.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					 if (rs3 != null)
				     { //关闭结果集对象
								try {
									rs3.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if (stmt3!= null) { // 关闭数据库操作对象
								try {
									stmt3.close();
								} 
								catch (SQLException e) {
									e.printStackTrace();
								}
							
							}
               if (rs4 != null)
                { //关闭结果集对象
	try 
               {
	          rs4.close();
               } 
               catch (SQLException e) 
              {
	    e.printStackTrace();
	}
	}			
               if (stmt4!= null) 
               { // 关闭数据库操作对象
	try {
	       stmt4.close();
	} 
               catch (SQLException e) 
               {
	        e.printStackTrace();
	}
	}
             	if (conn != null) { // 关闭数据库连接对象
	try {
	       if (!conn.isClosed()) {
				conn.close();
			       }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
            }
   }
}