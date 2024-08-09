import java.sql.*;
import java.sql.SQLException;

//import java.text.DecimalFormat;  
import java.math.BigDecimal;


public class onsale
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
       
       Connection conn=null;
       
       Statement stmt1=null;
       Statement[] typestmt=new Statement[12];
       
       for(int i=0;i<12;i++)
           typestmt[i] = null;
       
       ResultSet rs1=null;

       ResultSet[] typers=new ResultSet[12];//customer_username
       
       for(int i=0;i<12;i++)
    	   typers[i] = null;
     
       try
       {
           Class.forName(driver);
           conn = DriverManager.getConnection(url, username, password);
           
           
           stmt1 = conn.createStatement();
           
           int i;
           
           
           for(i=0;i<12;i++)
           typestmt[i] = conn.createStatement();
           
           String[] typesql = new String[12];  
           
           String sql1="select storeno,type from store;";
           typesql[0]="select productno from product where category_third_name=\"���յ���\";";
           typesql[1]="select productno from product where category_third_name=\"�������\";";
           typesql[2]="select productno from product where category_third_name=\"��Ϊ\";";
           typesql[3]="select productno from product where category_third_name=\"С��\";";
           typesql[4]="select productno from product where category_third_name=\"����\";";
           typesql[5]="select productno from product where category_third_name=\"�㽶\";";
           typesql[6]="select productno from product where category_third_name=\"��Ƭ\";";
           typesql[7]="select productno from product where category_third_name=\"����\";";
           typesql[8]="select productno from product where category_third_name=\"��T��\";";
           typesql[9]="select productno from product where category_third_name=\"�����п�\";";
           typesql[10]="select productno from product where category_third_name=\"ŮT��\";";
           typesql[11]="select productno from product where category_third_name=\"Ů��\";";
           
           String[] storeno=new String[11000];
           String[] storetype=new String[11000];
           String[][] productno=new String[12][11000];
           
           rs1=stmt1.executeQuery(sql1);
           
           for(i=0;i<12;i++)
           typers[i]=typestmt[i].executeQuery(typesql[i]);
           
           i=0;
           
           while(rs1.next())
           {
        	   storeno[i]=rs1.getString("storeno");
        	   storetype[i]=rs1.getString("type");
        	   
                   i++;
           }
           
           int[] k ={0,0,0,0,0,0,0,0,0,0,0,0};
           
           	for(i=0;i<12;i++)
           	{
           		
           	 while(typers[i].next())
             {
          	   productno[i][k[i]]=typers[i].getString("productno");
               k[i]++;
             }
           	 
           	}
           
           String s,p,t;
           
           int j,m;
           
           for(j=0;j<4000;j++)
           {
                  
                  
                   s= storeno[j];
                   t=storetype[j];
                   
                   int r;
                   double price;
                	  
                  for(m=0;m<25;m++)
                  {
                	  
                	 String sql="insert into onsale VALUES(?,?,?,?,?)";
                      
                     PreparedStatement ps = conn.prepareStatement(sql);
                 
                     if(t.equals("������Ʒ��"))
                     {
                    	 r=(int)(Math.random()*4);
                         p= productno[r][5*m+j%25];
                         price=mul(((int)(Math.random()*700000)+300000),0.01);
                         
                         int stock=(int)(Math.random()*1000)+1000;

                         ps.setString(1,s);
                         ps.setString(2,p);
                         ps.setDouble(3,price);
                         ps.setInt(4,stock);
                         ps.setInt(5,1);
                         
                         
                         ps.executeUpdate();
                         
                     }
                     if(t.equals("ʳƷ��"))
                     {
                    	 r=(int)(Math.random()*4)+4;
                         p= productno[r][5*m+j%25];
                         price=mul(((int)(Math.random()*9000)+1000),0.01);
                         
                         
                         int stock=(int)(Math.random()*1000)+1000;

                         ps.setString(1,s);
                         ps.setString(2,p);
                         ps.setDouble(3,price);
                         ps.setInt(4,stock);
                         ps.setInt(5,1);
                         
                         ps.executeUpdate();
                     }
                     if(t.equals("�·���"))
                     {
                    	 r=(int)(Math.random()*4)+8;
                         p= productno[r][5*m+j%25];
                         price=mul(((int)(Math.random()*80000)+20000),0.01);
                         
                         
                         int stock=(int)(Math.random()*1000)+1000;

                         ps.setString(1,s);
                         ps.setString(2,p);
                         ps.setDouble(3,price);
                         ps.setInt(4,stock);
                         ps.setInt(5,1);
                         
                         ps.executeUpdate();
                     }
                     
                  
                
                  
                  
                  
                  
                  // System.out.println("Completed!");
                  
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
                { //�رս��������
	try 
               {
	          rs1.close();
	          for(int i=0;i<12;i++)
		    	   typers[i].close();
               } 
               catch (SQLException e) 
              {
	    e.printStackTrace();
	}
	}			
               if (stmt1!= null) 
               { // �ر����ݿ��������
	try {
	       stmt1.close();
	       
	       for(int i=0;i<12;i++)
	    	 typestmt[i].close();
	       
	} 
               catch (SQLException e) 
               {
	        e.printStackTrace();
	}
	}
             	if (conn != null) { // �ر����ݿ����Ӷ���
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