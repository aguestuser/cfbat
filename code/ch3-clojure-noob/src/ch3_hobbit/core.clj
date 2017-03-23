(ns ch3-hobbit.core
  (:use [clojure.repl])
  (:gen-class))

(def body-parts [{:name "head" :size 3}
                 {:name "left-eye" :size 1}
                 {:name "left-ear" :size 1}
                 {:name "mouth" :size 1}
                 {:name "nose" :size 1}
                 {:name "left-shoulder" :size 3}
                 {:name "left-upper-arm" :size 3}
                 {:name "chest" :size 10}
                 {:name "back" :size 10}
                 {:name "left-forearm" :size 3}
                 {:name "abdomen" :size 6}
                 {:name "left-kidney" :size 1}
                 {:name "left-hand" :size 2}
                 {:name "left-knee" :size 2}
                 {:name "left-thigh" :size 4}
                 {:name "left-lower-leg" :size 3}
                 {:name "left-lower-leg" :size 3}
                 {:name "left-achilles" :size 1}
                 {:name "left-foot" :size 2}])

(defn matching-part
  "{:name string :size int} -> {:name string :size int}
  parses a righthand part from a lefthand part"
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize
  "[{:name string :size int} -> {:name string :size int}]
  adds righthand parts to a collection of lefthand parts"
  [parts]
  (loop [_parts parts acc []]
    (if (empty? _parts)
      acc
      (let [[hd & tl] _parts]
        (recur tl (into acc (set [hd (matching-part hd)])))))))

(defn og-symmetrize
  "[{:name string :size int}] -> [{:name string :size int}]
  adds righthand parts to a collection of lefthand parts"
  ([parts]
   (og-symmetrize parts []))
  ([parts, acc]
   (if (empty? parts)
     acc
     (let [[hd & tl] parts]
       (og-symmetrize tl (into acc (set [hd (matching-part hd)])))))))

(defn reduce-symmetrize
  "[{:name string :size int}] -> [{:name string :size int}]
  adds righthand parts to a collection of lefthand parts"
  [parts]
  (reduce
   (fn [acc part] (into acc (set [part (matching-part part)])))
   []
   parts))

(defn clvr-reduce-symmetrize
  "[{:name string :size int}] -> [{:name string :size int}]
  adds righthand parts to a collection of lefthand parts"
  [parts]
  (reduce  #(into %1 (set [%2 (matching-part %2)])) [] parts))

