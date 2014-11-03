-- Create a table to store migrations info
CREATE TABLE IF NOT EXISTS migrations
(
  name VARCHAR(255) PRIMARY KEY UNIQUE NOT NULL,
  applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;