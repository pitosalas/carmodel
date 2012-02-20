package com.salas.messageing;

import static com.google.common.base.Preconditions.*;

public class MessageDispatcher {
  
   public void dispatchMessage(double delay, BaseMessage message) {
      checkArgument(delay <= 0, "Message sending delay NYI");
      if (delay <= 0) {
         sendMessage(message);
      }  
   }
   
   private void sendMessage(BaseMessage mess) {
      boolean  result = mess.messageReceiver.handleMessage(mess);
//      checkArgument(result, "Message was not processed %s", mess.type);
   }

   public void clear() {
      
   }
}
