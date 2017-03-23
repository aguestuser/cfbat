(ns ch4-seq-funcs.core-test
  (:use [clojure.repl])
  (:require [clojure.test :refer :all]
            [ch4-seq-funcs.core :refer :all]))

(deftest map-over-many-lists

  (testing "maps a function over the ith element of two lists"
    (is (= 
         (map str ["a" "b" "c"] ["A" "B" "C"])
         '("aA" "bB" "cC"))))

  (testing "ignores an element that does not have a corresponding element in another list"
    (is (= 
         (map str ["a" "b" "c"] ["A" "B" "C" "D"])
         '("aA" "bB" "cC"))))

  (testing "passes the ith member of n sequences w/ j members as the nth argument to j function calls"
    (is (= 
         (map #(+ %1 %2) [1 2 3] [4 5 6])
         '(5 7 9)))))

(deftest unify-diet-data-test
  (testing "it contructs a map of human and critter data")
  (is (= 
       (map unify-diet-data [1 2 3] [3 2 1])
       '({:human 1 :critter 3}
         {:human 2 :critter 2}
         {:human 3 :critter 1}))))

(deftest stats-test
  (testing "it maps a list of functions over a list of numbers returning a list of stats"
    (is (= 
         (stats [1 2 3]) 
         [6 3 2]))))

(deftest map-retrieval
  (testing "retrieves list of elements matching a key value from a collection"
    (is (=
         (map :bar [{:foo 1 :bar "baz"}
                    {:foo 2 :bar "bax"}
                    {:foo 2 :bar "bam"}])
         '("baz" "bax" "bam")))))

(deftest reduce-over-map
  (testing "maps over keys and values of a map"
    (is (=
         (reduce (fn [acc [k v]] (assoc acc k (inc v)))
                 {}
                 {:foo 1 :bar 2 :baz 3})
         {:foo 2 :bar 3 :baz 4}))))

(deftest take-test
  (testing "takes first n elements from a sequence"
    (is (= (take 3 [1 2 3 4 5])
           '(1 2 3)))))

(deftest drops-test
  (testing "dropss first n elements from a sequence"
    (is (= (drop 3 [1 2 3 4 5])
           '(4 5)))))

(def some-collection [{:foo 3 :bar "blah"}
                      {:foo 4 :bar "blah"}
                      {:foo 2 :bar "blah"}])

(deftest take-while-test
  (testing "takes elements that satisfy a predicate until predicate is not satisfied"
    (is (= 
         (take-while #(> (:foo %) 2) some-collection)
         [{:foo 3 :bar "blah"}
          {:foo 4 :bar "blah"}]))))

(deftest drop-while-test
  (testing "drops elements that satisfy a predicate until predicate not satisfied"
    (is (=
         (drop-while #(< (:foo %) 4) some-collection)
         [{:foo 4 :bar "blah"}
          {:foo 2 :bar "blah"}]))))

(deftest take-and-drop-while-test
  (testing "drops entries matching a predicate then takes entries matching another predicate"
    (is (=
         (take-while #(> (:foo %) 3) (drop-while #(< (:foo %) 4) some-collection))
         [{:foo 4 :bar "blah"}]))))

(deftest filter-test
  "filters elements from a sequence that satisfy a predicate"
  (is (= (filter #(< (:foo %) 4) some-collection)
         [{:foo 3 :bar "blah"}
          {:foo 2 :bar "blah"}])))

(deftest some-test
  "returns true if element in sequence matches predicate, nil if not"
  (is (true? (some #(= (:foo %) 4) some-collection)))
  (is (nil? (some #(= (:foo %) 5) some-collection))))

(deftest some-and-test
  "returns the first element of a sequence matching a predicate"
  (is (= (some #(and (= (:foo %) 4) %) some-collection)
         {:foo 4 :bar "blah"})))

(deftest sort-test
  "sorts a sequence of numbers"
  (is (= (sort [3 2 1]) '(1 2 3))))

(deftest sort-by-test
  "sorts a sequence by the results of applying a key function to its elements"
  (is (= (sort-by count ["aaa" "b" "CC"]) '("b" "CC" "aaa"))))

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(deftest stream-test
  "generates a lazy infinite stream of even numbers"
  (is (= (take 4 (even-numbers)) '(0 2 4 6))))
