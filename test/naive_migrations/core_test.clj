(ns naive-migrations.core-test
  (:require [clojure.test :refer :all]
            [yesql.core :refer [defquery]]
            [naive-migrations.core :refer :all]
            [clojure.java.jdbc :as jdbc]))

(defquery create-test-db! "sql/create_test_db.sql")
(defquery drop-test-db! "sql/drop_test_db.sql")

(def management-conn
  {:subprotocol "mysql"
   :subname "//localhost:3306/"
   :user "root"
   :password ""})

(def query-conn
  {:subprotocol "mysql"
   :subname "//localhost:3306/naive_migrations_test"
   :user "root"
   :password ""})

(def migrations (load-migrations "sql/test_migrations.sql"))

(defn fixtures [f]
  (create-test-db! management-conn)
  (f)
  (drop-test-db! management-conn))

(use-fixtures :once fixtures)

(deftest apply-all-test
  (is 
    (try
      (apply-all query-conn migrations)
      true
      (catch Exception e (prn e) false))
    "All migrations are applied successfully."))
