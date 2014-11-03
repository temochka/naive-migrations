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

