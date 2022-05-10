
CREATE TABLE `payments` (
  `id` int(10) NOT NULL,
  `customer_name` varchar(45) NOT NULL,
  `account_number` varchar(45) NOT NULL,
  `date` varchar(100) NOT NULL,
  `payment_type` varchar(45) NOT NULL,
  `total_price` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `payments` (`id`, `customer_name`, `account_number`, `date`, `payment_type`, `total_price`) VALUES
(1, 'Test User', '123456789', '2022-04-21', 'Credit-Card', '1250.00'),
(1, 'Test User 2', '2222456789', '2022-04-22', 'Cash', '5450.00'),;

ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `payments`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;