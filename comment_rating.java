import java.sql.*;
import java.sql.SQLException;

public class comment_rating
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
       ResultSet rs1=null;   //orderNo
       ResultSet rs2=null;   //storeNo
       ResultSet rs3=null;   //productNo
       try
       {
           Class.forName(driver);
           conn = DriverManager.getConnection(url, username, password);
           stmt1 = conn.createStatement();
           stmt2 = conn.createStatement();
           stmt3 = conn.createStatement();
           String sql1="select orderNo from order_item";
           String sql2="select storeNo from order_item";
           String sql3="select productNo from order_item";
           String orderno[]=new String[210000];
           String storeno[]=new String[210000];
           String productno[]=new String[210000];
           rs1=stmt1.executeQuery(sql1);
           rs2=stmt2.executeQuery(sql2);
           rs3=stmt3.executeQuery(sql3);
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
       String g[]= {"good","excellent","very good"};
       String m[]= {"not bad","just so so","general"};
       String b[]= {"bad","very bad","terrible"};
        for(int loop=1;loop<=210000;loop++)
       {
                int r=(int)(Math.random()*5);
                String sql="insert into comment_rating(order_item_orderNo,order_item_storeNo,order_item_productNo,comment,rating) VALUES(?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,orderno[loop]);
                ps.setString(2,storeno[loop]);
                ps.setString(3,productno[loop]);
                if(r==0||r==1)
                {
                     int c=(int)(Math.random()*2);
                     ps.setString(4,b[c]);
                     ps.setInt(5,r);
                }
                 if(r==2||r==3)
                {
                     int c=(int)(Math.random()*2);
                     ps.setString(4,m[c]);
                     ps.setInt(5,r);
                }
                if(r==4||r==5)
                {
                     int c=(int)(Math.random()*2);
                     ps.setString(4,g[c]);
                     ps.setInt(5,r);
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