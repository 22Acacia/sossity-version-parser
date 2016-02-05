(ns version-parser.core
  (:gen-class)
  (:require 
    [clojure.java.io :as io]
    [clojure.tools.cli :refer [parse-opts]])
  (:import [java.io PushbackReader]))

;; read config into map
(defn conf [config] (with-open [r (clojure.java.io/reader config)] 
  (read (PushbackReader. r))))

(def cli-options
  [["-c" "--config CONFIG" "comma-separated paths to .clj config file for pipeline"]
   ["-o" "--output OUTPUT" "path to output terraform file"]])

(defn -main
  "Entry Point"
  [& args]
  
  (let [opts (:options (clojure.tools.cli/parse-opts args cli-options))]
    ; clear out the file before writing to it
    (def output (:output opts))
    (spit output "")
  
    (def pipelines (:pipelines conf (:config opts)))
  
    ; for each pipeline, create <key>/jarname jarname pairs and write to file
    (doseq [[k v]pipelines] 
      (spit output 
        (clojure.string/join " " 
          [(:pail v) (clojure.string/join "/" [(:key v) (:transform-jar v)]) (:transform-jar v) "\n"]) :append true))


    ; find the non-pipeline jars
    (def otherjars (:system-jar-info (:config conf(:config opts))))
  
    (doseq [[k v]otherjars]
      (spit output
        (clojure.string/join " "
          [(:pail v) (clojure.string/join "/" [(:key v) (:name v)]) (:name v) "\n"]) :append true))))