# Naive Migrations 

JDBC-compatible Clojure library. Keep your database migrations in a single SQL file and automatically apply them when your app starts up. Suitable for small apps when you need to make things run. There is no automated way to roll back a migration. "I'm on a rollercoaster that only goes up, my friend."

## Installation

Via Clojars:

``` clojure
[naive-migrations "0.1.0"]
```

## Usage

Let’s assume, you have a following `migrations.sql` file:

``` sql
-- name: create-users!
-- Creates a table to store all application users.
CREATE TABLE users
(
  id SERIAL,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  encrypted_password VARCHAR(255) NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  INDEX users_by_email (email(64))
) ENGINE=InnoDB;

-- name: add-plan-to-users!
-- Adds a plan column to the users table.
ALTER TABLE users
ADD COLUMN plan VARCHAR(255) NOT NULL DEFAULT "free";

-- name: index-users-by-plan!
-- Creates an index on plan column of the users table.
CREATE INDEX users_by_plan
ON users (plan);
```

Make sure all migrations have names, descriptions are optional. The format is inherited
from a minimalistic [yesql](https://github.com/krisajenkins/yesql) library. Then, in your Clojure app you can apply missing migrations programmatically:

``` clojure
(require '[naive-migrations.core :refer [load-migrations apply-all]])

; JDBC data source, see http://clojure-doc.org/articles/ecosystem/java_jdbc/home.html#setting-up-a-data-source
(def db {})

(def migrations (load-migrations "sql/migrations.sql"))

(apply-all db migrations)
```

Having your migrations as a single SQL file allows for tricks like this:

``` bash
; Load your migrations as a MySQL dump
mysql -uroot dbname < migrations.sql
```

Don’t get too excited though. This library is called naive for a reason. It won’t help you if you ever run an undesired migration on your production server. But you’re smart and this won’t happen to you, right?

## License

Copyright © 2014 Artem Chistyakov

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
