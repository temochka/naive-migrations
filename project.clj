(defproject naive-migrations "0.1.0"
  :description "Dead simple MySQL migrations assumed to always succeed."
  :url "http://github.com/temochka/naive-migrations"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [yesql "0.4.0"]]
  :profiles {:dev {:dependencies [[mysql/mysql-connector-java "5.1.32"]]}})
