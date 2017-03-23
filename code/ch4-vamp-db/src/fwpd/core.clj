(ns fwpd.core)

;; vamp-map :: {:name str :glitter-index int}

(def suspects-path "resources/suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int [str] (Integer. str))

(defn parse
  "str -> [[str]]
  parses a list of lists of strings from a csv string"
  [str]
  (map #(clojure.string/split % #",")(clojure.string/split str #"\n")))

(defn convert
  "[sym str] -> str|int"
  [k v]
  ((get {:glitter-index str->int
         :name identity} k) v))

(defn mapify
  "[[str]] -> vamp-map"
  [rows]
  (map (fn [row]
         (reduce (fn [acc [k v]] (assoc acc k (convert k v)))
                 {}
                 (map vector vamp-keys row)))
       rows))

(defn filter-glittery
  "vamp-map -> vamp-map"
  [min-glitter vamp-map]
  (filter #(>= (:glitter-index %) min-glitter) vamp-map))

;;; main func

(defn get-glittery-vamps
  "str -> vamp-map
  Given a filepath to a csv of vamp data, 
  Returns a lookup table of vamps filtered for those with glitter index >= 3"
  [path min-glitter]
  (filter-glittery min-glitter (mapify (parse (slurp path)))))
