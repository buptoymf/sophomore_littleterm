import java.sql.*;
import java.sql.SQLException;
import java.math.BigDecimal;


public class order_item
{
	
	public static double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
	
	
   public static void main(String args[])
   {
	   String driver=new connection_information().driver;
       String url=new connection_information().url;
       String username=new connection_information().username;
       String password=new connection_information().password;
       
       int loop,loop2;
       
       Connection conn=null;
       Statement stmt1=null;
       Statement stmt2=null;
       Statement stmt3=null;
       Statement stmt4=null;
       
       ResultSet rs1=null; 
       ResultSet rs2=null;
       ResultSet rs3=null;
       ResultSet rs4=null;
       try
       {
           Class.forName(driver);
           conn = DriverManager.getConnection(url, username, password);
           stmt1 = conn.createStatement();
           stmt2 = conn.createStatement();
           stmt3 = conn.createStatement();
           
           
           
           String sql1="select orderNo from `order`;";
           String sql2="select distinct storeNo,productno,price from onSale;";
           String sql3;
           
           String[] orderno=new String[110000];
           String[] storeno=new String[110000];
           String[] productno=new String[110000];           
           double  pce;
           
           
           rs1=stmt1.executeQuery(sql1);
           rs2=stmt2.executeQuery(sql2);

           
          // String num1="select count(distinct storeNo,productno,price) from onSale;";
           //String num2="select count(distinct orderno) from `order`;";
           
           
           //rs3=stmt3.executeQuery(num1);
           //rs4=stmt4.executeQuery(num2);
           
           int i=0;
           
           while(rs1.next())
          {
               orderno[i]=rs1.getString("orderNo");
                i++;
          }
           
          int j=0;
          
          while(rs2.next())
         {
               storeno[j]=rs2.getString("storeNo");
               productno[j]=rs2.getString("productNo");
               //pce[j]=rs2.getDouble("price");
                j++;
         }
         
         //System.out.println(i);
         
         
        for(loop=0;loop<i;loop++)
       {
        	int prand;
             
        	prand=(int)(Math.random()*j);
        	
        	sql3="select distinct productno,price from onsale where storeno =\""+ storeno[prand] +"\" order by stock desc;";
            
            rs3=stmt3.executeQuery(sql3);
            
            
        	for(loop2=0;loop2<2;loop2++)
        	{
        		int n=(int)(Math.random()*20)+1;
                
        		
        		
                 
                
               
                
                String sql="insert into order_item VALUES(?,?,?,?,?)";

                PreparedStatement ps = conn.prepareStatement(sql);
                
                ps.setString(1,orderno[loop]);
                ps.setString(2,storeno[prand]);
                rs3.next();
                ps.setString(3,rs3.getString("productno"));
                
                pce=rs3.getDouble("price");
                
                double p=mul(n,pce);
                
                ps.setInt(4,n);
                ps.setDouble(5,p);
                
                ps.executeUpdate();
        	}
             
       }
        
       // System.out.println("Completed!");
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