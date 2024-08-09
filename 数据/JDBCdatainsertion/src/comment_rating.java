import java.math.BigDecimal;
import java.sql.*;
import java.sql.SQLException;

public class comment_rating
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
       
       int loop;
       
       Connection conn=null;
       Statement stmt1=null;

       ResultSet rs1=null;   //orderNo
  //productNo
       try
       {
           Class.forName(driver);
           conn = DriverManager.getConnection(url, username, password);
           stmt1 = conn.createStatement();
           
           String sql1="select distinct orderno,storeNo,productno from order_item;";
           
           String[] orderno=new String[220000];
           String[] storeno=new String[220000];
           String[] productno=new String[220000];
           
           rs1=stmt1.executeQuery(sql1);

           int i=0;
           while(rs1.next())
          {
               orderno[i]=rs1.getString("orderNo");
               storeno[i]=rs1.getString("storeNo");
               productno[i]=rs1.getString("productNo");
                i++;
          }


       String[] g= {"Perfect!","Excellent!","Very good!","Fantastic!","It's the best!"};
       String[] m= {"Not bad.","Just so so.","General."};
       String[] b= {"Very bad!","Terrible!","Horrible!","Rubbish!","Disgusting."};
       
        for(loop=0;loop<i;loop++)
       {
                
                String sql="insert into comment_rating VALUES(?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                
               //int prand=(int)(Math.random()*i);
                
                double r=mul((int)(Math.random()*500),0.01)+0.01;
                
                ps.setString(1,orderno[loop]);
                ps.setString(2,storeno[loop]);
                ps.setString(3,productno[loop]);
                
                if(r<=2)
                {
                     int c=(int)(Math.random()*5);
                     ps.setString(4,b[c]);
                     ps.setDouble(5,r);
                     
                     ps.executeUpdate();
                }
                else if(r<=3.5)
                {
                     int c=(int)(Math.random()*3);
                     ps.setString(4,m[c]);
                     ps.setDouble(5,r);
                     
                     ps.executeUpdate();
                }
                else
                {
                     int c=(int)(Math.random()*5);
                     ps.setString(4,g[c]);
                     ps.setDouble(5,r);
                     
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