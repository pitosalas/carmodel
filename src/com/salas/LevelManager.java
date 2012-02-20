package com.salas;

import static com.google.common.base.Preconditions.*;

import java.util.*;

public class LevelManager {
   protected String current = null;
   protected HashMap<String, Level> gameLevels;

   public LevelManager() {
      gameLevels = new HashMap<String, Level>();
   }
   
   public void setCurrentLevel(String s) {
      current = s;
   }
   
   public Level getCurrentLevel() {
      checkNotNull(current);
      return gameLevels.get(current);
   }


}
