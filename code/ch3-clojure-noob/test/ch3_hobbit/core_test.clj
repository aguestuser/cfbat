(ns ch3-hobbit.core-test
  (:use [clojure.repl])
  (:require [clojure.test :refer :all]
            [ch3-hobbit.core :refer :all]))

(deftest matching-part-test
  (testing "parses a lefthand part from a righthand part"
    (is (=
         (matching-part {:name "left-foo" :size 2})
         {:name "right-foo" :size 2}))))

(def asym-parts [{:name "left-foo" :size 1}
                 {:name "head" :size 1}
                 {:name "left-bar" :size 1}])
(def sym-parts [{:name "left-foo" :size 1}
                {:name "right-foo" :size 1}
                {:name "head" :size 1}
                {:name "right-bar" :size 1} ;; b/c ordering lost when converting to set
                {:name "left-bar" :size 1}])

(deftest symmetrize-test
  (testing "adds righthand parts to a collection of lefthand parts"
    (is (= (symmetrize asym-parts) sym-parts))))

(deftest og-symmetrize-test
  (testing "adds righthand parts to a collection of lefthand parts"
    (is (= (og-symmetrize asym-parts) sym-parts))))

(deftest reduce-symmetrize-test
  (testing "adds righthand parts to a collection of lefthand parts"
    (is (= (reduce-symmetrize asym-parts) sym-parts))))

(deftest clvr-reduce-symmetrize-test
  (testing "adds righthand parts to a collection of lefthand parts"
    (is (= (clvr-reduce-symmetrize asym-parts) sym-parts))))
