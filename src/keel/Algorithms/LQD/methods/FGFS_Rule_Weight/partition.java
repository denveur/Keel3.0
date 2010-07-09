package keel.Algorithms.LQD.methods.FGFS_Rule_Weight;

import java.util.Vector;

/**
 *
 * File: partitions.java
 *
 * Properties and functions of the partitions of the fuzzy number
 *
 * @author Written by Ana Palacios Jimenez (University of Oviedo) 25/006/2010
 * @version 1.0
 */

public class partition {

	Vector<fuzzy> content= new Vector<fuzzy>();
	public partition(){}
	public int size()
	{
		return content.size();
	}
	public fuzzy get(int i)
	{
		return content.get(i);
	}
	
	public float aproximation(int partition, fuzzy x)
	{
		if(partition==0)//left
		{
			
				if (x.getdere()<=content.get(partition).getcent() || x.getizd()<=content.get(partition).getcent()) 
					return 1;
				else if (x.getizd()>=content.get(partition).getdere()) 
					return 0;
				else
				{
					float valor= 1-(x.getizd()-content.get(partition).getcent())/(content.get(partition).getdere()-content.get(partition).getcent());
					return valor;			
				}
			
			
		 }
		
		else if (partition==content.size()-1)
		{
			
				if (x.getizd()>=content.get(partition).getcent() || x.getdere()>=content.get(partition).getcent()) 
					return 1;
				else if (x.getdere()<=content.get(partition).getizd())				
					return 0;
				else 
				{
					float valor= (x.getdere()-content.get(partition).getizd())/(content.get(partition).getcent()-content.get(partition).getizd());
					return valor;
				}
			
				
		}
		else 
		{
			
				
				if (x.getdere()<=content.get(partition).getizd())
					return 0;
				else if (x.getizd()>=content.get(partition).getdere()) 
					return 0; 
				else if(x.getizd()==content.get(partition).getcent() || x.getdere()==content.get(partition).getcent())
					return 1;
				else if(x.getizd()<content.get(partition).getcent() && x.getdere()>content.get(partition).getcent())
					return 1;
			
				else if(x.getdere()<content.get(partition).getcent())
				{
					float valor= (x.getdere()-content.get(partition).getizd())/(content.get(partition).getcent()-content.get(partition).getizd());
					return valor;
				}
				else 
				{
					float valor= 1-(x.getizd()-content.get(partition).getcent())/(content.get(partition).getdere()-content.get(partition).getcent());
					return valor;
				}
		
				
		}
		
		
		
		
	}
	
	public float membership(int partition,float x)
	{
	
		if(partition==0)
		{
			if (x<=content.get(partition).getcent()) 
				return 1;
			else if (x>=content.get(partition).getdere())
				return 0;
			else 
			{
				float valor= 1-(x-content.get(partition).getcent())/(content.get(partition).getdere()-content.get(partition).getcent());
				return valor;			
			}
		 }
		else if (partition==content.size()-1)
		{
			if (x>=content.get(partition).getcent()) 
				return 1;
			else if (x<=content.get(partition).getizd()) 				
				return 0;
			else 
			{
				float valor= (x-content.get(partition).getizd())/(content.get(partition).getcent()-content.get(partition).getizd());
				return valor;
			}
				
		}
		else 
		{
			
			if (x<=content.get(partition).getizd())
			{
				
				return 0;
			}
			else if (x>=content.get(partition).getdere()) 
				return 0; 
			else if(x<=content.get(partition).getcent())
			{
				float valor= (x-content.get(partition).getizd())/(content.get(partition).getcent()-content.get(partition).getizd());
				return valor;
			}
			else
			{
				float valor= 1-(x-content.get(partition).getcent())/(content.get(partition).getdere()-content.get(partition).getcent());
				return valor;
			}
				
		}
	}
		
	
	
    public partition(float min, float max, int n)
    {
    	 if (n<=0) return;
    	 fuzzy datos;
    	 float d=0;
    	 if(n==1)
    		 d=0;
    	 else
    		 d=(max-min)/(n-1);
    	
    	 float iz=min-d, ce=min, de=min+d;
    	 datos= new fuzzy();

    	 content.addElement(datos.borrosotrapizda(ce,de));
    	 for (int i=1;i<n-1;i++) 
    	 {
    		 iz+=d; ce+=d; de+=d;
    		 datos= new fuzzy();
    		 content.addElement(datos.borrosotriangular(iz,ce,de));
    	 }
    	 iz+=d; ce+=d; de+=d;
    	 datos= new fuzzy();

    	 content.addElement(datos.borrosotrapdcha(iz,ce));

    	
    }
    
    public void show()
    {
    	System.out.println("FUZZY PARTITIONS");
        for (int i=0; i<content.size();i++)
        {
        	if(i==0)
        		System.out.println(content.get(i).centro+"    "+content.get(i).dcha);
        	else if (i==content.size()-1)
        		System.out.println(content.get(i).izda+"    "+content.get(i).centro);
        	else
        		System.out.println(content.get(i).izda+"    "+content.get(i).centro+"    "+content.get(i).dcha);
        }
        
        //return 0;
    }

	

}
