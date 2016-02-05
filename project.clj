(defproject version-parser "1.0.0"
  :description "parses tinyconfig.clj and writes out version information for build process"
  :url "http://22acacia.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.cli "0.3.3"]]
  :main version-parser.core
  :aot [version-parser.core])
