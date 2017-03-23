(defproject clojure-noob "0.1.0-SNAPSHOT"
  :description "just a n00b, learning how to clojure"
  :url "http://www.braveclojure.com/getting-started/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot clojure-noob.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
