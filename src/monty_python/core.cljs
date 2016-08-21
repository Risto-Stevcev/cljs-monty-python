(ns ^:figwheel-always monty-python.core
  (:require [cljs.nodejs :as nodejs]
            [cljs.core.logic.pldb :refer [db] :refer-macros [db-rel with-db]])

  (:require-macros [cljs.core.logic :refer [run* fresh ==]]))

(nodejs/enable-util-print!)

(db-rel duck d)
(db-rel female p)
(db-rel same-weight d p)

(declare mp-facts witch? witch burns wooden floats- female duck same-weight)


;  BEDEMIR:  Quiet, quiet.  Quiet!  There are ways of telling whether
;      she is a witch.
(defn witch? []
  (with-db mp-facts
    (run* [q]
      (witch q)
      (== q 'girl))))

;  BEDEMIR:  Tell me, what do you do with witches?
;  VILLAGER #2:  Burn!
;  CROWD:  Burn, burn them up!
(defn witch [x]
  (fresh [z]
    (burns x)
    (female x)
    (== x z)))

;  BEDEMIR:  And what do you burn apart from witches?
;  VILLAGER #1:  More witches!
;  VILLAGER #2:  Wood!
;  BEDEMIR:  So, why do witches burn?
;      [pause]
;  VILLAGER #3:  B--... 'cause they're made of wood...?
;  BEDEMIR:  Good!
;  CROWD:  Oh yeah, yeah...
(defn burns [x] (wooden x))
 
;  BEDEMIR:  So, how do we tell whether she is made of wood?
;  VILLAGER #1:  Build a bridge out of her.
;  BEDEMIR:  Aah, but can you not also build bridges out of stone?
;  VILLAGER #2:  Oh, yeah.
;  BEDEMIR:  Does wood sink in water?
;  VILLAGER #1:  No, no.
;  VILLAGER #2:  It floats!  It floats!
(defn wooden [x] (floats- x))
 
;  BEDEMIR:  What also floats in water?
;  VILLAGER #1:  Bread!
;  VILLAGER #2:  Apples!
;  VILLAGER #3:  Very small rocks!
;  VILLAGER #1:  Cider!
;  VILLAGER #2:  Great gravy!
;  VILLAGER #1:  Cherries!
;  VILLAGER #2:  Mud!
;  VILLAGER #3:  Churches -- churches!
;  VILLAGER #2:  Lead -- lead!
;  ARTHUR:  A duck.
;  CROWD:  Oooh.
;  BEDEMIR:  Exactly!  So, logically...,
;  VILLAGER #1:  If... she.. weighs the same as a duck, she's made of wood.
;  BEDEMIR:  And therefore--?
;  VILLAGER #1:  A witch!
(defn floats- [x]
  (with-db mp-facts 
    (fresh [z]
      (same-weight 'duck x)
      (== x z))))
 
;  BEDEMIR:  We shall use my larger scales!
;      [yelling]
;  BEDEMIR:  Right, remove the supports!
;      [whop]
;      [creak]
;  CROWD:  A witch!  A witch!
;  WITCH:  It's a fair cop.
;  CROWD:  Burn her!  Burn!  [yelling]
(def mp-facts
  (db
    [female 'girl]
    [duck 'duck]
    [same-weight 'duck 'girl]))
 

(defn -main [] (println (witch?)))

(set! *main-cli-fn* -main)
(defn on-js-reload [] (-main))
