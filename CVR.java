package assignment4;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import java.util.LinkedList;


public class CVR {
	
	static class Node{
		
		public String key;
		public values record;
		public Node next;
		public Node previous;

		
	    public Node()
		{
			key=null;
			record= null;
			next = null;
			previous = null;
		}
	    public Node(String vin)
		{
			key=vin;
			record= null;
			next = null;
			previous = null;
		}
	    
	    public Node(String vin,values r)
		{
			key=vin;
			record= r;
			next = null;
			previous = null;
		}
	    
		public Node(String vin, values r, Node xt, Node pr)
		{
			key = vin;	
			record = r;
			next = xt;
			previous = pr;
		}
		
		public void setNext(Node xt)
		{
			next = xt;
		}
		
		public void setPrevious(Node pr)
		{
			previous = pr;
		}
		
		public 	String getKey()
		{
			return key;	
		}
		
		
		public Node getNext()
		{
			return next;
		}
		
		public Node getPrevious()
		{
			return previous;
		}

				
	}
	
	static class values {
		
		ArrayList<String> values;
		
		public values() {
			values=new ArrayList<String>();
		}
		
		public values(String record){
			values=new ArrayList<String>();			
			values.add(record);
		}
		
		public void showRecord() {
				System.out.println(values);
			
		}
		
	}
	
	public static ArrayList<String> allKeysFromSequence(LinkedList<Node> CVR) {
		ArrayList<String> keys=new ArrayList<String>();
		for(Node node : CVR) {
			keys.add(node.key);
		}
		Collections.sort(keys);
		for (String object : keys) {
			System.out.println(object);
		}
		return keys;
		
	}
	
    public static void addToSequence(LinkedList<Node> CVR,String key) {
		Node temp=generateNode(key);
		if(key.compareTo(CVR.getFirst().key)<0)
        {
            CVR.addFirst(temp);
        }else {
            boolean insertFlag = false;
            for (Node e : CVR)
            {
                if(key.compareTo(CVR.getFirst().key)<0)
                {
                    int index = CVR.indexOf(e);
                    CVR.add(index, temp);
                    insertFlag = true;
                    break;
                }
            }
            if(!insertFlag)
            {
                CVR.addLast(temp);
            }
        }

    }
    
    public static void removeFromSequence(LinkedList<Node> CVR,String key) {
    	for (int i=0; i<CVR.size(); i++) {   
			if (CVR.get(i).key.equals(key)) {
				CVR.remove(CVR.get(i));
			}
		}    	
    }
    
    public static values getValuesFromSequence(LinkedList<Node> CVR,String key) {
    	values record=new values();
		for(Node node : CVR) {
			if (node.key.equals(key)) {
				record=node.record;
				record.showRecord();
			}
		} 
		return record;
    }
    
    public static String nextKeyFromSequence(LinkedList<Node> CVR,String key) {
    	String nextkey=null;
		for(Node node : CVR) {
			if (node.key.equals(key)) {
				nextkey=CVR.get(CVR.indexOf(node)+1).key;
			}
		} 
		System.out.println(nextkey);
		return nextkey;
    }
    
    public static String prevKeyFromSequence(LinkedList<Node> CVR,String key) {
    	String prevkey=null;
		for(Node node : CVR) {
			if (node.key.equals(key)) {
				prevkey=CVR.get(CVR.indexOf(node)-1).key;
			}
		} 
		System.out.println(prevkey);
		return prevkey;
    }
    
    public static values prevAccidsFromSequence(LinkedList<Node> CVR,String key) {
    	values record=null;
		for(Node node : CVR) {
			if (node.key.equals(key)) {
				record=node.record;
			}
		} 
		if(record!=null) {
			for(int i = record.values.size()-1; i>=0;i--){
			System.out.println(record.values.get(i));
			}
		}
		else System.out.println("No record according to this key!");
		return record;
    }
    
    
	static class tree {
		private Node root;
				
		public tree(){
			root=null;
		}
		public tree(Node node){
            root=node;
		}   
	}	
		public static void addToTree(Node root,String key) {
			Node node=generateNode(key);
			if (key.compareTo(root.key)>0) {
				if (root.next!=null)
				addToTree(root.next,key);
				else 
				{root.next=node;}
			}
			else {
				if (root.previous!=null) {
					addToTree(root.previous,key);
				}
				else
				{root.previous=node;}
				
			}
		}
		
		public static ArrayList<String> allKeysFromTree(Node root, ArrayList<String> allKeys) {
			if (root!=null) {
				
				allKeysFromTree(root.previous,allKeys);			
				System.out.println(root.key);
				allKeys.add(root.key);
			    allKeysFromTree(root.next,allKeys);
			}
			return allKeys;
		}

