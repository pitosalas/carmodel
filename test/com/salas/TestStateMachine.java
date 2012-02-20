package com.salas;

import static org.junit.Assert.*;

import org.junit.*;

import com.salas.entity.*;
import com.salas.messageing.*;
import com.salas.messageing.BaseMessage.*;
import com.salas.statemachine.*;
import com.salas.world.*;

public class TestStateMachine {
   public class Thing extends BaseEntity {
      public Thing(World ctx, EntityBody bdy, EntitySprite sprt) {
         super(ctx, bdy, sprt);
      }
      int entered;
      int executed;
      int exited;
      MessageTypes lastMessage;;
      StateMachine<Thing> mymachine;

      
      @Override
      public void updateState() {
         // TODO Auto-generated method stub
         
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

      @Override
      public boolean onMessage(BaseMessage m) {
         return false;
      }

   }

   public class ThingMessage extends BaseMessage {
      ThingMessage(Thing snd, Thing rcv, MessageTypes t) {
         super(t, snd, rcv, null, null);
      }
   }

   Thing athing;
   StateMachine<Thing> machine;
   TestStateClazz stateloc, stateglob;
   ThingMessage amessage;

   @Before
   public void setUp() throws Exception {
      athing = new Thing(null, null, null);
      machine = new StateMachine<Thing>(athing);
      stateloc = new TestStateClazz() {
         @Override
         public void execute(Thing t) {
            super.execute(t);
            t.executed += 400;
         }
         public void exit(Thing t) {
            super.exit(t);
            t.executed -= 10;
         }
      };

      stateglob = new TestStateClazz() {
         @Override
         public void execute(Thing t) {
            super.execute(t);
            t.executed += 300;
         }

         @Override
         public boolean onMessage(BaseMessage aMessage) {
            athing.lastMessage = aMessage.type;
            return true;
         }

         public void exit(Thing t) {
            super.exit(t);
            t.executed -= 20;
         }
      };
      amessage = new ThingMessage(athing, athing, MessageTypes.INVALID);
   }

   @Test
   public void testStateMachine() {
      assertEquals(athing, machine.getOwner());
   }

   @Test
   public void testUpdate() {
      machine.setGlobalState(stateglob);
      machine.update();
      assertEquals(301, athing.executed);

   }

   @Test
   public void testUpdate2() {
      machine.setCurrentState(stateloc);
      machine.update();
      assertEquals(401, athing.executed);

   }

   @Test
   public void testUpdate3() {
      machine.setGlobalState(stateglob);
      machine.setCurrentState(stateloc);
      machine.update();
      assertEquals(702, athing.executed);

   }

   @Test
   public void testChangeState() {
      machine.setCurrentState(stateloc);
      machine.changeState(stateglob);
      assertEquals(stateloc, machine.previousState());
      assertEquals(-10, athing.executed);
   }

   @Test
   public void testHandleMessage() {
      machine.setCurrentState(stateglob);
      machine.handleMessage(amessage);
      assertEquals(MessageTypes.INVALID, athing.lastMessage);

   }

   @Test
   public void testHandleMessage1() {
      machine.setCurrentState(stateloc);
      machine.handleMessage(amessage);
      assertEquals(null, athing.lastMessage);

   }

}
