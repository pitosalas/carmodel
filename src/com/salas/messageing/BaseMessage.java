package com.salas.messageing;

import com.salas.*;
import com.salas.entity.*;

public class BaseMessage {
   public enum MessageTypes {
      INVALID, RETURNTOBASE, PATROLL, GOTOINTERSECT;
   }

   public BaseEntity messageSender;
   public BaseEntity messageReceiver;
   public MessageTypes type;

   Double dispatchTime;

   // Message payload is generic. used in different ways by different messages.
   public Vector2 vectorOne;
   public Intersection intersectionOne;

   public BaseMessage(MessageTypes typ, BaseEntity snd, BaseEntity rcv,
         Vector2 vector, Intersection intersection) {
      type = typ;
      messageSender = snd;
      messageReceiver = rcv;
      vectorOne = vector;
      intersectionOne = intersection;
   }
}
