(ns ch4-seq-funcs.core
  (:use [clojure.repl])
  (:gen-class))

(defn unify-diet-data
  "[float] [float] -> {:human float :critter float}"
  [human critter]
  {:human human :critter critter})

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  "[int] -> [int]"
  [nums] 
  (map #(% nums) [sum count avg]))

