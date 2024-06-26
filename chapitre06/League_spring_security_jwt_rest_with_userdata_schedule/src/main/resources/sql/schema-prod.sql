USE league_prod;

CREATE TABLE IF NOT EXISTS saison (
    id INT AUTO_INCREMENT PRIMARY KEY,
    year INT NOT NULL UNIQUE,
    libelle VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS day (
  id INT AUTO_INCREMENT PRIMARY KEY,
  day INT NOT NULL,
  saisonId INT,
  FOREIGN KEY (saisonId) REFERENCES saison(id)
);

CREATE TABLE IF NOT EXISTS team (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS clash (
  id INT AUTO_INCREMENT PRIMARY KEY,
  dayId INT NOT NULL,
  homeTeamId INT NOT NULL,
  externTeamId INT NOT NULL,
  clashDate TIMESTAMP NOT NULL,
  FOREIGN KEY (dayId) REFERENCES day(id),
  FOREIGN KEY (homeTeamId) REFERENCES team(id),
  FOREIGN KEY (externTeamId) REFERENCES team(id)
);

CREATE TABLE IF NOT EXISTS saison_team (
  id INT AUTO_INCREMENT PRIMARY KEY,
  saisonId INT NOT NULL,
  teamId INT NOT NULL,
  FOREIGN KEY (saisonId) REFERENCES saison(id),
  FOREIGN KEY (teamId) REFERENCES team(id)
);