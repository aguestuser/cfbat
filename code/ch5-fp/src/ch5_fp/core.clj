(ns ch5-fp.core
  (:gen-class))

(require '[clojure.string :as s])

(defn sum
  "[int] -> int
  recursively sums a list of ints"
  ([nums] (sum nums 0))
  ([nums acc]
   (if (empty? nums) 
     acc
     (sum (rest nums) (+ acc (first nums))))))

(defn sum-alt
  "[int] -> int
  recursively sums a list of ints without an accumulator"
  [nums]
  (if (empty? nums)
    0
    (+ (first nums) (sum (rest nums)))))

(defn sum-safe
  "[int] -> int
  recursively sums a list of ints with performance optimization (constant call stack consumption)"
  ([nums] (sum nums 0))
  ([nums acc]
   (if (empty? nums)
     acc
     (recur (rest vals) (+ acc (first nums))))))

(defn clean
  "str -> str
  trims a string and uppercases LOL isntances"
  [str]
  (s/replace (s/trim str) #"lol" "LOL"))

(def moo
  {:name  "lil my"
   :attributes {:cyncism 100
                :beauty 100
                :dancing 100
                :proximity 0}})

(def beauty-of (comp :beauty :attributes))

(defn wonder-factor
  "divides a person's beauty attribute by 2, increments it and rounds down to produce a wonder factor"
  [person]
  (int (inc (/ (beauty-of person) 2))))

(def wonder-factor-comp (comp int inc #(/ % 2) beauty-of))

(defn expensive-computation
  [txt]
  ;; (Thread/sleep 2000)
  (str txt " after some expensive computations!"))

(def memo-expensive-computation (memoize expensive-computation))
