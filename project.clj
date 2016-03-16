(defproject naive-migrations "0.1.1"
  :description "Dead simple MySQL migrations assumed to always succeed."
  :url "http://github.com/temochka/naive-migrations"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [yesql "0.5.2"]]
  :profiles {:dev {:dependencies [[mysql/mysql-connector-java "5.1.38"]]}
             :1.6 {:dependencies  [[org.clojure/clojure "1.6.0"]]}
             :1.8 {:dependencies  [[org.clojure/clojure "1.8.0"]]}}
  :aliases {"test-all" ["with-profile" "+1.6:+1.8" "do"
                        ["clean"]
                        ["test"]]}
  :deploy-repositories [["releases" :clojars]])
