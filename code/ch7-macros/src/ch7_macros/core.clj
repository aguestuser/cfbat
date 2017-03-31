(ns ch7-macros.core
  (:gen-class))

(defmacro infix
  "do infix binary operations"
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))

(defmacro infix'
  "do infix binary operations"
  [[p1 op p2]]
  (list op p1 p2))

(defmacro code-critic
  "appends plaudit to good code, pan to bad code -- using plain quotes"
  [good bad]
  (list 'str
        (list 'str "Such great code!: " (list 'quote good) "\n")
        (list 'str "This code is teh SUCK!: " (list 'quote bad) "\n")))

(defmacro code-critic'
  "appends plaudit to good code, pan to bad code -- using syntax quotes"
  [good bad]
  `(str
        (str "Such great code!: " (quote ~good) "\n")
        (str "This code is teh SUCK!: " (quote ~bad) "\n")))

(defn critique
  [criticism code]
  `(str ~criticism (quote ~code) "\n"))

(defmacro code-critic''
  "appends plaudit to good code, pan to bad -- with HOF & helper"
  [good bad]
  `(str ~@(map #(apply critique %)
               [["Such great code!: " good]
                ["This code is teh SUCK!: " bad]])))

(defmacro bad-assign
   "will shadow any variable named `message`"
   [& statements]
   (concat (list 'let ['message " + inner message"])
           statements))

(defmacro good-assign
  "avoids shadowing by generating random `mesage###` sym"
  [& statements]
  (let [message (gensym 'message)]
    `(let [~message " + inner message"]
       ~@statements)))

(defmacro fancy-assign
  "avoids shadowing by generating random `mesage###` sym"
  [& statements]
  `(let [message# " + inner message"] ; implicitly uses `gensym 'message`
     (str ~@statements message#)))

(defn error-messages-for
  "Return a seq of error messages"
  [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))
