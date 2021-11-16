USE taxi;

SELECT * FROM cars WHERE max_passengers >= 3 AND type_id = 1 AND state_id = 1;
DELETE FROM receipts_has_cars WHERE receipts_id = ?;