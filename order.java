import java.sql.*;
import java.sql.SQLException;

public class order
{
   public static void main(String args[])
   {
       String driver="com.mysql.jdbc.Driver";
       String url="jdbc:mysql://localhost:3306/ShoppingWebsiteBackEndDB?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
       String username="root";
       String password="zrz0328";
       Connection conn=null;
       Statement stmt1=null;
       ResultSet rs1=null;   //customer_username
       try
       {
           Class.forName(driver);
           conn = DriverManager.getConnection(url, username, password);
           stmt1 = conn.createStatement();
           String sql1="select customer_userName from customer";
           String orderno[]=new String[110000];
           String userName[]=new String[11000];
           rs1=stmt1.executeQuery(sql1);
           int i=1;
           while(rs1.next())
           {
                  userName[i]=rs1.getString("customer_userName");
                   i++;
           }
           int k=100000;
           for(i=1;i<=100000;i++)
           {
               orderno[i]="o"+k;
               k++;
            }
           for(int j=1;j<=100000;j++)
           {
                  String sql="insert into order(orderNo,customer_userName,status,createDate,deliveryDate) VALUES(?,?,?,?,?)";
                  PreparedStatement ps = conn.prepareStatement(sql);
                  int userrand=(int)(Math.random()*9999)+1;
                  int starand=(int)(Math.random()*3)+1;
                  int yrand=(int)(Math.random()*2018)+2016;
                  int mrand=(int)(Math.random()*12)+1;
                  int drand=(int)(Math.random()*27)+1;
                  int hrand=(int)(Math.random()*24);
                  int mmrand=(int)(Math.random()*59);
                  int srand=(int)(Math.random()*59);
                  ps.setString(1,orderno[j]);
                  ps.setString(2,userName[userrand]);
                  ps.setInt(3,starand);
                  if(starand==1||starand==4)
                  {
                    ps.setString(4,"yrand-mrand-drand hrand:mrand:srand");
                    ps.setString(5,null);
                  }
                  else
                  {
                     ps.setString(4,"yrand-mrand-drand hrand:mrand:srand");
                     hrand=(int)(Math.random()*24);
                     mrand=(int)(Math.random()*59);
                     srand=(int)(Math.random()*59);
                     ps.setString(5,"yrand-mrand-drand+1 hrand:mrand:srand");
                  }
                  ps.executeUpdate();
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