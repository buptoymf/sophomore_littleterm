import java.sql.*;
import java.sql.SQLException;

public class order
{
   public static void main(String args[])
   {
	   String driver=new connection_information().driver;
       String url=new connection_information().url;
       String username=new connection_information().username;
       String password=new connection_information().password;
       
       
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
           String userName[]=new String[110000];
           rs1=stmt1.executeQuery(sql1);
           int i=0;
           
           while(rs1.next())
           {
                  userName[i]=rs1.getString("customer_userName");
                   i++;
           }
           
           int k=100000;
           
           for(i=0;i<100000;i++)
           {
               orderno[i]="o"+k;
               k++;
            }
           int j;
           
           
           
           for(j=0;j<50000;j++)
           {
                  String sql="insert into `order` VALUES(?,?,?,?,?)";
                  
                  PreparedStatement ps = conn.prepareStatement(sql);
                  
                
                  int userrand=(int)(Math.random()*10000)+1;
                  int starand=(int)(Math.random()*4)+1;
                  int yrand=(int)(Math.random()*3)+2016;
                  int mrand=(int)(Math.random()*12)+1;
                  
                  int drand;
                  
                  if(mrand==1 || mrand==3 || mrand==5 || mrand==7 || mrand==8 || mrand==10 ||mrand==12)
                  drand=(int)(Math.random()*30)+1;
                  else if (mrand==2)
                  drand=(int)(Math.random()*27)+1;
                  else
                  drand=(int)(Math.random()*29)+1;  
                  
                  int hrand=(int)(Math.random()*24);
                  int minrand=(int)(Math.random()*60);
                  int srand=(int)(Math.random()*60);
                  
                  ps.setString(1,orderno[j]);
                  ps.setString(2,userName[userrand]);
                  
                  if(starand==1)
                  ps.setString(3,"Ordered");
                  
                  if(starand==2)
                      ps.setString(3,"Delivered");
                  
                  if(starand==3)
                      ps.setString(3,"Completed");
                  
                  if(starand==4)
                      ps.setString(3,"Cancelled");
                  
                  if(starand==1||starand==4)
                  {
                    ps.setString(4,yrand +"-"+ mrand +"-" + drand +" "+ hrand + ":" + minrand +":"+ srand);
                    ps.setString(5,null);
                  }
                  else
                  {
                     ps.setString(4,yrand +"-"+ mrand +"-" + drand +" "+ hrand + ":" + minrand +":"+ srand);
                     hrand=(int)(Math.random()*24);
                     minrand=(int)(Math.random()*60);
                     srand=(int)(Math.random()*60);
                     drand++;
                     
                     ps.setString(5,yrand +"-"+ mrand +"-" + drand +" "+ hrand + ":" + minrand +":"+ srand);
                  }
                  ps.executeUpdate();
                  
                  //System.out.println("Completed!");
           }
           
           
           for(j=50000;j<100000;j++)
           {
                  String sql="insert into `order` VALUES(?,?,?,?,?)";
                  
                  PreparedStatement ps = conn.prepareStatement(sql);
                  
                
                  int userrand=(int)(Math.random()*10000)+1;
                  int starand=(int)(Math.random()*4)+1;
                  int yrand=(int)(Math.random()*3)+2016;
                  int mrand=(int)(Math.random()*12)+1;
                  
                  int drand;
                  
                  if(mrand==1 || mrand==3 || mrand==5 || mrand==7 || mrand==8 || mrand==10 ||mrand==12)
                  drand=(int)(Math.random()*30)+1;
                  else if (mrand==2)
                  drand=(int)(Math.random()*27)+1;
                  else
                  drand=(int)(Math.random()*29)+1;  
                  
                  int hrand=(int)(Math.random()*4)+20;
                  int minrand=(int)(Math.random()*60);
                  int srand=(int)(Math.random()*60);
                  
                  ps.setString(1,orderno[j]);
                  ps.setString(2,userName[userrand]);
                  
                  if(starand==1)
                  ps.setString(3,"Ordered");
                  
                  if(starand==2)
                      ps.setString(3,"Delivered");
                  
                  if(starand==3)
                      ps.setString(3,"Completed");
                  
                  if(starand==4)
                      ps.setString(3,"Cancelled");
                  
                  if(starand==1||starand==4)
                  {
                    ps.setString(4,yrand +"-"+ mrand +"-" + drand +" "+ hrand + ":" + minrand +":"+ srand);
                    ps.setString(5,null);
                  }
                  else
                  {
                     ps.setString(4,yrand +"-"+ mrand +"-" + drand +" "+ hrand + ":" + minrand +":"+ srand);
                     hrand=(int)(Math.random()*24);
                     minrand=(int)(Math.random()*60);
                     srand=(int)(Math.random()*60);
                     drand++;
                     
                     ps.setString(5,yrand +"-"+ mrand +"-" + drand +" "+ hrand + ":" + minrand +":"+ srand);
                  }
                  ps.executeUpdate();
                  
                  //System.out.println("Completed!");
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