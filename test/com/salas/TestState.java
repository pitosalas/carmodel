package com.salas;

import static org.junit.Assert.*;

import org.junit.*;

import com.salas.entity.*;
import com.salas.messageing.*;
import com.salas.messageing.BaseMessage.MessageTypes;
import com.salas.statemachine.*;
import com.salas.world.*;

public class TestState {
   public class Thing extends BaseEntity{

      public Thing(World ctx, EntityBody bdy, EntitySprite sprt) {
         super(ctx, bdy, sprt);
         // TODO Auto-generated constructor stub
      }
      int entered;
      int executed;
      int exited;
      MessageTypes messaged;
      @Override
      public void updateState() {
         // TODO Auto-generated method stub
         
      }
      @Override
      public String getToolTipText() {
         // TODO Auto-generated method stub
         return null;
      }
   }
   
   public class ThingMessage extends BaseMessage {

      ThingMessage(Thing snd, Thing rcv, MessageTypes type) {
         super(type, snd, rcv, null, null);
      }      
   }

   public class TestStateClazz extends State<Thing> {
      public void enter(Thing t) {
         t.entered++;
      }

      public void execute(Thing t) {
         t.executed++;
      }

      public void exit(Thing t) {
         t.exited++;
      }

//      @Override
//      public boolean onMessage(ThingMessage aMessage) {
//         athing.messaged = aMessage.type;
//         return true;
//      }

      @Override
      public boolean onMessage(BaseMessage m) {
         athing.messaged = m.type;
         return false;
      }

   }

   TestStateClazz astate;
   Thing athing;
   ThingMessage amessage;

   @Before
   public void setUp() throws Exception {
      astate = new TestStateClazz();
      athing = new Thing(null, null, null);
      amessage = new ThingMessage(athing, athing, MessageTypes.RETURNTOBASE);
   }

   @Test
   public void testEnter() {
      astate.enter(athing);
      assertEquals(1, athing.entered);
      assertEquals(0, athing.exited);
      assertEquals(0, athing.executed);
      assertEquals(null, athing.messaged);
   }

   @Test
   public void testExecute() {
      astate.execute(athing);
      assertEquals(0, athing.entered);
      assertEquals(0, athing.exited);
      assertEquals(1, athing.executed);
      assertEquals(null, athing.messaged);
   }

   @Test
   public void testExit() {
      astate.exit(athing);
      assertEquals(0, athing.entered);
      assertEquals(1, athing.exited);
      assertEquals(0, athing.executed);
      assertEquals(null, athing.messaged);
   }

   @Test
   public void testOnMessage() {
      astate.onMessage(amessage);
      assertEquals(0, athing.entered);
      assertEquals(0, athing.exited);
      assertEquals(0, athing.executed);
      assertEquals(MessageTypes.RETURNTOBASE, athing.messaged);
      
   }

}