		public static void removeFromTree(Node root,String key) {
			
			Node p=null;
			while(root!=null) {
				if(key.compareTo(root.key)>0) {
					p=root;
					root=root.next;
				}
				else if(key.compareTo(root.key)<0)
				{
					p=root;
					root=root.previous;
				}
				else {
					if(root.next==null&&root.previous==null) {
						if(p==null)
							root=null;
						else
						{
							if(p.previous==root)
								p.previous=null;
							else if(p.next==root)
								p.next=null;
						}

					}
					
					else if (root.next==null&&root.previous!=null) {
						if(p==null)
							root=root.previous;
						else
						{
							 if(p.previous==root)
								 p.previous=root.previous;
							 else if(p.next==root)
								 p.next=root.previous;
						}

					}
					
					else if (root.next!=null&&root.previous==null) {
						if(p==null)
							root=root.next;
						else
						{
							 if(p.previous==root)
								 p.previous=root.next;
							 else if(p.next==root)
								 p.next=root.next;
						}

					}
					
					else {
						Node rMin=root.next; 
						Node rMinP=null;
						while(rMin!=null)
						{
							rMinP=rMin;
							rMin=rMin.previous;
						}
						 Node temp=root;
						 root=rMin;
						 rMin=temp;

						 if(rMinP.previous==rMin)
							 rMinP.previous=rMin.next;
						 else if(rMinP.next==rMin)
							 rMinP.next=rMin.next;
						
					}
					
				}
				break;
			}
			
		}
		
		public static values getValuesFromTree(Node root,String key) {
			
			while(root!=null) {
				if(key.compareTo(root.key)>0) {
					root=root.next;
				}
				else if(key.compareTo(root.key)<0)
				{
					root=root.previous;
				}
				else {
					break;}				
				}
            root.record.showRecord();
			return root.record;
		}
		
		public static String nextKeyFromTree(ArrayList<String> allKeys,String key) {
			String temp=null;
			for(int i =0;i<allKeys.size()-1;i++) {
				if (allKeys.get(i).equals(key)) 
				{   
					temp=allKeys.get(i+1);
					System.out.println(temp);
				}			
			}
			return temp;
			/*while(root!=null) {
				if(key.compareTo(root.key)>0) {
					root=root.next;
				}
				else if(key.compareTo(root.key)<0)
				{
					root=root.previous;
				}
				else {
					break;
					}				
				}
			System.out.println(root.next.key);
			return root.next.key;
			*/
		}
		
		public static String prevKey(ArrayList<String> allKeys,String key) {
			
			String temp=null;
			for(int i =1;i<allKeys.size();i++) {
				if (allKeys.get(i).equals(key)) 
				{   
					temp=allKeys.get(i-1);
					System.out.println(temp);
				}			
			}
			return temp;
			/*while(root!=null) {
				if(key.compareTo(root.key)>0) {
					root=root.next;
				}
				else if(key.compareTo(root.key)<0)
				{
					root=root.previous;
				}
				else {
					break;
					}				
				}
			System.out.println(root.previous.key);
			return root.previous.key;
			*/
		}
			
		public static values prevAccids(Node root,String key) {
			while(root!=null) {
				if(key.compareTo(root.key)>0) {
					root=root.next;
				}
				else if(key.compareTo(root.key)<0)
				{
					root=root.previous;
				}
				else {
					break;
					}				
				}
			if(root.record.values!=null) {
				for(int i = root.record.values.size()-1; i>=0;i--){
				System.out.println(root.record.values.get(i));
				}
			}
			else System.out.println("No record according to this key!");
		
			return root.record;
			
		}	
		
	
	public static void setThreshold(int Threshold,int numberOfCVR) {
		if (numberOfCVR>Threshold) {
			System.out.println("Number of CVR is greater than Threshold, should implemented as a binary tree");
		}
		else System.out.println("Number of CVR is small or equal than Threshold, should implemented as a sequence");
	}
	
	public static int setKeyLength(int Length) {
		return Length;
	}
	
	public static String[] generate(int n,int length) {
		String[] VIN=new String[n];

		Random random = new Random();
        for (int i = 0; i < n; i++) {
    		String val = "";
        	for (int j = 0;j<length;j++) {

        		String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";

        		if ("char".equalsIgnoreCase(charOrNum)) {

        			int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
        			val += (char) (choice + random.nextInt(26));
        		} else if ("num".equalsIgnoreCase(charOrNum)) { 
        			val += String.valueOf(random.nextInt(10));
        		}
        	}
    		VIN[i]= val.toUpperCase();
        	System.out.println(VIN[i]);
        }
        return VIN;
    }

	public static Node generateNode(String key) {
		Node node=new Node(key);
		return node;
	}
	
	public static Node generateNode(String VIN,values record) {
		Node node=new Node(VIN,record);
		return node;
	}
	
	public static values generateValues(String record) {
		values value=new values(record);
		return value;
	}
	public static void add(LinkedList<Node> CVR,String key, String record) {
		for (Node i : CVR) {
			if(i.key.equals(key)) {
				if (i.record==null) {
				i.record=generateValues(record);
				}
				else i.record.values.add(record);				
			}
		}
	}
	
