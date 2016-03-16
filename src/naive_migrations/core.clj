(ns naive-migrations.core
  (:require [yesql.core :refer (defquery defqueries)]
            [clojure.java.jdbc :as sql]))

(defquery init! "sql/init.sql")
(defquery record! "sql/record_migration.sql")
(defquery select-applied "sql/select_applied_migrations.sql")

(def load-migrations defqueries)

(defn get-name
  [q]
  "Gets a name of yesql's generated query"
  (-> q meta :name name))

(defn apply-one
  [db migration]
  "Applies a migration to the database"
  (sql/with-db-transaction [conn db]
    (migration {} {:connection conn})
    (record! {:name (get-name migration)} {:connection conn})))

(defn apply-all
  [db migrations]
  "Applies each migration from a provided list excluding already applied ones.
   Creates the `migrations` table if needed"
  (init! {} {:connection db})
  (let [applied-names (set (map :name (select-applied {} {:connection db})))
        pending (filter #(not (contains? applied-names (get-name %))) migrations)]
    (doseq [m pending]
      (apply-one db m))))

