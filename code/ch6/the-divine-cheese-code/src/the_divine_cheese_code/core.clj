(ns the-divine-cheese-code.core
    ;(:use [clojure.repl])
  ;; (:use [the-divine-cheese-code.visualization.svg :as svg :only [points]])
  (:require [the-divine-cheese-code.visualization.svg :as svg :only [points]])
  (:gen-class))

;; with require/refer
;; (require '[the-divine-cheese-code.visualization.svg :as svg])
;; (refer 'the-divine-cheese-code.visualization.svg)

;; with use (shorter)
;; (use '[the-divine-cheese-code.visualization.svg :as svg])


(def heists [{:location "Cologne, Germany"
              :cheese-name "Archbishop Hildebold's Cheese Pretzel"
              :lat 50.95
              :lng 6.97}
             {:location "Zurich, Switzerland"
              :cheese-name "The Standard Emmental"
              :lat 47.37
              :lng 8.55}
             {:location "Marseille, France"
              :cheese-name "Le Fromage de Cosquer"
              :lat 43.30
              :lng 5.37}
             {:location "Zurich, Switzerland"
              :cheese-name "The Lesser Emmental"
              :lat 47.37
              :lng 8.55}
             {:location "Vatican City"
              :cheese-name "The Cheese of Turin"
              :lat 41.90
              :lng 12.45}])

(defn -main
  [& args]
  (println (svg/points heists)))
