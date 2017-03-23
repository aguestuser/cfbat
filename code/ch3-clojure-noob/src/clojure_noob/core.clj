(ns clojure-noob.core
  (:use [clojure.repl])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot!"))

(defn train
  []
  (println "Choo choo!"))

(defn always-foo
  []
  (if true
    (do "bar" 0 1 "foo")
    (do (println "printing bar") "bar")))

(defn add [x y] (+ x y))

(defn greet-all
  "Greet a variable number of friends"
  [& friends]
  (map #(str"Hi " % "!") friends))


(defn unlist 
  [[hd & tl]]
  (str "first: " hd " rest: " tl))

(defn fst-snd-rest
  [[fst snd & rst]]
  (str "first: " fst " second: " snd " rest: " rst))

(defn unmap
  [{:keys [lat lon]}]
  (str "lat: " lat " lon: " lon))

(defn inc-maker
  "create a custom incrementor"
  [increment]
  #(+ % increment))

(def inc3 (inc-maker 3))

(def human-consumption   [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})

(defn pairify [a b] [a b])
(defn zip [as bs] (map pairify as bs))
;; (defn zip [as bs] (map #(conj [%1] %2 as) bs))

(defn alert
  [severity]
  (str "WATCH OUT WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED"
         "TOTALLY FUCKED!")))

(defn x-chop
  "Describe the kind of chop you're inflicting on an adversary"
  ([name chop-type]
   (str "I " chop-type " chop " name "!"))
  ([name]
   (x-chop name "karate")))
