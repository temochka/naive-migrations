-- Saves a migration’s name, so it’s not repeated again
INSERT INTO migrations (name) VALUES (:name);