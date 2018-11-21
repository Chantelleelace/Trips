CREATE TABLE IF NOT EXISTS `FLIGHT` (
`flight_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
`person_id` INTEGER NOT NULL,
`arrival_airport` TEXT,
`departure_airport` TEXT,
`departure` INTEGER,
`arrival` INTEGER,
`flight_number` TEXT,
`confirmation_number` TEXT,
`passenger_name` TEXT,
`flight_rewards` TEXT)

CREATE  INDEX `index_Flight_person_id` ON `Flight` (`person_id`)

CREATE  INDEX `index_Flight_departure` ON `Flight` (`departure`)

CREATE  INDEX `index_Flight_arrival` ON `Flight` (`arrival`)

CREATE TABLE IF NOT EXISTS `Hotel` (
hotel_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
`person_id` INTEGER NOT NULL,
`hotel_name` TEXT,
`check_in` TEXT,
`check_out` TEXT,
`hotel_confirmation` TEXT,
`hotel_rewards` TEXT,
`room_type` TEXT,
`hotel_cost` TEXT,
`hotel_address` TEXT,
`hotel_phone` TEXT,
`name_on_reservation` TEXT)

CREATE  INDEX `index_Hotel_person_id` ON `Hotel` (`person_id`)

CREATE  INDEX `index_Hotel_check_in` ON `Hotel` (`check_in`)

CREATE  INDEX `index_Hotel_check_out` ON `Hotel` (`check_out`)

CREATE TABLE IF NOT EXISTS `Person` (`person_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)

CREATE TABLE IF NOT EXISTS `Transportation` (
`transportation_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
`person_id` INTEGER NOT NULL,
`company_name` TEXT,
`company_address` TEXT,
`company_phone` TEXT,
`pick_up` TEXT, `return` TEXT,
`rental_rewards` TEXT, `car_type` TEXT,
`rental_cost` TEXT,
`name_on_rental_reservation` TEXT,
`rental_confirmation` TEXT)

CREATE  INDEX `index_Transportation_person_id` ON `Transportation` (`person_id`)