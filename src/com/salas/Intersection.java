package com.salas;

public class Intersection extends BaseNode {
	private Vector2 pos = new Vector2();
	
	Intersection(int index, String  nme, float x, float y) {
	   super(index, nme);
		pos.x = x;
		pos.y = y;
      if (nme == null) {
         this.setName("intersection-"+index);
      }
	}
	
	Intersection(int index, String nme) {
      super(index, nme);
	   if (nme == null) {
	      this.setName("intersection-"+index);
	   }
	}
	
   void setPos(float x, float y) {
	   pos.x = x;
	   pos.y = y;
	}

   public Vector2 position() {
      return pos;
   }

}
