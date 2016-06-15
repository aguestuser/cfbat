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
  [& names]
  (map #(str "Hello " % "!") names))

(defn unlist 
  [[hd & tl]]
  (str "first: " hd " rest: " tl))

(defn unmap
  [{:keys [lat lon]}]
  (str "lat: " lat " lon: " lon))

(defn inc-maker
  "create a custom incrementor"
  [increment]
  #(+ % increment))

(def inc3 (inc-maker 3))