	public static void add(Node root,String key, String record) {
		while(root!=null) {
			if(key.compareTo(root.key)>0) {
				root=root.next;
			}
			else if(key.compareTo(root.key)<0)
			{
				root=root.previous;
			}
			else {
				if (root.record==null) 
				{
					root.record=generateValues(record);
				}
				else 
					root.record.values.add(record);
				break;
				}
			}				
			

	}
	
	
    public static void main(String[] args) {
    	
    	Scanner sc = new Scanner(System.in);
        System.out.println("pls input a number to set the Threshold.");        
        int Threshold=sc.nextInt();
        System.out.println("pls input the number of CVR you want to create."); 
        int numberOfCVR=sc.nextInt();     
        setThreshold(Threshold,numberOfCVR);
        System.out.println("pls input the length of VIN.");
        int lengthOfCVR=sc.nextInt();   
        System.out.println("VINs are:");
        String[] VIN=generate(numberOfCVR,lengthOfCVR);
      
        if (numberOfCVR>Threshold) {
            Node root=new Node(VIN[0]);
        	for(int i=1;i<VIN.length;i++) {
        		addToTree(root,VIN[i]);       
        	}
        	System.out.println();        	
        	System.out.println("the CVR is created as a tree.It's root is "+root);
        	System.out.println("All keys of CVR :"); 
        	ArrayList<String> allKeys=new ArrayList<String>();
        	allKeysFromTree(root,allKeys);
        	System.out.println(); 
        	
        	System.out.println("Will add value in the first key:"+allKeys.get(0));    
        	System.out.println("add [accident happened in 2019.4]"); 
        	add(root,allKeys.get(0),"accident happened in 2019.4");
        	System.out.println("add [accident happened in 2020.1]"); 
        	add(root,allKeys.get(0),"accident happened in 2020.1");
        	System.out.println();
        	
        	
        	System.out.println("Will get values in the first key:"+allKeys.get(0));  
        	getValuesFromTree(root,allKeys.get(0));
        	System.out.println();       	
        	
        	
        	System.out.println("Will get next key of the second key:"+allKeys.get(1));         	
        	nextKeyFromTree(allKeys,allKeys.get(1));
        	System.out.println(); 
        	
        	
        	System.out.println("Will get previous key of the third key:"+allKeys.get(2)); 
        	prevKey(allKeys,allKeys.get(2));
        	System.out.println();        	
        	
        	
        	System.out.println("Will show previous Accids of the first key:"+allKeys.get(0)); 
        	prevAccids(root,allKeys.get(0));
        	System.out.println();          	
        	
        	
        	System.out.println("Will remove the second key:"+allKeys.get(1));        	
        	removeFromTree(root,allKeys.get(1));
        	System.out.println("All keys after remove:");
        	allKeysFromTree(root,allKeys);
        }
        else {
        	LinkedList<Node> CVR=new LinkedList<Node>();
        	Arrays.sort(VIN);
        	for(int i=0;i<VIN.length;i++) {
        		Node node=new Node(VIN[i]);
        		CVR.add(node);
        	}
        	System.out.println();
        	System.out.println("the CVR is created as a sequence.");
        	System.out.println("All keys of CVR :");
        	System.out.println();       	
        	allKeysFromSequence(CVR);
        	System.out.println(); 
        	
           	System.out.println("Will add value in the first key:"+CVR.get(0).key);    
        	System.out.println("add [accident happened in 2019.4]"); 
        	add(CVR,CVR.get(0).key,"accident happened in 2019.4");
        	System.out.println("add [accident happened in 2020.1]"); 
        	add(CVR,CVR.get(0).key,"accident happened in 2020.1");
        	System.out.println();
        	
        	
        	System.out.println("Will get values in the first key:"+CVR.get(0).key); 
        	getValuesFromSequence(CVR,CVR.get(0).key);
        	System.out.println();       	
        	
        	
        	System.out.println("Will get next key of the second key:"+CVR.get(1).key);      
        	nextKeyFromSequence(CVR,CVR.get(1).key);
        	System.out.println(); 
        	
        	
        	System.out.println("Will get previous key of the third key:"+CVR.get(2).key); 
        	prevKeyFromSequence(CVR,CVR.get(2).key);
        	System.out.println();        	
        	
        	
        	System.out.println("Will show previous Accids of the first key:"+CVR.get(0).key); 
        	prevAccidsFromSequence(CVR,CVR.get(0).key);

        	System.out.println();          	
        	
        	
        	System.out.println("Will remove the second key:"+CVR.get(1).key);   
        	removeFromSequence(CVR,CVR.get(1).key);

        	System.out.println("All keys after remove:");
        	allKeysFromSequence(CVR);      	
        }
        sc.close();
     }
}
