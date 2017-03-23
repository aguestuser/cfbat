(ns fwpd.core-test
  (:require [clojure.test :refer :all]
            [fwpd.core :refer :all]))

;;; fixtures

(def vamp-matrix
  [["Edward Cullen" "10"]
   ["Bella Swan" "0"]
   ["Charlie Swan" "0"]
   ["Jacob Black" "3"]
   ["Carlisle Cullen" "6"]])


(def vamp-map
  [{:name "Edward Cullen" :glitter-index 10}
   {:name "Bella Swan" :glitter-index 0}
   {:name "Charlie Swan" :glitter-index 0}
   {:name "Jacob Black" :glitter-index 3}
   {:name "Carlisle Cullen" :glitter-index 6}])

(def glittery-vamps
  [{:name "Edward Cullen" :glitter-index 10}
   {:name "Jacob Black" :glitter-index 3}
   {:name "Carlisle Cullen" :glitter-index 6}])

;;; unit tests

(deftest parse-test
  (testing "parses a csv string to a list of lists"
    (is (= (parse-matrix (slurp suspects-path)) vamp-matrix))))

(deftest convert-test
  (testing "converts a glitter-index value from str to int"
    (is (= (convert :glitter-index "1") 1)))
  (testing "does nothing to convert a name value"
    (is (= (convert :name "foo") "foo"))))

(deftest mapify-test
  (testing "converts a matrix of vamps into a lookup table of vamps"
    (is (= (mapify vamp-matrix) vamp-map))))

(deftest filter-test
  (testing "filters all vampires with glitter indexes above a specified value"
    (is (= (filter-glittery 3 vamp-map)
           glittery-vamps))))

;;; integration

(deftest get-glittery-vamps-test
  (testing "parses a csv of vamp data into a map of vamps filtered by glitteriness"
    (is (= (get-glittery-vamps suspects-path 3)
           glittery-vamps))))
