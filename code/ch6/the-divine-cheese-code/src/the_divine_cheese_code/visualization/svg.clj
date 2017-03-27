(ns the-divine-cheese-code.visualization.svg
  (:gen-class))

(defn latlng->point
  "Convert lat/lng map to comma-separated string"
  [latlng]
  (str (:lat latlng) "," (:lng latlng)))

(defn points
  "Convert a list of lat/lng maps to a string of comma-separated lat/lng pairs"
  [locations]
  (clojure.string/join " " (map latlng->point locations)))
