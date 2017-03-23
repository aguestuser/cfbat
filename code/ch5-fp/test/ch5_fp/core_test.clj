(ns ch5-fp.core-test
  (:require [clojure.test :refer :all]
            [ch5-fp.core :refer :all]))

(deftest sum-test
  (testing "sums a list of ints"
    (is (= (sum [1 2 3 4]) 10))))

(deftest sum-alt-test
  (testing "sums a list of ints"
    (is (= (sum-alt [1 2 3 4]) 10))))

(deftest clean-test
  (testing "trims a string and uppercases LOL instances"
    (is (=
         (clean " i am so sassy lol!  ")
         "i am so sassy LOL!"))))

(deftest comp-test
  (testing "composes inc and multiplication operations"
    (is (= ((comp inc *) 2 3) 7)))
  (testing "composes attribute getter funcs"
    (is (= (beauty-of moo) 100)))
  (testing "uses anonymous functions to compose multi-arity functions in pipeline"
    (is (= (wonder-factor moo) 51))
    (is (= (wonder-factor-comp moo) 51))))

(deftest memo-test
  (testing "memoizes the results of expensive computations"
    (is (= (memo-expensive-computation "hmm")
           "hmm after some expensive computations!"))
    (is (= (memo-expensive-computation "hmm")  ;to illustrate the point, uncomment Thread/sleep
           "hmm after some expensive computations!"))))

