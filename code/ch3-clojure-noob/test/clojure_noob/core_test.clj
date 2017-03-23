(ns clojure-noob.core-test
  (:use [clojure.repl])
  (:require [clojure.test :refer :all]
            [clojure-noob.core :refer :all]))

(deftest fake-test(testing(is (= "foo" "foo"))))

(deftest rest-args
  (testing "accepts a variable number of arguments to a func"
    (is (=
         (greet-all "alice" "bob")
         ["Hi alice!" "Hi bob!"]))))

(deftest destructring
  (testing "destructres the first element of a list"
    (is (=
         (unlist [1 2 3 4])
         "first: 1 rest: (2 3 4)"))))

(deftest fst-snd-rest-test
  (testing "destructres the first two elements of a list"
    (is (=
         (fst-snd-rest [1 2 3 4])
         "first: 1 second: 2 rest: (3 4)"))))

(deftest unmap-test
  (testing "destructures a map"
    (is (=
         (unmap {:lat 1, :lon 2})
         "lat: 1 lon: 2"))))

(deftest inc-maker-test
  (testing "curries an increment to addition"
    (is (= ( (inc-maker 3) 2) 5))))

(deftest unify-diet-data-test
  (testing "combines human and critter consumption"
    (is (=
         (unify-diet-data [1 2 3] ['a 'b 'c])
         {:human [1 2 3], :critter ['a 'b 'c]}))))

(deftest pairify-test
  (testing "wraps two inputs in a pair"
    (is (=
         (pairify 1 'a)
         [1 'a]))))

(deftest zip-test
  (testing "zips two lists into a list of pairs"
    (is (=
         (zip [1 2 3] ['a 'b 'c])
         [[1 'a] [2 'b] [3 'c]]))))

(deftest map-getting
  (testing "retrieves deeply nested values from a map"
    (is (=
         (get-in {:foo {:bar "baz"}} [:foo :bar])
         "baz")))
  (testing "retrieves deeply nested values from a map"
    (is (=
         (get-in {:foo {:bar "baz"}} [:foo :baz] "sorry")
         "sorry")))
  (testing "retrieves values directly"
    (is (=
         ({:foo {:bar "baz"}} :foo)
         {:bar "baz"})))
  (testing "retrieves values directly with value passed first"
    (is (=
         (:foo {:foo {:bar "baz"}})
         {:bar "baz"})))
  (testing "retrieves values directly with value passed first and default value"
    (is (=
         (:foobar {:foo {:bar "baz"}} "sorry")
         "sorry"))))

(deftest list-getting
  (testing "retrieves element from a list"
    (is (= (get ['a 'b 'c] 0) 'a)))
  (testing "retrieves element from a list with default value"
    (is (= (get ['a 'b 'c] 4 "sorry") "sorry")))
  (testing "retrieves deeply nested element from a list"
    (is (= (get-in ['a ['b 'c]] [1 0]) 'b)))
  (testing "retrieves deeply nested element from a list with default value"
    (is (= (get-in ['a ['b 'c]] [0 3] "sorry") "sorry"))))

(deftest map-list-getting
  (testing "gets elements from nested map/list structures"
    (is (=
         (get-in {:foo [:bar {:baz "bang"}]} [:foo 1 :baz])
         "bang"))))

(deftest vectors
  (testing "conjugates an element to the end of a vector"
    (is (= (conj [1 2] 3) [1 2 3])))
  (testing "concatenates two vectors"
    (is (= (concat [1 2] [3 4]) [1 2 3 4]))))

(deftest lists
  (testing "conjugates an element to the beginning of a list"
    (is (= (conj '(1 2) 3) '(3 1 2))))) 


(deftest sets
  (testing "checks for set membership"
    (is (= (contains? #{:a :b} :b) true))
    (is (= (contains? #{:a :b} :c) false)))
  (testing "retrieves an element from a set"
    (is (= (:b #{:a :b}) :b))
    (is (= (get #{:a :b} :b) :b)))
  (testing "returns nil when an element cannot be retrieved from a set"
    (is (= (:c #{:a :b}) nil))
    (is (= (get #{:a :b} :c) nil)))
  (testing "returns default value when an element cannot be retrieved from a set"
    (is (= (:c #{:a :b} :d) :d))
    (is (= (get #{:a :b} :c :d) :d))))

(deftest conditionals
  (testing "branches execution based on equality check"
    (is (= (alert :mild) "WATCH OUT WE'RE MILDLY INCONVENIENCED")
    (is (= (alert :severe) "WATCH OUT WE'RE TOTALLY FUCKED!")))))

(deftest multi-arity
  (testing "overloads a function to handle variadic arities"
    (is (= (x-chop "my enemy" "jujitsu") "I jujitsu chop my enemy!"))
    (is (= (x-chop "my enemy") "I karate chop my enemy!"))))

