package com.salas;
import com.google.gson.Gson;

public class TestGson {
	
	class Actor {
		Actor(String nm, int x, int y, String tp, String dr) {
			xPos = x;
			yPos = y;
			type = tp;
			dir = dr;
		}
		int xPos;
		int yPos;
		String type;
		String dir;
		String name;
	}
	class XMap {
		Actor[] actors = {};
		String[] roads = {"hello", "world"};
		String [][] xsect = { { "r1", "r2" } , { "c1", "c2" }};
		XMap() {
			actors = new Actor[2];
			actors[0] = new Actor("a", 1, 10, "car", "N");
			actors[1] = new Actor("b", 1, 10, "car", "N");
		}
	}
	

	/**
	 * @param args
	 */
	
	private static void  p(String s) {
		System.out.println(s);
	}
	
	
	
	public static void main(String[] args) {
		TestGson a = new TestGson();
		a.test1();
	}
	
	public void test1() {
		Gson gson = new Gson();
		p (gson.toJson(1));  //          ==> prints 1
		p (gson.toJson("abcd")); //      ==> prints "abcd"
		p (gson.toJson(new Long(10))); //==> prints 10
		int[] values = { 1, 2, 3 };
		int [][] val2 = { { 1,2,3} , {5,6,7}};
		p (gson.toJson(val2));      //  ==> prints [1]
		
		
		XMap obj = new XMap();
		String json = gson.toJson(obj);
		p (json);



	}

}
