(ns ch7-macros.core-test
  (:require [clojure.test :refer :all]
            [ch7-macros.core :refer :all]))

(deftest test-infix
  (testing "allows expression of a binary operation using infix notation"
    (is (= (infix (1 + 1)) 2))
    (is (= (macroexpand '(infix (1 + 1))) '(+ 1 1)))
    (is (= (infix (1 * 1)) 1))
    (is (= (macroexpand '(infix (1 * 1))) '(* 1 1)))))

(deftest test-infix'
  (testing "expresses infix using destructuring"
    (is (= (infix' (1 + 1)) 2))
    (is (= (macroexpand '(infix' (1 + 1))) '(+ 1 1)))
    (is (= (infix' (1 * 1)) 1))
    (is (= (macroexpand '(infix' (1 * 1))) '(* 1 1)))))

(deftest test-quotes
  "quotes are cool!"
  (is (= (quote (+ 1 1)) '(+ 1 1))))

(deftest test-code-critic
  "appends plaudit to good code, pan to bad code -- plain quotes"
  (is (= (code-critic (+ 1 1) (1 + 1))
         "Such great code!: (+ 1 1)\nThis code is teh SUCK!: (1 + 1)\n")))


(deftest test-code-critic'
  "appends plaudit to good code, pan to bad code -- syntax quotes"
  (is (= (code-critic' (+ 1 1) (1 + 1))
         "Such great code!: (+ 1 1)\nThis code is teh SUCK!: (1 + 1)\n")))

(deftest test-critique
  "given some code and a critique, returns an unevaluated literal to concatenate them"
  (is (= (critique "holy crap!: " '(and true false))
         '(clojure.core/str "holy crap!: " (quote (and true false)) "\n"))))


(deftest test-code-critic''
  "appends plaudit to good code, pan to bad code -- syntax quotes"
  (is (= (code-critic'' (+ 1 1) (1 + 1))
         "Such great code!: (+ 1 1)\nThis code is teh SUCK!: (1 + 1)\n")))

(deftest test-variable-capture
  "shadows `message` if implemented incorrectly!"
  (let [message " + original message"]
    (is (= (bad-assign (str "foo" "bar" message))
           "foobar + inner message"))
    (is (= (good-assign (str "foo" "bar" message))
           "foobar + original message"))
    (is (= (fancy-assign (str "foo" "bar" message))
           "foobar + original message + inner message"))))

