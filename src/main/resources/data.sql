CREATE ALIAS are_anagrams FOR "com.tradereport.util.AnagramChecker.isAnagram";

CREATE TABLE IF NOT EXISTS events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    buyer_party VARCHAR(255),
    seller_party VARCHAR(255),
    premium_amount DOUBLE,
    premium_currency VARCHAR(255)
);
